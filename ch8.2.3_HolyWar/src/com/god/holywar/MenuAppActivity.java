/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 添加了应用菜单的Activity父类
 * 
 * @author 关东升
 *
 */
public abstract class MenuAppActivity extends AppActivity {

	private static final int CASTLE_MENU_ID = Menu.FIRST;// 城堡
	private static final int WARRADAR_MENU_ID = Menu.FIRST + 1;// 战争雷达
	private static final int AREAMAP_MENU_ID = Menu.FIRST + 2;// 作战地图
	private static final int FACTION_MENU_ID = Menu.FIRST + 3;// 宗派
	private static final int EXIT_MENU_ID = Menu.FIRST + 4;// 退出

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, CASTLE_MENU_ID, Menu.NONE, R.string.castle);
		menu.add(0, WARRADAR_MENU_ID, Menu.NONE, R.string.warradar);
		menu.add(0, AREAMAP_MENU_ID, Menu.NONE, R.string.areamap);
		menu.add(0, FACTION_MENU_ID, Menu.NONE, R.string.faction);
		menu.add(0, EXIT_MENU_ID, Menu.NONE, R.string.exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent i = new Intent();
		
		switch (item.getItemId()) {
		case CASTLE_MENU_ID:
			i.setClassName(this, "com.god.holywar.HolyWarActivity");
			break;
		case AREAMAP_MENU_ID:
			i.setClassName(this, "com.god.holywar.AreaMapActivity");
			break;
		case WARRADAR_MENU_ID:
			i.setClassName(this, "com.god.holywar.WarRadarActivity");
			break;
		case FACTION_MENU_ID:
			i.setClassName(this, "com.god.holywar.FactionActivity");
			break;
		case EXIT_MENU_ID:
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.setClassName(this, "com.god.holywar.LoginActivity");
			break;
		default:
			i = null;
		}

		if (i != null) {
			this.startActivity(i);
		}

		return super.onOptionsItemSelected(item);
	}
}
