package com.example.zyl.taopp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.zyl.plugininterface.PluginInterface;

/**
 * Created by zhaoyinglong on 2017/11/14.
 */

public class BaseActivity extends Activity implements PluginInterface {

    protected Activity that;

    @Override
    public void attach(Activity activity) {
        that = activity;
    }

    @Override
    public void onCreat(Bundle bundle) {

    }

    @Override
    public void setContentView(View view) {
        that.setContentView(view);
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }


    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @Nullable
    @Override
    public ActionBar getActionBar() {
        return that.getActionBar();
    }
}
