package com.example.idoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Net1 extends Activity{
	String cookies = "";
    SharedPreferences sp;
	WebView webview;
	String url;
	Button button;
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net1);
		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		initview();
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Net1.this,MainActivity.class);
				Net1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if(keyCode == KeyEvent.KEYCODE_BACK&&webview.canGoBack()){  
            webview.goBack(); 
            return true;  
        }  
        return super.onKeyDown(keyCode, event);
    } 
	
	private void initview(){
		webview = (WebView) findViewById(R.id.webView1);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setSupportZoom(true);  
		webview.getSettings().setBuiltInZoomControls(true); 
		webview.getSettings().setUseWideViewPort(true);  
        webview.setWebViewClient(new WebViewClient(){
        	public boolean shouldOverrideUrlLoading(WebView view,String url) {
				CookieManager cookieManager = CookieManager.getInstance();
				String cookies = cookieManager.getCookie(url);
				return false;
			}
        	
        });
        webview.loadUrl(url);
	}
	
	public void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookies);
        CookieSyncManager.getInstance().sync();
    }
	
}
