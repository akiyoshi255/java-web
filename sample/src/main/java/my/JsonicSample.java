package my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;

public class JsonicSample {

	public static void main(String[] args) {
		User user = new User();
		user.setId(123);
		user.setName("foo bar");
		user.setAddress("foo bar");
		System.out.println(JSON.encode(user));
		System.out.println();
		System.out.println(JSON.escapeScript(user));
		System.out.println();

		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", 123);
		userMap.put("name", "foo bar");
		userMap.put("address", "foo bar");
		System.out.println(JSON.encode(userMap));
		System.out.println();

		String[] names = { "foo", "bar", "baz" };
		System.out.println(JSON.encode(names));
		System.out.println();

		List<String> nameList = new ArrayList<String>();
		nameList.add("foo");
		nameList.add("bar");
		nameList.add("baz");
		System.out.println(JSON.encode(nameList));
		System.out.println();

		Map<Integer, String[]> namesMap = new HashMap<Integer, String[]>();
		namesMap.put(1, new String[] { "foo1", "bar1", "baz1" });
		namesMap.put(2, new String[] { "foo2", "bar2", "baz2" });
		namesMap.put(3, new String[] { "foo3", "bar3", "baz3" });
		System.out.println(JSON.encode(namesMap));
		System.out.println();

		Users users = new Users();
		users.getUsers().add(new User(1, "name1", "addr1"));
		users.getUsers().add(new User(2, "name2", "addr2"));
		users.getUsers().add(new User(3, "name3", "addr3"));
		System.out.println(JSON.encode(users, true));
		System.out.println();
	}
}
