package com.xqs.mybaby.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


public class SQLiteTemplate {
	/**
	 * Default Primary key
	 */
	protected String mPrimaryKey = "_id";


	/**
	 * DBManager
	 */
	private DBManager mDBManager;

	private boolean mIsTransaction = false;

	private SQLiteDatabase mDataBase = null;

	private static SQLiteTemplate mSQLiteTemplate = null;
	private SQLiteTemplate() {
	}

	private SQLiteTemplate(DBManager dBManager, boolean isTransaction) {
		this.mDBManager = dBManager;
		this.mIsTransaction = isTransaction;
	}


	public static SQLiteTemplate getInstance(DBManager dBManager,
			boolean isTransaction) {
		if(mSQLiteTemplate == null){
			mSQLiteTemplate = new SQLiteTemplate(dBManager, isTransaction);
		}
		return mSQLiteTemplate;
	}


	public synchronized void execSQL(String sql) {
		try {
			mDataBase = mDBManager.openDatabase();
			mDataBase.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
	}


	public synchronized void execSQL(String sql, Object[] bindArgs) {
		try {
			mDataBase = mDBManager.openDatabase();
			mDataBase.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
	}


	public synchronized long insert(String table, ContentValues content) {
		try {
			mDataBase = mDBManager.openDatabase();
			return mDataBase.insert(table, null, content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return 0;
	}


	public synchronized void deleteByIds(String table, Object... primaryKeys) {
		try {
			if (primaryKeys.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (@SuppressWarnings("unused")
				Object id : primaryKeys) {
					sb.append("?").append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				mDataBase = mDBManager.openDatabase();
				mDataBase.execSQL("delete from " + table + " where "
						+ mPrimaryKey + " in(" + sb + ")",
						(Object[]) primaryKeys);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
	}


	public synchronized int deleteByField(String table, String field, String value) {
		try {
			mDataBase = mDBManager.openDatabase();
			return mDataBase.delete(table, field + "=?", new String[] { value });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return 0;
	}

	public synchronized int deleteAll(String table){
		try{
			mDataBase = mDBManager.openDatabase();
			return mDataBase.delete(table,null,null);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(!mIsTransaction){
				closeDatabase(null);
			}
		}
		return 0;
	}


	public synchronized int deleteByCondition(String table, String whereClause,
			String[] whereArgs) {
		try {
			mDataBase = mDBManager.openDatabase();
			return mDataBase.delete(table, whereClause, whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return 0;
	}


	public synchronized int deleteById(String table, String id) {
		try {
			mDataBase = mDBManager.openDatabase();
			return deleteByField(table, mPrimaryKey, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return 0;
	}


	public synchronized int updateById(String table, String id, ContentValues values) {
		try {
			mDataBase = mDBManager.openDatabase();
			return mDataBase.update(table, values, mPrimaryKey + "=?",
					new String[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return 0;
	}



	public synchronized int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		try {
			mDataBase = mDBManager.openDatabase();
			return mDataBase.update(table, values, whereClause, whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return 0;
	}


	public synchronized Boolean isExistsById(String table, String id) {
		try {
			mDataBase = mDBManager.openDatabase();
			return isExistsByField(table, mPrimaryKey, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return null;
	}


	public synchronized Boolean isExistsByField(String table, String field, String value) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ")
				.append(field).append(" =?");
		try {
			mDataBase = mDBManager.openDatabase();
			return isExistsBySQL(sql.toString(), new String[]{value});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}
		return null;
	}

	public synchronized Boolean isExistsBySQL(String sql, String[] selectionArgs) {
		Cursor cursor = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.rawQuery(sql, selectionArgs);
			if (cursor.moveToFirst()) {
				return (cursor.getInt(0) > 0);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(cursor);
			}
		}
		return null;
	}


	public synchronized <T> T queryForObject(RowMapper<T> rowMapper, String sql,
			String[] args) {
		Cursor cursor = null;
		T object = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.rawQuery(sql, args);
			if (cursor.moveToFirst()) {
				object = rowMapper.mapRow(cursor, cursor.getCount());
			}
		} finally {
			if (!mIsTransaction) {
				closeDatabase(cursor);
			}
		}
		return object;

	}

	public synchronized String queryForString(String sql,String[] args){
		Cursor cursor = null;
		String object = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.rawQuery(sql, args);
			if (cursor.moveToFirst()) {
				object = cursor.getString(0);
			}
		} finally {
			if (!mIsTransaction) {
				closeDatabase(cursor);
			}
		}
		return object;
	}

	public synchronized <T> List<T> queryForList(RowMapper<T> rowMapper, String sql,
			String[] selectionArgs) {
		Cursor cursor = null;
		List<T> list = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.rawQuery(sql, selectionArgs);
			list = new ArrayList<T>();
			while (cursor.moveToNext()) {
				list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
			}
		} finally {
			if (!mIsTransaction&&(cursor!= null)) {
				closeDatabase(cursor);
			}
		}
		return list;
	}

	public synchronized <T> void insertList(InsertRow<T> insert,String sql,List<T> list){
		try{
			mDataBase = mDBManager.openDatabase();

			SQLiteStatement stat = mDataBase.compileStatement(sql);
			mDataBase.beginTransaction();
			for(T item:list){
				insert.insert(stat,item);

			}

			mDataBase.setTransactionSuccessful();

		}finally {
			mDataBase.endTransaction();

			if (!mIsTransaction) {
				closeDatabase(null);
			}
		}

	}


	public synchronized <T> List<T> queryForList(RowMapper<T> rowMapper, String sql,
			int startResult, int maxResult) {
		Cursor cursor = null;
		List<T> list = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.rawQuery(sql + " limit ?,?", new String[] {
					String.valueOf(startResult), String.valueOf(maxResult) });
			list = new ArrayList<T>();
			while (cursor.moveToNext()) {
				list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
			}
		} finally {
			if (!mIsTransaction) {
				closeDatabase(cursor);
			}
		}
		return list;
	}

	public synchronized Integer getCount(String sql, String[] args) {
		Cursor cursor = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.rawQuery("select count(*) from (" + sql + ")",
					args);
			if (cursor.moveToNext()) {
				return cursor.getInt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!mIsTransaction) {
				closeDatabase(cursor);
			}
		}
		return 0;
	}

	public synchronized <T> List<T> queryForList(RowMapper<T> rowMapper, String table,
			String[] columns, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy, String limit) {
		List<T> list = null;
		Cursor cursor = null;
		try {
			mDataBase = mDBManager.openDatabase();
			cursor = mDataBase.query(table, columns, selection, selectionArgs,
					groupBy, having, orderBy, limit);
			list = new ArrayList<T>();
			while (cursor.moveToNext()) {
				list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
			}
		} finally {
			if (!mIsTransaction) {
				closeDatabase(cursor);
			}
		}
		return list;
	}



	public String getPrimaryKey() {
		return mPrimaryKey;
	}


	public void setPrimaryKey(String primaryKey) {
		this.mPrimaryKey = primaryKey;
	}


	public interface RowMapper<T> {

		public T mapRow(Cursor cursor, int index);
	}

	public interface InsertRow<T>{
		public void insert(SQLiteStatement stat, T item);
	}


	public void closeDatabase(Cursor cursor) {
		if (null != mDataBase) {
			mDataBase.close();
		}
		if (null != cursor) {
			cursor.close();
		}
	}
}
