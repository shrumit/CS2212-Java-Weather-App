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

public class Tabs {

	private static JFrame frame;
	private static JTextField cityName;
	private static JTextField countryCode;
	private static URLReader ur;
	private static JTextArea textArea;
	
	private static JTextPane cPane;
	private static JTextPane sPane;
	private static JTextPane lPane;

//	private String displayString;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//TABS
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 137, 433, 323);
		frame.getContentPane().add(tabbedPane);

		// Current Weather Tab
		cPane = new JTextPane();
		cPane.setText("Current");
		tabbedPane.addTab("Current", null, cPane, null);

		// Short Term Weather Tab
		sPane = new JTextPane();
		sPane.setText("Short");
		tabbedPane.addTab("Short Term", null, sPane, null);

		// Long Term Weather Tab
		lPane = new JTextPane();
		lPane.setText("Long");
		tabbedPane.addTab("Long Term", null, lPane, null);

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
		cityName.setBounds(12, 100, 202, 28);
		frame.getContentPane().add(cityName);
		cityName.setColumns(10);

		// text field for country code
		countryCode = new JTextField("country code");
		countryCode.setBounds(226, 100, 111, 28);
		frame.getContentPane().add(countryCode);
		countryCode.setColumns(10);

		// button to fetch data from API
		JButton getButton = new JButton("Get");
		getButton.setBounds(350, 95, 70, 38);
		frame.getContentPane().add(getButton);

		// What happens once Get button is pressed
		getButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					ur = new URLReader(cityName.getText(), countryCode
							.getText());

					refreshData();

				} catch (WeatherException e) {
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
		String displayString = ur.getCurrent();
		cPane.setText(displayString);
		
		// Displays Short Term Weather <unfinished>
		

	}
}