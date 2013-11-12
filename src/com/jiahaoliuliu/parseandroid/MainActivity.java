package com.jiahaoliuliu.parseandroid;

import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "sg01PjAzn8mLxvQSerlm6fFtJ8bAq8AgZe58emP5", "IQlsc3R2e30ynjawQ6bZsZgfsbJwbqO9BbkLyHle");
		ParseAnalytics.trackAppOpened(getIntent());
		
		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		
		Intent receivedIntent = getIntent();
		String messageReceived = receivedIntent.getStringExtra(JsonReceiver.MESSAGE_ACTION);
		if (messageReceived != null && !messageReceived.equals("")) {
			TextView messageTextView = (TextView)findViewById(R.id.messageTextView);
			messageTextView.setText(messageReceived);
		}
	}

}
