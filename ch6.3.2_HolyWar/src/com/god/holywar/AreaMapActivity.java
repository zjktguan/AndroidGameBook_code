/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * 战争地图业务Activity
 * 
 * @author 关东升
 * 
 */
public class AreaMapActivity extends MenuAppActivity {

	private int rs[][] = new int[7][7];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.area_map);
		requestData(4, "00");

	}

	/**
	 * 请求服务器端数据方法
	 * @param actioncode 请求动作代码
	 * @param orientation 移动的方向
	 */
	private void requestData(int actioncode, String orientation) {

		try {

			JSONObject param = new JSONObject();
			JSONArray jarray = new JSONArray();
			jarray.put(0, orientation);

			param.put("verifycode", AppUtil.verifycode);
			param.put("actioncode", actioncode);
			param.put("data", jarray);

			requestURL(param.toString(), "areamap.php");

		} catch (JSONException e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
			release();
		}
	}

	/**
	 * 加载数据，设置屏幕精灵的显示
	 */
	private void load() {
		int num = R.id.area_map_ImageView11;
		for (int i = 0; i < 7; i++) {
			if (i >= 1) {
				num = num + 7;
			}
			for (int j = 0; j < 7; j++) {
				ImageView img = (ImageView) findViewById(num + j);
				switch (rs[i][j]) {
				case 1:
					img.setBackgroundResource(R.drawable.a);
					break;
				case 2:
					img.setBackgroundResource(R.drawable.b);
					break;
				case 3:
					img.setBackgroundResource(R.drawable.c);
					break;
				case 4:
					img.setBackgroundResource(R.drawable.d);
					break;
				case 5:
					img.setBackgroundResource(R.drawable.e);
					break;
				case 6:
					img.setBackgroundResource(R.drawable.f);
					break;
				case 7:
					img.setBackgroundResource(R.drawable.g);
					break;
				case 8:
					img.setBackgroundResource(R.drawable.h);
					break;
				case 9:
					img.setBackgroundResource(R.drawable.i);
					break;
				case 10:
					img.setBackgroundResource(R.drawable.j);
					break;
				case 11:
					img.setBackgroundResource(R.drawable.k);
					break;
				case 12:
					img.setBackgroundResource(R.drawable.mc);
					break;
				}
			}
		}
	}

	@Override
	protected void handleResult(String jsonStr) {

		try {
			JSONObject json = new JSONObject(jsonStr);
			if (json != null) {
				JSONArray jsonResponse = json.getJSONArray("aroundinfo");
				for (int i = 0; i < 7; i++) {
					JSONObject rec = jsonResponse.getJSONObject(i);
					JSONArray jarrayname = rec.getJSONArray("info");
					for (int j = 0; j < 7; j++) {
						rs[i][j] = jarrayname.getInt(j);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
		}
		release();
		load();
	}

}
