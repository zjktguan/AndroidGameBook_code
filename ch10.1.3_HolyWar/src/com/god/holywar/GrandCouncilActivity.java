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
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

			ActionBar.Tab totalInfoTab = actionBar
					.newTab()
					.setText("总揽")
					.setTabListener(
							new TabListener(new TotalInfoTabContentFragment()));
			ActionBar.Tab dispatchArmyTab = actionBar
					.newTab()
					.setText("出兵")
					.setTabListener(
							new TabListener(
									new DispatchArmyTabContentFragment()));
			ActionBar.Tab armyInfoTab = actionBar
					.newTab()
					.setText("军情")
					.setTabListener(
							new TabListener(new ArmyInfoTabContentFragment()));
			actionBar.addTab(totalInfoTab);
			actionBar.addTab(dispatchArmyTab);
			actionBar.addTab(armyInfoTab);

			if (sendarmy == 2) {// 战争雷达页面
				actionBar.selectTab(dispatchArmyTab);// 设定出兵Tab选中
			} else {
				actionBar.selectTab(totalInfoTab);// 设定总揽 Tab选中
			}
		}
	}

	/**
	 * ActionBar标签监听
	 * 
	 * @author 关东升
	 * 
	 */
	private class TabListener implements ActionBar.TabListener {
		private Fragment mFragment;

		public TabListener(Fragment fragment) {
			mFragment = fragment;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.fragment_content, mFragment);
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(mFragment);
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}

	/**
	 * 总揽 Fragment
	 * 
	 * @author 关东升
	 * 
	 */
	private class TotalInfoTabContentFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			LayoutInflater factory = LayoutInflater
					.from(GrandCouncilActivity.this);
			final View totalInfoView = factory.inflate(
					R.layout.grand_council02, null);
			// 军队数量设置
			TextView txtTransportAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_transport_amount);
			TextView txtScoutAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_scout_amount);
			TextView txtMilitiaAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_militia_amount);
			TextView txtSpearmanAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_spearman_amount);
			TextView txtAssassinAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_assassin_amount);
			TextView txtMilitaryGeneralAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_military_general_amount);
			TextView txtMarksmanAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_marksman_amount);
			TextView txtVoodoomanAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_voodoo_man_amount);
			TextView txtWarriorAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_warrior_amount);
			TextView txtBatteringRamAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_battering_ram_amount);
			TextView txtCatapultAmount = (TextView) totalInfoView
					.findViewById(R.id.grand_council01_catapult_amount);

			txtTransportAmount.setText(aryGrandCouncil[0]);
			txtScoutAmount.setText(aryGrandCouncil[1]);
			txtMilitiaAmount.setText(aryGrandCouncil[2]);
			txtSpearmanAmount.setText(aryGrandCouncil[3]);
			txtAssassinAmount.setText(aryGrandCouncil[4]);
			txtMilitaryGeneralAmount.setText(aryGrandCouncil[5]);
			txtMarksmanAmount.setText(aryGrandCouncil[6]);
			txtVoodoomanAmount.setText(aryGrandCouncil[7]);
			txtWarriorAmount.setText(aryGrandCouncil[8]);
			txtBatteringRamAmount.setText(aryGrandCouncil[9]);
			txtCatapultAmount.setText(aryGrandCouncil[10]);

			// 军饷消耗设置
			TextView txtSoldierPayAmount = (TextView) totalInfoView
					.findViewById(R.id.txtSoldierPayAmount);
			// 军饷设置
			txtSoldierPayAmount.setText(strSoldierPayAmount);

			// 运输兵可运输资源数量的设置
			final EditText txtDispatchTransportCount = (EditText) totalInfoView
					.findViewById(R.id.grand_council01_dispatch_scout_amount);
			txtDispatchTransportCount.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if (event.getAction() == KeyEvent.ACTION_UP) {
						String num1 = txtDispatchTransportCount.getText()
								.toString();
						Long num = 0L;
						try {
							num = 6000 * Long.parseLong(num1);
						} catch (Exception e) {
						}
						String numlast = String.valueOf(num);
						TextView keyunshuziyuanzongliangshuliang = (TextView) totalInfoView
								.findViewById(R.id.grand_council01_transport_total_count);
						keyunshuziyuanzongliangshuliang.setText(numlast);
					}
					return false;
				}
			});

			// 自己的城堡spinner
			Spinner spinner = (Spinner) totalInfoView
					.findViewById(R.id.grand_council01_spinner_castle_name);
			spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> apv, View view,
						int pos, long id) {
					selectGrandCouncil = aryCastleList[pos];
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});

			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
					GrandCouncilActivity.this,
					android.R.layout.simple_spinner_item, aryCastleList);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter1);

			return totalInfoView;
		}
	}

	/**
	 * 出兵设置 Fragment
	 * 
	 * @author 关东升
	 * 
	 */
	private class DispatchArmyTabContentFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return null;
		}
	}

	/**
	 * 军情 Fragment
	 * 
	 * @author 关东升
	 * 
	 */
	private class ArmyInfoTabContentFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return null;
		}
	}
	
	@Override
	protected boolean showWindowTitle() {
		return true;
	}


}
