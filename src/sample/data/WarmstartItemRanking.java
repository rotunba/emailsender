package sample.data;

import com.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WarmstartItemRanking {

	public static void main(String[] args) {
		Set<Integer> allUSers = User.allUsers(); // get all users 1 - 6040

		String csvFile = "/Users/rotunb200/Documents/workspace/sample/src/sample/data/ml-1m.csv";
		String output = "/Users/rotunb200/Documents/workspace/sample/src/sample/data/warmstart/itemranking/ml-1m.train.rating";
		String outputTestFile = "/Users/rotunb200/Documents/workspace/sample/src/sample/data/warmstart/itemranking/ml-1m.test.rating";
		String outputPositives = "/Users/rotunb200/Documents/workspace/sample/src/sample/data/warmstart/itemranking/ml-1m.test.positive";

		Set<Integer> testUsers = new HashSet<Integer>(); // keep track of users
															// in test file
		CSVReader reader, reader2 = null;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(output));
				BufferedWriter bwTestFile = new BufferedWriter(new FileWriter(outputTestFile));
				BufferedWriter bwTestPositives = new BufferedWriter(new FileWriter(outputPositives));) {
			int total = 0;
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				int user = Integer.parseInt(line[0]);
				int item = Integer.parseInt(line[1]);

				/*
				 * make sure user is in list of all users
				 */
				if (allUSers.contains(user)) {

					if (!testUsers.contains(user)) {
						testUsers.add(user);
						bwTestFile.write((user - 1) + "\t" + (item - 1) + "\t" + line[2] + "\t" + line[3] + "\n");
						// fill positives file
						String[] positivesLine;
						reader2 = new CSVReader(new FileReader(csvFile));
						Set<Integer> uniqueItemsPerUser = new HashSet<Integer>();
						String itemLine = (user - 1) + "\t";

						while ((positivesLine = reader2.readNext()) != null) {
							int positiveUser = Integer.parseInt(positivesLine[0]);
							int positiveItem = Integer.parseInt(positivesLine[1]);

							if (user == positiveUser && !uniqueItemsPerUser.contains(positiveItem)) {

								itemLine += (positiveItem - 1) + "\t";
								uniqueItemsPerUser.add(positiveItem);
							}
						}
						bwTestPositives.write(itemLine + "\n");
						// if (uniqueUsersPerItem.size() >= 50) {
						// bwTestPositives.write(itemLine + "\n");
						total += uniqueItemsPerUser.size();
						System.out.println((user - 1) + ":\t" + total);
						// }
						continue; // don't add this rating in the ratings file
					}

				}
				bw.write((user - 1) + "\t" + (item - 1) + "\t" + line[2] + "\t" + line[3] + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}