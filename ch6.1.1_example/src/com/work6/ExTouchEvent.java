/* ±æ ÈÕ¯’æ£∫http://www.androidbks.com
* ÷«Ω›iOSøŒÃ√£∫http://www.51work6.com
* ÷«Ω›iOSøŒÃ√‘⁄œﬂøŒÃ√£∫http://v.51work6.com
* ÷«Ω›iOSøŒÃ√–¬¿ÀŒ¢≤©£∫http://weibo.com/u/3215753973
* ◊˜’ﬂŒ¢≤©£∫http://weibo.com/516inc
* πŸ∑Ωcsdn≤©øÕ£∫http://blog.csdn.net/tonny_guan
* QQ£∫1575716557 ” œ‰£∫jylong06@163.com
*/ 

package com.work6;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class ExTouchEvent extends Activity {

	private static final String TAG = "ExTouchEvent";
	private TextView mAction;
	private TextView mPostion;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mAction = (TextView) findViewById(R.id.action);
		mPostion = (TextView) findViewById(R.id.postion);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int Action = event.getAction();
		float X = event.getX();
		float Y = event.getY();
		Log.v(TAG, "Action = " + Action);
		Log.v(TAG, "(" + X + "," + Y + ")");
		mAction.setText("Action = " + Action);
		mPostion.setText("Postion = (" + X + "," + Y + ")");
		return true;
	}

}