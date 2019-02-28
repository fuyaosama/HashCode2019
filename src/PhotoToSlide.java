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
			
			photolist.add(new Photo(tags, HorV.equals("H")));
		}
		sc.close();
	}
	void startsim() {
		
	}

    /**
     * Transfer all the photos into slides.
     */
    void arrangePhotos() {

        // Iterate all the photos.
        Photo lstVPhoto = null;
        int lstVPhotoIndex = -1;
        for(int i = 0; i < photolist.size(); i++) {
            Photo photo = photolist.get(i);
            if(photo.mark == false) {       // Horizontal Photo
                List<Integer> srcPhotos = new ArrayList<>();
                srcPhotos.add(i);
                slidelist.add(new Slide(photo.tags, srcPhotos));
            }
            else {      // Vertical Photo
                if(lstVPhotoIndex == -1) {     // Wait until collect 2 Vertical photos
                    lstVPhoto = photo;
                    lstVPhotoIndex = i;
                    continue;
                }
                List<Integer> srcPhotos = new ArrayList<>();
                srcPhotos.add(i);
                srcPhotos.add(lstVPhotoIndex);
                List<Integer> newTags = new ArrayList<>(lstVPhoto.tags);
                newTags.addAll(photo.tags);
                slidelist.add(new Slide(newTags, srcPhotos));

                lstVPhoto = null;
                lstVPhotoIndex = -1;
            }
        }
    }
	void arrangeSlides() {
		
	}
	void score() {
		Scorer sc = new Scorer(slidelist);
		System.out.print(sc.score(slidelist));
	}
	
	
	
	void print(String filename) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
	    writer.write(slidelist.size());
	    writer.newLine();
		for(int i=0;i<slidelist.size();i++) {
			for(Integer pho : slidelist.get(i).sourcePhotos) {
				writer.write(pho);
			}
			writer.newLine();
			
		}
		writer.close();
	}
}
