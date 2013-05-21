/* ±¾ÊéÍøÕ¾£ºhttp://www.androidbks.com
* ÖÇ½ÝiOS¿ÎÌÃ£ºhttp://www.51work6.com
* ÖÇ½ÝiOS¿ÎÌÃÔÚÏß¿ÎÌÃ£ºhttp://v.51work6.com
* ÖÇ½ÝiOS¿ÎÌÃÐÂÀËÎ¢²©£ºhttp://weibo.com/u/3215753973
* ×÷ÕßÎ¢²©£ºhttp://weibo.com/516inc
* ¹Ù·½csdn²©¿Í£ºhttp://blog.csdn.net/tonny_guan
* QQ£º1575716557 ÓÊÏä£ºjylong06@163.com
*/ 

package com.god.holywar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Ó¦ÓÃµÇÂ¼µÄActivity
 * @author ¹Ø¶«Éý
 *
 */
public class LoginActivity extends AppActivity {

	private String its[] = { "Ñ×»ÆÕù°Ô", "ÁúÐÐÌìÏÂ" };

	private EditText username;
	private EditText password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);

		Spinner splogin = (Spinner) findViewById(R.id.login_splongin);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, its);
		splogin.setAdapter(adapter);

		Button btn01 = (Button) findViewById(R.id.login_btnlogin);
		Button btn02 = (Button) findViewById(R.id.login_btnexit);

		btn01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					JSONArray jsonarr = new JSONArray();
					username = (EditText) findViewById(R.id.login_EditTextUsername);
					password = (EditText) findViewById(R.id.login_EditTextPassword);
					jsonarr.put(0, username.getText().toString().trim());
					jsonarr.put(1, password.getText().toString().trim());

					JSONObject param = new JSONObject();
					param.put("actioncode", 1);
					param.put("data", jsonarr);

					LoginActivity.this.requestURL(param.toString(), "login.php");

				} catch (JSONException e) {
					e.printStackTrace();
					AppUtil.button1Dialog(LoginActivity.this, "½âÂëJSON×Ö·û´®Ê§°Ü£¡");
					release();
				}

			}

		});

		btn02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.exit(0);
			}

		});

	}

	@Override
	protected void handleResult(String jsonStr) {
		try {
			JSONObject json = new JSONObject(jsonStr);
			if (json != null) {
				String loginflag = json.getString("success");
				AppUtil.verifycode = json.getString("result");
				AppUtil.faction = json.getString("faction");
				
				AppUtil.heroname = json.getString("heroname");
				AppUtil.herolevel = json.getString("herolevel");

				if (loginflag.equals("true")) {
					Intent it = new Intent(LoginActivity.this,
							HolyWarActivity.class);
					startActivity(it);
				} else {
					AppUtil.button1Dialog(this, "µÇÂ¼Ê§°Ü,ÇëÖØÐÂµÇÂ¼£¡");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		release();
	}

}