package my;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class UsersResource {
	@Path("/user")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		User user = new User();
		user.setId(123);
		user.setName("foo");
		user.setAddress("bar");
		return user;
	}
}
