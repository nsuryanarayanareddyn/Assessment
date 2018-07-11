package nytimes.in.co.nytimesdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import java.nio.file.WatchEvent;

import Uitility.SpinnerManager;

public class DeatiledArticle extends AppCompatActivity {
    private WebView mWebView;
private String mDetailedURL = "";
private LinearLayout mLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);
        mWebView = (WebView)findViewById(R.id.webview);
        mLayout = (LinearLayout)findViewById(R.id.backlayout) ;
        mDetailedURL = getIntent().getExtras().getString("url");
        mWebView.setWebViewClient(new myWebClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mDetailedURL);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            SpinnerManager.showSpinner(DeatiledArticle.this,"Loading...");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            SpinnerManager.hideSpinner(DeatiledArticle.this);
            super.onPageFinished(view, url);
        }
    }
}
