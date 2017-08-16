package tk.ieayoio.h2k.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by ieayoio on 2017/7/14.
 */
public class FileUtils {

    public static void createIfNotExists(String pathname) {
        File file = new File(pathname);

        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 不再允许直接调用被deleteFiles所包含
    private static boolean deleteDir(String dir) {
        File file = new File(dir);
        return deleteDir(file);

    }

    public static void deleteFiles(String... files) {
        if (files == null || files.length == 0) {
            return;
        }
        for (String file : files) {
            deleteDir(file);
        }
    }


    public static boolean deleteDir(File dir) {

        if (!dir.exists()) {
            return true;
        }

        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            if (children != null && children.length > 0) {
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


}
