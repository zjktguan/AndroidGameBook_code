/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.work6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private final static String TAG = "LoginActivity";
	private EditText userid;
	private EditText pwd;
	private Button btnLogin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnLogin = (Button) findViewById(R.id.btnLogin);
		userid = (EditText) findViewById(R.id.userid);
		pwd = (EditText) findViewById(R.id.password);

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (userid.getText().toString().equals("tony")
						&& pwd.getText().toString().equals("1")) {
					Intent it = new Intent(LoginActivity.this,
							SuccessActivity.class);
					it.putExtra("userid", userid.getText().toString());
					startActivityForResult(it, 1);
				} else {
					Intent it = new Intent(LoginActivity.this,
							FailureActivity.class);
					startActivityForResult(it, 2);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			// 登录成功之后返回的。
			Log.v(TAG, "from success activity return. resultCode=" + resultCode);
			break;
		case 2:
			// 登录失败之后返回的。
			Log.v(TAG, "from failure activity return. resultCode=" + resultCode);
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}