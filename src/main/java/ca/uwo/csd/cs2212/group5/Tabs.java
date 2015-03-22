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

public class Tabs {

	private JFrame frame;
	private JTextField cityName;
	private JTextField countryCode;
	private URLReader ur;
	private JTextArea textArea;
	private String displayString;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 137, 415, 308);
		frame.getContentPane().add(tabbedPane);

		JTextPane cPane = new JTextPane();
		cPane.setText("Current");
		tabbedPane.addTab("Current", null, cPane, null);

		JTextPane sPane = new JTextPane();
		sPane.setText("Short");
		tabbedPane.addTab("Short Term", null, sPane, null);

		JTextPane lPane = new JTextPane();
		lPane.setText("Long");
		tabbedPane.addTab("Long Term", null, lPane, null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);

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

		getButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					ur = new URLReader(cityName.getText(), countryCode
							.getText());
					displayString = ur.getCurrent();
					cPane.setText(displayString);

				} catch (WeatherException e) {
					displayString = e.getMessage();
				}
				//
			}

		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnProgram.add(mntmExit);
	}
}
