/* 掛抎厙桴ㄩhttp://www.androidbks.com
* 秷豎iOS諺斻ㄩhttp://www.51work6.com
* 秷豎iOS諺斻婓盄諺斻ㄩhttp://v.51work6.com
* 秷豎iOS諺斻陔檢峚痔ㄩhttp://weibo.com/u/3215753973
* 釬氪峚痔ㄩhttp://weibo.com/516inc
* 夥源csdn痔諦ㄩhttp://blog.csdn.net/tonny_guan
* QQㄩ1575716557 蚘眊ㄩjylong06@163.com
*/ 

package com.god.holywar;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;

import com.work6.designpattern.Scene;
import com.work6.designpattern.Spirit;

public class HolyWarScene extends Scene {

	private final static String TAG = "HolyWarScene";
	private int[] spiritsImage;
	private String[] spiritsName;

	public HolyWarScene(Context context, String[] spirits) {
		super(context);

		// 掖劓 場宎趙
		Spirit bgSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.castle_bg), 0, 0, 0, 0, null);
		addSpirit(bgSpirit);

		this.spiritsName = spirits;
		this.spiritsImage = AppUtil.getBuidingImgId(this.spiritsName);
		// 1#華輸
		Spirit spirit1 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[0]), 225, 26, 0, 0,
				"onTouchEvent1");
		addSpirit(spirit1);

		// 2#華輸
		Spirit spirit2 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[1]), 300, 30, 0, 0,
				"onTouchEvent2");
		addSpirit(spirit2);

		// 3#華輸
		Spirit spirit3 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[2]), 379, 103, 0, 0,
				"onTouchEvent3");
		addSpirit(spirit3);

		// 4#華輸
		Spirit spirit4 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[3]), 149, 45, 0, 0,
				"onTouchEvent4");
		addSpirit(spirit4);

		// 5#華輸
		Spirit spirit5 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[4]), 91, 80, 0, 0, "onTouchEvent5");
		addSpirit(spirit5);

		// 6#華輸
		Spirit spirit6 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[5]), 40, 120, 0, 0,
				"onTouchEvent6");
		addSpirit(spirit6);

		// 7#華輸
		Spirit spirit7 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[6]), 212, 85, 0, 0,
				"onTouchEvent7");
		addSpirit(spirit7);

		// 8#華輸
		Spirit spirit8 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[7]), 163, 128, 0, 0,
				"onTouchEvent8");
		addSpirit(spirit8);

		// 9#華輸
		Spirit spirit9 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[8]), 100, 150, 0, 0,
				"onTouchEvent9");
		addSpirit(spirit9);

		// 10#華輸
		Spirit spirit10 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[9]), 300, 130, 0, 0,
				"onTouchEvent10");
		addSpirit(spirit10);

		// 11#華輸
		Spirit spirit11 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[10]), 246, 175, 0, 0,
				"onTouchEvent11");
		addSpirit(spirit11);

		// 12#華輸
		Spirit spirit12 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[11]), 180, 220, 0, 0,
				"onTouchEvent12");
		addSpirit(spirit12);

		// 13#華輸
		Spirit spirit13 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[12]), 364, 171, 0, 0,
				"onTouchEvent13");
		addSpirit(spirit13);

		// 14#華輸
		Spirit spirit14 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[13]), 304, 220, 0, 0,
				"onTouchEvent14");
		addSpirit(spirit14);

		// 15#華輸
		Spirit spirit15 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[14]), 240, 265, 0, 0,
				"onTouchEvent15");
		addSpirit(spirit15);

		// 16#華輸
		Spirit spirit16 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[15]), 400, 230, 0, 0,
				"onTouchEvent16");
		addSpirit(spirit16);

		// 17#華輸
		Spirit spirit17 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[16]), 45, 10, 0, 0,
				"onTouchEvent17");
		addSpirit(spirit17);

		// 18#華輸
		Spirit spirit18 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[17]), 49, 178, 0, 0,
				"onTouchEvent18");
		addSpirit(spirit18);

		// 19#華輸
		Spirit spirit19 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[18]), 100, 215, 0, 0,
				"onTouchEvent19");
		addSpirit(spirit19);

		// 20#華輸
		Spirit spirit20 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[19]), 196, 278, 0, 0,
				"onTouchEvent20");
		addSpirit(spirit20);

	}

	/**
	 * 揖類1#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent1(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類1#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類2#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent2(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類2#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類3#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent3(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類3#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類4#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent4(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類4#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類5#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent5(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類5#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類6#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent6(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類6#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類7#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent7(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類7#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類8#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent8(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類8#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類9#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent9(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類9#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類10#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent10(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類10#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類11#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent11(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類11#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類12#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent12(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類12#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類13#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent13(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類13#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類14#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent14(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類14#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類15#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent15(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類15#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類16#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent16(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類16#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類17#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent17(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類17#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類18#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent18(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類18#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類19#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent19(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類19#華輸 " + sp.getCoordinates());
	}

	/**
	 * 揖類20#華輸
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent20(Spirit sp, MotionEvent event) {
		Log.v(TAG, "揖類20#華輸 " + sp.getCoordinates());
	}

}
