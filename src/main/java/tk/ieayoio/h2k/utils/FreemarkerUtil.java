package tk.ieayoio.h2k.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

/**
 * Created by ieayoio on 2017/8/9.
 */
public class FreemarkerUtil {

    /**
     * 根据模板文件生成代码
     *
     * @param template     resources/ftl下的模板文件名
     * @param params       参数
     * @param outputStream 输出
     * @throws IOException
     */
    public static void execute(String template, Map params, OutputStream outputStream) throws IOException {


        String path = DSResourceLoader.getDSResourceLoader("ftl").getPath();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        File file = new File(path);
        cfg.setDirectoryForTemplateLoading(file); //需要文件夹绝对路径
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template temp = cfg.getTemplate(template);
        Writer out = new OutputStreamWriter(outputStream);

        try {
            temp.process(params, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }


}
