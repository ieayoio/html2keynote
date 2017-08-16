package tk.ieayoio.h2k.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by ieayoio on 2017/7/18.
 */
public class CompressUtils {


    public static void compressByZIP(String outFile, String... files) throws IOException {

        if (files == null || files.length == 0) {
            return;
        }

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outFile));

        for (String filename : files) {
            File inFile = new File(filename);
            zipFile(inFile, zos, "");
        }
        zos.close();
    }


    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            for (File file : files)
                zipFile(file, zos, dir + inFile.getName() + "/");
        } else {
            String entryName = null;
            if (!"".equals(dir))
                entryName = dir + inFile.getName();
            else
                entryName = inFile.getName();
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);
            //文件缓冲区
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(inFile));
            byte[] datas = new byte[2048];
            int len = 0;
            while ((len = is.read(datas)) != -1)
                zos.write(datas, 0, len);
            is.close();
        }

    }


}
