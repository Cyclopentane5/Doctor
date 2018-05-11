package com.example.idoctor;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reminder extends Activity{
	EditText editText;
	int a = 1;
	Vibrator vibrator;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder);
		editText = (EditText) findViewById(R.id.editText1);
		String x = editText.getText().toString().trim();
		try{
			a = Integer.parseInt(x);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				startRemind(hour, minute+a);
				vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				long[] pattern = {a*1000*60,4000};
				vibrator.vibrate(pattern, -1);
				Toast.makeText(Reminder.this, "success", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopRemind(0);
				vibrator.cancel();
				Toast.makeText(Reminder.this, "Cancel", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});		
		
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
	
}
