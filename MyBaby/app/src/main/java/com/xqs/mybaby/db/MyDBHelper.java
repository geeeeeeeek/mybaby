
package com.xqs.mybaby.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xqs.mybaby.utils.Utils;

public class MyDBHelper extends SQLiteOpenHelper {

    private static MyDBHelper mMyDBHelper;

    private static String mDBName= null;
    public synchronized static MyDBHelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        if(mMyDBHelper == null){
            mMyDBHelper = new MyDBHelper(context, name,factory, version);
        }

        if((mDBName!= null)&&(!mDBName.equals(name))){
            destory();
            mMyDBHelper = new MyDBHelper(context, name,factory, version);
        }
        return mMyDBHelper;
    }

    public synchronized static void destory(){

        if(mMyDBHelper != null){
            mMyDBHelper.close();
            mMyDBHelper = null;
        }
    }

    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mDBName = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE [xmpp_notice]  ([_id] INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, [type] INTEGER, [title] NVARCHAR, [content] NVARCHAR, [notice_from] NVARCHAR, [notice_to] NVARCHAR, [notice_time] TEXT, [status] INTEGER, [msg_otherint2] INTEGER, [msg_other1] TEXT, [msg_other2] TEXT, [msg_other3] TEXT);");
        db.execSQL("CREATE TABLE [bdp_friend]  ([_id] INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, [erp] NVARCHAR, [erp_name] NVARCHAR, [face_url] NVARCHAR, [friend_other1] NVARCHAR, [friend_other2] TEXT, [friend_otherint1] INTEGER, [friend_otherint2] INTEGER);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Utils.log("MyDBHelper","oldVersion=" + oldVersion + ",newVersion" + newVersion);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
