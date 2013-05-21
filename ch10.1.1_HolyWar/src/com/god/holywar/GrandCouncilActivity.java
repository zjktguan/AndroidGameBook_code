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

	// ++++++++++军机处级别设定与升级 start+++++++
	// 来自服务器，军机处级别
	private String strGrandCouncilLevel;
	// 来自服务器，军机处升下一级所需要的材料
	private String[] aryUpgradeStuff = new String[5];
	// 保存地的编号
	private int soilid;
	// 保存来自哪个页面的跳转变量
	private int sendarmy;
	// ++++++++++军机处级别设定与升级 end+++++++++
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
	}

	@Override
	protected void handleResult(String jsonStr) {

	}

	@Override
	protected boolean showWindowTitle() {
		return true;
	}

}
