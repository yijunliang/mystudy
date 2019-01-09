/*package com.yjl.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}
	@RequestMapping("/error")
	public ModelAndView error() {
		System.out.println("进入错误处理控制器");
		RequestContext requestContext = RequestContext.getCurrentContext();
		Throwable throwable = requestContext.getThrowable();
		String message = throwable.getMessage();
		System.out.println(message);
		return new ModelAndView("/error.jsp");
	}

}
*/