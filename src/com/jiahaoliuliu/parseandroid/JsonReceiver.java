package com.jiahaoliuliu.parseandroid;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class JsonReceiver extends BroadcastReceiver {
	private static final String TAG = JsonReceiver.class.getSimpleName();
 
	public static final String MESSAGE_ACTION = "com.jiahaoliuliu.parseandroid.message";
  @Override
  public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      Log.v(TAG, "Action: " + action);

      String channel = intent.getExtras().getString("com.parse.Channel");
      Log.v(TAG, "Channel: " + channel);

      try {
		JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		String message = json.getString("message");
		Intent startMainActivityIntent = new Intent(context, MainActivity.class);
		startMainActivityIntent.putExtra(MESSAGE_ACTION, message);
		startMainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(startMainActivityIntent);
		Log.v(TAG, "JSON received: " + json.toString());
	} catch (JSONException e) {
		Log.e(TAG, "Error parsing the json object ", e);
	}
  }
}