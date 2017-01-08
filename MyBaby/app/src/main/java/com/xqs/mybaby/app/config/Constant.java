/**
 * 
 */
package com.xqs.mybaby.app.config;

import java.util.UUID;

/**
 * app所用到的常量
 * 
 */
public class Constant {

	public static float density; // 屏幕密度
	public static int height; // 屏幕高度
	public static int width; // 屏幕宽度
	public static UUID UUID;//应用唯一的标示

	/** sjh存储目录/文件 **/
	public static final String DIR = "/mybaby";
	public static final String CACHE_DIR = DIR + "/images";
	public static final String LOG_DIR = DIR + "/logs";
	public static final String DB_DIR = DIR+"/database";
	public static final String DISC_CACHE = DIR+"/disccache";

	/** http请求响应 **/
	public static final int RESULT_SUCCESS = 1;
	public static final int RESULT_FAILURE = -1;
	public static final int RESULT_IOERROR = -2;
	public static final int NETWORK_ERROR = -3;
	public static final int RESULT_AUTHORITY_ERROR=-4;

}
