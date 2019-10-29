
import javax.swing.ImageIcon;

public interface ImageTarget {
	public ImageIcon carregarIcon(String nomeDoArquivo);

	public void desenharImagem(int posX, int posY, int largura, int altura);
}