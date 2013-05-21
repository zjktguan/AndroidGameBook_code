/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

/**
 * 空地建房业务模块Activity
 * 
 * @author 关东升
 * 
 */
public class BuildingActivity extends MenuAppActivity {

	// 基础建筑信息
	String aryBasicBuildingName[] = { "民居", "仓库", "密库", "合众司" };
	String aryBuildingDev[] = { "决定城邦可居住人民数量的建筑，可以通过升级或建造多个民居来提升城邦人口总数。",
			"储存各类资源的建筑，等级越高可存储的资源越多。",
			"将部分资源秘密保护起来，以免在战争中遭受损失。密库等级越高，可保护资源越多。",
			"建立和加入宗派的必要建筑，等级越高可加入的宗派成员越多。" };
	String aryBuildingFf[] = { "木材:60    粮食:30    玄铁:0    人口:0    时间:00:00:55",
			"木材:150    粮食:120    玄铁:4    人口:9    时间:00:01:14",
			"木材:2500    粮食:2500    玄铁:80    人口:4    时间:00:02:09",
			"木材:500    粮食:500    玄铁:100    人口:100    时间:00:03:41", };

	// 军事建筑信息
	// 军事信息中分仙界和冥界，数组aryMilitaryBuilding1是冥界
	String aryMilitaryBuilding1[] = { "军机处", "练兵场", "万魔楼", "神弓营", "巫毒教", "武士馆",
			"骠骑营", "机巧坊" };
	// 数组aryMilitaryBuilding2是仙界
	String aryMilitaryBuilding2[] = { "军机处", "练兵场", "聚仙阁", "神弓营", "巫毒教", "武士馆",
			"骠骑营", "机巧坊" };
	String aryBuildingDev1[] = {
			"决定军事行动的重要建筑，包括侦查、征伐、运输等行动都由军机处进行。军机处等级越高，部队移动越快。",
			"招募兵勇的建筑，建筑等级越高招募速度越快，战斗单位可以在此学习战斗技能。",
			"修魔者的修炼场所，修魔者和魔物死后都在万魔楼复活。万魔楼等级越高，修魔者回血越快。",
			"招募神射手的建筑，建筑等级越高招募速度越快，战斗单位可以在此学习战斗技能。",
			"招募巫毒术士的建筑，建筑等级越高招募速度越快，战斗单位可以在此学习战斗技能。",
			"招募武术高手的建筑，建筑等级越高招募速度越快，战斗单位可以在此学习战斗技能。",
			"招募骠骑兵的建筑，建筑等级越高招募速度越快，战斗单位可以在此学习战斗技能。",
			"生产攻城器械的建筑，只有攻城器械才能攻击建筑，等级越高生产越快。" };

	String aryBuildingFf1[] = {
			"木材:260    粮食:210    玄铁:0    人口:0    时间:00:01:50",
			"木材:220    粮食:230    玄铁:0    人口:20    时间:00:01:32",
			"木材:2000    粮食:2000    玄铁:100    人口:100    时间:00:04:36",
			"木材:180    粮食:260    玄铁:8    人口:20    时间:00:02:09",
			"木材:220    粮食:200    玄铁:8    人口:20    时间:00:02:18",
			"木材:180    粮食:200    玄铁:10    人口:20    时间:00:02:32",
			"木材:280    粮食:300    玄铁:13    人口:20    时间:00:02:46",
			"木材:240    粮食:230    玄铁:18    人口:20    时间:00:01:32" };

	String aryBuildingScribe1[] = { "", "", "", "",
			"需要宗正殿10级，练兵场6级，军机处7级，神弓营3级。", "需要宗正殿10级，练兵场6级，军机处7级，神弓营3级。",
			"需要宗正殿15级，练兵场7级，军机处10级,万魔楼3级。", "需要巫毒教10级。武士馆6级，骠骑营5级。" };

	// 建造与无法建造的标志位，基础建筑
	private String aryBasicBuildingFlag[] = new String[4];
	// 建造与无法建造的标志位，军事建筑
	private String aryMilitaryBuildingFlag[] = new String[8];
	// 接收城堡页空地建房，地的编号
	private int soilid;
	// 操作请求动作代码
	private int actioncode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.building01);

		Bundle bundle = this.getIntent().getExtras();
		soilid = bundle.getInt("soilid");

		try {

			JSONObject param = new JSONObject();
			param.put("verifycode", AppUtil.verifycode);
			param.put("actioncode", 17);
			this.actioncode = 17;

			requestURL(param.toString(), "building.php");

		} catch (JSONException e) {
			e.printStackTrace();
			AppUtil.button1Dialog(this, "解码JSON字符串失败！");
			release();
		}
	}

	@Override
	protected boolean showWindowTitle() {
		return true;
	}

	@Override
	protected void handleResult(String jsonStr) {

	}
}
