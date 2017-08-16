package tk.ieayoio.h2k.html2keynote.convert;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import tk.ieayoio.h2k.html2keynote.data.DataBasic;
import tk.ieayoio.h2k.utils.DSResourceLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ieayoio on 2017/7/13.
 */
@SuppressWarnings("all")
public class IndexApxlGenerator {

    public static final String rootKey = "data";

    public static final String pageCount = "pageCount";

    /**
     * pdf命名规则必须是pg_0.pdf,pg_1.pdf,pg_2.pdf...
     *
     * @param count
     * @throws IOException
     * @throws TemplateException
     */
    public static void process(int count, String outpath) throws IOException {


        //获取项目根目录路径

        String path = DSResourceLoader.getDSResourceLoader("ftl").getPath();
//        String path = IndexApxlGenerator.class.getClassLoader().getResource("ftl").getPath();
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */
        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        File file = new File(path);
        cfg.setDirectoryForTemplateLoading(file); //需要文件夹绝对路径
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */
        /* Create a data-model */
        Map root = new HashMap();
        root.put(pageCount, count - 1);

        root.put(rootKey, DataBasic.getMap());
        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("index_apxl.ftl");
        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(new FileOutputStream(outpath));

//        Writer out = new OutputStreamWriter(System.out);
        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.

    }

    public static void main(String[] args) throws IOException, TemplateException {
        IndexApxlGenerator.process(6, "/Users/ieayoio/test/dfg/aotu/test.xml");
    }
}
