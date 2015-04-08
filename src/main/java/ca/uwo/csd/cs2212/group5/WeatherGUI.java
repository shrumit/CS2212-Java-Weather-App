package ca.uwo.csd.cs2212.group5;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Takes the cityId and calls an instance of Location from which it
 * fetches data for current, long term and short term weather conditions. This
 * data is displayed in a GUI window.
 * 
 * This class also displays the weather condition for Mars.
 * 
 * @author CS2212 Team 5
 *
 */
public class WeatherGUI {

	private static JFrame frame;
	private static Location loc;
	private static boolean isCelsius = true;
	private static String this_name;
	private static int this_cityId;
	private static final String iconUrl = "http://openweathermap.org/img/w/";
	private static final String iconUrl2 = ".png";
	private static MarsWeather mars;

	private static final Color megBackgroundCol = new Color(35, 43, 108);
	private static final Color minBackgroundCol = new Color(0, 12, 74);
	private static final Color marsBackgroundCol = new Color(171, 1, 1);
	private static final Color borderColor = new Color(156, 175, 194);
	private static final Color textColor = new Color(255, 255, 255);
	private static final Font textFont = new Font("Arial", Font.PLAIN, 20);
	private static final JFrame error = new JFrame("Controlled Error");

	/**
	 * Primary method which takes cityId and name and displays weather for that
	 * cityId.
	 * 
	 * @param cityId
	 *            OWM id of the location for which to display the weather
	 * @param name
	 *            name of the city, which is displayed on the title bar
	 */
	public static void startWeather(int cityId, String name) {

		if (cityId == -1) {
			try {

				this_name = "Mars - Curiosity Rover";
				this_cityId = cityId;

				frame = new JFrame(this_name);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mars = new MarsWeather();

				renderMenuBar();
				renderMars();

				frame.setBounds(50, 50, 400, 500);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				displayErrorFrame();

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
				frame.pack();
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				displayErrorFrame();
			}
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("In shutdown hook");
			}
		}, "Shutdown-thread"));

	}

	/**
	 * Displays all necessary information in the Mars window
	 */
	private static void renderMars() {

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.setBackground(marsBackgroundCol);

		System.out.println(mars.getRefreshTime());
		TitledBorder refreshTime = new TitledBorder("Refresh Time: "
				+ mars.getRefreshTime());
		refreshTime.setTitleJustification(TitledBorder.RIGHT);
		panel.setBorder(refreshTime);

		JLabel pressure = new JLabel("Pressure: " + mars.mr.getPressure()
				+ " hPa");
		pressure.setForeground(textColor);
		JLabel humidity = new JLabel("Humidity: " + mars.mr.getHumidity()
				+ " %");
		humidity.setForeground(textColor);
		JLabel wind = new JLabel("Wind: " + mars.mr.getWindSpeed()
				+ mars.mr.getWindDir());
		wind.setForeground(textColor);
		JLabel max = new JLabel("High: " + mars.mr.getMaxTemp(isCelsius));
		max.setForeground(textColor);
		JLabel min = new JLabel("Low: " + mars.mr.getMinTemp(isCelsius));
		min.setForeground(textColor);
		JLabel condition = new JLabel(mars.mr.getCondition());
		condition.setForeground(textColor);

		ImageIcon icon = new ImageIcon(getIconImage(mars.mr.getIcon()));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel image = new JLabel(new ImageIcon(newimg));

		condition.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(image, "wrap");
		panel.add(condition, "wrap");
		panel.add(min, "wrap");
		panel.add(max, "wrap");
		panel.add(pressure, "wrap");
		panel.add(humidity, "wrap");
		panel.add(wind, "wrap");

		frame.add(panel);

	}

	/**
	 * Reloads all necessary information by recalling the initial method with
	 * the same parameters.
	 */
	public static void refreshData() {
		startWeather(this_cityId, this_name);
	}

	/**
	 * Sets the menu bar and adds action listeners for the various options in
	 * it.
	 */
	private static void renderMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnProgram.add(mntmExit);

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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

	/**
	 * Displays a dialog box with an error message in case of an error while
	 * fetching data from the OWM api. This is most useful when the API service
	 * is offline.
	 */
	private static void displayErrorFrame() {

		final JFrame error = new JFrame("Controlled Error");
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

	/**
	 * Displays the window for the weather and populates it with all the weather
	 * data arranged in an organized manner.
	 */
	private static void renderGUI() {

		setUIFont(new javax.swing.plaf.FontUIResource(textFont));

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		frame.add(panel);

		cPanel = new JPanel();
		cPanel.setLayout(new MigLayout());
		populateCurrent();
		panel.add(cPanel, "wrap, alignx center");

		JLabel ltermForecast = new JLabel(" ");
		panel.add(ltermForecast, "wrap");

		lPanel = new JPanel();
		lPanel.setLayout(new GridLayout());
		populateLT();
		panel.add(lPanel, "alignx center, wrap");

		JLabel stermForecast = new JLabel(" ");
		panel.add(stermForecast, "wrap");

		sPanel = new JPanel();
		sPanel.setLayout(new GridLayout());
		populateST();
		panel.add(sPanel, "growx, pushx, growy, pushy, wrap");
		panel.setBackground(megBackgroundCol);

	}

	/**
	 * Populated the data needed in the current weather frame
	 */
	private static void populateCurrent() {
		JLabel temp = new JLabel(loc.cw.getTemperature(isCelsius)
				+ getUnitChar());

		temp.setFont(new Font("Arial", 0, 100));
		temp.setForeground(textColor);

		TitledBorder refreshTime = new TitledBorder("Refresh Time: "
				+ loc.getRefreshTime());

		refreshTime.setTitleColor(borderColor);
		refreshTime.setTitleJustification(TitledBorder.LEFT);
		refreshTime.setTitlePosition(TitledBorder.ABOVE_TOP);
		// refreshTime.setBorder(new LineBorder(Color.orange));

		cPanel.setBorder(refreshTime);

		JLabel sunrise = new JLabel("Sunrise: " + loc.cw.getSunrise());
		sunrise.setForeground(textColor);
		JLabel sunset = new JLabel("Sunset: " + loc.cw.getSunset());
		sunset.setForeground(textColor);
		JLabel pressure = new JLabel("Pressure: " + loc.cw.getPressure()
				+ " hPa");
		pressure.setForeground(textColor);

		JLabel humidity = new JLabel("Humidity: " + loc.cw.getHumidity() + " %");
		humidity.setForeground(textColor);
		JLabel speed = new JLabel("Wind: " + loc.cw.getWind() + "  "
				+ loc.cw.getWindDir());
		speed.setForeground(textColor);
		JLabel max = new JLabel("High: " + loc.cw.getMaxTemp(isCelsius)
				+ getUnitChar());
		max.setForeground(textColor);
		JLabel min = new JLabel("Low: " + loc.cw.getMinTemp(isCelsius)
				+ getUnitChar());
		min.setForeground(textColor);

		ImageIcon icon = new ImageIcon(getIconImage(loc.cw.getIconCode()));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel image = new JLabel(new ImageIcon(newimg));

		cPanel.add(image, "spany");
		cPanel.add(temp, "spany");
		cPanel.add(min, "wrap");
		cPanel.add(max, "wrap");
		cPanel.add(sunrise, "wrap");
		cPanel.add(sunset, "wrap");
		cPanel.add(humidity, "wrap");
		cPanel.add(pressure, "wrap");
		cPanel.add(speed);
		cPanel.setBackground(megBackgroundCol);

	}

	/**
	 * Populated the data needed in the long term weather frame
	 */
	private static void populateLT() {
		JPanel[] ilPanel = new JPanel[5];

		for (int i = 0; i < 5; i++) {
			ilPanel[i] = new JPanel();
			ilPanel[i].setLayout(new MigLayout());

			TitledBorder timeB = new TitledBorder(loc.lt[i].getTime());
			timeB.setTitleJustification(TitledBorder.RIGHT);
			timeB.setTitleColor(borderColor);

			// JLabel time = new JLabel (loc.lt[i].getTime());
			JLabel des = new JLabel(loc.lt[i].getDescription());
			des.setForeground(textColor);
			JLabel temp = new JLabel(loc.lt[i].getTemp(isCelsius)
					+ getUnitChar());
			temp.setForeground(textColor);
			temp.setFont(new Font(temp.getFont().getFontName(), 1, 25));
			JLabel low = new JLabel("Low: " + loc.lt[i].getMinTemp(isCelsius)
					+ getUnitChar());
			low.setForeground(textColor);
			JLabel high = new JLabel("  High: "
					+ loc.lt[i].getMaxTemp(isCelsius) + getUnitChar());
			high.setForeground(textColor);

			JLabel image = new JLabel(new ImageIcon(
					getIconImage(loc.lt[i].getIcon())));

			ilPanel[i].add(temp);
			ilPanel[i].add(image, "wrap");
			ilPanel[i].add(des, "wrap, span 2");
			ilPanel[i].add(low);
			ilPanel[i].add(high, "wrap");

			ilPanel[i].setBackground(minBackgroundCol);
			ilPanel[i].setBorder(timeB);
			lPanel.add(ilPanel[i]);

		}

	}

	/**
	 * Populated the data needed in the short term weather frame
	 */
	private static void populateST() {
		JPanel[] isPanel = new JPanel[8];

		for (int i = 0; i < 8; i++) {
			isPanel[i] = new JPanel();
			isPanel[i].setLayout(new MigLayout());

			TitledBorder timeB = new TitledBorder(loc.st[i].getTime());
			timeB.setTitleJustification(TitledBorder.RIGHT);
			timeB.setTitleColor(borderColor);

			// timeB.setTitleColor(Color.BLUE);

			JLabel des = new JLabel(loc.st[i].getDescription());
			des.setForeground(textColor);
			JLabel temp = new JLabel(loc.st[i].getTemp(isCelsius)
					+ getUnitChar());
			temp.setForeground(textColor);
			JLabel image = new JLabel(new ImageIcon(
					getIconImage(loc.st[i].getIcon())));
			isPanel[i].add(temp);
			isPanel[i].add(image, "wrap");
			temp.setFont(new Font(sPanel.getFont().getFontName(), 1, 25));
			isPanel[i].add(des, "wrap, span 2");
			isPanel[i].setBorder(timeB);

			isPanel[i].setBackground(minBackgroundCol);
			sPanel.add(isPanel[i]);

		}
	}

	/**
	 * Returns the condition icon image from OWM given the code
	 * 
	 * @param code icon code used by OWM
	 * @return Image of the current weather condition
	 */
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

	/**
	 * Returns the Unicode character for degrees along with the symbol for the
	 * temperature unit.
	 * 
	 * @return a string containing the unicode and the unit
	 */
	private static String getUnitChar() {
		if (isCelsius)
			return ("\u00B0C");
		else
			return ("\u00B0F");
	}

	/**
	 * Sets a global font
	 * 
	 * @param f font to be set
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
