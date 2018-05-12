package com.example.idoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	public static String name = "course.db";
	private static int version = 1;
	
	private SQLiteDatabase database;
	
	public DBHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.database=db;
		String sql = "create table part(name varchar(100))";
		String sql1 = "create table symptom(part varchar(100),symptom varchar(100))";
		String sql2 = "create table disease(symptom varchar(100),name varchar(50),ratio varchar(30),info varchar(200),treat varchar(200))";
		String sql3 = "create table medicine(medicine varchar(100),info varchar(100))";
		String sql4 = "create table url(info varchar(100),url varchar(100))";	
		database.execSQL(sql);
		database.execSQL(sql1);
		database.execSQL(sql2);
		database.execSQL(sql3);
		database.execSQL(sql4);
		String[] a= new String[8];
		a[0] = "head";
		a[1] = "body";
		a[2] = "nick";
		a[3] = "chest";
		a[4] = "stomach";
		a[5] = "pelvis";
		a[6] = "limb";
		a[7] = "back";
		for(int i=0;i<a.length;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", a[i]);
			database.insert("part", null, contentValues);
		}
		String[] b = new String[3];
		b[0] = "headache";
		b[1] = "stomache";
		b[2] = "eyehurt";
		for(int i=0;i<3;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("part", a[i]);
			contentValues.put("symptom", b[i]);
			database.insert("symptom", null, contentValues);
		}
		String[] c = new String[3];
		c[0]="a";
		c[1]="b";
		c[2]="c";
		String[] d = new String[3];
		d[0]="28.0";
		d[1]="64.0";
		d[2]="2.0";
		String[] e = new String[3];
		e[0]="some reason";
		e[1]="other reason";
		e[2]="another reason";
		String[] f = new String[3];
		f[0]="water";
		f[1]="suger";
		f[2]="apple";
		for(int i=0;i<3;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("symptom", b[i]);
			contentValues.put("name", c[i]);
			contentValues.put("ratio", d[i]);
			contentValues.put("info", e[i]);
			contentValues.put("treat", f[i]);
			database.insert("disease", null, contentValues);
		}
		String a1 = "someinfo";
		String b1 = "https://www.baidu.com";
		ContentValues contentValues1 = new ContentValues();
		contentValues1.put("Info", a1);
		contentValues1.put("url", b1);
		database.insert("url", null, contentValues1);
		
		
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void insert(ContentValues values){
		SQLiteDatabase database = getWritableDatabase();
			database.insert("medicine", null, values);
			database.close();
	}
	
	
	public Cursor query(int number) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("part", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("part", null, null,null, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query1(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("symptom", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("symptom", null, "part=?", new String[]{x}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query2(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("disease", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("disease", null, "symptom=?", new String[]{x}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query3(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("disease", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("disease", null, "name=?", new String[]{x}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query4(int number) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("medicine", null, null,null, null, null, null);
			
		}
		return cursor;
	}
	
	public Cursor query5(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("medicine", null, "medicine=?",new String[]{x}, null, null, null);
			
		}
		return cursor;
	}
	
	public Cursor query6(int number) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("url", null, null,null, null, null, null);
			
		}
		return cursor;
	}
	
	public void delete(String name){
		if(database==null)
		{
			database=getWritableDatabase();
			database.delete("medicine", "medicine=?", new String[]{name});
			
		}
	
	}
	
	

}
