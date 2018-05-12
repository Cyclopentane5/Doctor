package com.example.idoctor;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Addmedecine extends Activity{
	EditText editText1,editText2;
	String a,b;
	Button button;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmedicine);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				a = editText1.getText().toString().trim();
				b = editText2.getText().toString().trim();
				// TODO Auto-generated method stub
				ContentValues contentValues = new ContentValues();
				contentValues.put("medicine", a);
				contentValues.put("info", b);
				DBHelper dbHelper7 = new DBHelper(getApplicationContext());
				dbHelper7.insert(contentValues);
				Intent intent = new Intent(Addmedecine.this,MainActivity.class);
				Addmedecine.this.startActivity(intent);
			}
		});
	}
}
