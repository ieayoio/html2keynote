package tk.ieayoio.h2k.html2keynote.data;

import java.util.HashMap;

/**
 * Created by ieayoio on 2017/7/12.
 */
public class VarPackage extends HashMap<String, Integer> {

    public static final String BASE = "base";
    public static final String STEP = "step";

    public VarPackage(Integer base, Integer step) {
        put(VarPackage.BASE, base);
        put(VarPackage.STEP, step);
    }

    public static VarPackage var(Integer base, Integer step) {
        return new VarPackage(base, step);
    }

    public Integer getBase() {
        return get(VarPackage.BASE);
    }

    public Integer getStep() {
        return get(VarPackage.STEP);
    }
}
