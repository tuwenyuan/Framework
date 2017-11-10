package com.example.administrator.newsubject.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.example.administrator.newsubject.base.BaseApplication;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;

/**
 * @描述: 文件帮助类
 * @项目名: ugou
 * @包名: com.ugou88.ugou.common.utils
 * @类名: FileUtil
 * @作者: zuojie
 * @创建时间: 2016/3/15
 */
public class FileUtil {

    private static final String TAG = FileUtil.class.getCanonicalName();

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

    /**
     * 判断是否有SDCard
     *
     * @return 有为true，没有为false
     */
    public static boolean hasSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 获取SDCard的目录路径功能
     */
    public static String getSDCardPath() {
        File sdcardDir = null;
        if (hasSDCard()) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }

    public static long getAvailableStorage() {

        String storageDirectory = null;
        storageDirectory = Environment.getExternalStorageDirectory().toString();

        try {
            StatFs stat = new StatFs(storageDirectory);
            long avaliableSize = ((long) stat.getAvailableBlocks() * (long) stat.getBlockSize());
            return avaliableSize;
        } catch (RuntimeException ex) {
            return 0;
        }
    }


    /**
     * 获取文件名
     *
     * @param str url或文件路径
     * @return
     */
    public static String getFileName(String str) {

        String filename = null;


        try {
            if (!TextUtils.isEmpty(str)) {
                // 形如http://cheshi.qiniudn.com/102445/HeaderFace/140617101722116.jpg?imageView2/0/w/230/h/230
                if (str.contains("?")) {
                    // 问号的转义：? ==> \\?
                    String[] strAry = str.split("\\?");
                    if (!TextUtils.isEmpty((strAry[0]))) {
                        String subFilename = strAry[0].substring(strAry[0].lastIndexOf("/") + 1);
                        String name = subFilename.substring(0, subFilename.indexOf("."));
                        String suffix = subFilename.substring(subFilename.indexOf(".") + 1);
                        String w = str.substring(str.lastIndexOf("/") + 1);

                        filename = name + "-" + w + "." + suffix;
                    }

                } else {
                    filename = str.replaceAll("/", "").replace(":", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;

    }

    public static String getFileNameFromUrl(String url) {
        // 通过 ‘？’ 和 ‘/’ 判断文件名
        int index = url.lastIndexOf('?');
        String filename;
        if (index > 1) {
            filename = url.substring(url.lastIndexOf('/') + 1, index);
        } else {
            filename = url.substring(url.lastIndexOf('/') + 1);
        }

        if (filename == null || "".equals(filename.trim())) {// 如果获取不到文件名称
            filename = UUID.randomUUID() + ".mp4";// 默认取一个文件名
        }
        return filename;
    }


    /**
     * 获取文件名
     *
     * @param file 文件
     * @return
     */
    public static String getFileName(File file) {
        return getFileName(file.getAbsolutePath());
    }


    public static void mkdirs(String path) {
        if (TextUtils.isEmpty(path)) {
            //ILog.d("IFileUtil.mkdirs:文件目录为空");
            return;
        }

        mkdirs(new File(path));
    }

    public static void mkdirs(File file) {
        if (!file.exists()) {
            boolean isSuccess = file.mkdirs();
            //ILog.d("FileUtil.mkdirs:文件夹创建" + (isSuccess ? "成功" : "失败"));
        } else {
            //ILog.d("FileUtil.mkdirs:文件夹已存在");
        }
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file != null && file.exists()) {
                file.delete();
                return true;
            }
        }

        return false;
    }

    /**
     * 创建文件
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File createNewFile(String filePath, String fileName)
            throws IOException {

        File folderFile = new File(filePath);

        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }

        File file = new File(filePath, fileName);
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        return file;
    }

    /**
     * 获取文件后缀
     *
     * @param path
     * @return
     */
    public static String getFileSuffix(String path) {
        String type = "jpg";
        try {
            int index = path.lastIndexOf('.');
            if (index != -1) {
                type = path.substring(index + 1).toLowerCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return type;
    }

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public static String getFileSuffix(File file) {
        String path = file.getAbsolutePath();
        return getFileSuffix(path);
    }

    public static String getFilePathByType(String type) {
        return Environment.getExternalStorageDirectory().getPath()+ "/." + "framwork" + "/.image" + File.separator + type;
    }

    public static boolean isUrl(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String regex = "^(https|http|ftp|file)?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(path);
        return matcher.matches();
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {                                    // 文件存在时
                InputStream inStream = new FileInputStream(oldPath);    // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;                                // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取压缩比例
     *
     * @param options
     * @param targeW
     * @param targeH
     * @return
     */
    private static int computeSampleSize(BitmapFactory.Options options, int targeW, int targeH) {
        int w = options.outWidth;
        int h = options.outHeight;
        float candidateW = w / targeW;
        float candidateH = h / targeH;

        float candidate = Math.max(candidateW, candidateH);
        if (candidate == 0) {
            return 1;
        }
        if (candidate > 1) {
            if ((w > targeW) && (w / candidate) < targeW) {
                candidate -= 1;
            }
        }

        if (candidate > 1) {
            if ((h > targeH) && (h / candidate) < targeH) {
                candidate -= 1;
            }
        }

        return (int) candidate;
    }

    /**
     * 根据Bitmap保存文件
     *
     * @param bitmap
     */
    public static void saveFileByBitmap(Bitmap bitmap, String savePath) {
        if (null != bitmap) {
            File file = new File(savePath);
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bos) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据Bitmap保存文件
     *
     * @param bitmap
     */
    public static boolean saveFileByBitmapTrue(Bitmap bitmap, String savePath) {
        boolean flag = false;
        if (null != bitmap) {
            try {
                File path = new File(savePath);
                String filepath = savePath + File.separator + System.currentTimeMillis() + ".jpg";
                File file = new File(filepath);
                if (!path.exists()) {
                    path.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            return flag;
        }
        return flag;
    }


    /**
     * 写入文件内容
     *
     * @param str      需要写入的内容
     * @param savePath 文件路径
     * @return
     */
    public static boolean saveFileByString(String str, String savePath) {
        boolean flag = false;
        if (null != str) {
            FileOutputStream o = null;
            try {
                File file = new File(savePath);
                if (file.exists()) {
                    file.delete();
                }
                o = new FileOutputStream(file);
                o.write(str.getBytes("UTF-8"));
                o.close();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            return flag;
        }
        return flag;
    }

    public static boolean saveFileByBody(ResponseBody body, String savePath) {
        File file = new File(savePath);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        long fileSize = body.contentLength();

        try {
            byte[] fileReader = new byte[1024 * 1024];
            long fileSizeDownloaded = 0;
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
                 //ILog.e("file download: " + fileSizeDownloaded + " of " + fileSize);
            }
            //ILog.e("file download: " + fileSizeDownloaded + " of " + fileSize);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isFileExists(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            if(file.length() > 0){
                return true;
            }
        }
        return false;
    }
    /**
     * 写入Bean对象文件内容
     *
     * @param obj      bean对象
     * @param savePath 文件路径
     * @return
     */
    public static boolean saveFileByBean(Object obj, String savePath) {
        String jsonStr = new Gson().toJson(obj);
        return FileUtil.saveFileByString(jsonStr, savePath);
    }

    /**
     * 读取文件内容
     *
     * @param savePath 读取的文件路径
     * @return [str] 返回读取的内容
     */
    public static String readFileToString(String savePath) {
        String str = "";
        File file = new File(savePath);
        if (!file.exists()) {
            return str;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            int len = in.available();
            byte[] buf = new byte[len];
            in.read(buf);
            str = new String(buf, "UTF-8");
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return str;
    }

    /**
     * 读取Bean对象文件内容
     *
     * @param savePath 文件路径
     * @param classOfT bean对象类型
     * @param <T>
     * @return
     */
    public static <T> T readFileToBean(String savePath, Class<T> classOfT) {
        String jsonStr = readFileToString(savePath);
        return new Gson().fromJson(jsonStr, classOfT);
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String filePath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean deleteAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                deleteAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
//              delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }
}
