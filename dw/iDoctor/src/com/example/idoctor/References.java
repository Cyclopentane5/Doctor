package com.example.idoctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class References extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	ListView listView1;
	String[] x = new String[20];
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.references);
		DBHelper dbHelper8 = new DBHelper(getApplicationContext());
		Cursor cursor8 = dbHelper8.query6(1);
		int i=0;
		while(cursor8.moveToNext())
		{
			String info = cursor8.getString(cursor8.getColumnIndex("info"));
			String url = cursor8.getString(cursor8.getColumnIndex("url"));
			addList(info);
			x[i]=url;
			i++;
		}
		listView1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.symptom1,new String[]{"name"},new int[]{R.id.textView1});
		listView1.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent  = new Intent(References.this,Net1.class);
				int i = (int)id;
				intent.putExtra("url", x[i]);
				References.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
		};
		listView1.setOnItemClickListener(itemClickListener);
	}
	
	private void addList(Object a){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("name",a);
		list1.add(aMap);
	}
}
