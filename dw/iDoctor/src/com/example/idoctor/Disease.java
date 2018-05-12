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

public class Disease extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	
	ListView listView1;
	String x;
	String a;
	String[] b = new String[20];
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disease);
		Intent intent = getIntent();
		x = intent.getStringExtra("name");
		a = intent.getStringExtra("symptom");
		DBHelper dbHelper2 = new DBHelper(getApplicationContext());
		Cursor cursor2 = dbHelper2.query2(2,a);
		int i=0;
		while(cursor2.moveToNext())
		{
			String name = cursor2.getString(cursor2.getColumnIndex("name"));
			String ratio = cursor2.getString(cursor2.getColumnIndex("ratio"));
			addList(name,ratio);
			b[i] = name;
			i++;
		}
		
		listView1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.disease1,new String[]{"name","ratio"},new int[]{R.id.textView1,R.id.textView2});
		listView1.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent  = new Intent(Disease.this,Info.class);
				int i = (int)id;
				intent.putExtra("name", x);
				intent.putExtra("symptom", a);
				intent .putExtra("disease", b[i]);
				Disease.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
		};
		listView1.setOnItemClickListener(itemClickListener);
		
		
}



private void addList(Object a,Object b){
	Map<String,Object> aMap = new HashMap<String, Object>();
	aMap.put("name",a);
	aMap.put("ratio",b);
	list1.add(aMap);
}
}
