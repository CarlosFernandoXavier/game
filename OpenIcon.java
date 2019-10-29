
import javax.swing.ImageIcon;

public class OpenIcon {
	
	public ImageIcon carregarImagemIcon(String arquivo) {
		return new ImageIcon(getClass().getResource(arquivo));
	}

	public void desenharImagemIcon(int posicaoX, int posicaoY) {
		System.out.println("OpenGL Image desenhada");
	}

}
