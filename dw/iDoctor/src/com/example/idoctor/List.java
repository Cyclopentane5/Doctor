package com.example.idoctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.R.xml;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class List extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	String[] a = new String[8];
	Button button1;
	ListView listview1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		button1 = (Button) findViewById(R.id.button1);
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		int i=0;
		Cursor cursor = dbHelper.query(1);
		while(cursor.moveToNext())
		{
			String name = cursor.getString(cursor.getColumnIndex("name"));
			addList(name);
			a[i]=name;
			i++;
		}
		dbHelper.close();
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(List.this,MainActivity.class);
				List.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		listview1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.list1,new String[]{"name"},new int[]{R.id.textView1});
		listview1.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent  = new Intent(List.this,Symptom.class);
				int i = (int)id;
				String x = a[i];
				intent.putExtra("name", x);
				List.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
		};
		
		listview1.setOnItemClickListener(itemClickListener);
	}
	
	private void addList(Object a){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("name", a);
		list1.add(aMap);
	}
}
