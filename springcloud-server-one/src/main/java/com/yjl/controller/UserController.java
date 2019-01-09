package com.yjl.controller;
/**
 * 用户控制器类
 * @author Administrator
 *
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yj.utils.ParamUtil;
import com.yj.utils.ResultUtil;
import com.yjl.dao.UserDao;
import com.yjl.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="/addUser",method=RequestMethod.GET)
	public ModelAndView insertUser(User user) {//通过bean来获取页面参数
		System.out.println(user.getUserName()+"|"+user.getPassword());
		userDao.insertUser(user);
		return new ModelAndView("/success.html");
	}
	
	@RequestMapping("/addUserUI")
	public ModelAndView addUserUI() {
		return new ModelAndView("/addUser.html");
	}
	
	@RequestMapping("/deleteUserUI")
	public ModelAndView deleteUserUI() {
		return new ModelAndView("/deleteUser.jsp");
	}
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(String userId) {//通过参数获取页面参数(此参数名要和页面参数名一致)
		userDao.deleteUser(ParamUtil.string2Integer(userId));
		return new ModelAndView("/success.html");
	}
	
	@RequestMapping("/findAllUserUI")
	public String findAllUserUI() {
		return "/index.jsp";
	}
	
	@RequestMapping("/findAllUser")
	public ModelAndView findAllUser(HttpServletRequest request) {//通过request获取到页面参数
		List<User> findUser = userDao.findUser(request.getParameter("userName"));
		ModelAndView modelAndView = new ModelAndView("/deleteUser.jsp");
		modelAndView.addObject("userList", findUser);
		return modelAndView;
	}
	
	@RequestMapping("/updateUserUI")
	public ModelAndView updateUserUI(int userId) {
		User findOneUser = userDao.findOneUser(userId);
		ModelAndView modelAndView = new ModelAndView("/updateUser.jsp");
		modelAndView.addObject("user", findOneUser);
		return modelAndView;
	}
	
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(User user) {//通过request获取到页面参数
		userDao.updateUser(user);
		return new ModelAndView("/success.html");
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("/index.html");
	}
	
	/**
	 * @ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
	  * 一般在异步获取数据时使用【也就是AJAX】，在使用 @RequestMapping后，返回值通常解析为跳转路径，
	  * 但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 
	  * 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。
	 * @RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。
	 * @param request
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public String findAll(HttpServletRequest request, HttpServletResponse resp) throws IOException {//通过request获取到页面参数
        //查询参数
		String userName = request.getParameter("userName");
        System.out.println("查询参数:"+userName);
        List<User> la = userDao.findUser(userName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",la.size());
        map.put("rows",la);
        //转为json格式数据
        String s= JSON.toJSONString(map);
        return s;
	}
	
	
	@RequestMapping("/findAllByLimit")
	@ResponseBody
	public String findAllByLimit(@RequestParam Map<String, Object> paramMap) {//通过request获取到页面参数
        List<User> list = userDao.findUserByLimit(paramMap);
        int total = userDao.findUserByLimitTotal(paramMap);
        return ResultUtil.wrapupResult(list,total);
	}
	
	@RequestMapping(value="/springbootAddUser",method=RequestMethod.GET)
	@ResponseBody
	public String springbootAddUser(@RequestParam Map<String, Object> paramMap) {//通过request获取到页面参数
		System.out.println("接收到的参数是:"+paramMap);
		User user = new User();
		user.setUserName(paramMap.get("userName").toString());
		user.setPassword(paramMap.get("password").toString());
		userDao.insertUser(user );
		//前台使用json格式传递参数，后台需要返回正确的json格式结果才能执行成功函数
		return "success";
	}
	
	
	@RequestMapping(value="/springbootDeleteUser",method=RequestMethod.GET)
	@ResponseBody
	public String springbootDeleteUser(@RequestParam Map<String, Object> paramMap) {//通过request获取到页面参数
		System.out.println("接收到的参数是:"+paramMap);
		//分割字符串，返回一个分割之后的数组(可以封装)
		/*String deleteUserId = paramMap.get("deleteUserId").toString();
		String[] deleteUserIdArray = deleteUserId.split(",");*/
		String[] deleteUserIdArray = ParamUtil.stringToArray(paramMap, "deleteUserId", ",");
		for(int i = 0; i < deleteUserIdArray.length; i++) {
			userDao.deleteUser(Integer.parseInt(deleteUserIdArray[i]));
		}
		//前台使用json格式传递参数，后台需要返回正确的json格式结果才能执行成功函数
		return "success";
	}
	@RequestMapping(value = "/sprinbootUpdateUser", method = RequestMethod.GET)
	@ResponseBody
	public String sprinbootUpdateUser(@RequestParam Map<String, Object> paramMap) {
		System.out.println("接收到的参数是:"+paramMap);
		int id = Integer.parseInt(paramMap.get("id").toString());
		String userName = paramMap.get("userName").toString();
		String password = paramMap.get("password").toString();
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		userDao.updateUser(user);//可以改为传map，让mybatis从map中取查询参数
		return "success";
	}
	
	
	@RequestMapping("/findUserById")
	@ResponseBody
	public Object findUserById(int userId) {
		System.out.println("要查询的id是:"+userId);
		return JSON.toJSONString(userDao.findOneUser(userId));
	}
	
	@RequestMapping("/findUserByIds")
	@ResponseBody
	public List<User> findUserByIds(List<Integer> ids) {
		return userDao.findUserByIds(ids);
	}

}
