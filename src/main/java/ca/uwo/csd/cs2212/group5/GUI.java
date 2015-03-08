package ca.uwo.csd.cs2212.group5;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class GUI {

	private JFrame frame;
	private JTextField cname;
	private JTextField ccode;
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
		frame.setBounds(100, 100, 450, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		cname = new JTextField("city name");
		cname.setBounds(10, 10, 202, 28);
		frame.getContentPane().add(cname);
		cname.setColumns(10);

		ccode = new JTextField("country code");
		ccode.setBounds(217, 10, 111, 28);
		frame.getContentPane().add(ccode);
		ccode.setColumns(10);

		JButton btn = new JButton("Get");
		btn.setBounds(340, 5, 90, 38);
		frame.getContentPane().add(btn);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 86, 410, 329);
		frame.getContentPane().add(textArea);

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try{
					ur = new URLReader(cname.getText(), ccode.getText());
					String displayString = ur.getCurrent(); 
					
				}
				catch (WeatherException e){
					displayString = e.getMessage();
				}
				
			}
			
			
			
		});
	}
}
