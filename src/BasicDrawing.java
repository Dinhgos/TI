import javax.swing.*;
import java.awt.*;

/**
 * Třída pro vytvoření okna a zpracování stavů
 * @author Xuan Toan Dinh
 * @version 1.0
 */
public class BasicDrawing {

	/**
	 * čas mezi stavy
	 */
	private static int time;

	/**
	 * plátno
	 */
	private static DrawingPanel panel;

	/**
	 * ukládá informace o stisknutí tlačítka
	 */
	private static boolean isPressedA, isPressedB, start;

	/**
	 * Vstupní bod programu
	 * @param args argumenty pro spuštění
	 * @throws InterruptedException chyba funkce Thread.sleep()
	 */
	public static void main(String[] args) throws InterruptedException {
		init();
		JFrame win = new JFrame();
		win.setTitle("Krizovatka");

		makeGui(win);
		win.pack();

		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLocationRelativeTo(null);
		win.setVisible(true);

		process();
	}

	/**
	 * Metoda nastaví výchozí hodnoty
	 */
	private static void init() {
		start = false;
		time = 4000;
		isPressedA = false;
		isPressedB = false;
	}

	/**
	 * Metoda přidá oknu ovládací prvky
	 * @param win okno
	 */
	private static void makeGui(JFrame win){
		panel = new DrawingPanel();
		win.setLayout(new BorderLayout());
		win.add(panel, BorderLayout.CENTER);

		JButton btnA = new JButton("A");
		JButton btnB = new JButton("B");
		JButton bttnExit = new JButton("Exit");
		JButton start = new JButton("Start");


		JPanel butttons = new JPanel();
		butttons.add(btnA);
		butttons.add(btnB);
		butttons.add(start);
		butttons.add(bttnExit);

		win.add(butttons, BorderLayout.SOUTH);
		bttnExit.addActionListener(e -> win.dispose());
		start.addActionListener(e -> startProg());
		btnA.addActionListener(e -> pressA());
		btnB.addActionListener(e -> pressB());
	}

	/**
	 * Změní hondotu isPressedB na true
	 */
	private static void pressB() {
		isPressedB = true;
	}

	/**
	 * Změní hondotu isPressedA na true
	 */
	private static void pressA() {
		isPressedA = true;
	}

	/**
	 * Změní hondotu start na true
	 */
	private static void startProg() {
		start = true;
	}

	/**
	 * Metoda se stará o přechod mezi stavy
	 * @throws InterruptedException chyba funkce Thread.sleep()
	 */
	private static void process() throws InterruptedException {
		while (!start) {
			Thread.sleep(1000);
		}

		for (int i = 0; i < 3; i++) {
			panel.changeState(i);
			Thread.sleep(time);
		}

		while (true) {
			for (int i = 3; i < 11; i++) {
				panel.changeState(i);

				if (isPressedA || isPressedB) {
					Thread.sleep(time/2);
				} else {
					Thread.sleep(time);
				}

				if (isPressedA) {
					if (i == 4) {
						isPressedA = false;
					}
				}

				if (isPressedB) {
					if (i == 9) {
						isPressedB = false;
					}
				}
			}
		}
	}
}