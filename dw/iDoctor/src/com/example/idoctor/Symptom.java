package com.example.idoctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

public class Symptom extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	Button button1;
	ListView listview1;
	String x;
	String[] a = new String[20];
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.symptom);
		Intent intent  = getIntent();
		x = intent.getStringExtra("name");
		button1 = (Button) findViewById(R.id.button1);
		DBHelper dbHelper1 = new DBHelper(getApplicationContext());
		Cursor cursor1 = dbHelper1.query1(2,x);
		int i=0;
		while(cursor1.moveToNext())
		{
			String symptom = cursor1.getString(cursor1.getColumnIndex("symptom"));
			addList(symptom);
			a[i]=symptom;
			i++;
		}
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Symptom.this,List.class);
				Symptom.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		listview1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.symptom1,new String[]{"symptom"},new int[]{R.id.textView1});
		listview1.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent  = new Intent(Symptom.this,Disease.class);
				int i = (int)id;
				intent.putExtra("name", x);
				intent .putExtra("symptom", a[i]);
				Symptom.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
		};
		listview1.setOnItemClickListener(itemClickListener);
	}
	private void addList(Object a){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("symptom",a);
		list1.add(aMap);
	}
}
