package ca.uwo.csd.cs2212.group5;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class SearchGUI {

	private static JFrame frame;
	private static JButton searchButton;
	private static JTextField searchField;
	private static JList list;
	private static JButton select;
	private static JButton mars;
	private static DefaultListModel model;
	private static Search search;
	private static Iterator iterator;
	private static int currentIndex = -1;
	private static final Color backgroundCol = new Color(237, 243, 248);


	/**
	 * driver for the search window
	 * @param args
	 */
	public static void main(String[] args) {

		frame = new JFrame("Select Location");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 300, 0, 0);

		renderGUI();
		readInput();
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * reads the search query
	 */
	public static void readInput() {
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				model.removeAllElements();
				select.setEnabled(false);

				String entry = searchField.getText();

				if (entry.length() < 3) {
					model.addElement("Query must be longer than two characters.");
					readInput();
				} else if (!isAlpha(entry)) {
					model.addElement("Invalid character entered.");
					readInput();
				}

				else {
					search = new Search(entry);
					iterator = search.getResults();
					while (iterator.hasNext()) {
						model.addElement(iterator.next());
					}
					if (!((String) model.getElementAt(0))
							.equalsIgnoreCase("No results found.")) {
						select.setEnabled(true);
						selection();
					}
				}
			}

		});

		mars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				WeatherGUI.startWeather(-1, " ");
			}
		});
	}

	/**
	 * determines which search result you select
	 */
	private static void selection() {
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent le) {
				currentIndex = list.getSelectedIndex();
			}
		});

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (currentIndex >= 0) {
					int cityId = search.getId(currentIndex);
					String name = search.getName(currentIndex);
					frame.dispose();
					WeatherGUI.startWeather(cityId, name);
				}
			}
		});

	}

	/**
	 * verifies if a valid city name is given
	 * @param arg
	 * @return
	 */
	private static boolean isAlpha(String arg) {
		return arg.matches("[a-zA-Z,. ]+");
	}

	/**
	 * sets up the frame for the GUI
	 */
	private static void renderGUI() {

		setUIFont(new javax.swing.plaf.FontUIResource(new Font("Arial",
				Font.PLAIN, 20)));

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.setBackground(backgroundCol);
		frame.add(panel);

		JLabel city = new JLabel("City:");

		searchField = new JTextField();

		searchButton = new JButton("Search");

		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		select = new JButton("Choose");
		select.setEnabled(false);

		mars = new JButton("Mars");

		// panel.add(city);
		panel.add(searchField, "growx, pushx");
		panel.add(searchButton, "wrap, skip");
		panel.add(new JScrollPane(list), "growx, pushx, growy, pushy");
		panel.add(select, "skip, wrap");
		panel.add(mars);

	}
	
	/**
	 * sets the global font
	 * @param f
	 */
	private static void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, f);
			}
		}
	}
}
