package com.song.zzb.wyzzb.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.song.zzb.wyzzb.R;


/**
 * Created by song on 2016/6/9.
 */
public class BanMaWebActivity extends Activity {
    private WebView webView;
    private String  url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banmaweb);
        webView = (WebView) findViewById(R.id.webview);

//        Bundle bundle = getIntent().getExtras();
//        if(bundle!=null){
//            url = bundle.getBundle("url").toString();
////             url =  bundle.getString();
//
//        }
        if(getIntent().getExtras().isEmpty())  ;
        webView.loadUrl(getIntent().getExtras().getString("url"));
//        ToastUtil.showToast(getApplicationContext(),url);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            // Set progress bar during loading
            public void onProgressChanged(WebView view, int progress) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        if(!(webView.canGoBack())){
            finish();
        }else if (webView.canGoBack()) {
            webView.goBack( );
        }
    }
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
//            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
