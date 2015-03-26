package ca.uwo.csd.cs2212.group5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Tabs {

	private static JFrame frame;
	private static JTextField cityName;
	private static JTextField countryCode;
	private static URLReader ur;
	private static JTextArea textArea;

	private static JTextPane cPane;
	private static JTextPane sPane;
	private static JTextPane lPane;
	private static JTextPane textPane_1;

	private static Location city;
	private static boolean isCelsius = true;

	// private String displayString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabs window = new Tabs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tabs() {
		initialize();
	}

	private static final int width = 700;
	private static final int height = 1000;
	private static JTextPane[] sterms = new JTextPane[8];
	private static JTextPane[] lterms = new JTextPane[5];

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {

		frame = new JFrame();
		// frame.getContentPane().setBackground(new Color(1, 61, 134));
		frame.setBounds(100, 100, 936, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// This is the menu bar item "Program"
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);

		// Implements Program>Exit on the menu bar
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnProgram.add(mntmExit);

		// text field for city name
		cityName = new JTextField("city name");
		cityName.setBounds(12, 13, 202, 28);
		frame.getContentPane().add(cityName);
		cityName.setColumns(10);

		// text field for country code
		countryCode = new JTextField("country code");
		countryCode.setBounds(227, 13, 111, 28);
		frame.getContentPane().add(countryCode);
		countryCode.setColumns(10);

		// button to fetch data from API
		JButton getButton = new JButton("Get");
		getButton.setBounds(351, 8, 70, 38);
		frame.getContentPane().add(getButton);

		JPanel curPanel = new JPanel();
		curPanel.setBounds(12, 85, 474, 337);
		frame.getContentPane().add(curPanel);
		GridBagLayout gbl_curPanel = new GridBagLayout();

		gbl_curPanel.columnWidths = new int[] { 0, 0 };
		gbl_curPanel.rowHeights = new int[] { 0, 0 };
		gbl_curPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_curPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		curPanel.setLayout(gbl_curPanel);

		// What happens once Get button is pressed
		getButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					// ur = new URLReader(cityName.getText(), countryCode
					// .getText());
					//
					// refreshData();
					city = new Location(88319);
					refreshData();

					// } catch (WeatherException e) {
				} catch (Exception e) {

				}
				//
			}

		});

	}

	/**
	 * Gets data strings from URLReader object and puts it on displayString.
	 * <Note that .setText may not be the best way to implement this>
	 * 
	 */
	private static void refreshData() {

		// Displays Current Weather
		// String displayString = ur.getCurrent();
		// cPane.setText(displayString);
		//
		// Displays Short Term Weather <unfinished>
		displayShort();
		displayLong();
		// displayCurrent();

	}

	private static void displayShort() {

		JPanel stermPanel = new JPanel();
		stermPanel.setBounds(12, 626, 894, -181);
		stermPanel.setLayout(new GridLayout(1, 8, 0, 0));

		for (int i = 0; i < 8; i++) {

			sterms[i] = new JTextPane();
			sterms[i].setContentType("text/html");
			stermPanel.add(sterms[i]);
			sterms[i].setText(city.st[i].toString(isCelsius));

			// sterms[i].setBackground((Color.GRAY));
			System.out.println("finished");
		}
		frame.getContentPane().add(stermPanel);

	}

	private static void displayLong() {
		for (int i = 0; i < 5; i++) {
			lterms[i] = new JTextPane();
			lterms[i].setContentType("text/html");
			lterms[i].setBounds(50 + (i * 100), height - 120, 150, 100);

			lterms[i].setText(city.lt[i].toString(isCelsius));

			lterms[i].setBackground((Color.GRAY));
			frame.getContentPane().add(lterms[i]);
		}
	}
}