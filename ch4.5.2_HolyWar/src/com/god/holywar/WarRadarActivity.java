/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 战争雷达业务模块Activity
 * 
 * @author 关东升
 * 
 */
public class WarRadarActivity extends MenuAppActivity {

	// 地形(玩家)名称
	private String aryName[];
	// 宗派/描述
	private String aryFaction[];
	// 坐标
	private String aryCoordinate[];
	// 搜索项目
	private String[] it1;
	// 玄铁矿级别
	private String[] it2;
	// 有无宠物
	private String[] it3;
	// 操作请求动作代码
	private int actioncode;
	// 画面中的ListView
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.war_radar);
		// 初始化数组
		it1 = getResources().getStringArray(R.array.war_radar_search_item);
		// 玄铁矿级别
		it2 = getResources().getStringArray(R.array.war_radar_Iron_Ore_Level);
		// 有无宠物
		it3 = getResources().getStringArray(R.array.war_radar_pet_no_yes);

		final Spinner spinner1 = (Spinner) findViewById(R.id.war_radar_Spinner01);
		final Spinner spinner2 = (Spinner) findViewById(R.id.war_radar_Spinner02);
		// 名称 | 地形
		final TextView tv1 = (TextView) findViewById(R.id.war_radar_TextView01);
		// 宗派 | 描述
		final TextView tv2 = (TextView) findViewById(R.id.war_radar_TextView02);
		// 搜索内容
		final TextView txtSearchContent = (TextView) findViewById(R.id.txtSearchContent);
		// 隐藏控件
		spinner2.setVisibility(View.INVISIBLE);
		txtSearchContent.setVisibility(View.INVISIBLE);

		Button btnss = (Button) findViewById(R.id.war_radar_buttonSearch);

		listView = (ListView) findViewById(R.id.war_radar_ListView01);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, it1);
		spinner1.setAdapter(adapter1);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (spinner1.getSelectedItem().toString().equals("玩家城邦")) {
					tv1.setText(R.string.name);
					tv2.setText(R.string.faction);
					spinner2.setVisibility(View.INVISIBLE);
					txtSearchContent.setVisibility(View.INVISIBLE);
				} else if (spinner1.getSelectedItem().toString().equals("玄铁矿")) {
					tv1.setText(R.string.terrain);
					tv2.setText(R.string.describe);
					spinner2.setVisibility(View.VISIBLE);
					txtSearchContent.setVisibility(View.VISIBLE);
					txtSearchContent.setText(R.string.iron_ore_level_2);
					ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
							WarRadarActivity.this,
							android.R.layout.simple_spinner_item, it2);
					spinner2.setAdapter(adapter2);
				} else {
					tv1.setText(R.string.terrain);
					tv2.setText(R.string.describe);
					spinner2.setVisibility(View.VISIBLE);
					txtSearchContent.setVisibility(View.VISIBLE);
					txtSearchContent.setText(R.string.pet_no_yes_2);
					ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
							WarRadarActivity.this,
							android.R.layout.simple_spinner_item, it3);
					spinner2.setAdapter(adapter2);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		//TODO 点击查询按钮
		
		//TODO 跳转到军机处画面
	}

	@Override
	protected void handleResult(String jsonStr) {
		//TODO 点击查询按钮
	}
}
