package ca.uwo.csd.cs2212.group5;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Color;

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

	private static final int width = 1000;
	private static final int height = 700;
	private static JTextPane[] sterms = new JTextPane[8];
	private static JTextPane[] lterms = new JTextPane[5];

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {

		frame = new JFrame();
		// frame.getContentPane().setBackground(new Color(1, 61, 134));
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// for (int i = 0; i < 8; i++){
		//
		//
		// sterms[i] = new JTextPane();
		// sterms[i].setContentType("text/html");
		// sterms[i].setBounds(width-200, (i*70)+20, 150, 70);
		//
		// sterms[i].setText(city.st[i].toString());
		//
		// // sterms[i].setBackground((Color.GRAY));
		// frame.getContentPane().add(sterms[i]);
		// }

		// //TABS
		// JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPane.setBounds(12, 137, 433, 323);
		// tabbedPane.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		// frame.getContentPane().add(tabbedPane);
		//
		// // Current Weather Tab
		// cPane = new JTextPane();
		// cPane.setBackground(new Color(210, 229, 243));
		// cPane.setText("Current");
		// cPane.setContentType("text/html");
		// tabbedPane.addTab("Current", null, cPane, null);
		//
		// // Short Term Weather Tab
		// sPane = new JTextPane();
		// sPane.setText("Short");
		// sPane.setContentType("text/html");
		// tabbedPane.addTab("Short Term", null, sPane, null);
		//
		// // Long Term Weather Tab
		// lPane = new JTextPane();
		// lPane.setText("Long");
		// lPane.setContentType("text/html");
		// tabbedPane.addTab("Long Term", null, lPane, null);
		//
		// textPane_1 = new JTextPane();
		// tabbedPane.addTab("New tab", null, textPane_1, null);
		// textPane_1.setText("Current");
		// textPane_1.setContentType("text/html");
		// textPane_1.setBackground(new Color(210, 229, 243));

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

		JTextPane currentPane = new JTextPane();
		currentPane.setBounds(12, 59, 575, 298);
		frame.getContentPane().add(currentPane);

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

	}

	private static void displayShort() {
		for (int i = 0; i < 8; i++) {

			sterms[i] = new JTextPane();
			sterms[i].setContentType("text/html");
			sterms[i].setBounds(width - 200, (i * 100) + 20, 150, 100);

			sterms[i].setText(city.st[i].toString(isCelsius));

			// sterms[i].setBackground((Color.GRAY));
			frame.getContentPane().add(sterms[i]);
		}

	}
	
	private static void displayLong() {
		for (int i = 0; i < 5; i++){
			lterms[i] = new JTextPane();
			lterms[i].setContentType("text/html");
			lterms[i].setBounds(50+(i*100), height-120, 150, 100);
			
			lterms[i].setText(city.lt[i].toString(isCelsius));
			
			lterms[i].setBackground((Color.GRAY));
			frame.getContentPane().add(lterms[i]);
		}
	}

}