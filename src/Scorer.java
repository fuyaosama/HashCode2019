import java.util.ArrayList;
import java.util.List;

public class Scorer {
    List<Photo> photoList;

    public Scorer(List<Photo> photoList) {
        // Set references to Photo
        this.photoList = photoList;
    }

    public int score(List<Slide> slideList) {

        int ans = 0;
        // Iterate all the slides
        Slide lstSlide = null;
        for(Slide slide: slideList) {
            if(lstSlide == null) {
                lstSlide = slide;
                continue;
            }

            List<Integer> intersection = new ArrayList<>(lstSlide.tags);
            intersection.retainAll(slide.tags);

            List<Integer> lstSlideDif = new ArrayList<>(lstSlide.tags);
            lstSlideDif.removeAll(intersection);

            List<Integer> curSlideDif = new ArrayList<>(slide.tags);
            curSlideDif.removeAll(intersection);

            int sharedTagsCnt = intersection.size();
            int lstSlideTagsCnt = lstSlideDif.size();
            int curSlideTagsCnt = curSlideDif.size();

            ans += Math.min(sharedTagsCnt, Math.min(lstSlideTagsCnt, curSlideTagsCnt));
            lstSlide = slide;
        }
        
        return ans;
    }
}
