package ua.nure.bei.SummaryTask4.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

	private String encoding;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("enc");
		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");
		encoding = fConfig.getInitParameter("encoding");
		LOG.trace("Encoding from web.xml --> " + encoding);
		LOG.debug("Filter initialization finished");
	}

}