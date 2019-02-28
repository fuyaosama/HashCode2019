import java.util.List;

public class Photo {
    List<Integer> tags;
    boolean mark;   // 0: H; 1: V

    public Photo(List<Integer> tags, boolean mark) {
        this.tags = tags;
        this.mark = mark;
    }
}
