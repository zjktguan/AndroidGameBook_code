/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.work6.designpattern;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Scene extends SurfaceView implements SurfaceHolder.Callback {

	private final static String TAG = "Scene";

	private SurfaceHolder holder;
	private Director mDirector;

	private ArrayList<Spirit> spirits = new ArrayList<Spirit>();

	public Scene(Context context) {
		super(context);
		// 创建一个新的SurfaceHolder
		holder = getHolder();
		holder.addCallback(this);

		// 背景
		Spirit bgSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.chengbao));
		bgSpirit.getSpeed().setX(0);
		bgSpirit.getSpeed().setY(0);
		bgSpirit.getCoordinates().setX(0);
		bgSpirit.getCoordinates().setY(0);
		spirits.add(bgSpirit);

		// 宗正殿
		Spirit junjichuSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.zhongzhengdianimg));
		spirits.add(junjichuSpirit);
		junjichuSpirit.getSpeed().setX(0);
		junjichuSpirit.getSpeed().setY(0);
		junjichuSpirit.getCoordinates().setX(300);
		junjichuSpirit.getCoordinates().setY(30);

		// 运动的精灵
		Spirit runSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.chengzhuimgbtn));
		runSpirit.getSpeed().setX(2);
		junjichuSpirit.getSpeed().setY(0);
		runSpirit.getCoordinates().setX(0);
		runSpirit.getCoordinates().setY(160);
		spirits.add(runSpirit);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return false;
		}

		synchronized (holder) {

			float X = event.getX();
			float Y = event.getY();
			// 宗正殿
			Spirit junjichuSpirit = spirits.get(1);
			// 触摸宗正殿
			if ((X >= junjichuSpirit.getCoordinates().getX() && X <= (junjichuSpirit
					.getCoordinates().getX() + junjichuSpirit.getImage()
					.getWidth()))
					&& (Y >= junjichuSpirit.getCoordinates().getY() && Y <= (junjichuSpirit
							.getCoordinates().getY() + junjichuSpirit
							.getImage().getHeight()))) {
				Log.v(TAG, "触摸 宗正殿...");
			}

			// 运动的精灵
			Spirit runSpirit = spirits.get(2);
			// 触摸运动的精灵
			if ((X >= runSpirit.getCoordinates().getX() && X <= (runSpirit
					.getCoordinates().getX() + runSpirit.getImage().getWidth()))
					&& (Y >= runSpirit.getCoordinates().getY() && Y <= (runSpirit
							.getCoordinates().getY() + runSpirit.getImage()
							.getHeight()))) {
				Log.v(TAG, "触摸 运动的精灵...");
			}

			return true;
		}
	}

	@Override
	public void onDraw(Canvas canvas) {

		for (Spirit graphic : spirits) {

			Spirit.Coordinates coord;
			Spirit.Speed speed;

			coord = graphic.getCoordinates();
			speed = graphic.getSpeed();

			// Direction
			if (speed.getXDirection() == Spirit.Speed.X_DIRECTION_RIGHT) {
				coord.setX(coord.getX() + speed.getX());
			} else {
				coord.setX(coord.getX() - speed.getX());
			}
			if (speed.getYDirection() == Spirit.Speed.Y_DIRECTION_DOWN) {
				coord.setY(coord.getY() + speed.getY());
			} else {
				coord.setY(coord.getY() - speed.getY());
			}

			// borders for x...
			if (coord.getX() < 0) {
				speed.toggleXDirection();
				coord.setX(-coord.getX());
			} else if (coord.getX() + graphic.getImage().getWidth() > getWidth()) {
				speed.toggleXDirection();
				coord.setX(coord.getX() + getWidth()
						- (coord.getX() + graphic.getImage().getWidth()));
			}

			// borders for y...
			if (coord.getY() < 0) {
				speed.toggleYDirection();
				coord.setY(-coord.getY());
			} else if (coord.getY() + graphic.getImage().getHeight() > getHeight()) {
				speed.toggleYDirection();
				coord.setY(coord.getY() + getHeight()
						- (coord.getY() + graphic.getImage().getHeight()));
			}

			Bitmap bitmap = graphic.getImage();

			canvas.drawBitmap(bitmap, coord.getX(), coord.getY(), null);
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// 启动线程
		mDirector = new Director();
		mDirector.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 停在线程
		if (mDirector != null) {
			mDirector.requestExitAndWait();
			mDirector = null;
		}
	}

	class Director extends Thread {

		private boolean done;

		Director() {
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
					// 回调MySurfaceView的onDraw方法
					onDraw(canvas);
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
