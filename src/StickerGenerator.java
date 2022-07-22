import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static java.awt.Transparency.TRANSLUCENT;

public class StickerGenerator {

    public void cria(InputStream inputStream, String nomeArquivo, String frase, Double nota, String data) throws Exception {

        // Leitura da imagem
//        InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
//        InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, TRANSLUCENT);

        // Copiar a imagem original na nova imagem
        Graphics2D graphics2D = (Graphics2D) novaImagem.getGraphics();
        graphics2D.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int) largura / 15));
        graphics2D.setColor(Color.YELLOW);

        // Escrever uma frase centralizada na nova imagem
        int tamanhoFrase = graphics2D.getFontMetrics().stringWidth(frase);
        int localFrase = (novaImagem.getWidth() - tamanhoFrase) / 2;
        graphics2D.drawString(frase, localFrase, novaAltura - 80);

        // Adicionar nota/Data do filme na nova imagem
        int localNota = novaImagem.getHeight() / 20;

        if (nota == 0) {
            graphics2D.drawString("Data: " + data, (novaImagem.getWidth() - graphics2D.getFontMetrics().stringWidth(data) * 3) + 40, 70);
        }
        if (nota >= 9.0) {
            graphics2D.drawString("Nota: " + nota, 10, localNota);
            graphics2D.drawImage(ImageIO.read(new File("selos/aprovado.png")), novaImagem.getWidth() - 250 / 2, 0, null);
        }
        if (nota < 9 && nota > 0) {
            graphics2D.drawString("Nota: " + nota, 10, localNota);
            graphics2D.drawImage(ImageIO.read(new File("selos/negado.png")), novaImagem.getWidth() - 250 / 2, 0, null);
        }

        // Nova resolução da imagem(512x512)
        BufferedImage Sticker = resize(novaImagem, 512, 512);

        // Escrever a imagem nova em um arquivo
        ImageIO.write(Sticker, "png", new File("saida/" + nomeArquivo));

    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
        return Thumbnails.of(img).forceSize(newW, newH).asBufferedImage();
    }
}