/* 本书网站：http://www.androidbks.com
* 智捷iOS课堂：http://www.51work6.com
* 智捷iOS课堂在线课堂：http://v.51work6.com
* 智捷iOS课堂新浪微博：http://weibo.com/u/3215753973
* 作者微博：http://weibo.com/516inc
* 官方csdn博客：http://blog.csdn.net/tonny_guan
* QQ：1575716557 邮箱：jylong06@163.com
*/ 

package com.god.holywar;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

/**
 * 应用Activity父类，封装了网络通讯的线程。
 * 
 * @author 关东升
 * 
 */
public abstract class AppActivity extends Activity {

	/** 网络处理线程 */
	private NetThread thread;

	/** 请求的JSON字符串 */
	private String jsonStr;

	/** 请求服务端脚本文件 */
	private String url;

	/** 接收消息 */
	private Handler handler = new MyHandler();

	/**
	 * 处理请求服务器后的结果
	 * 
	 * @param jsonStr
	 *            从服务器端返回的JSON字符串
	 */
	protected abstract void handleResult(String jsonStr);

	/**
	 * 请求网络资源
	 * 
	 * @param jsonStr
	 *            传递给服务器端的参数，JSON字符串
	 */
	protected void requestURL(String jsonStr, String url) {

		this.jsonStr = jsonStr;
		this.url = url;
		thread = new NetThread();
		thread.start();
		showDialog(1);

	}

	/**
	 * 释放资源，包括合并线程，关闭等待框等处理。
	 */
	protected void release() {

		dismissDialog(1);
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 子类重写可以设置是否显示窗口标题
	 * 
	 * @return false 是不显示 true 是显示
	 */
	protected boolean showWindowTitle() {
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!showWindowTitle()) {
			// 全屏显示，隐藏标题栏
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		// 隐藏状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 保持横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("请稍后...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		return dialog;
	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// process incoming messages here
			switch (msg.what) {
			case 0:
				if (msg.obj != null) {
					String jsonStr = (String) msg.obj;
					handleResult(jsonStr);
				} else {
					dismissDialog(1);
				}
				break;
			case -1:
				AppUtil.button1Dialog(AppActivity.this, "网络异常错误！");				
				break;
			case -2:
				AppUtil.button1Dialog(AppActivity.this, "请到androidbks.com注册，替换AppUtil.email!");				
			}
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
			super.handleMessage(msg);
		}
	}

	class NetThread extends Thread {

		@Override
		public void run() {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(AppUtil.URL_PREFIX + url + "?email=" +AppUtil.email);
			try {
				// StringEntity se = new StringEntity(jsonStr);
				StringEntity se = new StringEntity(jsonStr, "utf-8");
				httppost.setEntity(se);

				httpclient.getParams().setIntParameter(
						HttpConnectionParams.SO_TIMEOUT, 10000); // Socket超时设置
				httpclient.getParams().setIntParameter(
						HttpConnectionParams.CONNECTION_TIMEOUT, 10000);// 连接超时设置

				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entityOut = response.getEntity();

				if (entityOut != null) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(entityOut.getContent(),
									"utf-8"));
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					if (sb == null || sb.toString().equals("")) {
						handler.sendEmptyMessage(-2); //非授权用户错误
					} else {						
						Message lmsg;
						lmsg = new Message();
						lmsg.obj = sb == null ? "" : sb.toString();
						lmsg.what = 0;// 代表成功
						handler.sendMessage(lmsg);
					}
				}

			} catch (Exception e) {
				handler.sendEmptyMessage(-1);
			}
		}
	}

}
