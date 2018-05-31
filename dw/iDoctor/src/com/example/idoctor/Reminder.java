package com.example.idoctor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Reminder extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	EditText editText;
	int a = 0;
	String x;
	Vibrator vibrator;
	ListView listView1;
	String[] b = new String[20];
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder);
		editText = (EditText) findViewById(R.id.editText1);
		
		
		
		DBHelper dbHelper4 = new DBHelper(getApplicationContext());
		Cursor cursor4 = dbHelper4.query4(1);
		int i=0;
		while(cursor4.moveToNext())
		{
			String midicine = cursor4.getString(cursor4.getColumnIndex("medicine"));
			String info = cursor4.getString(cursor4.getColumnIndex("info"));
			addList(midicine,info);
			b[i] = midicine;
			i++;
		}
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				x = editText.getText().toString().trim();
				try{
					a = Integer.parseInt(x);
				}
				catch (Exception e) {
					
				}
				if (a>0){
					Calendar calendar = Calendar.getInstance();
					int hour = calendar.get(Calendar.HOUR_OF_DAY);
					int minute = calendar.get(Calendar.MINUTE);
					startRemind(hour, minute+a);
					vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					long[] pattern = {a*1000*60,4000};
					vibrator.vibrate(pattern, -1);
					Toast.makeText(Reminder.this, "Success", Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(Reminder.this, "Please enter positive integer", Toast.LENGTH_SHORT).show();
				}
				// TODO Auto-generated method stub
				
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					stopRemind(0);
					vibrator.cancel();
					Toast.makeText(Reminder.this, "Cancel", Toast.LENGTH_SHORT).show();
				}
				catch (Exception e) {
					Toast.makeText(Reminder.this, "No alarm", Toast.LENGTH_SHORT).show();
				}
				
				// TODO Auto-generated method stub
				
			}
		});		
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Reminder.this,Addmedecine.class);
				Reminder.this.startActivity(intent);
					
				// TODO Auto-generated method stub
				
			}
		});
		
		listView1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.reminder1,new String[]{"name","ratio"},new int[]{R.id.textView1,R.id.textView2});
		listView1.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent  = new Intent(Reminder.this,Detail.class);
				int i = (int)id;
				intent.putExtra("medicine", b[i]);
				Reminder.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
		};
		listView1.setOnItemClickListener(itemClickListener);
		
	}
	
void startRemind(int hour,int minute){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		long systemTime = System.currentTimeMillis();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    long selectTime = calendar.getTimeInMillis();
	    if(systemTime > selectTime) {
	         calendar.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    Intent intent = new Intent(Reminder.this, receiver.class);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 0, intent,0);
	    AlarmManager alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}

	private void stopRemind(int i){
	 
    Intent intent = new Intent(Reminder.this, receiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 0, intent, i);
    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
    alarmManager.cancel(pendingIntent);
	}
	
	private void addList(Object a,Object b){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("name",a);
		aMap.put("ratio",b);
		list1.add(aMap);
	}
	
}
