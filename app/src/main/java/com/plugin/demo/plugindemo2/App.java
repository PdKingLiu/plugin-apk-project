package com.plugin.demo.plugindemo2;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * @author liupeidong
 * Created on 2019/10/21 21:58
 */
public class App extends Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "060f143283bcbc6daa1fc0b82c9e0589");
    }

}
