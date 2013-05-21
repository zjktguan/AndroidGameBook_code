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
import android.widget.Button;
import android.widget.TextView;

/**
 * 宗派挑战画面Activity
 * 
 * @author 关东升
 * 
 */
public class ChallengeActivity extends MenuAppActivity {

	// 仙山名字
	private String strSpiritMountainName;
	// 仙山占据
	private String strSpiritMountainOccupy;
	// 占据时间
	private String strOccupyTime;
	// 挑战还是防守标志
	private String flag;
	// 操作请求动作代码
	private int actioncode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.faction02);

		Bundle bundle = this.getIntent().getExtras();
		strSpiritMountainName = bundle.getString("spirit_mountain_name");

		try {

			JSONArray jsonarr = new JSONArray();
			jsonarr.put(0, strSpiritMountainName);

			JSONObject param = new JSONObject();
			param.put("actioncode", 22);
			param.put("data", jsonarr);

			this.actioncode = 22;

			requestURL(param.toString(), "spirit_mountain.php");

		} catch (JSONException e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
			release();
		}

	}

	@Override
	protected void handleResult(String jsonStr) {

		if (this.actioncode == 22) {
			try {
				JSONObject json = new JSONObject(jsonStr);
				if (json != null) {
					JSONArray jsonarray = json.getJSONArray("challenge_info");
					strSpiritMountainOccupy = jsonarray.getString(0);
					strOccupyTime = jsonarray.getString(1);
					flag = jsonarray.getString(2);
				}
			} catch (JSONException e) {
				strSpiritMountainOccupy = "";
				strOccupyTime = "0";
				flag = "";
			}
			release();
			init();
		}
	}

	/**
	 * 初始化画面
	 */
	private void init() {

		TextView txtSpiritMountainName = (TextView) findViewById(R.id.faction02_spirit_mountain_name);
		txtSpiritMountainName.setText(strSpiritMountainName);

		TextView txtSpiritMountainOccupy = (TextView) findViewById(R.id.faction02_spirit_mountain_occupy);
		txtSpiritMountainOccupy.setText(strSpiritMountainOccupy);
		TextView txtfactionOccupyTime = (TextView) findViewById(R.id.faction02_occupy_time);
		txtfactionOccupyTime.setText(strOccupyTime);

		Button btnChallenge = (Button) findViewById(R.id.faction02_btn_challenge);

		if (flag.equals("btn_defend")) {
			btnChallenge.setText("防守据点");
		} else if (flag.equals("btn_challenge")) {
			btnChallenge.setText("挑战据点");
		} else {
			btnChallenge.setVisibility(View.GONE);
		}

		// +++++++++++++++++点击挑战据点按钮 start ++++++++++++++++++
		btnChallenge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(ChallengeActivity.this,
						GrandCouncilActivity.class);
				it.putExtra("sendarmy", 2);
				startActivity(it);
			}
		});
		// +++++++++++++++++点击挑战据点按钮 end ++++++++++++++++++
	}
}
