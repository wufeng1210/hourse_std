package com.hourse.web.util.common;


import com.hourse.web.util.PropertiesUtils;

/***********************************************************************************
*1-->2013-05-24  create by stevinzhu@cairenhui.com  定义如分隔符,前缀符    BugNo:--
*2-->
***********************************************************************************/
public class Constant {
	//项目地址
	public static final String WEB_ADDR = PropertiesUtils.get("oos.webaddr");
	//静态资源地址
	public static final String STATIC_RESOURCES_ADDR = PropertiesUtils.get("oos.staticresrouce.addr");
    //视频验证呼叫号
	public static final String DELAULT_QUEUE_ID = PropertiesUtils.get("default_queue_id", "1000");
	//金仕达密码加密算法SN串
	public static  final  String PWD_ENCRYPT="000000";
	
	public static final String ERROR_INFO = "errorInfo";

	public static final String ERROR_NO = "errorNo";
	
	public static final String ATTR_BROKER = "BROKER";
	public static final String ATTR_BRANCH = "BRANCH";
	public static final String ATTR_BANK = "BANK";
	public static final String CRH_SESSION_ID = "crh_session_id_app";
	
	public static final String RES_MAP_KEY_IN_REQ = "resMap_forInterceptor"; // 返回结果存在request中的key
} 
