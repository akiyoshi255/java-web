package my;

import java.util.ArrayList;
import java.util.List;

public class Users {
	private List<User> users;

	public Users() {
		this.users = new ArrayList<User>();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
