/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 应用工具类，封装了一些常量和通用方法。
 * 
 * @author 关东升
 * 
 */
public class AppUtil {

	public static final String URL_PREFIX = "http://androidbks.com/holywar/";
	public static String verifycode = "";// 验证码
	public static String faction = "";// 宗派
	public static String heroname = "";// 英雄名称
	public static String herolevel = "";// 英雄级别
	
	public static String email = "你的androidbks.com注册用户邮箱";//你的androidbks.com注册用户邮箱

	public static final String FACTION_FAIRYLAND = "0"; // 宗派标志 0 仙界
	public static final String FACTION_GHOSTLAND = "1"; // 宗派标志 1冥界

	/**
	 * 只有一个close按钮的消息提示框
	 * 
	 * @param context
	 * @param message
	 */
	public static void button1Dialog(Context context, String message) {
		AlertDialog dlg = new AlertDialog.Builder(context)
				.setTitle(message)
				.setIcon(R.drawable.info)
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
	 * 建筑物的名字 获得在res中建筑物图片的id
	 * 
	 * @param name
	 *            建筑物的名字 数组
	 * @return 建筑物图片的id 数组
	 */
	public static int[] getBuidingImgId(String name[]) {
		int[] building1 = new int[20];
		for (int i = 0; i < 20; i++) {
			String aname = name[i];
			if (aname.equals("space")) {
				building1[i] = R.drawable.blank03;
				continue;
			}
			if (aname.equals("temple")) {
				building1[i] = R.drawable.temple;
				continue;
			}
			if (aname.equals("residence")) {
				building1[i] = R.drawable.residence;
				continue;
			}
			if (aname.equals("storehouse")) {
				building1[i] = R.drawable.storehouse;
				continue;
			}
			if (aname.equals("chamber_secrets")) {
				building1[i] = R.drawable.chamber_secrets;
				continue;
			}
			if (aname.equals("huzhongsi")) {
				building1[i] = R.drawable.meeting_room;
				continue;
			}
			if (aname.equals("grand_council")) {
				building1[i] = R.drawable.grand_council;
				continue;
			}
			if (aname.equals("parade_ground")) {
				building1[i] = R.drawable.parade_ground;
				continue;
			}
			if (aname.equals("demons_pavilion")) {
				building1[i] = R.drawable.demons_pavilion;
				continue;
			}
			if (aname.equals("gongjingying")) {
				building1[i] = R.drawable.archery_camp;
				continue;
			}
			if (aname.equals("voodoo")) {
				building1[i] = R.drawable.voodoo;
				continue;
			}
			if (aname.equals("martial_club")) {
				building1[i] = R.drawable.martial_club;
				continue;
			}
			if (aname.equals("cavalry_camp")) {
				building1[i] = R.drawable.cavalry_camp;
				continue;
			}
			if (aname.equals("army_arsenal")) {
				building1[i] = R.drawable.army_arsenal;
				continue;
			}
			if (aname.equals("sawmill")) {
				building1[i] = R.drawable.blank03;
				continue;
			}
			if (aname.equals("grain_field")) {
				building1[i] = R.drawable.blank03;
				continue;
			}
			if (aname.equals("land_tower_air_defense")) {
				building1[i] = R.drawable.land_tower_air_defense;
				continue;
			}
			if (aname.equals("wall")) {
				building1[i] = R.drawable.blank02;
				continue;
			}
			if (aname.equals("land_tower_ground_defense")) {
				building1[i] = R.drawable.land_tower_ground_defense;
				continue;
			}
			if (aname.equals("space_land_tower")) {
				building1[i] = R.drawable.blank01;
			}
		}
		return building1;
	}

	/**
	 * 通过选择地块图片id和地块名称，获得弹出对话框中需要图标。
	 * 良田、城墙、木材厂、空箭塔四种情况，绘制的图片于对话框图标不同。
	 * @param imageid
	 * @param name
	 * @return
	 */
	public static int getDialogIconId(int imageid, String name) {
		int iconid = imageid;
		if (iconid == R.drawable.blank01 || iconid == R.drawable.blank02
				|| iconid == R.drawable.blank03) {
			if (name.equals("grain_field")) { //良田
				return R.drawable.grain_field;
			} else if (name.equals("wall")) {//城墙
				return R.drawable.wall;
			} else if (name.equals("sawmill")) {//木材厂
				return R.drawable.sawmill;
			}  else if (name.equals("space_land_tower")) {//空箭塔
				return R.drawable.land_tower;
			} 
			
		}
		return imageid;
	}

	/**
	 * 建筑物的名字 获得在res中建筑物图片的id
	 * 
	 * @param name
	 *            建筑拼音代码
	 * @return 建筑逻辑名
	 */
	public static String getHanZname(String aname) {
		if (aname.equals("space")) {
			return "空地";
		}
		if (aname.equals("temple")) {
			return "宗正殿";
		}
		if (aname.equals("residence")) {
			return "民居";
		}
		if (aname.equals("storehouse")) {
			return "仓库";
		}
		if (aname.equals("chamber_secrets")) {
			return "密库";
		}
		if (aname.equals("meeting_room")) {
			return "合众司";
		}
		if (aname.equals("grand_council")) {
			return "军机处";
		}
		if (aname.equals("parade_ground")) {
			return "练兵场";
		}
		if (aname.equals("demons_pavilion")) {
			return "万魔楼";
		}
		if (aname.equals("archery_camp")) {
			return "神弓营";
		}
		if (aname.equals("voodoo")) {
			return "巫毒教";
		}
		if (aname.equals("martial_club")) {
			return "武士馆";
		}
		if (aname.equals("cavalry_camp")) {
			return "骠骑营";
		}
		if (aname.equals("army_arsenal")) {
			return "机巧坊";
		}
		if (aname.equals("sawmill")) {
			return "木材厂";
		}
		if (aname.equals("grain_field")) {
			return "粮田";
		}
		if (aname.equals("land_tower_air_defense")) {
			return "防空箭塔";
		}
		if (aname.equals("wall")) {
			return "城墙";
		}
		if (aname.equals("land_tower_ground_defense")) {
			return "防地箭塔";
		}
		if (aname.equals("space_land_tower")) {
			return "空箭塔";
		}
		return "空地";
	}
	
	/**
	 * 建筑物汉字名 获得建筑逻辑名字
	 * 
	 * @param hanname
	 *            建筑逻辑名
	 * @return 建筑物汉字的名字
	 */
	public static String getNameHanZ(String hanname) {
		if (hanname.equals("空地")) {
			return "space";
		}
		if (hanname.equals("宗正殿")) {
			return "temple";
		}
		if (hanname.equals("民居")) {
			return "residence";
		}
		if (hanname.equals("仓库")) {
			return "storehouse";
		}
		if (hanname.equals("密库")) {
			return "chamber_secrets";
		}
		if (hanname.equals("合众司")) {
			return "meeting_room";
		}
		if (hanname.equals("军机处")) {
			return "grand_council";
		}
		if (hanname.equals("练兵场")) {
			return "parade_ground";
		}
		if (hanname.equals("万魔楼")) {
			return "demons_pavilion";
		}
		if (hanname.equals("神弓营")) {
			return "archery_camp";
		}
		if (hanname.equals("巫毒教")) {
			return "voodoo";
		}
		if (hanname.equals("武士馆")) {
			return "martial_club";
		}
		if (hanname.equals("骠骑营")) {
			return  "cavalry_camp";
		}
		if (hanname.equals("机巧坊")) {
			return "army_arsenal";
		}
		if (hanname.equals("木材厂")) {
			return "sawmill";
		}
		if (hanname.equals("粮田")) {
			return "grain_field";
		}
		if (hanname.equals("防空箭塔")) {
			return "land_tower_air_defense";
		}
		if (hanname.equals("城墙")) {
			return "wall";
		}
		if (hanname.equals("防地箭塔")) {
			return "land_tower_ground_defense";
		}
		if (hanname.equals("空箭塔")) {
			return "space_land_tower";
		}
		return "space";
	}
	/**
	 * 通过建筑名字和兵种名字，返回招募一个招兵所需要材料信息。 这些信息是放置一个数组的，包括：招募兵种名、木材、粮食和玄铁。
	 * 
	 * @param buildingName
	 *            建筑名字
	 * @param soldiername
	 *            兵种名字
	 * @return
	 */
	public static String[] recruitSoldierStuffInfo(String buildingName,
			String soldiername) {
		String[] arySoldierStuffInfo = new String[5];
		String soldierHZname = "";
		String wood = "";
		String food = "";
		String ironOre = "";
		if (buildingName.equals("parade_ground")) {
			if (soldiername.equals("transport_soldier")) {
				soldierHZname = "运输兵";
				wood = "700";
				food = "600";
				ironOre = "0";
			}
			if (soldiername.equals("scout_soldier")) {
				soldierHZname = "侦察兵";
				wood = "350";
				food = "300";
				ironOre = "0";
			}
			if (soldiername.equals("militia")) {
				soldierHZname = "民兵";
				wood = "500";
				food = "400";
				ironOre = "0";
			}
			if (soldiername.equals("spearman")) {
				soldierHZname = "枪兵";
				wood = "700";
				food = "625";
				ironOre = "0";
			}
			if (soldiername.equals("assassin")) {
				soldierHZname = "刺客";
				wood = "875";
				food = "775";
				ironOre = "125";
			}
		}
		if (buildingName.equals("marksman")) {
			soldierHZname = "神射手";
			wood = "700";
			food = "600";
			ironOre = "0";

		}
		if (buildingName.equals("voodoo_man")) {
			soldierHZname = "巫毒术士";
			wood = "1100";
			food = "1000";
			ironOre = "300";

		}
		if (buildingName.equals("warrior")) {
			soldierHZname = "武士";
			wood = "1600";
			food = "1400";
			ironOre = "300";

		}
		if (buildingName.equals("military_general")) {
			soldierHZname = "骠骑";
			wood = "1875";
			food = "1625";
			ironOre = "500";

		}
		if (buildingName.equals("army_arsenal")) {
			if (soldiername.equals("battering_ram")) {
				soldierHZname = "冲撞车";
				wood = "10000";
				food = "10000";
				ironOre = "6600";
			}
			if (soldiername.equals("catapult")) {
				soldierHZname = "投石车";
				wood = "17000";
				food = "17000";
				ironOre = "12600";
			}
		}

		arySoldierStuffInfo[0] = soldiername;
		arySoldierStuffInfo[1] = soldierHZname;
		arySoldierStuffInfo[2] = wood;
		arySoldierStuffInfo[3] = food;
		arySoldierStuffInfo[4] = ironOre;

		return arySoldierStuffInfo;
	}

}
