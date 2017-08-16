package tk.ieayoio.h2k.html2keynote.convert;

import tk.ieayoio.h2k.utils.CompressUtils;
import tk.ieayoio.h2k.utils.DSResourceLoader;
import tk.ieayoio.h2k.utils.FileUtils;
import tk.ieayoio.h2k.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ieayoio on 2017/7/19.
 */
public class Html2Key {


    public static String tmpPath = DSResourceLoader.getDSResourceLoader("tmp").getPath();

//    public static String tmpPath = Html2Key.class.getClassLoader().getResource("tmp").getPath();

    public static String getPath(String parentPath, String file) {

        return parentPath + "/" + file;

    }

    public static String convert(String outPath, String... htmlFiles) throws FileNotFoundException {

        if (htmlFiles == null || htmlFiles.length == 0) {
            return null;
        }

        try {

            String pathForKey = StringUtil.isBank(outPath) ? tmpPath : outPath;

            String tmpFolder = UUID.randomUUID().toString();
            String outFolderPath = getPath(pathForKey, tmpFolder);
            File outFolder = new File(outFolderPath);
            outFolder.mkdirs();

            List<String> files = new ArrayList<>();

            for (int i = 0; i < htmlFiles.length; i++) {
                String htmlfile = htmlFiles[i];
                String path = getPath(outFolderPath, "pg_" + i + ".pdf");
                Html2Pdf.execute(htmlfile, path);
                files.add(path);
            }


            String path = getPath(outFolderPath, "index.apxl");
            IndexApxlGenerator.process(htmlFiles.length, path);
            files.add(path);

            String keyFileName = tmpFolder + ".key";

            String[] filesArray = files.toArray(new String[0]);
            CompressUtils.compressByZIP(getPath(outFolderPath, keyFileName), filesArray);

            FileUtils.deleteFiles(filesArray);

            return getPath(outFolderPath, keyFileName);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }


}
