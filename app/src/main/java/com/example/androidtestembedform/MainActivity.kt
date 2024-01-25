package com.example.androidtestembedform

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.util.Log


//import androidx.appcompat.app.AppCompatActivity


class MyJavaScriptInterface {
    @JavascriptInterface
    fun postMessageHandler(message: String?) {
        val tag = "PostMessage"
        println(message)
        Log.d(tag, message.toString())
    }
}


class MainActivity : ComponentActivity() {

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
        val urlToShow = "http://192.168.1.131:3000"
        //val urlToShow = "https://consdev.inaltectest.com/consentimientosportal/?token=ZjQ5NjllZmYzYTA4NDJhNDg0MTA3NmE0MjA2M2EwZDAsNjU4MDY4MWEwZTUwZGIxNWVlY2I4NjUw&edit=true"
        webView.loadUrl(urlToShow)

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTestEmbedFormTheme {
        Greeting("Inaltec")
    }
}*/