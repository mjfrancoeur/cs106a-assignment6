import java.io.BufferedReader;
import java.util.HashMap;

import acm.util.ErrorException;

import java.io.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	public NameSurferDataBase(String filename) {
		loadData(filename);
	}

	/* Method: loadData(filename) */
	/**
	 * Opens file with BufferedReader rd
	 */
	private void loadData(String filename) {

		entriesMap = new HashMap<String, NameSurferEntry>();

		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				} else {
					NameSurferEntry entry = new NameSurferEntry(line);
					entriesMap.put(entry.getName(), entry);
				}
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		if (entriesMap.get(name) == null) {
			return null;
		} else {
			return entriesMap.get(name);
		}
	}

	/* private instance variables */
	private HashMap<String, NameSurferEntry> entriesMap;

}
