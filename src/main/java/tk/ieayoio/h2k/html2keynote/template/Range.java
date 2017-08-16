package tk.ieayoio.h2k.html2keynote.template;

/**
 * Created by ieayoio on 2017/7/13.
 */
public class Range {

    private int start; //目标起始下标
    private int end; // 目标结束下标+1

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Range() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLength() {
        int length = end - start;
        return length < 0 ? 0 : length;
    }


    @Override
    public String toString() {
        return "Range{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
