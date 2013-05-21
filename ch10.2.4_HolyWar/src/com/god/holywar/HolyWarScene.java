/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.work6.designpattern.Scene;
import com.work6.designpattern.Spirit;

/**
 * 应用中的游戏场景类
 * 
 * @author 关东升
 * 
 */
public class HolyWarScene extends Scene {

	private final static String TAG = "HolyWarScene";
	private int[] spiritsImage;
	private String[] spiritsName;

	/** 接收消息 */
	private Handler handler = new MyHandler();

	/** 线程成员变量 */
	private ActivityThread thread;

	public HolyWarScene(Context context, String[] spirits) {
		super(context);

		// 背景 初始化
		Spirit bgSpirit = new Spirit(BitmapFactory.decodeResource(
				getResources(), R.drawable.castle_bg), 0, 0, 0, 0, null);
		addSpirit(bgSpirit);

		this.spiritsName = spirits;
		this.spiritsImage = AppUtil.getBuidingImgId(this.spiritsName);
		// 1#地块
		Spirit spirit1 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[0]), 225, 26, 0, 0,
				"onTouchEvent1");
		addSpirit(spirit1);

		// 2#地块
		Spirit spirit2 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[1]), 300, 30, 0, 0,
				"onTouchEvent2");
		addSpirit(spirit2);

		// 3#地块
		Spirit spirit3 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[2]), 379, 103, 0, 0,
				"onTouchEvent3");
		addSpirit(spirit3);

		// 4#地块
		Spirit spirit4 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[3]), 149, 45, 0, 0,
				"onTouchEvent4");
		addSpirit(spirit4);

		// 5#地块
		Spirit spirit5 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[4]), 91, 80, 0, 0, "onTouchEvent5");
		addSpirit(spirit5);

		// 6#地块
		Spirit spirit6 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[5]), 40, 120, 0, 0,
				"onTouchEvent6");
		addSpirit(spirit6);

		// 7#地块
		Spirit spirit7 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[6]), 212, 85, 0, 0,
				"onTouchEvent7");
		addSpirit(spirit7);

		// 8#地块
		Spirit spirit8 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[7]), 163, 128, 0, 0,
				"onTouchEvent8");
		addSpirit(spirit8);

		// 9#地块
		Spirit spirit9 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[8]), 100, 150, 0, 0,
				"onTouchEvent9");
		addSpirit(spirit9);

		// 10#地块
		Spirit spirit10 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[9]), 300, 130, 0, 0,
				"onTouchEvent10");
		addSpirit(spirit10);

		// 11#地块
		Spirit spirit11 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[10]), 246, 175, 0, 0,
				"onTouchEvent11");
		addSpirit(spirit11);

		// 12#地块
		Spirit spirit12 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[11]), 180, 220, 0, 0,
				"onTouchEvent12");
		addSpirit(spirit12);

		// 13#地块
		Spirit spirit13 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[12]), 364, 171, 0, 0,
				"onTouchEvent13");
		addSpirit(spirit13);

		// 14#地块
		Spirit spirit14 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[13]), 304, 220, 0, 0,
				"onTouchEvent14");
		addSpirit(spirit14);

		// 15#地块
		Spirit spirit15 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[14]), 240, 265, 0, 0,
				"onTouchEvent15");
		addSpirit(spirit15);

		// 16#地块
		Spirit spirit16 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[15]), 400, 230, 0, 0,
				"onTouchEvent16");
		addSpirit(spirit16);

		// 17#地块
		Spirit spirit17 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[16]), 45, 10, 0, 0,
				"onTouchEvent17");
		addSpirit(spirit17);

		// 18#地块
		Spirit spirit18 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[17]), 49, 178, 0, 0,
				"onTouchEvent18");
		addSpirit(spirit18);

		// 19#地块
		Spirit spirit19 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[18]), 100, 215, 0, 0,
				"onTouchEvent19");
		addSpirit(spirit19);

		// 20#地块
		Spirit spirit20 = new Spirit(BitmapFactory.decodeResource(
				getResources(), spiritsImage[19]), 196, 278, 0, 0,
				"onTouchEvent20");
		addSpirit(spirit20);

	}

	/**
	 * 触摸1#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent1(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸1#地块 " + sp.getCoordinates());
		handleSoilid(1);
	}

	/**
	 * 触摸2#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent2(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸2#地块 " + sp.getCoordinates());
		handleSoilid(2);
	}

	/**
	 * 触摸3#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent3(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸3#地块 " + sp.getCoordinates());
		handleSoilid(3);
	}

	/**
	 * 触摸4#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent4(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸4#地块 " + sp.getCoordinates());
		handleSoilid(4);
	}

	/**
	 * 触摸5#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent5(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸5#地块 " + sp.getCoordinates());
		handleSoilid(5);
	}

	/**
	 * 触摸6#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent6(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸6#地块 " + sp.getCoordinates());
		handleSoilid(6);
	}

	/**
	 * 触摸7#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent7(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸7#地块 " + sp.getCoordinates());
		handleSoilid(7);
	}

	/**
	 * 触摸8#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent8(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸8#地块 " + sp.getCoordinates());
		handleSoilid(8);
	}

	/**
	 * 触摸9#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent9(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸9#地块 " + sp.getCoordinates());
		handleSoilid(9);
	}

	/**
	 * 触摸10#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent10(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸10#地块 " + sp.getCoordinates());
		handleSoilid(10);
	}

	/**
	 * 触摸11#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent11(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸11#地块 " + sp.getCoordinates());
		handleSoilid(11);
	}

	/**
	 * 触摸12#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent12(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸12#地块 " + sp.getCoordinates());
		handleSoilid(12);
	}

	/**
	 * 触摸13#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent13(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸13#地块 " + sp.getCoordinates());
		handleSoilid(13);
	}

	/**
	 * 触摸14#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent14(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸14#地块 " + sp.getCoordinates());
		handleSoilid(14);
	}

	/**
	 * 触摸15#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent15(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸15#地块 " + sp.getCoordinates());
		handleSoilid(15);
	}

	/**
	 * 触摸16#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent16(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸16#地块 " + sp.getCoordinates());
		handleSoilid(16);
	}

	/**
	 * 触摸17#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent17(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸17#地块 " + sp.getCoordinates());
		handleSoilid(17);
	}

	/**
	 * 触摸18#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent18(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸18#地块 " + sp.getCoordinates());
		handleSoilid(18);
	}

	/**
	 * 触摸19#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent19(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸19#地块 " + sp.getCoordinates());
		handleSoilid(19);
	}

	/**
	 * 触摸20#地块
	 * 
	 * @param sp
	 * @param event
	 */
	public void onTouchEvent20(Spirit sp, MotionEvent event) {
		Log.v(TAG, "触摸20#地块 " + sp.getCoordinates());
		handleSoilid(20);
	}

	/**
	 * 处理对应地号的业务
	 * 
	 * @param soilid
	 *            地块编号
	 */
	private void handleSoilid(int soilid) {

		String name = spiritsName[soilid - 1];
		if (name.equals("space")) {
			Intent it = new Intent(this.getContext(), BuildingActivity.class);
			it.putExtra("soilid", soilid);
			this.getContext().startActivity(it);
			return;
		} else if (name.equals("grand_council")) {
			Intent it = new Intent(this.getContext(),
					GrandCouncilActivity.class);
			it.putExtra("sendarmy", 1);
			it.putExtra("soilid", soilid);
			this.getContext().startActivity(it);
			return;
		}

		JSONArray jsonarr = new JSONArray();
		try {
			jsonarr.put(0, soilid);
			JSONObject param = new JSONObject();
			param.put("actioncode", 14);
			param.put("verifycode", AppUtil.verifycode);
			param.put("data", jsonarr);
			thread = new ActivityThread(param.toString(), "castle.php", soilid,
					14);
			thread.start();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 弹出和处理空箭塔对话框
	 * 
	 * @param soilid
	 */
	private void handleSpaceLandTowerDialog(final int soilid) {

		LayoutInflater factory = LayoutInflater.from(this.getContext());
		View textEntryView = factory.inflate(R.layout.upgradelist, null);

		String namee[] = { "穿刺攻击形式，可以抵御小规模的部队", "普通攻击形式，可以抵御一定规模的地面部队" };

		ListView list = (ListView) textEntryView
				.findViewById(R.id.upgradelist_listview);

		ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 2; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("NAME", namee[i]);
			map.put("Upgrade", "升级");
			listData.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this.getContext(), listData,
				R.layout.upgrade02, new String[] { "NAME", "Upgrade" },
				new int[] { R.id.upgrade02_TextView01,
						R.id.upgrade02_TextView02 });
		list.setAdapter(adapter);

		AlertDialog dlg = new AlertDialog.Builder(this.getContext())
				.setTitle("箭塔炮台")
				.setIcon(R.drawable.land_tower_air_defense)
				.setView(textEntryView)
				.setNegativeButton(R.string.close,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).create();
		dlg.show();

		// 触摸ListView项目时候的事件处理。
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			private String name;
			private String wood;
			private String food;
			private String ironOre;
			private String population;
			private String time;

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int img = 0;

				if (position == 0) {
					img = R.drawable.land_tower_ground_defense;
					name = "穿刺攻击形式";
					wood = "1500";
					food = "500";
					ironOre = "20";
					population = "10";
					time = "00:01:00";
				}
				if (position == 1) {
					img = R.drawable.land_tower_air_defense;
					name = "普通攻击形式";
					wood = "2500";
					food = "800";
					ironOre = "30";
					population = "10";
					time = "00:01:00";
				}

				LayoutInflater factory2 = LayoutInflater.from(HolyWarScene.this
						.getContext());
				View textEntryView2 = factory2
						.inflate(R.layout.upgrade01, null);

				// dialog里的数据设置
				TextView txtWood = (TextView) textEntryView2
						.findViewById(R.id.upgrade01_wood);
				txtWood.setText(wood);
				TextView txtFood = (TextView) textEntryView2
						.findViewById(R.id.upgrade01_food);
				txtFood.setText(food);
				TextView txtIronOre = (TextView) textEntryView2
						.findViewById(R.id.upgrade01_ironOre);
				txtIronOre.setText(ironOre);
				TextView txtPopulation = (TextView) textEntryView2
						.findViewById(R.id.upgrade01_population);
				txtPopulation.setText(population);
				TextView txtTime = (TextView) textEntryView2
						.findViewById(R.id.upgrade01_time);
				txtTime.setText(time);

				AlertDialog dlg = new AlertDialog.Builder(HolyWarScene.this
						.getContext())
						.setTitle(name)
						.setIcon(img)
						.setView(textEntryView2)
						.setPositiveButton(R.string.upgrade,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										try {
											JSONArray jsonarr = new JSONArray();
											jsonarr.put(0, soilid);

											JSONObject param = new JSONObject();
											param.put("verifycode",
													AppUtil.verifycode);
											param.put("actioncode", 15);
											param.put("data", jsonarr);

											thread = new ActivityThread(param
													.toString(), "castle.php",
													soilid, 15);
											thread.start();
										} catch (JSONException e) {
											AppUtil.button1Dialog(
													HolyWarScene.this
															.getContext(),
													"数据传送错误！");
										}
									}
								})
						.setNegativeButton(R.string.close,
								new DialogInterface.OnClickListener() {

									// @Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).create();
				dlg.show();

			}
		});

	}

	/**
	 * 带有招兵建筑升级
	 * 
	 * @param jsonStr
	 * @param soilid
	 */
	private void handleRequestUpgradeRecruitSoldierInfoResult(String jsonStr,
			final int soilid) {
		try {
			String[] upgradInfo = new String[6];
			// 解码JSON
			JSONObject json = new JSONObject(jsonStr.toString());

			String building_level = json.getString("building_level");
			JSONArray jsonResponse = json.getJSONArray("upgrade_info");
			upgradInfo[1] = jsonResponse.getString(0);
			upgradInfo[2] = jsonResponse.getString(1);
			upgradInfo[3] = jsonResponse.getString(2);
			upgradInfo[4] = jsonResponse.getString(3);
			upgradInfo[5] = jsonResponse.getString(4);
			upgradInfo[0] = AppUtil.getHanZname(spiritsName[soilid - 1])
					+ building_level + " 级";

			LayoutInflater factory = LayoutInflater.from(this.getContext());
			View textEntryView = factory.inflate(R.layout.upgrade01, null);

			// dialog里的数据设置
			TextView wood = (TextView) textEntryView
					.findViewById(R.id.upgrade01_wood);
			wood.setText(upgradInfo[1]);
			TextView food = (TextView) textEntryView
					.findViewById(R.id.upgrade01_food);
			food.setText(upgradInfo[2]);
			TextView ironOre = (TextView) textEntryView
					.findViewById(R.id.upgrade01_ironOre);
			ironOre.setText(upgradInfo[3]);
			TextView population = (TextView) textEntryView
					.findViewById(R.id.upgrade01_population);
			population.setText(upgradInfo[4]);
			TextView time = (TextView) textEntryView
					.findViewById(R.id.upgrade01_time);
			time.setText(upgradInfo[5]);

			int iconid = AppUtil.getDialogIconId(spiritsImage[soilid - 1],
					spiritsName[soilid - 1]);
			AlertDialog dlg = new AlertDialog.Builder(this.getContext())
					.setTitle(upgradInfo[0])
					.setIcon(iconid)
					.setView(textEntryView)
					.setPositiveButton(R.string.upgrade,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									try {
										JSONArray jsonarr = new JSONArray();
										jsonarr.put(0, soilid);

										JSONObject param = new JSONObject();
										param.put("verifycode",
												AppUtil.verifycode);
										param.put("actioncode", 15);
										param.put("data", jsonarr);

										thread = new ActivityThread(param
												.toString(), "castle.php",
												soilid, 15);
										thread.start();
									} catch (JSONException e) {
										AppUtil.button1Dialog(
												HolyWarScene.this.getContext(),
												"数据传送错误！");
									}
								}
							})
					.setNeutralButton(R.string.recruit_soldier,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 调用士兵招募对话框处理方法
									popupRecruitSoldierDialog(soilid);
								}
							})
					.setNegativeButton(R.string.close,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create();
			dlg.show();
		} catch (JSONException e) {
			AppUtil.button1Dialog(this.getContext(), "数据传送错误！");
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 普通建筑升级
	 * 
	 * @param jsonStr
	 * @param soilid
	 */
	private void handleRequestUpgradeInfoResult(String jsonStr, final int soilid) {
		String[] upgradInfo = new String[6];

		try {
			// 解码JSON
			JSONObject json = new JSONObject(jsonStr.toString());
			String building_level = json.getString("building_level");
			JSONArray jsonResponse = json.getJSONArray("upgrade_info");
			upgradInfo[1] = jsonResponse.getString(0);
			upgradInfo[2] = jsonResponse.getString(1);
			upgradInfo[3] = jsonResponse.getString(2);
			upgradInfo[4] = jsonResponse.getString(3);
			upgradInfo[5] = jsonResponse.getString(4);

			upgradInfo[0] = AppUtil.getHanZname(spiritsName[soilid - 1])
					+ building_level + " 级";

			LayoutInflater factory = LayoutInflater.from(this.getContext());
			View textEntryView = factory.inflate(R.layout.upgrade01, null);

			// dialog里的数据设置
			TextView wood = (TextView) textEntryView
					.findViewById(R.id.upgrade01_wood);
			wood.setText(upgradInfo[1]);
			TextView food = (TextView) textEntryView
					.findViewById(R.id.upgrade01_food);
			food.setText(upgradInfo[2]);
			TextView ironOre = (TextView) textEntryView
					.findViewById(R.id.upgrade01_ironOre);
			ironOre.setText(upgradInfo[3]);
			TextView population = (TextView) textEntryView
					.findViewById(R.id.upgrade01_population);
			population.setText(upgradInfo[4]);
			TextView time = (TextView) textEntryView
					.findViewById(R.id.upgrade01_time);
			time.setText(upgradInfo[5]);

			int iconid = AppUtil.getDialogIconId(spiritsImage[soilid - 1],
					spiritsName[soilid - 1]);

			AlertDialog dlg = new AlertDialog.Builder(this.getContext())
					.setTitle(upgradInfo[0])
					.setIcon(iconid)
					.setView(textEntryView)
					.setPositiveButton(R.string.upgrade,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									try {
										JSONArray jsonarr = new JSONArray();
										jsonarr.put(0, soilid);

										JSONObject param = new JSONObject();
										param.put("verifycode",
												AppUtil.verifycode);
										param.put("actioncode", 15);
										param.put("data", jsonarr);

										thread = new ActivityThread(param
												.toString(), "castle.php",
												soilid, 15);
										thread.start();
									} catch (JSONException e) {
										AppUtil.button1Dialog(
												HolyWarScene.this.getContext(),
												"数据传送错误！");
									}
								}
							})
					.setNegativeButton(R.string.close,
							new DialogInterface.OnClickListener() {

								// @Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
			dlg.show();

		} catch (JSONException e) {
			AppUtil.button1Dialog(this.getContext(), "数据传送错误！");
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 
	 * 招募士兵对话框处理
	 * 
	 * @param soilid
	 *            地块编号
	 */
	private void popupRecruitSoldierDialog(final int soilid) {
		// dialog兵种列表
		LayoutInflater factory = LayoutInflater.from(this.getContext());
		View textEntryview1 = factory.inflate(R.layout.upgradelist, null);
		ListView listrecruit = (ListView) textEntryview1
				.findViewById(R.id.upgradelist_listview);

		int imgid = 0;
		String title = "招募兵种";
		String type[];
		ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
		final String name = spiritsName[soilid - 1];
		if (name.equals("parade_ground")) {
			imgid = R.drawable.parade_ground;
			type = new String[5];
			type[0] = "运输兵";
			type[1] = "侦察兵";
			type[2] = "民兵";
			type[3] = "枪兵";
			type[4] = "刺客";

			for (int i = 0; i < type.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("NAME", type[i]);
				map.put("Recruit", "招募");
				listData.add(map);
			}
		}
		if (name.equals("archery_camp")) {
			imgid = R.drawable.archery_camp;
			type = new String[1];
			type[0] = "神射手";

			for (int i = 0; i < type.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("NAME", type[i]);
				map.put("Recruit", "招募");
				listData.add(map);
			}
		}
		if (name.equals("voodoo")) {
			imgid = R.drawable.voodoo;
			type = new String[1];
			type[0] = "巫毒术士";

			for (int i = 0; i < type.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("NAME", type[i]);
				map.put("Recruit", "招募");
				listData.add(map);
			}
		}
		if (name.equals("martial_club")) {
			imgid = R.drawable.martial_club;
			type = new String[1];
			type[0] = "武士";

			for (int i = 0; i < type.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("NAME", type[i]);
				map.put("Recruit", "招募");
				listData.add(map);
			}
		}
		if (name.equals("cavalry_camp")) {
			imgid = R.drawable.cavalry_camp;
			type = new String[1];
			type[0] = "骠骑";

			for (int i = 0; i < type.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("NAME", type[i]);
				map.put("Recruit", "招募");
				listData.add(map);
			}
		}
		if (name.equals("army_arsenal")) {
			imgid = R.drawable.army_arsenal;
			type = new String[2];
			type[0] = "冲撞车";
			type[1] = "投石车";

			for (int i = 0; i < type.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("NAME", type[i]);
				map.put("Recruit", "招募");
				listData.add(map);
			}
		}
		SimpleAdapter adapter = new SimpleAdapter(
				HolyWarScene.this.getContext(), listData, R.layout.upgrade02,
				new String[] { "NAME", "Recruit" }, new int[] {
						R.id.upgrade02_TextView01, R.id.upgrade02_TextView02 });
		listrecruit.setAdapter(adapter);
		AlertDialog dlg = new AlertDialog.Builder(
				HolyWarScene.this.getContext())
				.setTitle(title)
				.setIcon(imgid)
				.setView(textEntryview1)
				.setNegativeButton(R.string.close,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).create();
		dlg.show();

		listrecruit
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					private int img;
					private String soldierInfo[] = new String[5];

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						if (name.equals("parade_ground")) {
							switch (position) {
							case 0:
								img = R.drawable.transport_soldier;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "transport_soldier");
								break;
							case 1:
								img = R.drawable.scout_soldier;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "scout_soldier");
								break;
							case 2:
								img = R.drawable.militia;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "militia");
								break;
							case 3:
								img = R.drawable.spearman;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "spearman");
								break;
							case 4:
								img = R.drawable.e_assassin;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "assassin");
							}
						}
						if (name.equals("archery_camp") && position == 0) {
							img = R.drawable.marksman;
							soldierInfo = AppUtil.recruitSoldierStuffInfo(name,
									"marksman");
						}
						if (name.equals("voodoo") && position == 0) {
							img = R.drawable.voodoo_man;
							soldierInfo = AppUtil.recruitSoldierStuffInfo(name,
									"voodoo_man");
						}
						if (name.equals("martial_club") && position == 0) {
							img = R.drawable.warrior;
							soldierInfo = AppUtil.recruitSoldierStuffInfo(name,
									"warrior");
						}

						if (name.equals("cavalry_camp") && position == 0) {
							img = R.drawable.military_general;
							soldierInfo = AppUtil.recruitSoldierStuffInfo(name,
									"military_general");
						}

						if (name.equals("army_arsenal")) {
							switch (position) {
							case 0:
								img = R.drawable.battering_ram;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "battering_ram");
								break;
							case 1:
								img = R.drawable.catapult;
								soldierInfo = AppUtil.recruitSoldierStuffInfo(
										name, "catapult");
							}
						}
						// 弹出招募士兵数量对话框
						popupRecruitSoldierNumberDialog(soldierInfo, img);

					}
				});
	}

	/**
	 * 通过招募士兵信息和图片，弹出招募士兵数量对话框
	 * 
	 * @param soldierInfo
	 *            士兵招募信息
	 * @param img
	 *            招募士兵图片id
	 */
	private void popupRecruitSoldierNumberDialog(final String soldierInfo[],
			int img) {

		LayoutInflater factoryrecruit = LayoutInflater.from(HolyWarScene.this
				.getContext());
		final View textEntryviewrecruit = factoryrecruit.inflate(
				R.layout.upgrade03, null);
		TextView wood3 = (TextView) textEntryviewrecruit
				.findViewById(R.id.upgrade03_wood);
		wood3.setText(soldierInfo[2]);
		TextView food3 = (TextView) textEntryviewrecruit
				.findViewById(R.id.upgrade03_food);
		food3.setText(soldierInfo[3]);
		TextView ironOre3 = (TextView) textEntryviewrecruit
				.findViewById(R.id.upgrade03_ironOre);
		ironOre3.setText(soldierInfo[4]);
		AlertDialog dlg = new AlertDialog.Builder(
				HolyWarScene.this.getContext())
				.setTitle(soldierInfo[1])
				.setIcon(img)
				.setView(textEntryviewrecruit)
				.setPositiveButton(R.string.recruit,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								EditText recruitnumber = (EditText) textEntryviewrecruit
										.findViewById(R.id.upgrade03_recruitnumber);
								String soldiernumber = recruitnumber.getText()
										.toString().trim();

								JSONArray jsonarr = new JSONArray();
								try {
									jsonarr.put(0, soldierInfo[0]);
									jsonarr.put(1, soldiernumber);
									JSONObject param = new JSONObject();
									param.put("verifycode", AppUtil.verifycode);
									param.put("actioncode", 16);
									param.put("data", jsonarr);

									thread = new ActivityThread(param
											.toString(), "castle.php", 0, 16);
									thread.start();
								} catch (JSONException e) {
									e.printStackTrace();
								}

							}
						})
				.setNegativeButton(R.string.close,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).create();
		dlg.show();
	}

	/**
	 * 士兵招募完成回调方法。
	 * 
	 * @param jsonStr
	 *            返回的JSON字符串
	 */
	private void handleRequestRecruitSoldierResult(String jsonStr) {
		try {
			// 解码JSON
			JSONObject json = new JSONObject(jsonStr);
			if (json != null) {
				String info = json.getString("recruit_soldier");
				Toast.makeText(HolyWarScene.this.getContext(), info,
						Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			AppUtil.button1Dialog(this.getContext(), "数据传送错误！");
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 点击升级按钮的回调处理。
	 */
	private void handleClickBuildingUpgrade(String jsonStr) {
		String backFlag = "44";
		JSONObject json;
		try {
			json = new JSONObject(jsonStr);
			if (json != null) {
				backFlag = json.getString("upgrade_back_flag");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (backFlag.equals("1")) {
			Toast.makeText(this.getContext(), "升级中...", Toast.LENGTH_SHORT)
					.show();
		} else if (backFlag.equals("44")) {
			Toast.makeText(this.getContext(), "数据错误！", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(this.getContext(), "材料不足，不能升级！", Toast.LENGTH_SHORT)
					.show();
		}
	}

	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 0:
				if (msg.obj != null) {
					Map data = (Map) msg.obj;
					Integer soilid = (Integer) data.get("soilid");
					Integer actioncode = (Integer) data.get("actioncode");
					String jsonData = (String) data.get("jsonData");
					if (actioncode == 14) {
						String name = spiritsName[soilid - 1];
						if (name.equals("temple") || name.equals("residence")
								|| name.equals("storehouse")
								|| name.equals("chamber_secrets")
								|| name.equals("meeting_room")
								|| name.equals("demons_pavilion")
								|| name.equals("wall")
								|| name.equals("sawmill")
								|| name.equals("grain_field")
								|| name.equals("land_tower_air_defense")
								|| name.equals("land_tower_ground_defense")) {
							handleRequestUpgradeInfoResult(jsonData, soilid);
						} else if (name.equals("parade_ground")
								|| name.equals("archery_camp")
								|| name.equals("voodoo")
								|| name.equals("martial_club")
								|| name.equals("cavalry_camp")
								|| name.equals("army_arsenal")) {
							handleRequestUpgradeRecruitSoldierInfoResult(
									jsonData, soilid);
						} else if (name.equals("space_land_tower")) {
							handleSpaceLandTowerDialog(soilid);
						}
					} else if (actioncode == 15) {
						handleClickBuildingUpgrade(jsonData);
					} else if (actioncode == 16) {
						handleRequestRecruitSoldierResult(jsonData);
					}
				}
				break;
			}
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
			super.handleMessage(msg);
		}
	};

	class ActivityThread extends Thread {

		/** 请求的JSON字符串 */
		private String jsonStr;

		/** 请求服务端脚本文件 */
		private String url;

		/** 当前请求的地号 */
		private int soilid;

		/**
		 * 请求动作 12 城堡初始化 14 建筑升级 15 建筑管理页面（点击建筑的“升级”按钮） 16
		 * 建筑中带招募士兵的（点击招募士兵的“招募”按钮）
		 */
		private int actioncode = 14;

		ActivityThread(String jsonStr, String url, int soilid, int actioncode) {
			super();
			this.jsonStr = jsonStr;
			this.url = url;
			this.soilid = soilid;
			this.actioncode = actioncode;
		}

		@Override
		public void run() {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(AppUtil.URL_PREFIX + url);
			try {
				StringEntity se = new StringEntity(jsonStr);
				httppost.setEntity(se);

				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entityOut = response.getEntity();

				if (entityOut != null) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(entityOut.getContent(),
									"utf-8"));
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					Map data = new HashMap();
					data.put("soilid", soilid);
					data.put("actioncode", actioncode);
					data.put("jsonData", sb == null ? "" : sb.toString());
					Message lmsg = new Message();
					lmsg.obj = data;
					lmsg.what = 0;// 代表成功
					handler.sendMessage(lmsg);
				}

			} catch (Exception e) {
				AppUtil.button1Dialog(HolyWarScene.this.getContext(), "网络异常错误！");
				handler.sendEmptyMessage(-1);
			}

		}
	}

}
