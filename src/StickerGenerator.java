

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.awt.Transparency.TRANSLUCENT;
import static java.lang.Integer.parseInt;

public class StickerGenerator {

    public void cria(InputStream inputStream, String nomeArquivo, Double nota, String frase) throws Exception {

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
        int localFrase = (novaImagem.getWidth() - tamanhoFrase - 100) / 2;
        if (nota >= 8.5) {
            frase = "Excelente!";
        } else if (nota >= 7.5) {
            frase = "Bom!";
        } else if (nota >= 6.5) {
            frase = "Regular!";
        } else {
            frase = "Ruim!";
        }
        graphics2D.drawString(frase, localFrase, novaAltura - 80);

        // Escrever a nota no topo na nova imagem e o selo de aprovado / negado
        int localNota = novaImagem.getHeight() / 20;
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawString(nota + " / 10", 10, localNota);
        if (nota >= 8.5) {
            graphics2D.drawImage(ImageIO.read(new File("selos/aprovado.png")), novaImagem.getWidth() - 250 / 2, 0, null);
        } else if (nota <= 8.4) {
            graphics2D.drawImage(ImageIO.read(new File("selos/negado.png")), -10, 0, null);
        }

        // Inserindo selo de acordo com a nota


        // Nova resolução da imagem(512x512)
        BufferedImage Sticker = resize(novaImagem, 512, 512);

        // Escrever a imagem nova em um arquivo
        ImageIO.write(Sticker, "png", new File("saida/" + nomeArquivo));

    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
        return Thumbnails.of(img).forceSize(newW, newH).asBufferedImage();
    }
}
