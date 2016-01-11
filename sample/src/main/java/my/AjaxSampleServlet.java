package my;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet(name = "AjaxSampleServlet", urlPatterns = { "/ajaxSample" })
public class AjaxSampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(CalcResource.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("AjaxSampleServlet#doPost");

		User user = new User();
		user.setId(123);
		user.setName("foo bar");
		user.setAddress("foo bar");
		// String json = JSON.encode(user);
		String json = JSON.encode(request.getParameterMap());

		response.setStatus(HttpServletResponse.SC_OK);
		// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType("application/json");
		OutputStream out = response.getOutputStream();
		out.write(json.getBytes());
		response.flushBuffer();
	}
}
