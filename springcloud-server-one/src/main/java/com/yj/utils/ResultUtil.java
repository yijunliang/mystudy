package com.yj.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 *  返回结果封装类
 * @author Administrator
 *
 */
public class ResultUtil {
	/**
	 *  把返回结果包装为bootstrap能识别的json格式
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String wrapupResult(List list, Integer total) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",total);
        map.put("rows",list);
        return JSON.toJSONString(map);
	}

}
