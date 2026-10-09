package com.novos.home;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
  private WebView web;
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    getWindow().setStatusBarColor(Color.rgb(3,10,28));
    getWindow().setNavigationBarColor(Color.rgb(3,10,28));
    try {
      web = new WebView(this);
      web.setBackgroundColor(Color.rgb(3,10,28));
      WebSettings s = web.getSettings();
      s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setLoadsImagesAutomatically(true);
      s.setSupportZoom(false); s.setMediaPlaybackRequiresUserGesture(true);
      web.setWebViewClient(new WebViewClient() {
        @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest req) { return false; }
        @Override public void onRenderProcessGone(WebView view, android.webkit.RenderProcessGoneDetail detail) {
          view.destroy(); web = null; showFallback("The browser component stopped unexpectedly. Tap Restart to reopen NOVA.");
        }
      });
      web.setWebChromeClient(new WebChromeClient());
      setContentView(web);
      web.loadUrl("file:///android_asset/index.html");
    } catch (Throwable t) { showFallback(t.getClass().getSimpleName()+": "+String.valueOf(t.getMessage())); }
  }
  private void showFallback(String message) {
    LinearLayout box = new LinearLayout(this); box.setOrientation(1); box.setPadding(28,48,28,28); box.setBackgroundColor(Color.rgb(3,10,28));
    TextView title = new TextView(this); title.setText("NOVA Home needs a restart"); title.setTextColor(Color.WHITE); title.setTextSize(24);
    TextView detail = new TextView(this); detail.setText(message); detail.setTextColor(Color.LTGRAY); detail.setTextSize(15); detail.setPadding(0,18,0,18);
    TextView restart = new TextView(this); restart.setText("TAP HERE TO RESTART"); restart.setTextColor(Color.CYAN); restart.setTextSize(16); restart.setPadding(0,20,0,20); restart.setOnClickListener(v -> recreate());
    box.addView(title); box.addView(detail); box.addView(restart); setContentView(box);
  }
  @Override public void onBackPressed() { if (web != null && web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}
