package ca.uwo.csd.cs2212.group5;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class SearchGUI {

	private static JFrame mainFrame;
	private static JFrame sf;
	private static JButton srch;
	private static JTextField srchf;
	private static JList list;
	private static JButton select;
	private static DefaultListModel model;
	private static Search sr;
	private static Iterator ir;
	private static int currentIndex = -1;

	public static void main(String[] args) {

		sf = new JFrame("Select Location");
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sf.setBounds(500, 300, 500, 200);

		searchRender();
		readInput();
		// sf.pack();
		sf.setVisible(true);

	}

	public static void readInput() {
		srch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				model.removeAllElements();
				select.setEnabled(false);
				
				String entry = srchf.getText();

				if (entry.length() < 3) {
					model.addElement("Query is too short! Search again please.");
					readInput();
				} else if (!isAlpha(entry)) {
					model.addElement("Invalid character entered! Search again please.");
					readInput();
				}

				else {
					sr = new Search(entry);
					ir = sr.getResults();
					while (ir.hasNext()) {
						model.addElement(ir.next());
					}
					if (!((String) model.getElementAt(0))
							.equalsIgnoreCase("No results found.")) {
						select.setEnabled(true);
						selection();
					}
				}
			}

		});
	}

	private static void selection() {
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent le) {
				currentIndex = list.getSelectedIndex();
			}
		});

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (currentIndex>=0){
					int cityId = sr.getId(currentIndex);
					System.out.println(cityId + " " + currentIndex);
				}
			}
		});

	}

	private static boolean isAlpha(String arg) {
		return arg.matches("[a-zA-Z,. ]+");
	}

	private static void searchRender() {
		sf.getContentPane().setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.setLayout(new GridLayout());

		sf.add(top, BorderLayout.NORTH);

		srchf = new JTextField("");
		top.add(srchf);

		srch = new JButton("Search");
		srch.setPreferredSize(new Dimension(0, 35));
		top.add(srch);
		

		String[] arr = { "randon" };
		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		sf.add(list, BorderLayout.CENTER);

		select = new JButton("Choose");
		select.setEnabled(false);
		sf.add(select, BorderLayout.EAST);

	}
}
