import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This class is used to contain the image and the pixel stats of the ground/terrain. It will also make explosions on the ground
 * 
 * @author Don, Trung Hieu (Austin)
 */
public class Ground {
	
/**
 * image - The BufferedImage of the ground
 * raster - the raster used to indicate whether the pixel is transparent, white or solid and set the pixel to a certain color
 * bitmap - an int 2d array used to contain all the value of the pixels of the image showing whether it is solid or tranparent (0 means solid,1 means transparent)
 * pixelSize - the int telling whether the pixel is solid or tranparent by looking at the length to see if the color is RGB or RGBA array
 */
	private BufferedImage image;
	private WritableRaster raster;
	private int[][] bitmap;
	private int pixelSize;

	/**
	 * The main constructor used to initialize all variables
	 */
	public Ground() {
		
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("terrain1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.raster = image.getRaster();
		setupBitmap();

	}

	/**
	 * The getter method of the image
	 * 
	 * @return image - the BufferedImage of the ground/terrain
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * The method used to make explosion by using raster to set a certain number of in-ranged-pixels to be transparent.
	 * 
	 * @param centerX - The int passed through from the main class showing the x position of the point where the bullet first meets the ground
	 * @param centerY - The int passed through from the main class showing the y position of the point where the bullet first meets the ground
	 * @param explosionRadius - The int determine the explosion radius depending on the stats of the weapon
	 */
	public void explode(int centerX, int centerY, int explosionRadius) {
		// initialize the distance
		double distance = 0;
		
		// create a rectangle surrounds the explosion 
		Rectangle r = new Rectangle(centerX - explosionRadius, centerY - explosionRadius, 2 * explosionRadius,
				2 * explosionRadius);
		
		// use a nested for loop to check for all the pixels of the image. It
		// must be in the rectangle surrounds the explosion and the distance
		// from the pixel to the point where the bullet hits the ground. The
		// distance is calculated using Pythagorean
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				distance = Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY));
				if (r.contains(x, y) && distance < explosionRadius) {
					raster.setPixel(x, y, new int[] { 255, 255, 255, 0 });
				}
			}
		}

	}

	/**
	 * This method is used to set up the bitmap using 2D Array. It will check if
	 * pixel colorvalue is white or transparent 0 = pixel is solid, 1 pixel is
	 * transparent to objects, assuming length of 3 is RBG and length 4 is RBGA
	 * which contain the transparent alpha value
	 */
	public void setupBitmap() {
		bitmap = new int[image.getWidth()][image.getHeight()];
		
		// use nested for loop to go through all pixels
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int[] pixel = null;
				
				pixel = raster.getPixel(x, y, pixel);

				// if it is rgb and white
				if (pixel.length == 3) {
					pixelSize = 3;
					//set the pixel to be 1
					if (pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255)
						bitmap[x][y] = 1;
				}
				// if it is tgba and transparent
				if (pixel.length == 4) {
					pixelSize = 4;
					// set the pixel to be 1 as well
					if (pixel[3] == 0) {
						bitmap[x][y] = 1;
					}
				}
			}
		}
	}

	/**
	 * The method that is used to draw the ground
	 * 
	 * @param g2 - The graphics2D passed from the main class
	 */
	public void draw(Graphics2D g2) {
		g2.drawImage(image, 0, 0, null);
	}

	/**
	 * The getter method of the 2d int array bitmap
	 * 
	 * @return bitmap - the 2d int array that contains all the value of the pixels of the image showing whether it is solid or transparent
	 */
	public int[][] getBitmap() {
		return bitmap;
	}
}
