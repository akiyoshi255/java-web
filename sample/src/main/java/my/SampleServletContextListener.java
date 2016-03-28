package my;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SampleServletContextListener implements ServletContextListener {
	private static final Log LOG = LogFactory
			.getLog(SampleServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("contextInitialized");
		ServletContext context = event.getServletContext();
		LOG.info("contextPath=" + context.getContextPath());

		LOG.info("contextInitParameters");
		Enumeration<String> names = context.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = context.getInitParameter(name);
			LOG.info(name + "=" + value);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOG.info("contextDestroyed");
	}
}
