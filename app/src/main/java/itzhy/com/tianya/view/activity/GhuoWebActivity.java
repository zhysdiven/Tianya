package itzhy.com.tianya.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.ButterKnife;
import butterknife.InjectView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.utils.LogUtils;

public class GhuoWebActivity extends AppCompatActivity {

    @InjectView(R.id.ghuo_web)
    WebView ghuoWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghuo_web);
        ButterKnife.inject(this);

        init();
    }

    private void init() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        final String titles = intent.getStringExtra("title");

        LogUtils.i("title : " + titles + ",url :" + url);

        WebSettings settings = ghuoWeb.getSettings();
        settings.setSaveFormData(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setUseWideViewPort(true);

        setTitle(titles);
        ghuoWeb.loadUrl(url);


        ghuoWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        ghuoWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (TextUtils.isEmpty(titles)) setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (ghuoWeb.canGoBack()) {
            ghuoWeb.goBack();
            return;
        }
    }
}
