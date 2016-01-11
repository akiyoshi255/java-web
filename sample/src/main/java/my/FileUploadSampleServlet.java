package my;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUploadSampleServlet", urlPatterns = { "/fileUploadSample" })
@MultipartConfig(fileSizeThreshold = 1048576, maxFileSize = 1048576, location = "temp")
public class FileUploadSampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		File file = new File("temp");
		file.mkdir();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part file = req.getPart("file");

		InputStream in = file.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		String line = br.readLine();
		while (line != null) {
			System.out.println(line);
			line = br.readLine();
		}

		resp.sendRedirect("fileUploadSample.html");
	}
}
