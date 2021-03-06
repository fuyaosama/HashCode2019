import java.io.*;
import java.util.*;




public class PhotoToSlide {	
	List<Photo> photolist = new ArrayList();
	List<Slide> slidelist = new ArrayList();
	HashMap<String,Integer> tagindexmap = new HashMap();
	int tagcount = 0;
	void parse(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		int photonumber = sc.nextInt();
		
		for(int i=0;i<photonumber;i++) {
			String HorV = sc.next();
			int tagnum = sc.nextInt();
			List<Integer> tags = new ArrayList();
			for(int j=0;j<tagnum;j++) {
				String tag = sc.next();
				if(tagindexmap.containsKey(tag)) {
					tags.add(tagindexmap.get(tag));
				}
				else {
					tags.add(tagcount);
					tagindexmap.put(tag, tagcount++);
					
				}
				
			}
			
			photolist.add(new Photo(tags, !HorV.equals("H")));
		}
		sc.close();
	}
	void startsim() {
		arrangePhotos();
		arrangeSlides();
		score();
	}

    /**
     * Transfer all the photos into slides.
     */
    void arrangePhotos() {

        PriorityQueue<Integer> vPhotoQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return photolist.get(o1).tags.size() - photolist.get(o2).tags.size();
            }
        });

        // Iterate all the photos.
        for(int i = 0; i < photolist.size(); i++) {
            Photo photo = photolist.get(i);
            if (!photo.mark) {       // Horizontal Photo
                List<Integer> srcPhotos = new ArrayList<>();
                srcPhotos.add(i);
                slidelist.add(new Slide(photo.tags, srcPhotos));
            } else {      // Vertical Photo
                vPhotoQueue.add(i);
            }
        }

        while(vPhotoQueue.size() > 1) {
            int photo1 = vPhotoQueue.poll();
            int photo2 = vPhotoQueue.poll();

            List<Integer> srcPhotos = new ArrayList<>();
            srcPhotos.add(photo1);
            srcPhotos.add(photo2);
            List<Integer> newTags = new ArrayList<>();
            newTags.addAll(photolist.get(photo1).tags);
            newTags.addAll(photolist.get(photo2).tags);
            slidelist.add(new Slide(newTags, srcPhotos));
        }
    }
	void arrangeSlides() {
		Collections.sort(slidelist,(a,b)->b.tags.size()-a.tags.size());
		Slide slide,comp;
		Scorer sc  = new Scorer();
		for(int i=1;i<slidelist.size();i++) {
			int max = 0,pos=i;
			slide = slidelist.get(i-1);
			for(int j=i;j<i+1000&&j<slidelist.size();j++) {
				comp = slidelist.get(j);
				int temp = sc.scoretwo(slide, comp);
				if(temp>max) {
					max = temp;
					pos = j;
				}
			}
			comp = slidelist.get(pos);
			slidelist.remove(pos);
			slidelist.add(i,comp);
		}
	}
	void score() {
		Scorer sc = new Scorer();
		System.out.println(sc.score(slidelist));
	}
	
	
	
	void print(String filename) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
	    writer.write(String.valueOf(slidelist.size()));
	    writer.newLine();
		for(int i=0;i<slidelist.size();i++) {
			for(Integer pho : slidelist.get(i).sourcePhotos) {
				writer.write(String.valueOf(pho));
				writer.write(" ");
			}
			writer.newLine();
		}
		writer.close();
	}
}
