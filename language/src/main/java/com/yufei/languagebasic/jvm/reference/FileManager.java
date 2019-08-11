package com.yufei.languagebasic.jvm.reference;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XinYufei on 2018/1/3.
 */
class FileManager {
    private Map<String, SoftReference<File>> imageCache = new HashMap<String, SoftReference<File>>();

    //保存File的软引用到HashMap
    public void saveFileToCache(String path) {
        // 强引用的File对象
        File File = new File(path);
        // 软引用的File对象
        SoftReference<File> softFile = new SoftReference<File>(File);
        // 添加该对象到Map中使其缓存
        imageCache.put(path, softFile);
        // 使用完后手动将位图对象置null
        File = null;
    }

    public File getFileByPath(String path) {

        // 从缓存中取软引用的File对象
        SoftReference<File> softFile = imageCache.get(path);
        // 判断是否存在软引用
        if (softFile == null) {
            return null;
        }
        // 取出File对象，如果由于内存不足File被回收，将取得空
        File File = softFile.get();
        return File;
    }
}