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
	void arrangePhotos() {
		
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
