/**
 * 
 * @author Niculaescu Oana 321CB
 *
 */
public class Type {
	
	private String str;
	private int frequency;
	
	public Type(String str, int frequency){
		this.setStr(str);
		this.setFrequency(frequency);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
