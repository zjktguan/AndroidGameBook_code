/* ±¾ÊéÍøÕ¾£ºhttp://www.androidbks.com
* ÖÇ½İiOS¿ÎÌÃ£ºhttp://www.51work6.com
* ÖÇ½İiOS¿ÎÌÃÔÚÏß¿ÎÌÃ£ºhttp://v.51work6.com
* ÖÇ½İiOS¿ÎÌÃĞÂÀËÎ¢²©£ºhttp://weibo.com/u/3215753973
* ×÷ÕßÎ¢²©£ºhttp://weibo.com/516inc
* ¹Ù·½csdn²©¿Í£ºhttp://blog.csdn.net/tonny_guan
* QQ£º1575716557 ÓÊÏä£ºjylong06@163.com
*/ 

package com.work6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FailureActivity extends Activity {
	
	Button btnBack ; 
	TextView label;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.failure);
		label  = (TextView)findViewById(R.id.label);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(5);
				FailureActivity.this.finish();
			}
		});

	}

}
