import java.io.*;
import java.util.*;




public class PhotoToSlide {	
	
	List<Slide> slidelist = new ArrayList();
	void parse(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		int photonumber = sc.nextInt();
		for(int i=0;i<photonumber;i++) {
			String HorV = sc.next();
			int tagnum = sc.nextInt();
			List<String> tags = new ArrayList();
			for(int j=0;j<tagnum;j++) {
				tags.add(sc.next());
			}
			
			
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
		
	}
	
	
	
	void print(String filename) {
		for(int i=0;i<slidelist;i++) {
			
		}
	}
	
}
