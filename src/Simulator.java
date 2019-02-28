
import java.io.*;
import java.util.*;

public class Simulator {
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] inputs = {"a_example","b_lovely_landscapes","c_memorable_moments","d_pet_pictures","e_shiny_selfies"};
		for(String in: inputs) {
			PhotoToSlide pho = new PhotoToSlide();
			pho.parse(in+".txt");
			pho.startsim();
			pho.print(in+"out"+".txt");
		}
		
	}

}
