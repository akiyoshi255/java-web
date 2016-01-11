package my;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

@WebServlet(name = "FileDownloadServlet", urlPatterns = { "/fileDownloadSample" })
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setId(123);
		user.setName("foo bar");
		user.setAddress("foo bar");
		// String json = JSON.encode(user);
		String json = JSON.encode(user);

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment; filename=users.txt");

		OutputStream out = response.getOutputStream();
		out.write(json.getBytes());
		response.flushBuffer();
	}
}
