# New_Flower_Database



Download the Sqlite-jdbc-3.23.1 and put it in the same folder with the java files.
The sqlite-jdbc will allow java to run sqlite3 queries.

Before compiling, change the classpath of the flowers.db file in the SqliteDB class.

After compiling, run the program with:

java -classpath ".;sqlite-jdbc-3.23.1.jar" FlowerClub

# How the program works:

Menu is prompted with 4 options:
1) View a list of the 10 most recent sightings of a flower selected by the user via an integer input on a list
2) Update from one of the three tables specific values requested
3) Insert a new flower sighting into the sightings table
4) Terminate the program
