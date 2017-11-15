package com.example.zyl.plugindemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by zhaoyinglong on 2017/11/14.
 */

public class PluginManager {

    //tpp  的资源
    private Resources mResources;

    //tpp 的class
    private DexClassLoader mDexClassLoader;

    private Context mContext;

    private PackageInfo mPakageInfo;

    private PluginManager() {
    }

    private static final PluginManager instance = new PluginManager();

    public static PluginManager getInstance() {
        return instance;
    }

    public void loadPath(String path) {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            mPakageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
            File dexOutFile = mContext.getDir("dex", Context.MODE_PRIVATE);

            mDexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath(),
                    null, mContext.getClassLoader());

            AssetManager assetManager = AssetManager.class.newInstance();

            Method addAssertPath = AssetManager.class.getMethod("addAssetPath", String.class);

            addAssertPath.invoke(assetManager, path);
            mResources = new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());
        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Resources getResources() {
        return mResources;
    }

    public DexClassLoader getClassLoader() {
        return mDexClassLoader;
    }

    public PackageInfo getPakageInfo() {
        return mPakageInfo;
    }
}
