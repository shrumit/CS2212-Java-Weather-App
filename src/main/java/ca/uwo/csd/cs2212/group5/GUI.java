package ca.uwo.csd.cs2212.group5;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

/**
 * Implements the GUI. Also houses the main method for the program.
 * 
 * @author Team 5
 *
 */
public class GUI {

	private JFrame frmWeatherApp;
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
					GUI window = new GUI();
					window.frmWeatherApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWeatherApp = new JFrame();
		frmWeatherApp.getContentPane().setFont(
				new Font("SansSerif", Font.PLAIN, 13));
		frmWeatherApp.setTitle("Weather App - Team 5 CS2212");
		frmWeatherApp.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmWeatherApp.getContentPane().setBackground(Color.GRAY);
		frmWeatherApp.setBounds(100, 100, 450, 475);
		frmWeatherApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWeatherApp.getContentPane().setLayout(null);

		// text field for city name
		cityName = new JTextField("city name");
		cityName.setBounds(12, 100, 202, 28);
		frmWeatherApp.getContentPane().add(cityName);
		cityName.setColumns(10);

		//text field for country code
		countryCode = new JTextField("country code");
		countryCode.setBounds(226, 100, 111, 28);
		frmWeatherApp.getContentPane().add(countryCode);
		countryCode.setColumns(10);

		//button to fetch data from API
		JButton btn = new JButton("Get");
		btn.setBounds(350, 95, 70, 38);
		frmWeatherApp.getContentPane().add(btn);

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 169, 410, 246);
		frmWeatherApp.getContentPane().add(textArea);
		
		JToggleButton tglbtnFarhenheit = new JToggleButton("Farhenheit");
		tglbtnFarhenheit.setBounds(12, 13, 93, 25);
		frmWeatherApp.getContentPane().add(tglbtnFarhenheit);
		
		JToggleButton tglbtnCelsius = new JToggleButton("Celsius");
		tglbtnCelsius.setBounds(104, 13, 93, 25);
		frmWeatherApp.getContentPane().add(tglbtnCelsius);

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					ur = new URLReader(cityName.getText(), countryCode.getText());
					displayString = ur.getCurrent();
					textArea.setText(displayString);

				} catch (WeatherException e) {
					displayString = e.getMessage();
				}
				//
			}

		});
	}
}
