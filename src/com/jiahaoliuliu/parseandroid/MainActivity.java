package com.jiahaoliuliu.parseandroid;

import android.media.RingtoneManager;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "sg01PjAzn8mLxvQSerlm6fFtJ8bAq8AgZe58emP5", "IQlsc3R2e30ynjawQ6bZsZgfsbJwbqO9BbkLyHle");
		ParseAnalytics.trackAppOpened(getIntent());

		ParseInstallation.getCurrentInstallation().saveInBackground();

		Intent intent = getIntent();
		Log.v(LOG_TAG, "Intent " + intent.toString());
	}

}
