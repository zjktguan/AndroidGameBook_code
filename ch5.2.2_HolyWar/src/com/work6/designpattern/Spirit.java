/* ±¾ÊéÍøÕ¾£ºhttp://www.androidbks.com
* ÖÇ½ÝiOS¿ÎÌÃ£ºhttp://www.51work6.com
* ÖÇ½ÝiOS¿ÎÌÃÔÚÏß¿ÎÌÃ£ºhttp://v.51work6.com
* ÖÇ½ÝiOS¿ÎÌÃÐÂÀËÎ¢²©£ºhttp://weibo.com/u/3215753973
* ×÷ÕßÎ¢²©£ºhttp://weibo.com/516inc
* ¹Ù·½csdn²©¿Í£ºhttp://blog.csdn.net/tonny_guan
* QQ£º1575716557 ÓÊÏä£ºjylong06@163.com
*/ 

package com.work6.designpattern;

import java.lang.reflect.Method;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * ÓÎÏ·ÒýÇæ¾«ÁéÀà¡£
 * @author ¹Ø¶«Éý
 *
 */
public class Spirit {

	private final static String TAG = "Spirit";

	private Bitmap _bitmap;
	private Coordinates _coordinates;
	private Speed _speed;
	private String _callBackMethod;

	public Spirit(Bitmap bitmap, int xCoordinates, int yCoordinates,
			int xSpeed, int ySpeed, String callBackMethod) {
		_bitmap = bitmap;
		_coordinates = new Coordinates();
		_speed = new Speed();
		_coordinates.setX(xCoordinates);
		_coordinates.setY(yCoordinates);
		_speed.setX(xSpeed);
		_speed.setY(ySpeed);
		_callBackMethod = callBackMethod;
	}

	/**
	 * ´¥ÃþÊÂ¼þ
	 */
	public boolean onTouchEvent(View superView, MotionEvent event) {

		//Log.v(TAG, "´¥Ãþ¾«Áé...");
		if (_callBackMethod == null || "".equals(_callBackMethod)) {
			return false;
		}
		try {
			Class<? extends View> clazz = superView.getClass();
			Method m = clazz.getDeclaredMethod(_callBackMethod, Spirit.class,
					MotionEvent.class);
			m.invoke(superView, this, event);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Bitmap getImage() {
		return _bitmap;
	}

	public Speed getSpeed() {
		return _speed;
	}

	public Coordinates getCoordinates() {
		return _coordinates;
	}

	public class Speed {

		public static final int X_DIRECTION_RIGHT = 1;
		public static final int X_DIRECTION_LEFT = -1;
		public static final int Y_DIRECTION_DOWN = 1;
		public static final int Y_DIRECTION_UP = -1;

		private int _x = 1;
		private int _y = 1;

		private int _xDirection = X_DIRECTION_RIGHT;
		private int _yDirection = Y_DIRECTION_DOWN;

		/**
		 * @return the _xDirection
		 */
		public int getXDirection() {
			return _xDirection;
		}

		/**
		 * @param direction
		 *            the _xDirection to set
		 */
		public void setXDirection(int direction) {
			_xDirection = direction;
		}

		public void toggleXDirection() {
			if (_xDirection == X_DIRECTION_RIGHT) {
				_xDirection = X_DIRECTION_LEFT;
			} else {
				_xDirection = X_DIRECTION_RIGHT;
			}
		}

		/**
		 * @return the _yDirection
		 */
		public int getYDirection() {
			return _yDirection;
		}

		/**
		 * @param direction
		 *            the _yDirection to set
		 */
		public void setYDirection(int direction) {
			_yDirection = direction;
		}

		public void toggleYDirection() {
			if (_yDirection == Y_DIRECTION_DOWN) {
				_yDirection = Y_DIRECTION_UP;
			} else {
				_yDirection = Y_DIRECTION_DOWN;
			}
		}

		/**
		 * @return the _x
		 */
		public int getX() {
			return _x;
		}

		/**
		 * @param speed
		 *            the _x to set
		 */
		public void setX(int speed) {
			_x = speed;
		}

		/**
		 * @return the _y
		 */
		public int getY() {
			return _y;
		}

		/**
		 * @param speed
		 *            the _y to set
		 */
		public void setY(int speed) {
			_y = speed;
		}

		public String toString() {
			String xDirection;
			if (_xDirection == X_DIRECTION_RIGHT) {
				xDirection = "right";
			} else {
				xDirection = "left";
			}
			return "Speed: x: " + _x + " | y: " + _y + " | xDirection: "
					+ xDirection;
		}
	}

	/**
	 * Contains the coordinates of the graphic.
	 */
	public class Coordinates {
		private int _x = 0;
		private int _y = 0;

		public int getX() {
			return _x;
		}

		public void setX(int value) {
			_x = value;
		}

		public int getY() {
			return _y;
		}

		public void setY(int value) {
			_y = value;
		}

		public String toString() {
			return "Coordinates: (" + _x + "/" + _y + ")";
		}
	}
}
