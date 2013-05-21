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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * 宗派业务模块Activity
 * 
 * @author 关东升
 * 
 */
public class FactionActivity extends MenuAppActivity {

	private String arySpiritMountain[] = new String[9];
	// 操作请求动作代码
	private int actioncode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		try {

			JSONObject param = new JSONObject();
			param.put("verifycode", AppUtil.verifycode);
			param.put("actioncode", 21);
			this.actioncode = 21;

			requestURL(param.toString(), "spirit_mountain.php");

		} catch (JSONException e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
			release();
		}

	}

	@Override
	protected void handleResult(String jsonStr) {

		if (this.actioncode == 21) {
			try {
				JSONObject json = new JSONObject(jsonStr);
				if (json != null) {
					JSONArray jsonarray = json.getJSONArray("info");
					for (int i = 0; i < jsonarray.length(); i++) {
						arySpiritMountain[i] = jsonarray.getString(i)
								.toString();
					}
				}
			} catch (JSONException e) {
				for (int i = 0; i < 9; i++) {
					arySpiritMountain[i] = "";
				}
			}
			release();
		}

		init();
	}

	/**
	 * 画面初始化
	 */
	private void init() {

		// TODO 画面初始化
	}

}
