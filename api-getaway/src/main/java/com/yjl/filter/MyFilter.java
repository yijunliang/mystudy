package com.yjl.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
 * 对到达网关的请求进行拦截过滤，实现自定义过滤器(此类)之后，它不会直接生效，需要为其创建具体的bean才能启动过滤器
 * 在应用主类中创建这个过滤器类的实例。
 * @author Administrator
 *
 */
public class MyFilter extends ZuulFilter{
	@Override
	//过滤器的具体逻辑
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		System.out.println("请求信息:"+request.getMethod() + "|" + request.getRequestURL().toString() + "|" + request.getContentType());
		Object accessToken = request.getParameter("accessToken");
		//使用zuul网关过滤器之后需要设置请求头信息，要不然无法加载出数据
		HttpServletResponse response = requestContext.getResponse();
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		if(null == accessToken) {
			System.out.println("获取到的token是空的");
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(401);
			return null;
		}
		System.out.println("是可以被接受的同token");
		
		/*try {*/
			//doSomething();
		/*} catch (Exception e) {
			System.out.println(e.getMessage());
			requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			requestContext.set("error.exception", e.getMessage());
		}*/
		
		return null;
	}

	@Override
	//判断该过滤器是否需要被执行，返回真需要执行，返回假不需要执行
	public boolean shouldFilter() {
		return true;
	}

	@Override
	//过滤器的执行顺序，根据返回值决定
	public int filterOrder() {
		return 0;
	}

	@Override
	//过滤器的类型，决定过滤器在请求的那个生命周期执行。pre代表请求在被路由之前执行
	public String filterType() {
		return "pre";
	}
	
	public void doSomething() {
		throw new RuntimeException("产生了一些错误...");
	}
}
