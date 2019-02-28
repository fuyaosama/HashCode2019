
public class simulator {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] inputs = {"a_example","b_lovely_landscapes","c_memorable_moments","d_pet_pictures","e_shiny_selfies"};
		for(String in: inputs) {
			photos pho = new photos();
			pho.parse(in+".in");
			pho.simulate();
			pho.print(in+".out");
		}
		
	}

}
