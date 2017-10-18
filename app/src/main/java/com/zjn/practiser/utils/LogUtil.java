package com.zjn.practiser.utils;

import android.content.Context;
import android.util.Log;


/**  
 * 调试信息工具类
* @author 张佳男
*  
*/
public class LogUtil {
	/**
	 * 是否是调试模式 ，调试模式下会在控制台打印log信息
	 * */
	public static boolean IsDebug = true;
	/**
	 *  错误信息
	 * @param context 上下文
	 * @param msg 日志信息
	 */
	public static void e(Context context, String msg) {
		e(context.getClass().getSimpleName(), msg);
	}
	/**
	 *  错误信息
	 * @param tag 标记信息
	 * @param msg 日志信息
	 */
	public static void e(String tag, String msg) {
		if (IsDebug) {
			Log.e(tag, msg+"  ");
		}
	}
	/**
	 *  普通信息
	 * @param context 上下文
	 * @param msg 日志信息
	 */
	public static void i(Context context, String msg) {
		i(context.getClass().getSimpleName(), msg);
	}
	/**
	 *  普通信息
	 * @param tag 标记信息
	 * @param msg 日志信息
	 */
	private static void i(String tag, String msg) {
		if (IsDebug) {
			Log.i(tag, msg+"  ");
		}
	}
	/**
	 *  详细信息
	 * @param context 上下文
	 * @param msg 日志信息
	 */
	public static void v(Context context, String msg) {
		v(context.getClass().getSimpleName(), msg);
	}
	/**
	 *  详细信息
	 * @param tag 标记信息
	 * @param msg 日志信息
	 */
	private static void v(String tag, String msg) {
		if (IsDebug) {
			Log.v(tag, msg+"  ");
		}
	}
	/**
	 *  DEBUG级信息
	 * @param context 上下文
	 * @param msg 日志信息
	 */
	public static void d(Context context, String msg) {
		d(context.getClass().getSimpleName(), msg);
	}
	/**
	 *  DEBUG级信息
	 * @param tag 标记信息
	 * @param msg 日志信息
	 */
	public static void d(String tag, String msg) {
		if (IsDebug) {
			Log.d(tag, msg+"  ");
		}
	}
	/**
	 *  System.out.println();信息
	 * @param context 当前上下文
	 * @param msg 日志信息
	 */
	public static void out(Context context, String msg){
		out(context.getClass().getName(), msg);
	}
	/**
	 *  System.out.println();信息
	 * @param tag 标记信息
	 * @param msg 日志信息
	 */
	public static void out(String tag, String msg){
		if (IsDebug) {
			System.out.println(tag+"----"+msg);
		}
	}
}
