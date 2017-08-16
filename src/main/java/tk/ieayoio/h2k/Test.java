package tk.ieayoio.h2k;

import tk.ieayoio.h2k.html2keynote.convert.Html2Key;
import tk.ieayoio.h2k.utils.DSResourceLoader;
import tk.ieayoio.h2k.utils.FileUtils;

import java.io.FileNotFoundException;

/**
 * Created by ieayoio on 2017/8/16.
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        String tmpPath = Html2Key.tmpPath;

        FileUtils.deleteFiles(tmpPath);

        long s = System.currentTimeMillis();
        System.out.println(s);

        ClassLoader classLoader = Html2Key.class.getClassLoader();

        String table1 = DSResourceLoader.getDSResourceLoader("test/table.html").getPath();
        String table2 = DSResourceLoader.getDSResourceLoader("test/table2.html").getPath();
//        String table1 = classLoader.getResource("test/table.html").getPath();
//        String table2 = classLoader.getResource("test/table2.html").getPath();

        String convert = Html2Key.convert(tmpPath, table1, table2, table1, table2, table1, table2, table1, table2, table1, table2);
        long e = System.currentTimeMillis();
        System.out.println(e);
        System.out.println("e-s:" + (e - s));
        System.out.println(convert);


    }
}
