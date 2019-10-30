package game;
import javax.swing.ImageIcon;

public class OpenIcon {
	
	public ImageIcon carregarImagemIcon(String arquivo) {
		return new ImageIcon(getClass().getResource(arquivo));
	}
}
