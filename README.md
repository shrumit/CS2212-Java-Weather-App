# team5
Private repository for Team5

TEAM 5 - README.md


#Title: Weather Application

##Synopsis

This is a very lightweight weather app that is not resource-taxing like other applications. It provides weather and temperature information for any city available in the OpenWeatherMap database and on Mars. 

The structure of the program can be found in a [UML diagram.](https://i.imgur.com/hg67sQZ.png) In a nutshell, upon startup, the program asks for user input regarding location. If a city on Earth is desired, the user inputs a city name and/without the respective country. After pressing "Search", an API call to [OpenWeatherMap](http://openweathermap.org/api) is made to determine all the results provided by the API. Quality is controlled by creating cases that determine if the search query is valid, ie. if it is longer than 2 characters, contains valid character etc. After choosing the city, another API call is made to OpenWeatherMap, this time to collect the weather and temperature data. Similarly, in Mars mode, we make an API call to MarsWeather. The data returned is then parsed through to distinguish the different pieces of information, which are then stored as object fields. Long term, short term and current are all taken as different objects. The information is then displayed in the GUI, as given by WeatherGUI, which fills in the designated frames with the required information in Long term, short term and current. If in Mars mode, the same WeatherGUI class is used, except the mode value is toggled to let the program know that the Mars GUI is needed. In the toolbar, we are given the options of exiting, changing locations, toggling temperature units, and refreshing data. Libraries used include Swing for the GUI and MigLayout for layout and formatting.

##Install

In order to install the program, the user must have the following installed:

*Github
*Java
*Maven

On github, download the package as a ZIP or clone to Desktop with this SSH clone URL: git@github.com:UWO-2212-W2015/team5.git. A packaged version is found in the JAR file in the folder "target". The JAR is named cs2212group5-1.0-jar-with-dependencies.jar. THis packaged version is ready to go, and can be used by simply clicking on it.

In the git bash, the commands are as follows:

`git clone git@github.com:UWO-2212-W2015/team5.git`
`java -jar target/cs2212group5-1.0-jar-with-dependencies.jar`

##Build

In order to build the program, the user must have the following installed:

*Github
*Java
*Maven

In git bash, the commands are as follows:

`git clone git@github.com:UWO-2212-W2015/team5.git`
`mvn package`

##Usage Demonstration

[This video]() demonstrates the features of the program.

##Javadoc

Javadocs can be found at https://github.com/UWO-2212-W2015/team5/tree/master/doc or in /doc from the main folder.


