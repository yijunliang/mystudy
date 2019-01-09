package com.yjl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访问静态资源index.html的控制器，此种方式和struts2的action访问方式类似
 * 隐藏了访问的页面名称
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/index")
public class IndexController {
	@RequestMapping("/myIndex")
	public ModelAndView myIndex() {
		//返回页面，通过视图解析器解析(可以在配置文件中配置前后缀)
		return new ModelAndView("/index.html");
	}

}
