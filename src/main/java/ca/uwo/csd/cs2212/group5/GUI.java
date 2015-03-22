package ca.uwo.csd.cs2212.group5;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.Font;

/**
 * Implements the GUI. Also houses the main method for the program.
 * 
 * @author Team 5
 *
 */
public class GUI {

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
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(
				new Font("SansSerif", Font.PLAIN, 13));
		frame.setTitle("Weather App - Team 5 CS2212");
		frame.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 169, 410, 246);
		frame.getContentPane().add(textArea);
		
		JButton unitButton = new JButton("Change Units");
		
		unitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		unitButton.setBounds(12, 131, 123, 25);
		frame.getContentPane().add(unitButton);
		
//		ButtonGroup group = new ButtonGroup();
//		group.add(rdFarh);
//		group.add(rdCels);
		
		//end of radio

		getButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					ur = new URLReader(cityName.getText(), countryCode
							.getText());
					displayString = ur.getCurrent();
					textArea.setText(displayString);

				} catch (WeatherException e) {
					displayString = e.getMessage();
				}
				//
			}

		});
			
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
