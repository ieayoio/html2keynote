package tk.ieayoio.h2k.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ieayoio on 2017/7/26.
 */
public class DSResourceLoader {

    private static ResourceLoader loader = new DefaultResourceLoader();

    private String name;

    private Resource resource;


    private DSResourceLoader(String name) {
        this.name = name;
        this.resource = loader.getResource(name);

    }

    public static DSResourceLoader getDSResourceLoader(String name) {
        return new DSResourceLoader(name);
    }

    public String getPath() {
        try {
            return resource.getURL().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public URL getURL() {
        try {
            return resource.getURL();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public File getFile() {
        try {
            return resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getInputStream() {
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFileName() {
        return resource.getFilename();
    }

    public static void main(String[] args) {


        System.out.println(DSResourceLoader.getDSResourceLoader("test/table.html").getPath());

    }


}
