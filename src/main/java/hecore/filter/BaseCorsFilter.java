
/**
 * @author Administrator
 *
 */
package hecore.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
//@WebFilter(filterName="BaseCorsFilter",urlPatterns="/*")
public class BaseCorsFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		 System.out.println("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest rep, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//System.out.println("执行过滤操作");
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		fc.doFilter(rep, res);
	}

	@Override
	public void init(FilterConfig fcong) throws ServletException {
		// TODO Auto-generated method stub
		 System.out.println("过滤器初始化");
	}
	
}