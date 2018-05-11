package com.example.idoctor;
import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class receiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,Reminder.class),0); 
		Notification notification = new Notification.Builder(context).setSmallIcon(R.drawable.ic_launcher).setTicker("New message!").setContentTitle("Attention!").setContentText("time up!").setContentIntent(pendingIntent).setNumber(1).getNotification();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); 
		notificationManager.notify(1,notification);
	}

}
