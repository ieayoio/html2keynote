package tk.ieayoio.h2k.html2keynote.convert;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * Created by ieayoio on 2017/7/19.
 */
public class Html2Pdf {

    public static void execute(String inputFile, String outputFile) throws FileNotFoundException {
//        String inputFile = "/Users/ieayoio/test/testkeynote/test.html";
        URI uri = new File(inputFile).toURI();

//        String outputFile = "/Users/ieayoio/test/testkeynote/test1.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(uri.toString());


//        URL simsun = DSResourceLoader.getDSResourceLoader("fonts/simsun.ttc").getURL();
//        URL simsun = Html2Pdf.class.getClassLoader().getResource("fonts/simsun.ttc");
        URL msyh = Html2Pdf.class.getClassLoader().getResource("fonts/msyh.ttf");

//        System.out.println(simsun);

        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        try {
//            fontResolver.addFont(simsun.getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);


            fontResolver.addFont(msyh.getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);


            // 解决图片的相对路径问题
            // renderer.getSharedContext().setBaseURL("file:/D:/Work/Demo2do/Yoda/branch/Yoda%20-%20All/conf/template/");

            renderer.layout();
            renderer.createPDF(os);
            os.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        execute("/Users/ieayoio/test/testkeynote/test.html", "/Users/ieayoio/test/testkeynote/test1.pdf");
    }
}
