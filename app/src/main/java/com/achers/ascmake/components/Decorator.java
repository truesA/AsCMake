package com.achers.ascmake.components;

/**
 * Created on 2018/7/10 15:49
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks: 抽象的装饰者 持有一个被装饰者
 */
public class Decorator extends Component{

    private Component component;


    public Decorator(Component component){
        this.component=component;
    }

    @Override
    public void loadUrl() {
        component.loadUrl();
    }


    public void setComponents(){

    }
}
