import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Třída pro načtení obrázků a vykreslení plátna
 * @author Xuan Toan Dinh
 * @version 1.0
 */
public class DrawingPanel extends JPanel {

	/**
	 * pole jednotlivých obrázků
	 */
	private ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();

	/**
	 * současný obrázek
	 */
	private BufferedImage currentImage;

	/**
	 * počet stavů
	 */
	private int numStages = 12;

	/**
	 * Metoda volá metody setPanelSize() a loadImages()
	 */
	public DrawingPanel() {
		setPanelSize();
		loadImages();
	}

	/**
	 * Metoda načte obrázky do pole
	 * Pokud se nepodaří načíst obrázek, tak se vypíše chybová hláška a použije se černý obdélník
	 */
	private void loadImages() {
		BufferedImage image;

		for (int i = 0; i < numStages; i++) {
			String imageFileName = "images/" + i + ".png";

			try {
				image = ImageIO.read(new File(imageFileName));
			} catch (IOException e) {
				System.out.println("Failed to load: " + imageFileName);
				image = new BufferedImage(1280, 960, BufferedImage.TYPE_INT_RGB);
			}

			imageList.add(image);

			currentImage = imageList.get(0);
		}
	}

	/**
	 * Metoda se stará o vykreslení obrázku
	 * @param g grafický kontext
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(currentImage, 0, 0, null);
	}

	/**
	 * Metoda nastaví velikost plátna
	 */
	private void setPanelSize() {
		Image img = new ImageIcon("images/1.png").getImage();
		Dimension d = new Dimension();
		d.width = img.getWidth(null);
		d.height = img.getHeight(null);
		setPreferredSize(d);
	}

	/**
	 * Metoda překreslí plátno
	 * @param state číslo dalšího brázku/stavu
	 */
	public void changeState(int state) {
		currentImage = imageList.get(state);
		this.repaint();
	}
}
