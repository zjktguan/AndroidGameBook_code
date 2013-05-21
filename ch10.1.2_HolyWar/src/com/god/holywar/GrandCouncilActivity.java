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

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;

/**
 * 军机处Activity
 * 
 * @author 关东升 si92@sina.com
 * 
 * 
 */
public class GrandCouncilActivity extends MenuAppActivity {

	// 来自服务器，各种军队的数量
	private String[] aryGrandCouncil = new String[11];
	// 来自服务器，军饷消耗数量
	private String strSoldierPayAmount;
	// 来自服务器，自己的城堡名字列表
	private String[] aryCastleList;
	// 来自服务器，战斗数量
	private int warnumber;
	// 来自服务器，战斗的具体文字信息
	private String[][] war;

	// 来自服务器，军机处级别
	private String strGrandCouncilLevel;
	// 来自服务器，军机处升下一级所需要的材料
	private String[] aryUpgradeStuff = new String[5];
	// 保存地的编号
	private int soilid;
	// 保存来自哪个页面的跳转变量
	private int sendarmy;

	// 操作请求动作代码
	private int actioncode;

	// ++++++++++ and ++++++++++++
	// 选择的城堡名字
	private String selectGrandCouncil;
	// 英雄主动技能
	private String skill2 = "不使用主动技能";
	// 攻击或是增援
	private String attack = "攻击";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.building01);

		Bundle bundle = this.getIntent().getExtras();
		soilid = bundle.getInt("soilid");
		sendarmy = bundle.getInt("sendarmy");

		try {

			JSONObject param = new JSONObject();
			param.put("verifycode", AppUtil.verifycode);
			param.put("actioncode", 23);
			this.actioncode = 23;

			requestURL(param.toString(), "grand_council.php");

		} catch (JSONException e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
			release();
		}
	}

	@Override
	protected void handleResult(String jsonStr) {

		if (this.actioncode == 23) {
			try {
				JSONObject json = new JSONObject(jsonStr);

				JSONArray jsonResponse2 = json.getJSONArray("total_info");
				for (int i = 0; i < jsonResponse2.length(); i++) {
					JSONObject jsonResponse22 = jsonResponse2.getJSONObject(i);
					JSONArray jsonResponse221 = jsonResponse22
							.getJSONArray("army_amount");
					for (int j = 0; j < jsonResponse221.length(); j++) {
						aryGrandCouncil[j] = jsonResponse221.getString(j);
					}
					JSONArray jsonResponse222 = jsonResponse22
							.getJSONArray("soldier_pay_amount");
					strSoldierPayAmount = jsonResponse222.getString(0);
					JSONArray jsonResponse223 = jsonResponse22
							.getJSONArray("self_castle_list");
					aryCastleList = new String[jsonResponse223.length()];
					for (int j = 0; j < jsonResponse223.length(); j++) {
						aryCastleList[j] = jsonResponse223.getString(j);
					}
				}

				JSONObject jsonResponse4 = json.getJSONObject("warsituating");
				warnumber = jsonResponse4.getInt("warnumber");
				JSONArray jsonResponse5 = jsonResponse4.getJSONArray("war");
				war = new String[warnumber][4];
				for (int i = 0; i < warnumber; i++) {
					JSONObject warin = jsonResponse5.getJSONObject(i);
					JSONArray warnw = warin.getJSONArray("warin");
					for (int j = 0; j < warnw.length(); j++) {
						war[i][j] = warnw.getString(j);
					}
				}
				// 军机处升级信息
				strGrandCouncilLevel = json.getString("building_level");
				JSONArray jsonResponse1 = json
						.getJSONArray("building_upgrade_info");
				for (int i = 0; i < jsonResponse1.length(); i++) {
					aryUpgradeStuff[i] = jsonResponse1.getString(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			release();

			final ActionBar actionBar = getActionBar();
			View actionvBarView = getLayoutInflater().inflate(
					R.layout.action_bar_custom, null);
			actionBar.setCustomView(actionvBarView);
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
					| ActionBar.DISPLAY_SHOW_TITLE
					| ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setTitle("军机处  " + strGrandCouncilLevel + "  级");
			actionBar.setLogo(R.drawable.grand_council);

			// TODO
		}
	}
	
	@Override
	protected boolean showWindowTitle() {
		return true;
	}

}
