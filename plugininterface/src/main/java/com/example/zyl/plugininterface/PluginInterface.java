package com.example.zyl.plugininterface;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by zhaoyinglong on 2017/11/14.
 */

public interface PluginInterface {
    //绑定上下文
    void attach(Activity activity);

    void onCreat(Bundle bundle);
}
