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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Toast_2 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn01 = (Button) findViewById(R.id.Button01);
		btn01.setText(R.string.pictoast);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ImageView view = new ImageView(Toast_2.this);
				view.setImageResource(R.drawable.image);
				Toast toast = new Toast(Toast_2.this);
				toast.setView(view);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.show();
			}

		});
	}
}