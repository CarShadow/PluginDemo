package com.example.zyl.plugindemo;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zyl.plugininterface.PluginInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhaoyinglong on 2017/11/14.
 */

public class ProxyActivity extends Activity {

    private String className; //需要加载的页面的全路径 com.example.zyl.taopp.MainActivity

    private PluginInterface mPluginInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        className = getIntent().getStringExtra("className");

        try {
//            Class clz = Class.forName(className);
            Class clz = getClassLoader().loadClass(className);
            Constructor constructor = clz.getConstructor(new Class[]{});
            Object obj = constructor.newInstance(new Object[]{});
            mPluginInterface = (PluginInterface) obj;

            mPluginInterface.attach(this);

            Bundle bundle = new Bundle();

            mPluginInterface.onCreat(bundle);

        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * tpp的资源
     *
     * @return
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }


    /**
     * tpp的class
     *
     * @return
     */
    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getClassLoader();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    @Override
//    public void startActivity(Intent intent) {
//        String className = intent.getStringExtra("className");
//        Intent intent1 = new Intent(this, ProxyActivity.class);
//        intent1.putExtra("className", className);
//        super.startActivity(intent1);
//    }
}
