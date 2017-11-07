package com.example.administrator.newsubject.utils;

import com.example.administrator.newsubject.base.BaseApplication;

import java.io.File;

/**
 * @author 涂文远
 * @version $Rev$
 * @time 2016/12/26 13:42
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class FileUtil {
    /**
     * @return  创建缓存目录
     */
    public static File getcacheDirectory()
    {
        File file = new File(BaseApplication.getInstance().getApplicationContext().getExternalCacheDir(), "UgouCache");
        if(!file.exists())
            file.mkdirs();
        return file;
    }
}
