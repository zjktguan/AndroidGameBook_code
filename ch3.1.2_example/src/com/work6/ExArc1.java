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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class ExArc1 extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	private class MyView extends View {
		public MyView(Context context) {
			super(context);
		}

		
		@Override
		public void onDraw(Canvas canvas) {
			
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.WHITE);
			
			RectF oval1 = new RectF(20.0f, 20.0f, 60.0f, 60.0f);
			canvas.drawRect(oval1, paint);
			
			paint.setColor(Color.RED);
			canvas.drawArc(oval1, 0, 360, true, paint);
			
			RectF oval2 = new RectF(180.0f, 20.0f, 220.0f, 60.0f);
			canvas.drawArc(oval2, 90, 135, true, paint);

			RectF oval3 = new RectF(260.0f, 20.0f, 300.0f, 60.0f);
			canvas.drawArc(oval3, 90, 135, false, paint);
		}
	}
}
