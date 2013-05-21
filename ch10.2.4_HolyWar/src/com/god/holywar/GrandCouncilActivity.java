/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

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

			// ++++++++++点击升级对话框的升级按钮 start+++++++++
			// 点击升级对话框的升级按钮
			Button btnUpdate = (Button) actionvBarView
					.findViewById(R.id.action_bar_custom_btnUpdate);
			btnUpdate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					LayoutInflater factory = LayoutInflater
							.from(GrandCouncilActivity.this);
					View textEntryView = factory.inflate(R.layout.upgrade01,
							null);

					// dialog里的数据设置
					TextView mucai = (TextView) textEntryView
							.findViewById(R.id.upgrade01_wood);
					mucai.setText(aryUpgradeStuff[0]);
					TextView liangshi = (TextView) textEntryView
							.findViewById(R.id.upgrade01_food);
					liangshi.setText(aryUpgradeStuff[1]);
					TextView xuantie = (TextView) textEntryView
							.findViewById(R.id.upgrade01_ironOre);
					xuantie.setText(aryUpgradeStuff[2]);
					TextView renkou = (TextView) textEntryView
							.findViewById(R.id.upgrade01_population);
					renkou.setText(aryUpgradeStuff[3]);
					TextView shijian = (TextView) textEntryView
							.findViewById(R.id.upgrade01_time);
					shijian.setText(aryUpgradeStuff[4]);

					AlertDialog dlg = new AlertDialog.Builder(
							GrandCouncilActivity.this)
							.setTitle("军机处  " + strGrandCouncilLevel + "  级")
							.setIcon(R.drawable.grand_council)
							.setView(textEntryView)
							.setPositiveButton(R.string.upgrade,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											try {

												JSONArray jsonarr = new JSONArray();
												jsonarr.put(0, soilid);

												JSONObject param = new JSONObject();
												param.put("verifycode",
														AppUtil.verifycode);
												param.put("actioncode", 15);
												param.put("data", jsonarr);
												GrandCouncilActivity.this.actioncode = 15;
												requestURL(param.toString(),
														"castle.php");

											} catch (JSONException e) {
												e.printStackTrace();
												AppUtil.button1Dialog(
														GrandCouncilActivity.this,
														"解码JSON字符串失败！");
												release();
											}
										}
									})
							.setNegativeButton(R.string.close,
									new DialogInterface.OnClickListener() {

										// @Override
										public void onClick(
												DialogInterface dialog,
												int which) {

										}
									}).create();
					dlg.show();
				}
			});
			// ++++++++++点击升级对话框的升级按钮 end+++++++++++

		} else if (this.actioncode == 24) { // 点击侦查按钮的后的回调
			String scout_back_flag = "44";
			try {
				JSONObject json = new JSONObject(jsonStr);
				scout_back_flag = json.getString("scout_back_flag");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			release();

			if (scout_back_flag.equals("1")) {
				Toast.makeText(GrandCouncilActivity.this, "侦察兵正在侦察...",
						Toast.LENGTH_SHORT).show();
			} else if (scout_back_flag.equals("44")) {
				Toast.makeText(GrandCouncilActivity.this, "网络连接失败！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(GrandCouncilActivity.this, "侦察失败，请重新侦察！",
						Toast.LENGTH_SHORT).show();
			}
		} else if (this.actioncode == 25) {// 点击运输按钮回调
			String transport_back_flag = "44";
			try {
				JSONObject json = new JSONObject(jsonStr);
				transport_back_flag = json.getString("transport_back_flag");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			release();

			if (transport_back_flag.equals("1")) {
				Toast.makeText(GrandCouncilActivity.this, "运输兵正在运输...",
						Toast.LENGTH_SHORT).show();
			} else if (transport_back_flag.equals("44")) {
				Toast.makeText(GrandCouncilActivity.this, "网络连接失败！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(GrandCouncilActivity.this, "运输失败，请重新运输！",
						Toast.LENGTH_SHORT).show();
			}
		} else if (this.actioncode == 24) { // 点击侦查按钮的后的回调
			String scout_back_flag = "44";
			try {
				JSONObject json = new JSONObject(jsonStr);
				scout_back_flag = json.getString("scout_back_flag");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			release();

			if (scout_back_flag.equals("1")) {
				Toast.makeText(GrandCouncilActivity.this, "侦察兵正在侦察...",
						Toast.LENGTH_SHORT).show();
			} else if (scout_back_flag.equals("44")) {
				Toast.makeText(GrandCouncilActivity.this, "网络连接失败！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(GrandCouncilActivity.this, "侦察失败，请重新侦察！",
						Toast.LENGTH_SHORT).show();
			}
		} else if (this.actioncode == 26) {// 发兵按钮回调

			String send_soldier_back_flag = "44";
			try {
				JSONObject json = new JSONObject(jsonStr);
				send_soldier_back_flag = json
						.getString("send_soldier_back_flag");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			release();

			if (send_soldier_back_flag.equals("1")) {
				Toast.makeText(GrandCouncilActivity.this, "发兵成功...",
						Toast.LENGTH_SHORT).show();
			} else if (send_soldier_back_flag.equals("44")) {
				Toast.makeText(GrandCouncilActivity.this, "网络连接失败！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(GrandCouncilActivity.this, "发兵失败，请重新发兵！",
						Toast.LENGTH_SHORT).show();
			}
		}
		// ++++++++++点击升级对话框的升级按钮 start+++++++++
		else if (this.actioncode == 15) {// 点击升级按钮回调

			String buildingUpgrade = "44";
			try {
				JSONObject json = new JSONObject(jsonStr);
				buildingUpgrade = json.getString("upgrade_back_flag");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			release();

			if (buildingUpgrade.equals("1")) {
				Toast.makeText(GrandCouncilActivity.this, "升级中...",
						Toast.LENGTH_SHORT).show();
			} else if (buildingUpgrade.equals("44")) {
				Toast.makeText(GrandCouncilActivity.this, "网络连接失败！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(GrandCouncilActivity.this, "材料不足，不能升级",
						Toast.LENGTH_SHORT).show();
			}
		}
		// ++++++++++点击升级对话框的升级按钮 end+++++++++
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

			// ++++++++++++++++ 点击侦查按钮 start ++++++++++++++++
			Button btnScout = (Button) totalInfoView
					.findViewById(R.id.grand_council01_btn_scout);
			// 总览画面点击侦查按钮和
			btnScout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						EditText txtDispatchScoutAmount = (EditText) totalInfoView
								.findViewById(R.id.grand_council01_dispatch_scout_amount);
						EditText otherchengbangname = (EditText) totalInfoView
								.findViewById(R.id.grand_council01_other_castle_name);

						JSONArray jsonarr = new JSONArray();
						jsonarr.put(0, txtDispatchScoutAmount.getText()
								.toString());
						jsonarr.put(1, otherchengbangname.getText().toString());

						JSONObject param = new JSONObject();
						param.put("verifycode", AppUtil.verifycode);
						param.put("actioncode", 24);
						param.put("data", jsonarr);

						GrandCouncilActivity.this.actioncode = 24;

						requestURL(param.toString(), "grand_council.php");

					} catch (JSONException e) {
						e.printStackTrace();
						AppUtil.button1Dialog(GrandCouncilActivity.this,
								"解码JSON字符串失败！");
						release();
					}
				}

			});

			// ++++++++++++++++点击侦查按钮 end ++++++++++++++++

			// ++++++++++++++++点击运输按钮 start ++++++++++++++++
			Button btnTransport = (Button) totalInfoView
					.findViewById(R.id.grand_council01_btn_transport);
			// 总览画面点击运输按钮
			btnTransport.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					try {

						EditText txtWoodTransportCount = (EditText) totalInfoView
								.findViewById(R.id.grand_council01_wood_transport_count);
						EditText txtFoodTransportCount = (EditText) totalInfoView
								.findViewById(R.id.grand_council01_food_transport_count);
						EditText txtIronOreTransportCount = (EditText) totalInfoView
								.findViewById(R.id.grand_council01_IronOre_transport_count);

						JSONArray jsonarr = new JSONArray();

						jsonarr.put(0, txtDispatchTransportCount.getText()
								.toString());
						jsonarr.put(1, txtWoodTransportCount.getText()
								.toString());
						jsonarr.put(2, txtFoodTransportCount.getText()
								.toString());
						jsonarr.put(3, txtIronOreTransportCount.getText()
								.toString());
						jsonarr.put(4, selectGrandCouncil);

						JSONObject param = new JSONObject();
						param.put("verifycode", AppUtil.verifycode);
						param.put("actioncode", 25);
						param.put("data", jsonarr);
						GrandCouncilActivity.this.actioncode = 25;

						requestURL(param.toString(), "grand_council.php");

					} catch (JSONException e) {
						e.printStackTrace();
						AppUtil.button1Dialog(GrandCouncilActivity.this,
								"解码JSON字符串失败！");
						release();
					}
				}
			});
			// ++++++++++++++++点击运输按钮 end ++++++++++++++++

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

			LayoutInflater factory = LayoutInflater
					.from(GrandCouncilActivity.this);
			final View dispatchArmyView = factory.inflate(
					R.layout.grand_council03, null);

			LinearLayout hidvisaa = (LinearLayout) dispatchArmyView
					.findViewById(R.id.grand_council03_hidvisaa);
			LinearLayout hidvisbb = (LinearLayout) dispatchArmyView
					.findViewById(R.id.grand_council03_hidvisbb);
			LinearLayout hidviscc = (LinearLayout) dispatchArmyView
					.findViewById(R.id.grand_council03_hidviscc);
			LinearLayout hidvisdd = (LinearLayout) dispatchArmyView
					.findViewById(R.id.grand_council03_hidvisdd);

			final CheckBox ckbhero = (CheckBox) dispatchArmyView
					.findViewById(R.id.grand_council03_ckb_hero);

			// 0是没有英雄
			if (AppUtil.herolevel.equals("0")) {
				hidvisaa.setVisibility(View.VISIBLE);
				hidvisbb.setVisibility(View.GONE);
				hidviscc.setVisibility(View.GONE);
				hidvisdd.setVisibility(View.GONE);
			} else {
				hidvisaa.setVisibility(View.GONE);
				hidvisbb.setVisibility(View.VISIBLE);
				hidviscc.setVisibility(View.VISIBLE);
				hidvisdd.setVisibility(View.VISIBLE);
				ckbhero.setText(AppUtil.heroname);
			}

			// 军队已有总量的数量设置
			TextView bing01 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing01);
			TextView bing02 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing02);
			TextView bing03 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing03);
			TextView bing04 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing04);
			TextView bing05 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing05);
			TextView bing06 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing06);
			TextView bing07 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing07);
			TextView bing08 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing08);
			TextView bing09 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing09);
			TextView bing10 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing10);
			TextView bing11 = (TextView) dispatchArmyView
					.findViewById(R.id.grand_council03_bing11);
			bing01.setText(aryGrandCouncil[0]);
			bing02.setText(aryGrandCouncil[1]);
			bing03.setText(aryGrandCouncil[2]);
			bing04.setText(aryGrandCouncil[3]);
			bing05.setText(aryGrandCouncil[4]);
			bing06.setText(aryGrandCouncil[5]);
			bing07.setText(aryGrandCouncil[6]);
			bing08.setText(aryGrandCouncil[7]);
			bing09.setText(aryGrandCouncil[8]);
			bing10.setText(aryGrandCouncil[9]);
			bing11.setText(aryGrandCouncil[10]);

			// ++++++++++++++++++++ ++++++++++++++++++++
			// 获得主动技能
			final RadioGroup radiogroup01 = (RadioGroup) dispatchArmyView
					.findViewById(R.id.grand_council03_radiogroup01);
			radiogroup01
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							RadioButton rb = (RadioButton) radiogroup01
									.findViewById(checkedId);
							skill2 = rb.getText().toString();

						}
					});

			// 获得单选按钮的增援与发兵
			RadioGroup radiogroup02 = (RadioGroup) dispatchArmyView
					.findViewById(R.id.grand_council03_radiogroup02);
			radiogroup02
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							RadioButton rb = (RadioButton) dispatchArmyView
									.findViewById(checkedId);
							attack = rb.getText().toString();
						}
					});
			Button btnDispatchSoldier = (Button) dispatchArmyView
					.findViewById(R.id.grand_council03_dispatch_soldier);
			btnDispatchSoldier.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String herostate = "0";
					// 有英雄 而且选中
					if (AppUtil.herolevel.equals("1") && ckbhero.isChecked()) {
						herostate = "1";
					}
					TextView txtSkill2 = (TextView) findViewById(R.id.grand_council03_hero_skill2);
					String hero_skill2 = txtSkill2.getText().toString();

					// 获得发兵的兵种信息
					// 运输兵
					EditText txtTransportAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_transport_amount);
					// 侦察兵
					EditText txtScoutAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_scout_amount);
					// 民兵
					EditText txtMilitiaAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_militia_amount);
					// 枪兵
					EditText txtSpearmanAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_spearman_amount);
					// 刺客
					EditText txtAssassinAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_assassin_amount);
					// 骠骑
					EditText txtMilitaryGeneralAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_military_general_amount);
					// 神射手
					EditText txtMarksmanAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_marksman_amount);
					// 巫毒术士
					EditText txtVoodooManAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_voodoo_man_amount);
					// 武士
					EditText txtWarriorAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_warrior_amount);
					// 冲撞车
					EditText txtBatteringRramAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_battering_ram_amount);
					// 投石车
					EditText txtCatapultAmount = (EditText) dispatchArmyView
							.findViewById(R.id.grand_council03_catapult_amount);

					// 给服务器，出兵中发兵的兵种信息
					String sendsoldieramount[] = new String[11];
					sendsoldieramount[0] = txtTransportAmount.getText()
							.toString();
					sendsoldieramount[1] = txtScoutAmount.getText().toString();
					sendsoldieramount[2] = txtMilitiaAmount.getText()
							.toString();
					sendsoldieramount[3] = txtSpearmanAmount.getText()
							.toString();
					sendsoldieramount[4] = txtAssassinAmount.getText()
							.toString();
					sendsoldieramount[5] = txtMilitaryGeneralAmount.getText()
							.toString();
					sendsoldieramount[6] = txtMarksmanAmount.getText()
							.toString();
					sendsoldieramount[7] = txtVoodooManAmount.getText()
							.toString();
					sendsoldieramount[8] = txtWarriorAmount.getText()
							.toString();
					sendsoldieramount[9] = txtBatteringRramAmount.getText()
							.toString();
					sendsoldieramount[10] = txtCatapultAmount.getText()
							.toString();
					try {
						JSONObject json02 = new JSONObject();
						json02.put("herostate", herostate);
						JSONObject json03 = new JSONObject();
						json03.put("skill2", hero_skill2);
						JSONObject json04 = new JSONObject();
						json04.put("skill1", skill2);

						JSONArray ssa = new JSONArray();
						for (int i = 0; i < sendsoldieramount.length; i++) {
							ssa.put(i, sendsoldieramount[i]);
						}
						JSONObject json05 = new JSONObject();
						json05.put("sendsoldieramount", ssa);

						JSONObject json06 = new JSONObject();
						json06.put("reinforcedefend", attack);

						JSONObject json07 = new JSONObject();
						// 获得城镇的名称
						EditText grand_council03_city_name = (EditText) dispatchArmyView
								.findViewById(R.id.grand_council03_city_name);
						json07.put("attackplacename", grand_council03_city_name
								.getText().toString());

						JSONArray jsonarr = new JSONArray();
						jsonarr.put(0, json02);
						jsonarr.put(1, json03);
						jsonarr.put(2, json04);
						jsonarr.put(3, json05);
						jsonarr.put(4, json06);
						jsonarr.put(5, json07);

						JSONObject param = new JSONObject();
						param.put("verifycode", AppUtil.verifycode);
						param.put("actioncode", 26);
						param.put("data", jsonarr);
						GrandCouncilActivity.this.actioncode = 26;

						requestURL(param.toString(), "grand_council.php");

					} catch (JSONException e) {
						e.printStackTrace();
						AppUtil.button1Dialog(GrandCouncilActivity.this,
								"解码JSON字符串失败！");
						release();
					}

				}
			});

			return dispatchArmyView;
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

			LayoutInflater factory = LayoutInflater
					.from(GrandCouncilActivity.this);
			final View armyInfoView = factory.inflate(R.layout.grand_council04,
					null);

			// 军情数据设置
			ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < warnumber; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("warinfo", war[i][0]);
				map.put("hero_copy", war[i][1]);
				map.put("dispatch_army_amount", war[i][2]);
				map.put("enddate", war[i][3]);
				listData.add(map);
			}

			SimpleAdapter adapter = new SimpleAdapter(
					GrandCouncilActivity.this, listData,
					R.layout.grand_council05, new String[] { "warinfo",
							"hero_copy", "dispatch_army_amount", "enddate" },
					new int[] { R.id.grand_council05_textview1,
							R.id.grand_council05_textview2,
							R.id.grand_council05_textview3,
							R.id.grand_council05_textview4 });

			final ListView list1 = (ListView) armyInfoView
					.findViewById(R.id.grand_council04_listview);
			list1.setAdapter(adapter);

			return armyInfoView;
		}
	}

	@Override
	protected boolean showWindowTitle() {
		return true;
	}

}
