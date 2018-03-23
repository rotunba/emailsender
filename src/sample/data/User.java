package sample.data;

import java.util.HashSet;
import java.util.Set;

public class User {

	public static Set<Integer> allUsers() {
		Set<Integer> allUsers = new HashSet<Integer>();
		for (int i = 1; i <= 6040; i++)
			allUsers.add(i);
		return allUsers;
	}

}
