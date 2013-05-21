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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Toast_3 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn01 = (Button) findViewById(R.id.Button01);
		btn01.setText(R.string.mixtoast);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast toast = new Toast(Toast_3.this);
				LinearLayout layout = new LinearLayout(Toast_3.this);
				layout.setOrientation(LinearLayout.VERTICAL);

				ImageView view = new ImageView(Toast_3.this);
				view.setImageResource(R.drawable.image);
				TextView textView = new TextView(Toast_3.this);
				textView.setText("»ğ¼ı¶Ó");

				layout.addView(view);
				layout.addView(textView);
				toast.setView(layout);
				toast.show();
			}
		});
	}
}