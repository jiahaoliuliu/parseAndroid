package com.jiahaoliuliu.parseandroid;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class JsonReceiver extends BroadcastReceiver {
	
	//Sample JSON data
	/*
	{
	    "action": "com.jiahaoliuliu.parseandroid.json",
	    "message": "New message from the parse server",
	    "url": "http://www.google.com"
	}*/

    private static final String LOG_TAG = JsonReceiver.class.getSimpleName();
    private static final String ALERT_KEY = "message";
    private static final String URL_KEY = "url";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

            // Alert Message
            String alertMessage = "";
            if (json.has(ALERT_KEY)) {
            	alertMessage = json.getString(ALERT_KEY);
            	Log.v(LOG_TAG, "Alert message: " + alertMessage);
            }

            // Abs Url
            String absUrl = "";
            if (json.has(URL_KEY)) {
            	absUrl = json.getString(URL_KEY);
            	Log.v(LOG_TAG, "Abs Url: " + absUrl);
            }

    		NotificationManager nm = (NotificationManager)        
   	             context.getSystemService(Context.NOTIFICATION_SERVICE);
			Intent delayedIntent = new Intent(context, MainActivity.class);
			if (!absUrl.equals("")) {
				delayedIntent = new Intent(Intent.ACTION_VIEW);
				delayedIntent.setData(Uri.parse(absUrl));
				Log.v(LOG_TAG, "Delayed intent contains the follow url: " + absUrl);
			}

           PendingIntent i=PendingIntent.getActivity(context, 0, delayedIntent,0);
           NotificationCompat.Builder mBuilder =
   	    	    new NotificationCompat.Builder(context);

   	    	mBuilder.setSmallIcon(R.drawable.ic_launcher)
   	            .setTicker("New push message arrived")
   	            .setWhen(System.currentTimeMillis())
   	            .setContentTitle(alertMessage)
   	            .setContentText(absUrl)
   	            .setContentIntent(i)
   	            .setDefaults(Notification.DEFAULT_SOUND |
   	            		Notification.DEFAULT_VIBRATE)
   	            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
   	            .setVibrate(new long[] {1000, 1000, 1000, 1000})
   	            .setAutoCancel(true);
           nm.notify(1, mBuilder.build());

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing the json object ", e);
        }
    }
}