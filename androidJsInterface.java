//android 示例代码

//main\res\layout  -- layout 代码
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="fill_parent"
    android:layout_height="fill_parent"  tools:context=".MainActivity">
    <WebView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:id="@+id/myWview">
    </WebView>
</RelativeLayout>


//main\java\com\androd\demoapp  webview所在的Activity
public class MainActivity extends Activity {
    public WebView myWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebview = (WebView)findViewById(R.id.myWview);
        WebSettings wsetting = myWebview.getSettings();
        wsetting.setJavaScriptEnabled(true);
	// web页面地址
        myWebview.loadUrl("http://127.0.0.1/template_app_native.html");
        myWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wview, String url) {
                wview.loadUrl(url);
                return true;
            }
        });
        myWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result)
            {
                return super.onJsAlert(view, url, message, result);
            }
        });
	//html页面调用 webview 方法的入口，接口。-- 这里的"android" 在页面上使用为 window.android.方法()
        myWebview.addJavascriptInterface(new AndroidToastForJs(MainActivity.this),"android");
    }

    // webview 调用html页面上的方法
    public void setMsgToWebpage()
    {
	//getNativeMsg 为web页面中js 方法
        myWebview.loadUrl("javascript:getNativeMsg('android调用web页面js方法')");
    }
}
// Js接口方法实现类
public class AndroidToastForJs {
    private Context mContext;

    public AndroidToastForJs(Context context){
        this.mContext = context;
    }

    //打开app
    @JavascriptInterface
    public void openApp(int appid, String pkgname,  String title, int ctype)
    {
	//实现处理逻辑...
    }
}
