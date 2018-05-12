package com.example.idoctor;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class MainActivity extends ActivityGroup {
	
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		tabHost = (TabHost) findViewById(android.R.id.tabhost);  
		tabHost.setup(getLocalActivityManager());
		
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("selfdoctor" , getResources().getDrawable(R.drawable.ic_launcher)).setContent(new Intent(this, List.class)));  
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("network" , null).setContent(new Intent(this, Network.class)));  
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("reminder" , null).setContent(new Intent(this, Reminder.class)));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("reference" , null).setContent(new Intent(this, References.class)));
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
