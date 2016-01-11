package my;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/")
public class CalcResource {
	Log LOG = LogFactory.getLog(CalcResource.class);

	@Path("/add")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CalcResult add(@QueryParam("x") int xParam, @QueryParam("y") int yParam) {
		LOG.info("x=" + xParam + "y=" + yParam);

		// int x = Integer.parseInt(xParam);
		// int y = Integer.parseInt(yParam);
		int x = xParam;
		int y = yParam;

		CalcResult result = new CalcResult();
		result.setCalcType("add2");
		result.setArg1(x);
		result.setArg2(y);
		result.setResult(x + y);
		return result;
	}

	@Path("/sub")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CalcResult sub(@QueryParam("x") String xParam, @QueryParam("y") String yParam) {
		int x = Integer.parseInt(xParam);
		int y = Integer.parseInt(yParam);

		CalcResult result = new CalcResult();
		result.setCalcType("sub");
		result.setArg1(x);
		result.setArg2(y);
		result.setResult(x - y);
		return result;
	}
}
