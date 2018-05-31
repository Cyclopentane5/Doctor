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
	String[] a = new String[9];
	ListView listview1;
	Object[] img = new Object[9];
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		img[0] = R.drawable.head;
		img[1] = R.drawable.body;
		img[2] = R.drawable.throat;
		img[3] = R.drawable.chest;
		img[4] = R.drawable.stomach;
		img[5] = R.drawable.pelvis;
		img[6] = R.drawable.limb;
		img[7] = R.drawable.back;
		img[8] = R.drawable.skin;
		
		
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		int i=0;
		Cursor cursor = dbHelper.query(1);
		while(cursor.moveToNext())
		{
			String name = cursor.getString(cursor.getColumnIndex("name"));
			addList(name,i);
			a[i]=name;
			i++;
		}
		dbHelper.close();
		
		listview1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.list1,new String[]{"img"},new int[]{R.id.img});
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
	
	private void addList(Object a,int i){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("img", img[i]);
		list1.add(aMap);
	}
}
