package sample.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Item {

	public static Set<Integer> testItems(int maxTestSize) {
		String itemFile = "/Users/rotunb200/Documents/workspace/sample/src/sample/data/ml-1m.movies.dat";
		Set<Integer> uniqueItems = new HashSet<Integer>();
		int count = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(itemFile))) {

			String line = br.readLine();

			while (line != null && count < maxTestSize) {
				String[] lineArray = line.split("::");
				if (lineArray[2] != "") {
					uniqueItems.add(Integer.parseInt(lineArray[0]));
					count++;
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uniqueItems;
	}

	public static Set<Integer> testItems() {
		String itemFile = "/Users/rotunb200/Documents/workspace/sample/src/sample/data/ml-1m.movies.dat";
		Set<Integer> uniqueItems = new HashSet<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(itemFile))) {

			String line = br.readLine();

			while (line != null) {
				String[] lineArray = line.split("::");
				if (lineArray[2] != "")
					uniqueItems.add(Integer.parseInt(lineArray[0]));
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uniqueItems;
	}
}
