/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.work6;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListView_1 extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mStrings));
		getListView().setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View v,
							int pos, long id) {
						Toast.makeText(ListView_1.this, mStrings[pos],
								Toast.LENGTH_SHORT).show();
					}
				});
	}

//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		Toast.makeText(ListView_1.this, mStrings[position],
//				Toast.LENGTH_SHORT).show();
//	}

	private String[] mStrings = { "北京市", "天津市", "上海", "重庆", "乌鲁木齐", "拉萨", "银川",
			"呼和浩特", "包头", "南宁", "哈尔滨", "大庆", "长春", "吉林", "沈阳", "大连", "石家庄",
			"秦皇岛", "济南", "青岛", "烟台", "南京", "苏州", "徐州", "合肥", "杭州", "福州", "厦门",
			"广州", "深圳", "汕头", "珠海", "海口", "三亚", "昆明", "贵阳", "成都", "长沙", "武汉",
			"郑州", "洛阳", "开封", "太原", "大同", "西安", "咸阳", "延安", "兰州", "西宁", "南昌",
			"九江", "台北", "台中", "香港", "澳门" };
}