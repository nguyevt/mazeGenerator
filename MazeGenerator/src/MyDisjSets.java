// Vincent Nguyen, CSE 373, WIN2014
// Assignment #3, 02/20/14
//
// This program's behavior is to provide an implementation of a disjoint
// set of ints, assuming that elements in the set are unique and numbered
// starting at 0 and ending at numElements - 1. 

public class MyDisjSets implements DisjointSets {

	private int[] up;
	private int numSets;
	
	// Caller provides an int value, corresponding to the desired size of the 
	// disjoint set.
	// Constructs a new disjoint set with size passed in as a parameter.
	public MyDisjSets(int numElements) {
		this.numSets = numElements;
		this.up = new int[numElements];
		for (int i = 0; i < up.length; i++) {
			up[i] = -1;
		}
	}
	
	// Throws InvalidSetNameException if either of set1 or set2 are not valid 
	// names of a set.
	// Throws InvalidElementException if either of set1 or set2 is not a 
	// valid element.
	// Caller provides the name of two sets.
	// Combines the two disjoint sets into one set.
	public void union(int set1, int set2) {
		error(set1);
		error(set2);
		if (up[set1] >= up[set2]) {
			up[set2] = up[set1] + up[set2];
			up[set1] = set2;
		} else {
			up[set1] = up[set1] + up[set2];
			up[set2] = set1;
		}
		numSets--;
	}
	
	// Throws InvalidElementException if x is not a valid element.
	// Caller provides an element being searched for.
	// Returns the name of the set containing the element being searched for.
	public int find(int x) {
		if (x < 0 || x >= up.length) {
			throw new InvalidElementException();
		}
		int value = x;
		while (up[value] > 0) {
			value = up[value];
		}
		if (x == value) {
			return value;
		}
		int temp = up[x];
		while (temp != value) {
			up[x] = value;
			x = temp;
			temp = up[x];
		}
		return value;
	}

	// Returns the current total number of sets.
	public int numSets() {
		return this.numSets;
	}

	// Throws InvalidElementException if x is not a valid element.
	// Caller provides the name of an element.
	// Returns true if the element is the name of a set, false otherwise.
	public boolean isSetName(int x) {
		if (x < 0 || x >= up.length) {
			throw new InvalidElementException();
		}
		return up[x] < 0;
	}

	// Throws InvalidSetNameException if either of set1 or set2 are not valid 
	// names of a set.
	// Throws InvalidElementException if either of set1 or set2 is not a 
	// valid element.
	// Caller provides the name of a set.
	// Returns the total number of elements in the set.
	public int numElements(int setNum) {
		error(setNum);
		return -1 * up[setNum];
	}

	// Throws InvalidSetNameException if either of set1 or set2 are not valid 
	// names of a set.
	// Throws InvalidElementException if either of set1 or set2 is not a 
	// valid element.
	// Caller provides the name of a set.
	// Prints out the elements in the given set.
	public void printSet(int setNum) {
		error(setNum);
		int[] set = getElements(setNum);
		System.out.print("{" + set[0]);
		for (int i = 1; i < set.length; i++) {
			System.out.print(", " + set[i]);
		}
		System.out.println("}");
	}

	// Throws InvalidSetNameException if either of set1 or set2 are not valid 
	// names of a set.
	// Throws InvalidElementException if either of set1 or set2 is not a 
	// valid element.
	// Caller provides the name of a set.
	// Returns an array containing the elements in the given set.
	public int[] getElements(int setNum) {
		error(setNum);
		int[] temp = new int[up.length];
		temp[0] = setNum;
		int size = getElementsHelper(temp, setNum, 1);
		int[] elements = new int[size];
		for (int i = 0; i < size; i++) {
			elements[i] = temp[i];
		}
		return elements;
	}
	
	// Caller provides an array of ints, the name of a set, and an index value.
	// Helper method called when getElements is called, which finds all the 
	// elements of a given set.
	// Returns the number of elements found in a specified set and modifies
	// the passed array to include the elements in the given set.
	private int getElementsHelper(int[] temp, int setNum, int index) {
		for (int i = 0; i < up.length; i++) {
			if (up[i] == setNum) {
				temp[index] = i;
				index = getElementsHelper(temp, i, index + 1);
			}
		}
		return index;
	}
	
	// Method that checks whether a given element is a valid element and valid
	// set name. Throws an InvalidElementException if the given element is 
	// invalid and throws an InvalidSetNameException if the given element is 
	// an invalid set name.
	private void error(int setNum) {
		if (setNum < 0 || setNum >= up.length) {
			throw new InvalidElementException();
		}
		if (!isSetName(setNum)) {
			throw new InvalidSetNameException();
		}
	}
}