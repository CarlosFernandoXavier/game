package game;
import javax.swing.ImageIcon;

public class OpenIconAdapter extends OpenIcon implements ImageIconTarget {

	@Override
	public ImageIcon carregarIcon(String nomeDoArquivo) {
		return carregarImagemIcon(nomeDoArquivo);
	}

}
