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
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView txt2 = (TextView) findViewById(R.id.TextView02);
        Spinner s1 = (Spinner) findViewById(R.id.Spinner01);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                            txt2.setText(adapter.getItem(position).toString());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    	txt2.setText("没选中的");
                    }
                });

        final TextView txt4 = (TextView) findViewById(R.id.TextView04);
        Spinner s2 = (Spinner) findViewById(R.id.Spinner02);
        ArrayAdapter<CharSequence> adapterC = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_item, CONSTELLATIONS);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s2.setAdapter(adapterC);
        s2.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                    	txt4.setText(CONSTELLATIONS[position]);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    	txt4.setText("没选中的");
                    }
                });
    }
    
    static final String[] CONSTELLATIONS = new String[] {
    	"白羊座", "金牛座", "双子座",
    	"巨蟹座", "狮子座", "处女座",
    	"天枰座", "天蝎座", "射手座", 
    	"魔蝎座", "水瓶座", "双鱼座"
        };
}