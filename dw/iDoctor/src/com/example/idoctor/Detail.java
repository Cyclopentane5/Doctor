package com.example.idoctor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends Activity{
	TextView textView1,textView2;
	Button button;
	String x;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		Intent intent = getIntent();
		x = intent.getStringExtra("medicine");
		
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		DBHelper dbHelper5 = new DBHelper(getApplicationContext());
		Cursor cursor5 = dbHelper5.query5(1,x);
		while(cursor5.moveToNext())
		{
			String midicine = cursor5.getString(cursor5.getColumnIndex("medicine"));
			String info = cursor5.getString(cursor5.getColumnIndex("info"));
			textView1.setText(midicine);
			textView2.setText(info);
		}
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHelper dbHelper6 = new DBHelper(getApplicationContext());
				dbHelper6.delete(x);
				Intent intent = new Intent(Detail.this,MainActivity.class);
				Detail.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}

}
