package com.hourse.web.http;

import com.hourse.web.util.PropertiesUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * http请求基础类
 * 
 * @author Ring
 * 
 */
public class HttpPostHandle {
	private static Logger logger = Logger.getLogger("HttpPostHandle");

	public static final String BAIDU_MAP_AK = PropertiesUtils.get("ak.baidumap", "r7VN2piSzzyp6P9hMZiFkwar");
	public static final String BAIDU_MAP_SERVER = PropertiesUtils.get("server.baidumap", "http://115.239.210.16");
	
	public static final String BAIDU_MAP_URL_Direction = "/direction/v1"; 	//查询两地线路信息
	public static final String BAIDU_MAP_URL_GEOCODER_V2 = "/geocoder/v2/";

	public static String ENCODING = "UTF8";

	/**
	 * 组装http请求信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String formatRequest(Map<String, Object> map) {
		String res = "";
		if (null != map && map.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				if (value instanceof List) {
					List<String> list = (List<String>) value;
					for (String str : list) {
						sb.append(key + "=" + str + "&");
					}
				} else {
					//					if(value.toString().length() > 100) {
					//						sb.append(key + "=" + value.toString().substring(0,10) + "...&");
					//					} else{
					sb.append(key + "=" + value + "&");
					//					}
				}
			}
			res = sb.toString();
		}
		return res;
	}

	/**
	 * 日志
	 * @param result
	 * @return
	 */
	private static String result2Log(String result) {
		if (StringUtils.isNotBlank(result)) {
			boolean pwdExisted = result.contains("password=") ? true : false;
			boolean imageExisted = result.contains("image_data=") ? true : false;
			boolean bigContentExisted = result.contains("\"econtract_content\":\"") ? true : false;
			if (pwdExisted) {
				return result.replaceAll("password=\\d{6}", "password=******");
			} else if(imageExisted){
				return "[图片数据已过滤]";
			} else if (bigContentExisted) {
				return "[大数据已过滤]";
			}
			else{
				return result;
			}
		}
		return "";
	}




	public static String httpGetDirection(Map<String, Object> params){
		params.put("ak", BAIDU_MAP_AK);
		params.put("mode", "driving");
		params.put("output", "json");
		
		String url = BAIDU_MAP_SERVER + BAIDU_MAP_URL_Direction + "?" + formatRequest(params);
		String rstString = null;
		try {

			HttpResult result = HttpHelper.get(url, ENCODING);
			if (null != result) {
				int statusCode = result.getStatuCode();
				if (statusCode == 200) {
					String responseStr = result.getHtml();
					if (responseStr.indexOf("error_no") != -1) {
						//							res = formatResponse(responseStr, type);
					} else {
						byte[] bytes = result.getResponse();
						rstString = new String(bytes,ENCODING);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rstString;
	}

	/**
	 * 通过经纬度查地址信息
	 * 或者通过地址查经纬度
	 * @param params
	 * @return
	 */
	public static String httpGetAddress(Map<String, Object> params){
		params.put("ak", BAIDU_MAP_AK);
		params.put("output", "json");
		
		String url = BAIDU_MAP_SERVER + BAIDU_MAP_URL_GEOCODER_V2 + "?" + formatRequest(params);
		String rstString = null;
		try {

			HttpResult result = HttpHelper.get(url, ENCODING);
			if (null != result) {
				int statusCode = result.getStatuCode();
				if (statusCode == 200) {
					String responseStr = result.getHtml();
					if (responseStr.indexOf("error_no") != -1) {
						//							res = formatResponse(responseStr, type);
					} else {
						byte[] bytes = result.getResponse();
						rstString = new String(bytes,ENCODING);
					}
				}
			}
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.error(">>>>>>百度解析失败>>>>>");
		}
		return rstString;
	}
	




	@SuppressWarnings("unchecked")
	public static void main(String[] a) {
	}
}
