/* ±¾ÊéÍøÕ¾£ºhttp://www.androidbks.com
* ÖÇ½İiOS¿ÎÌÃ£ºhttp://www.51work6.com
* ÖÇ½İiOS¿ÎÌÃÔÚÏß¿ÎÌÃ£ºhttp://v.51work6.com
* ÖÇ½İiOS¿ÎÌÃĞÂÀËÎ¢²©£ºhttp://weibo.com/u/3215753973
* ×÷ÕßÎ¢²©£ºhttp://weibo.com/516inc
* ¹Ù·½csdn²©¿Í£ºhttp://blog.csdn.net/tonny_guan
* QQ£º1575716557 ÓÊÏä£ºjylong06@163.com
*/ 

package com.work6;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ExViewTouchEvent extends Activity {

	private static final String TAG = "ExViewTouchEvent";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	private class MyView extends View {
		public MyView(Context context) {
			super(context);
			setFocusable(true);
			setLongClickable(true);
		}

		@Override
		public void onDraw(Canvas canvas) {

			Paint paint = new Paint();
			Bitmap pic = BitmapFactory.decodeResource(getResources(),
					R.drawable.cat);
			canvas.drawBitmap(pic, 10, 10, paint);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			Log.v(TAG, "onTouchEvent Action = " + event.getAction());
			return super.onTouchEvent(event);
		}
	}
}