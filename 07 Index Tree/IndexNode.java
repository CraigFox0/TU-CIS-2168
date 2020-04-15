import java.util.ArrayList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list; 

	IndexNode left;
	IndexNode right;
	
	// Constructors
	
	public IndexNode(String word, int lineNumber) {
		this.word = word;
		occurences = 1;
		list = new ArrayList<>();
		list.add(lineNumber);
	}

	public void addOccurence(int lineNumber) {
		occurences++;
		list.add(lineNumber);
	}

	// return the word, the number of occurrences, and the lines it appears on.
	// string is one line
	
	public String toString(){
		return word + " " + occurences + " " + list;
	}
	
	
	
}
