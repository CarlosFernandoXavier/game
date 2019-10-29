
import javax.swing.ImageIcon;

public class OpenIconAdapter extends OpenIcon implements ImageTarget {

	@Override
	public ImageIcon carregarIcon(String nomeDoArquivo) {
		return carregarImagemIcon(nomeDoArquivo);
	}

	@Override
	public void desenharImagem(int posX, int posY, int largura, int altura) {
		desenharImagemIcon(posX, posY);
	}

}
