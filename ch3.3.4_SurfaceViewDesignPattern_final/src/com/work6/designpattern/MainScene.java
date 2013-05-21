/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.work6.designpattern;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;

public class MainScene extends Scene {

	private final static String TAG = "MainScene";
	
	public MainScene(Context context) {
		super(context);

		// 背景
		Spirit bgSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.castle_bg), 0, 0, 0, 0, null);
		addSpirit(bgSpirit);

		// 宗正殿
		Spirit junjichuSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.temple), 300, 30, 0, 0,
				"onTouchEventJunjichuSpirit");
		addSpirit(junjichuSpirit);

		// 运动的精灵
		Spirit runSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.master), 0, 160, 2, 0,
				"onTouchEventRunSpirit");
		addSpirit(runSpirit);
	}
	

	void onTouchEventJunjichuSpirit(Spirit sp, MotionEvent event) {
		Log.v(TAG, "X=" + event.getX() + " Y=" + event.getY());
		Log.v(TAG, "onTouchEventJunjichuSpirit ... sp " + sp.getCoordinates());
	}

	void onTouchEventRunSpirit(Spirit sp, MotionEvent event) {
		Log.v(TAG, "X=" + event.getX() + " Y=" + event.getY());
		Log.v(TAG, "onTouchEventRunSpirit ... sp " + sp.getCoordinates());
	}

}
