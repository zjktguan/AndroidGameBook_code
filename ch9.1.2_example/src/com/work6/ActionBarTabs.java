/* ±æ ÈÕ¯’æ£∫http://www.androidbks.com
* ÷«Ω›iOSøŒÃ√£∫http://www.51work6.com
* ÷«Ω›iOSøŒÃ√‘⁄œﬂøŒÃ√£∫http://v.51work6.com
* ÷«Ω›iOSøŒÃ√–¬¿ÀŒ¢≤©£∫http://weibo.com/u/3215753973
* ◊˜’ﬂŒ¢≤©£∫http://weibo.com/516inc
* πŸ∑Ωcsdn≤©øÕ£∫http://blog.csdn.net/tonny_guan
* QQ£∫1575716557 ” œ‰£∫jylong06@163.com
*/ 

package com.work6;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActionBarTabs extends Activity implements View.OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.action_bar_tabs);

		Button btn_add_tab = (Button) findViewById(R.id.btn_add_tab);
		btn_add_tab.setOnClickListener(this);

		Button btn_remove_tab = (Button) findViewById(R.id.btn_remove_tab);
		btn_remove_tab.setOnClickListener(this);

		Button btn_toggle_tabs = (Button) findViewById(R.id.btn_toggle_tabs);
		btn_toggle_tabs.setOnClickListener(this);

		Button btn_remove_all_tabs = (Button) findViewById(R.id.btn_remove_all_tabs);
		btn_remove_all_tabs.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		ActionBar bar = getActionBar();
		switch (v.getId()) {
		case R.id.btn_add_tab:
			int tabCount = bar.getTabCount();
			String text = "Tab " + tabCount;
			bar.addTab(bar
					.newTab()
					.setText(text)
					.setTabListener(
							new TabListener(new TabContentFragment(text))));
			break;
		case R.id.btn_remove_tab:
			bar.removeTabAt(bar.getTabCount() - 1);
			break;
		case R.id.btn_toggle_tabs:
			if (bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
				bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
				bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE,
						ActionBar.DISPLAY_SHOW_TITLE);
			} else {
				bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
			}
			break;
		case R.id.btn_remove_all_tabs:
			getActionBar().removeAllTabs();
			break;
		}
	}

	private class TabListener implements ActionBar.TabListener {
		private TabContentFragment mFragment;

		public TabListener(TabContentFragment fragment) {
			mFragment = fragment;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.fragment_content, mFragment, mFragment.getText());
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(mFragment);
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(ActionBarTabs.this, "Reselected!",
					Toast.LENGTH_SHORT).show();
		}

	}

	private class TabContentFragment extends Fragment {
		private String mText;

		public TabContentFragment(String text) {
			mText = text;
		}

		public String getText() {
			return mText;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View fragView = inflater.inflate(R.layout.action_bar_tab_content,
					container, false);

			TextView text = (TextView) fragView.findViewById(R.id.text);
			text.setText(mText);

			return fragView;
		}

	}

}
