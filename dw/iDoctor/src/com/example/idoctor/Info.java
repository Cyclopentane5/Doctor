package com.example.idoctor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Info extends Activity{
	String x;
	String a;
	String b;
	Button button1;
	TextView textView1,textView2,textView3;
	String name,info,treat;
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		Intent intent = getIntent();
		x = intent.getStringExtra("name");
		a = intent.getStringExtra("symptom");
		b = intent.getStringExtra("disease");
		DBHelper dbHelper3 = new DBHelper(getApplicationContext());
		Cursor cursor3 = dbHelper3.query3(2,b);
		while(cursor3.moveToNext())
		{
			name = cursor3.getString(cursor3.getColumnIndex("name"));
			info = cursor3.getString(cursor3.getColumnIndex("info"));
			treat = cursor3.getString(cursor3.getColumnIndex("treat"));
			
		}
		textView1.setText(name);
		textView2.setText(info);
		textView3.setText(treat);
		button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Info.this,Disease.class);
				intent.putExtra("name", x);
				intent.putExtra("symptom", a);
				Info.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
}
