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
import org.json.JSONObject;

import android.os.Bundle;

/**
 * 城堡初始化画面Activity，也是游戏中最主要的部分。
 * 
 * @author 关东升
 * 
 */
public class HolyWarActivity extends MenuAppActivity {

	// 建筑物名称
	private String[] aryBuildingName;
	// 游戏场景
	private HolyWarScene myScene = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化建筑物名称数组，共有20个块地
		aryBuildingName = new String[20];
		for (int i = 0; i < 20; i++) {
			aryBuildingName[i] = "space";
		}
		try {
			// 编码JSON，发送JSON字符串
			JSONObject param = new JSONObject();
			param.put("verifycode", AppUtil.verifycode);
			param.put("actioncode", 12);
			HolyWarActivity.this.requestURL(param.toString(), "castle.php");
		} catch (Exception e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "编码JSON字符串失败！");
		}
	}

	@Override
	protected void handleResult(String jsonStr) {
		try {
			// 解码JSON
			JSONObject json = new JSONObject(jsonStr);
			if (json != null) {
				JSONArray jsonResponse = json.getJSONArray("initdata");
				for (int i = 0; i < jsonResponse.length(); i++) {
					aryBuildingName[i] = jsonResponse.getString(i).toString();
				}
			}
			// 把自定义的SurfaceView添加到Activity内容视图
			myScene = new HolyWarScene(this, aryBuildingName);
			setContentView(myScene);

		} catch (Exception e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
		}
		release();

	}

}
