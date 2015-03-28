package ca.uwo.csd.cs2212.group5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class WeatherGUI {

	private static JFrame frame;
	private static Location loc;
	private static boolean isCelsius = true;
	private static String this_name;
	private static int this_cityId;
	// private static final String iconUrl =
	// "<img src=\"http://openweathermap.org/img/w/";
	// private static final String iconUrl2 =
	// ".png\" style=\"width:50px;height:50px\">";
	private static final String iconUrl = "http://openweathermap.org/img/w/";
	private static final String iconUrl2 = ".png";
	private static MarsWeather mars;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void startWeather(int cityId, String name) {

		System.out.println(cityId);
		if (cityId == -1) {
			try {

				this_name = "Mars - Curiosity Rover";
				this_cityId = cityId;

				mars = new MarsWeather();

				frame = new JFrame(this_name);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				renderMenuBar();
				renderMars();

				frame.pack();
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		else {
			this_name = name;
			this_cityId = cityId;

			try {
				loc = new Location(cityId);
				frame = new JFrame(name);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				renderMenuBar();
				renderGUI();
				// frame.setBounds(0, 0, 1000, 500);
				frame.pack();
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				displayErrorFrame();
			}
		}

	}

	private static void renderMars() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		System.out.println(mars.getRefreshTime());
		TitledBorder refreshTime = new TitledBorder("Refresh Time: "+ mars.getRefreshTime());
		refreshTime.setTitleJustification(TitledBorder.RIGHT);
		panel.setBorder(refreshTime);

		JLabel pressure = new JLabel("PRESSURE: " + mars.mr.getPressure()
				+ " hPa");

		JLabel humidity = new JLabel("HUMIDITY: " + mars.mr.getHumidity()
				+ " %");
		JLabel wind = new JLabel("WIND: " + mars.mr.getWindSpeed()
				+ mars.mr.getWindDir());
		JLabel max = new JLabel("HIGH: " + mars.mr.getMaxTemp(isCelsius));
		JLabel min = new JLabel("LOW: " + mars.mr.getMinTemp(isCelsius));
		JLabel condition = new JLabel(mars.mr.getCondition());

		panel.add(condition, "wrap");
		panel.add(min, "wrap");
		panel.add(max, "wrap");
		panel.add(pressure, "wrap");
		panel.add(humidity, "wrap");
		panel.add(wind, "wrap");
		
		frame.add(panel);

	}

	public static void refreshData() {
		startWeather(this_cityId, this_name);
	}

	private static void renderMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnProgram.add(mntmExit);

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu mnLocation = new JMenu("Location");
		menuBar.add(mnLocation);
		JMenuItem mntmChangeCity = new JMenuItem("Change City");
		mnLocation.add(mntmChangeCity);

		mntmChangeCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SearchGUI.main(null);
			}
		});

		JMenuItem mntmMars = new JMenuItem("Mars Mode");
		mnLocation.add(mntmMars);
		
		mntmMars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				startWeather(-1, null);
			}
		});


		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);
		JMenuItem mntmToggleData = new JMenuItem("Toggle Units");
		mnData.add(mntmToggleData);

		mntmToggleData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				isCelsius = !isCelsius;
				refreshData();
			}
		});

		JMenuItem mntmRefreshData = new JMenuItem("Refresh Data");
		mnData.add(mntmRefreshData);

		mntmRefreshData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				refreshData();
			}
		});

	}

	private static void displayErrorFrame() {

		JFrame error = new JFrame("Controlled Error");
		error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		error.setBounds(500, 300, 0, 0);

		JPanel erPanel = new JPanel();

		erPanel.setLayout(new MigLayout());

		JLabel errorMessage = new JLabel(
				"There was an error in fetching data from the API.");
		errorMessage.setFont(new Font("Arial", 0, 20));

		JButton goBack = new JButton("Back to search");

		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				error.dispose();
				SearchGUI.main(null);
			}
		});

		error.add(erPanel);
		erPanel.add(errorMessage, "wrap");
		erPanel.add(goBack, "span");
		error.pack();
		error.setVisible(true);
	}

	private static JPanel cPanel;
	private static JPanel lPanel;
	private static JPanel sPanel;

	private static void renderGUI() {

		setUIFont(new javax.swing.plaf.FontUIResource(new Font("Arial",
				Font.PLAIN, 20)));
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		frame.add(panel);

		cPanel = new JPanel();
		cPanel.setLayout(new MigLayout());
		populateCurrent();
		panel.add(cPanel, "wrap");

		JLabel ltermForecast = new JLabel("LONG TERM FORECAST");
		panel.add(ltermForecast, "wrap");

		lPanel = new JPanel();
		lPanel.setLayout(new GridLayout());
		populateLT();
		panel.add(lPanel, "growx, pushx, growy, pushy, wrap");

		JLabel stermForecast = new JLabel("SHORT TERM FORECAST");
		panel.add(stermForecast, "wrap");

		sPanel = new JPanel();
		sPanel.setLayout(new GridLayout());
		populateST();
		panel.add(sPanel, "growx, pushx, growy, pushy, wrap");

	}

	private static void populateCurrent() {
		JLabel temp = new JLabel(loc.cw.getTemperature(isCelsius)
				+ getUnitChar());

		temp.setFont(new Font("Arial", 0, 100));

		TitledBorder refreshTime = new TitledBorder("Refresh Time: "+loc.getRefreshTime());
		refreshTime.setTitleJustification(TitledBorder.RIGHT);
		refreshTime.setBorder(new LineBorder(Color.orange));
		
		cPanel.setBorder(refreshTime);

		JLabel sunrise = new JLabel("SUNRISE: " + loc.cw.getSunrise());
		JLabel sunset = new JLabel("SUNSET: " + loc.cw.getSunset());
		JLabel pressure = new JLabel("PRESSURE: " + loc.cw.getPressure()
				+ " hPa");

		JLabel humidity = new JLabel("HUMIDITY: " + loc.cw.getHumidity() + " %");
		JLabel speed = new JLabel("WIND: " + loc.cw.getWind()
				+ loc.cw.getWindDir());
		JLabel max = new JLabel("HIGH: " + loc.cw.getMaxTemp(isCelsius));
		JLabel min = new JLabel("LOW: " + loc.cw.getMinTemp(isCelsius));

		cPanel.add(temp);
		cPanel.add(min, "wrap");
		cPanel.add(max, "wrap");
		cPanel.add(sunrise, "wrap");
		cPanel.add(sunset, "wrap");
		cPanel.add(pressure, "wrap");
		cPanel.add(humidity, "wrap");

	}

	private static void populateLT() {
		JPanel[] ilPanel = new JPanel[5];

		for (int i = 0; i < 5; i++) {
			ilPanel[i] = new JPanel();
			ilPanel[i].setLayout(new MigLayout());

			TitledBorder timeB = new TitledBorder(loc.lt[i].getTime());
			timeB.setTitleJustification(TitledBorder.RIGHT);

			// JLabel time = new JLabel (loc.lt[i].getTime());
			JLabel des = new JLabel(loc.lt[i].getDescription());
			JLabel temp = new JLabel(loc.lt[i].getTemp(isCelsius)
					+ getUnitChar());
			temp.setFont(new Font(temp.getFont().getFontName(), 1, 25));
			JLabel low = new JLabel("Low: " + loc.lt[i].getMinTemp(isCelsius)
					+ getUnitChar());
			JLabel high = new JLabel("High: " + loc.lt[i].getMaxTemp(isCelsius)
					+ getUnitChar());

			JLabel image = new JLabel(new ImageIcon(
					getIconImage(loc.lt[i].getIcon())));

			ilPanel[i].add(temp);
			ilPanel[i].add(image, "wrap");
			ilPanel[i].add(des, "wrap");
			ilPanel[i].add(low, "wrap");
			ilPanel[i].add(high, "wrap");

			ilPanel[i].setBorder(timeB);
			lPanel.add(ilPanel[i]);

		}

	}

	private static void populateST() {
		JPanel[] isPanel = new JPanel[8];

		for (int i = 0; i < 8; i++) {
			isPanel[i] = new JPanel();
			isPanel[i].setLayout(new MigLayout());

			TitledBorder timeB = new TitledBorder(loc.st[i].getTime());
			timeB.setTitleJustification(TitledBorder.RIGHT);

			JLabel des = new JLabel(loc.st[i].getDescription());
			JLabel temp = new JLabel(loc.st[i].getTemp(isCelsius)
					+ getUnitChar());

			JLabel image = new JLabel(new ImageIcon(
					getIconImage(loc.st[i].getIcon())));

			isPanel[i].add(temp);
			isPanel[i].add(image, "wrap");
			temp.setFont(new Font(sPanel.getFont().getFontName(), 1, 25));
			isPanel[i].add(des, "wrap");
			isPanel[i].setBorder(timeB);

			sPanel.add(isPanel[i]);

		}
	}

	private static Image getIconImage(String code) {
		StringBuilder ret = new StringBuilder();
		ret.append(iconUrl);
		ret.append(code);
		ret.append(iconUrl2);

		try {
			URL url = new URL(ret.toString());
			Image image = ImageIO.read(url);
			return image;
		} catch (Exception e) {
			return null;
		}

	}

	private static String getUnitChar() {
		if (isCelsius)
			return ("\u00B0 C");
		else
			return ("\u00B0 F");
	}

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
