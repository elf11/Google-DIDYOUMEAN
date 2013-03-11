/**
 * 
 * @author Niculaescu Oana 321CB
 *
 */
public class Container {
	
	private int wordNumber;
	private int frequency;
	private String obtained;
	private int distLevenshtein;
	
	Container(int wn, int f, String s, int leven){
		this.setWordNumber(wn);
		this.setFrequency(f);
		this.setObtained(s);
		this.setDistLevenshtein(leven);
	}

	public int getWordNumber() {
		return wordNumber;
	}

	public void setWordNumber(int wordNumber) {
		this.wordNumber = wordNumber;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getObtained() {
		return obtained;
	}

	public void setObtained(String obtained) {
		this.obtained = obtained;
	}

	public int getDistLevenshtein() {
		return distLevenshtein;
	}

	public void setDistLevenshtein(int distLevenshtein) {
		this.distLevenshtein = distLevenshtein;
	}


}
