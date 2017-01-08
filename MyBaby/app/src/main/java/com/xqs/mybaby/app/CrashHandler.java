package com.xqs.mybaby.app;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {  
     
    /** 系统默认的UncaughtException处理类 */ 
    private Thread.UncaughtExceptionHandler mDefaultHandler;  
    /** CrashHandler实例 */ 
    private static CrashHandler INSTANCE;  
    /** 程序的Context对象 */ 
    private Context mContext;  
    /** 保证只有一个CrashHandler实例 */ 
    private CrashHandler() {}  
    /** 获取CrashHandler实例 ,单例模式*/ 
    public static CrashHandler getInstance() {  
        if (INSTANCE == null) {  
            INSTANCE = new CrashHandler();  
        }  
        return INSTANCE;  
    }  
   
    /** 
     * 初始化,注册Context对象, 
     * 获取系统默认的UncaughtException处理器, 
     * 设置该CrashHandler为程序的默认处理器 
     *  
     * @param ctx 
     */ 
    public void init(Context ctx) {  
        mContext = ctx;  
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();  
        Thread.setDefaultUncaughtExceptionHandler(this);   
    }  
   
    /** 
     * 当UncaughtException发生时会转入该函数来处理 
     */ 
    @Override 
    public void uncaughtException(Thread thread, Throwable ex) {  
        if (!handleException(ex) && mDefaultHandler != null) {  
            //如果用户没有处理则让系统默认的异常处理器来处理  
            mDefaultHandler.uncaughtException(thread, ex);  
        } else {  //如果自己处理了异常，则不会弹出错误对话框，则需要手动退出app
            try {  
                Thread.sleep(3000);  
            } catch (InterruptedException e) {  
            }  
            android.os.Process.killProcess(android.os.Process.myPid());  
            System.exit(10);  
        }  
    }  
   
    /** 
     * 自定义错误处理,收集错误信息
     */ 
    private boolean handleException(final Throwable ex) {  
        if (ex == null) {  
            return false;  
        }



    	    
       Log.e("CrashHandler===",getErrorInfo(ex));
   
        return false;  
    }  
    
    private String getErrorInfo(Throwable arg1) {  
        Writer writer = new StringWriter();  
        PrintWriter pw = new PrintWriter(writer);  
        arg1.printStackTrace(pw);  
        pw.close();  
        String error= writer.toString();  
        return error;  
    }  

}