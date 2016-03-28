package my;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
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

	private static final Log LOG = LogFactory.getLog(AjaxSampleServlet.class);

	private Category[] largeCategories = null;

	private Map<String, Category[]> largeToMiddleCategoryMap = new HashMap<String, Category[]>();

	private Map<String, Category[]> middleToSmallCategoryMap = new HashMap<String, Category[]>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		Category largeCategory1 = new Category("largeId1", "largeData1");
		Category largeCategory2 = new Category("largeId2", "largeData2");
		Category largeCategory3 = new Category("largeId3", "largeData3");
		largeCategories = new Category[] { largeCategory1, largeCategory2, largeCategory3 };

		Category middleCategory1 = new Category("middleId1", "middleData1");
		Category middleCategory2 = new Category("middleId2", "middleData2");
		Category middleCategory3 = new Category("middleId3", "middleData3");
		largeToMiddleCategoryMap.put("largeId1", new Category[] { middleCategory1, middleCategory2 });
		largeToMiddleCategoryMap.put("largeId2", new Category[] { middleCategory1, middleCategory3 });
		largeToMiddleCategoryMap.put("largeId3", new Category[] { middleCategory2, middleCategory3 });

		Category smallCategory1 = new Category("smallId1", "smallData1");
		Category smallCategory2 = new Category("smallId2", "smallData2");
		Category smallCategory3 = new Category("smallId3", "smallData3");
		middleToSmallCategoryMap.put("middleId1", new Category[] { smallCategory1, smallCategory2 });
		middleToSmallCategoryMap.put("middleId2", new Category[] { smallCategory1, smallCategory3 });
		middleToSmallCategoryMap.put("middleId3", new Category[] { smallCategory2, smallCategory3 });
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("\"parameters\": {");
		for (String paramName : request.getParameterMap().keySet()) {
			String paramValue = request.getParameter(paramName);
			LOG.info("    \"" + paramName + "\": " + paramValue);
		}
		LOG.info("}");

		String largeCategoryId = request.getParameter("largeCategoryId");
		String middleCategoryId = request.getParameter("middleCategoryId");

		CategorySearchResult searchResult = new CategorySearchResult();
		searchResult.setLargeCategoryId(largeCategoryId);
		searchResult.setMiddleCategoryId(middleCategoryId);
		if (largeCategoryId == null) {
			if (middleCategoryId == null) {
				// 大カテゴリ
				searchResult.setResult(largeCategories);
			} else {
				// エラー
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			if (middleCategoryId == null) {
				// 中カテゴリ
				searchResult.setResult(largeToMiddleCategoryMap.get(largeCategoryId));
			} else {
				// 小カテゴリ
				searchResult.setResult(middleToSmallCategoryMap.get(middleCategoryId));
			}
		}

		String responseJson = JSON.escapeScript(searchResult);
		LOG.info(responseJson);
		byte[] responseData = responseJson.getBytes();

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setContentLength(responseData.length);
		response.getOutputStream().write(responseData);
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
