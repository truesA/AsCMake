package com.achers.ascmake.components;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.achers.ascmake.R;
import com.achers.ascmake.components.beatweb.Param;
import com.achers.ascmake.components.componentWeb.ComponentWebView;
import com.achers.ascmake.components.componentWeb.DecoratorWeb;
import com.achers.ascmake.components.componentWeb.WebBeas;

public class ComponentActivity extends AppCompatActivity {

    private WebView componentWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        componentWebView=findViewById(R.id.componentWebView);

        WebBeas decoratorWeb = new DecoratorWeb(this, componentWebView,null);
//        decoratorWeb.DecoratorWeb(componentWebView);
        decoratorWeb.setWebViewClient();
        decoratorWeb.loadUrl("https://www.jianshu.com/p/6658cfd26b22");


//        ComponentWebView componentWebView =new ComponentWebView(this);
//        componentWebView.ComponentWebView(componentWebView);
//
//        componentWebView.loadUrl("https://www.jianshu.com/p/6658cfd26b22");

    }

    public void component(View view){
        Component component =new ConcreteComponent();

        Component concreteDecoratorA = new ConcreteDecoratorA(component);
        Component concreteDecoratorB = new ConcreteDecoratorB(component);

        concreteDecoratorA.loadUrl();
        concreteDecoratorB.loadUrl();

    }
}
