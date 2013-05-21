/* ������վ��http://www.androidbks.com
* �ǽ�iOS���ã�http://www.51work6.com
* �ǽ�iOS�������߿��ã�http://v.51work6.com
* �ǽ�iOS��������΢����http://weibo.com/u/3215753973
* ����΢����http://weibo.com/516inc
* �ٷ�csdn���ͣ�http://blog.csdn.net/tonny_guan
* QQ��1575716557 ���䣺jylong06@163.com
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

		// ���� ��ʼ��
		Spirit bgSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.castle_bg), 0, 0, 0, 0, null);
		addSpirit(bgSpirit);

		this.spiritsName = spirits;
		this.spiritsImage = AppUtil.getBuidingImgId(this.spiritsName);
		// 1#�ؿ�
		Spirit spirit1 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[0]), 225, 26, 0, 0,
				"onTouchEvent1");
		addSpirit(spirit1);

		// 2#�ؿ�
		Spirit spirit2 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[1]), 300, 30, 0, 0,
				"onTouchEvent2");
		addSpirit(spirit2);

		// 3#�ؿ�
		Spirit spirit3 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[2]), 379, 103, 0, 0,
				"onTouchEvent3");
		addSpirit(spirit3);

		// 4#�ؿ�
		Spirit spirit4 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[3]), 149, 45, 0, 0,
				"onTouchEvent4");
		addSpirit(spirit4);

		// 5#�ؿ�
		Spirit spirit5 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[4]), 91, 80, 0, 0, "onTouchEvent5");
		addSpirit(spirit5);

		// 6#�ؿ�
		Spirit spirit6 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[5]), 40, 120, 0, 0,
				"onTouchEvent6");
		addSpirit(spirit6);

		// 7#�ؿ�
		Spirit spirit7 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[6]), 212, 85, 0, 0,
				"onTouchEvent7");
		addSpirit(spirit7);

		// 8#�ؿ�
		Spirit spirit8 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[7]), 163, 128, 0, 0,
				"onTouchEvent8");
		addSpirit(spirit8);

		// 9#�ؿ�
		Spirit spirit9 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[8]), 100, 150, 0, 0,
				"onTouchEvent9");
		addSpirit(spirit9);

		// 10#�ؿ�
		Spirit spirit10 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[9]), 300, 130, 0, 0,
				"onTouchEvent10");
		addSpirit(spirit10);

		// 11#�ؿ�
		Spirit spirit11 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[10]), 246, 175, 0, 0,
				"onTouchEvent11");
		addSpirit(spirit11);

		// 12#�ؿ�
		Spirit spirit12 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[11]), 180, 220, 0, 0,
				"onTouchEvent12");
		addSpirit(spirit12);

		// 13#�ؿ�
		Spirit spirit13 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[12]), 364, 171, 0, 0,
				"onTouchEvent13");
		addSpirit(spirit13);

		// 14#�ؿ�
		Spirit spirit14 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[13]), 304, 220, 0, 0,
				"onTouchEvent14");
		addSpirit(spirit14);

		// 15#�ؿ�
		Spirit spirit15 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[14]), 240, 265, 0, 0,
				"onTouchEvent15");
		addSpirit(spirit15);

		// 16#�ؿ�
		Spirit spirit16 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[15]), 400, 230, 0, 0,
				"onTouchEvent16");
		addSpirit(spirit16);

		// 17#�ؿ�
		Spirit spirit17 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[16]), 45, 10, 0, 0,
				"onTouchEvent17");
		addSpirit(spirit17);

		// 18#�ؿ�
		Spirit spirit18 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[17]), 49, 178, 0, 0,
				"onTouchEvent18");
		addSpirit(spirit18);

		// 19#�ؿ�
		Spirit spirit19 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[18]), 100, 215, 0, 0,
				"onTouchEvent19");
		addSpirit(spirit19);

		// 20#�ؿ�
		Spirit spirit20 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[19]), 196, 278, 0, 0,
				"onTouchEvent20");
		addSpirit(spirit20);

	}

	/**
	 * ����1#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent1(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����1#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����2#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent2(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����2#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����3#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent3(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����3#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����4#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent4(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����4#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����5#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent5(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����5#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����6#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent6(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����6#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����7#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent7(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����7#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����8#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent8(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����8#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����9#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent9(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����9#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����10#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent10(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����10#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����11#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent11(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����11#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����12#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent12(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����12#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����13#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent13(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����13#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����14#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent14(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����14#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����15#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent15(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����15#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����16#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent16(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����16#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����17#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent17(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����17#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����18#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent18(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����18#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����19#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent19(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����19#�ؿ� " + sp.getCoordinates());
	}

	/**
	 * ����20#�ؿ�
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent20(Spirit sp, MotionEvent event) {
		Log.v(TAG, "����20#�ؿ� " + sp.getCoordinates());
	}

}