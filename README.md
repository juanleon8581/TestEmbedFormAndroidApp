# Embed our form with a webview in a Android App

To use our form in your native Android application, you first need a WebView. You can find the official documentation for this component here.
[https://developer.apple.com/documentation/webkit/wkwebview](https://developer.android.com/reference/android/webkit/WebView)

Here is a simple usage example.

1. Create a Layout file.
2. Insert the following code

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="16dp"
    android:paddingTop="16dp"
    android:paddingRight="16dp"
    android:paddingBottom="16dp"
    tools:context=".MainActivity">

    <WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```

3. In the AndroidManifest.xml add

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

4. In the MainActivity add

```kotlin
private lateinit var webView: WebView

  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      val jsInterface = MyJavaScriptInterface()
      webView = findViewById(R.id.webView)
      webView.settings.javaScriptEnabled = true // Enable JavaScript if needed
      webView.addJavascriptInterface(jsInterface, "Android")

      // Set a WebViewClient to handle the navigation within the WebView
      webView.webViewClient = object : WebViewClient() {
          override fun shouldOverrideUrlLoading(
              view: WebView?,
              request: WebResourceRequest?
          ): Boolean {
              view?.loadUrl(request?.url.toString())
              return true
          }
      }

      // Load a URL in the WebView
      val urlToShow = "http://192.168.1.131:3000" //Replace by the URL you need
      webView.loadUrl(urlToShow)

  }
```

```kotlin
//This is the function that receives the postmessage and processes it.
class MyJavaScriptInterface {
    @JavascriptInterface
    fun postMessageHandler(message: String?) {
        val tag = "PostMessage"
        println(message)
        Log.d(tag, message.toString())
    }
}
```