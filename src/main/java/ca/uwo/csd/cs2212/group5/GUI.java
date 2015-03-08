package ca.uwo.csd.cs2212.group5;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class GUI {

	private JFrame frmWeatherApp;
	private JTextField cityName;
	private JTextField countryCode;
	private URLReader ur;
	private String displayString;
	private JTextArea textArea;

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

		cityName = new JTextField("city name");
		cityName.setBounds(10, 10, 202, 28);
		frmWeatherApp.getContentPane().add(cityName);
		cityName.setColumns(10);

		countryCode = new JTextField("country code");
		countryCode.setBounds(217, 10, 111, 28);
		frmWeatherApp.getContentPane().add(countryCode);
		countryCode.setColumns(10);

		JButton btn = new JButton("Get");
		btn.setBounds(340, 5, 90, 38);
		frmWeatherApp.getContentPane().add(btn);

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 86, 410, 329);
		frmWeatherApp.getContentPane().add(textArea);

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					ur = new URLReader(cityName.getText(), countryCode.getText());
					String displayString = ur.getCurrent();
					textArea.setText(displayString);

				} catch (WeatherException e) {
					displayString = e.getMessage();
				}
				//
			}

		});
	}
}
