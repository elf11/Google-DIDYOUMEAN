import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 
 * @author Niculaescu Oana Georgiana
 * 
 * Clasa main, contine metoda ce calculeaza distanta Levenshtein intre doua cuvinte.
 *
 */


public class Main {
	
	/**
	 * 
	 * @param str1 - sirul trimis de comparat
	 * @param str2 - cuvant din dictionar
	 * @return ultima linie din matrice ce contine distante dintre fiecare subsir al sirului trimis ca parametru de comparat cu dictionarul
	 */
		
		public static int[] compute(String str1, String str2){		
			
			int str1Len = str1.length();
			int str2Len = str2.length();

			int[][] matrix = new int[str2Len + 1][str1Len + 1];
					
			matrix[0][0] = 0;
			
			for(int i = 1; i <= str2Len; i++)
				matrix[i][0] = i;
			
			for(int i = 1; i <= str1Len; i++)
				matrix[0][i] = i;
			
			for(int i = 1; i <= str1Len; i++)
				for(int j = 1; j <= str2Len; j++)
					if(str1.charAt(i-1) == str2.charAt(j-1))
						matrix[j][i] = matrix[j-1][i-1];
					else{
						int one = matrix[j-1][i] + 1;
						int two = matrix[j][i-1] + 1;
						int three = matrix[j-1][i-1] + 1;
						
						matrix[j][i] = Math.min(one, Math.min(two,three));
					}

			return matrix[str2Len];
		}

	
	
public static void main(String[] args) throws IOException{
		
		Type[] dic = new Type[8000];
		
		BufferedReader reader;
		String s;
		
		try{
			
			reader = new BufferedReader(new FileReader("dict.txt"));
			
			s = reader.readLine();
			
			int i = 0;
			
			while(s != null){
				
				String[] tokens;
				
				tokens = s.split(" ");
				
				dic[i]=new Type(tokens[0],Integer.parseInt(tokens[1]));
				i++;
				
				s = reader.readLine();
			}
		}catch(FileNotFoundException e){
			System.out.println("ERROR: That file can not be found.");
		}
		
		
		Scanner scan = new Scanner(System.in);
		
		String console;
		
		int[] array = null;
		console = scan.nextLine();
	
		int[][] LevenshteinMatrix = new int[console.length()][console.length()];
		
		
		/**
		 * initializarea unei matrice de containere in care vom retine numaruld e cuvinte din sirul nostru, distanta Levenshtein pana la 
		 * cel mai apropiat matching, frecventa matchingului si sirul obtinut -matchingul
		 */
		
		Container[][] intermediateMatrix = new Container[console.length()][console.length()];
		console = console.replaceAll(" ", "");
		
		for(int i = 0; i < console.length(); i++ ){
			for(int j = 0; j < console.length(); j++){
				intermediateMatrix[i][j]=new Container(0,0,null,0);
				intermediateMatrix[i][j].setDistLevenshtein(Integer.MAX_VALUE);
				intermediateMatrix[i][j].setWordNumber(0);
				intermediateMatrix[i][j].setFrequency(Integer.MIN_VALUE);
				intermediateMatrix[i][j].setObtained(null); //sirul obtinut
			}
		}
		
		
		
		for(int i = 0; i < console.length(); i++){
			String str = "";
			
			str = console.substring(i,console.length()); 
			
			/**
			 * Initializare matrice cu cele mai apropiate valori pentru fiecare dintre subsirurile ce compun sirul original fara spatii.
			 */
				
			for(int p = 0; p < 8000; p++){
				
				array = compute(str, dic[p].getStr());
			
				for(int j = i; j < console.length(); j++){
						if(intermediateMatrix[i][j].getDistLevenshtein() > array[j - i + 1]){
							intermediateMatrix[i][j].setDistLevenshtein(array[j - i + 1]);
							intermediateMatrix[i][j].setFrequency(dic[p].getFrequency());
							intermediateMatrix[i][j].setWordNumber(1);
							intermediateMatrix[i][j].setObtained(dic[p].getStr());
						}
						else 
							
						if(intermediateMatrix[i][j].getDistLevenshtein() == array[j - i +1]){
							if(intermediateMatrix[i][j].getFrequency() < dic[p].getFrequency()){
								intermediateMatrix[i][j].setDistLevenshtein(array[j - i +1]);
								intermediateMatrix[i][j].setFrequency(dic[p].getFrequency());
								intermediateMatrix[i][j].setWordNumber(1);
								intermediateMatrix[i][j].setObtained(dic[p].getStr());
							}
						}
				}
				
			}
		}
		
		for(int len = 2; len <= console.length(); len++){
			for(int i = 0; i <= console.length() - len; i++){
				int j = len + i -1;
				
				for(int k = i; k < j; ++k){
					if (intermediateMatrix[i][j].getDistLevenshtein() > intermediateMatrix[i][k].getDistLevenshtein() + intermediateMatrix[k+1][j].getDistLevenshtein()){
						intermediateMatrix[i][j].setDistLevenshtein(intermediateMatrix[i][k].getDistLevenshtein() + intermediateMatrix[k+1][j].getDistLevenshtein());
						intermediateMatrix[i][j].setFrequency(intermediateMatrix[i][k].getFrequency() + intermediateMatrix[k+1][j].getFrequency());
						intermediateMatrix[i][j].setWordNumber(intermediateMatrix[i][k].getWordNumber() + intermediateMatrix[k+1][j].getWordNumber());
						intermediateMatrix[i][j].setObtained(intermediateMatrix[i][k].getObtained() + " "+ intermediateMatrix[k+1][j].getObtained());
					}
					else
					if(intermediateMatrix[i][j].getDistLevenshtein() == intermediateMatrix[i][k].getDistLevenshtein() + intermediateMatrix[k+1][j].getDistLevenshtein()){
						
						if(intermediateMatrix[i][j].getWordNumber() > intermediateMatrix[i][k].getWordNumber() + intermediateMatrix[k+1][j].getWordNumber()){
							intermediateMatrix[i][j].setDistLevenshtein(intermediateMatrix[i][k].getDistLevenshtein() + intermediateMatrix[k+1][j].getDistLevenshtein());
							intermediateMatrix[i][j].setFrequency(intermediateMatrix[i][k].getFrequency() + intermediateMatrix[k+1][j].getFrequency());
							intermediateMatrix[i][j].setWordNumber(intermediateMatrix[i][k].getWordNumber() + intermediateMatrix[k+1][j].getWordNumber());
							intermediateMatrix[i][j].setObtained(intermediateMatrix[i][k].getObtained() + " "+ intermediateMatrix[k+1][j].getObtained());
						}else
							if(intermediateMatrix[i][j].getWordNumber() == intermediateMatrix[i][k].getWordNumber() + intermediateMatrix[k+1][j].getWordNumber()){
							
								if(intermediateMatrix[i][j].getFrequency() < intermediateMatrix[i][k].getFrequency() + intermediateMatrix[k+1][j].getFrequency()){
									intermediateMatrix[i][j].setDistLevenshtein(intermediateMatrix[i][k].getDistLevenshtein() + intermediateMatrix[k+1][j].getDistLevenshtein());
									intermediateMatrix[i][j].setFrequency(intermediateMatrix[i][k].getFrequency() + intermediateMatrix[k+1][j].getFrequency());
									intermediateMatrix[i][j].setWordNumber(intermediateMatrix[i][k].getWordNumber() + intermediateMatrix[k+1][j].getWordNumber());
									intermediateMatrix[i][j].setObtained(intermediateMatrix[i][k].getObtained() + " "+ intermediateMatrix[k+1][j].getObtained());
								}else
									if(intermediateMatrix[i][j].getFrequency() == intermediateMatrix[i][k].getFrequency() + intermediateMatrix[k+1][j].getFrequency()){
									 if(intermediateMatrix[i][j].getObtained().compareTo(intermediateMatrix[i][k].getObtained() + " "+ intermediateMatrix[k+1][j].getObtained())>0){
										 intermediateMatrix[i][j].setDistLevenshtein(intermediateMatrix[i][k].getDistLevenshtein() + intermediateMatrix[k+1][j].getDistLevenshtein());
										 intermediateMatrix[i][j].setFrequency(intermediateMatrix[i][k].getFrequency() + intermediateMatrix[k+1][j].getFrequency());
										 intermediateMatrix[i][j].setWordNumber(intermediateMatrix[i][k].getWordNumber() + intermediateMatrix[k+1][j].getWordNumber());
										 intermediateMatrix[i][j].setObtained(intermediateMatrix[i][k].getObtained() + " "+ intermediateMatrix[k+1][j].getObtained());
									 }
									}
						}
					}	
				}
			}
		}
		
		
		
		System.out.println(intermediateMatrix[0][console.length() - 1].getObtained());
	}

}
