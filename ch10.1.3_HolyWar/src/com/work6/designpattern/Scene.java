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
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 游戏引擎的场景类，本质是一个View。
 * @author 关东升
 *
 */
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
	}

	public void addSpirit(Spirit sp) {
		spirits.add(sp);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return false;
		}

		float X = event.getX();
		float Y = event.getY();
		// TODO 根据x和y的坐标判断触摸了那个精灵
		// Log.v(TAG, "X=" + X + " Y=" + Y);

		synchronized (holder) {
			for (Spirit sp : spirits) {

				Spirit.Coordinates coord;
				coord = sp.getCoordinates();

				// 判断是否触摸了某个精灵
				if ((X >= coord.getX() && X <= (coord.getX() + sp.getImage()
						.getWidth()))
						&& (Y >= coord.getY() && Y <= (coord.getY() + sp
								.getImage().getHeight()))) {
					sp.onTouchEvent(this, event);
				}

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
		// 停止线程
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
				//Android 4.03之后进入背景状态canvas为null
				if (canvas == null) {
					requestExitAndWait();
				}
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
