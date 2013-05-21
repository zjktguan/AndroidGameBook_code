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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {

	private final static String TAG = "MySurfaceView";

	private SurfaceHolder holder;
	private MySurfaceViewThread mySurfaceViewThread;

	public MySurfaceView(Context context) {
		super(context);
		// 创建一个新的SurfaceHolder
		holder = getHolder();
		holder.addCallback(this);
		setFocusableInTouchMode(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return false;
		}

		synchronized (holder) {
			float X = event.getX();
			float Y = event.getY();
			// TODO 根据x和y的坐标判断触摸了那个精灵
			Log.v(TAG, "X=" + X + " Y=" + Y);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		synchronized (holder) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				Log.v(TAG, "向上移动精灵");
				//TODO 自己编码
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				Log.v(TAG, "向下移动精灵");
				//TODO 自己编码
				break;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// 启动线程
		mySurfaceViewThread = new MySurfaceViewThread();
		mySurfaceViewThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 停在线程
		if (mySurfaceViewThread != null) {
			mySurfaceViewThread.requestExitAndWait();
			mySurfaceViewThread = null;
		}
	}

	class MySurfaceViewThread extends Thread {

		private boolean done;

		MySurfaceViewThread() {
			super();
			done = false;
		}

		@Override
		public void run() {
			SurfaceHolder surfaceHolder = holder;
			// 反复循环执行线程
			while (!done) {
				// 锁定surface并返回canvas用于绘制2D图形
				Canvas canvas = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					// TODO: Draw on the canvas! 绘制图形
					Bitmap gbImage = BitmapFactory.decodeResource(
							getResources(), R.drawable.castle_bg);// 背景图
					canvas.drawBitmap(gbImage, 0, 0, null);
				}
				// 解锁画布，渲染当前图像
				surfaceHolder.unlockCanvasAndPost(canvas);
				try {
					Thread.sleep(1000 / 60);// 60帧率
				} catch (InterruptedException e) {
				}
			}

		}

		public void requestExitAndWait() {
			// 完成线程并合并子线程到主线程
			done = true;
			try {
				join();
			} catch (InterruptedException ex) {
			}
		}

	}

}
