// TESTING CODE

import java.util.Arrays;


public class TestScripts {

	public static void main(String[] args) {
		
		DisjointSets zero = new MyDisjSets(1);
		System.out.println(zero.numSets());
		zero.printSet(0);
		
		DisjointSets test = new MyDisjSets(8);
		
		System.out.println("Total sets (should be 8): " + test.numSets());
		System.out.println("Test single element case: ");
		test.printSet(0);
		System.out.println(Arrays.toString(test.getElements(0)));
		System.out.println();
		test.union(2,1);
		test.union(6,5);
		test.union(4,7);
		test.union(5,7);
		System.out.println("2 elements in set 1: " + test.numElements(1));
		System.out.println("4 elements in set 7: " + test.numElements(7));
		System.out.println("1 element in set 0: " + test.numElements(0));
		System.out.println("Find element 6: should return 7: " + test.find(6));
		test.printSet(7);
		System.out.println("Number of sets: should return 4: " + test.numSets());
		System.out.println("False, 4 is not a set name: " + test.isSetName(4));
		System.out.println("True, 7 is a set name: " + test.isSetName(7));
		System.out.println(Arrays.toString(test.getElements(7)));
		test.union(7, 3);
		test.union(test.find(1), 7);
		test.union(0, 7);
		// All disjoint sets are now combined into one set
		System.out.println("Total sets = 1: " + test.numSets());
		System.out.println(Arrays.toString(test.getElements(7)));
		test.printSet(7);
		
		DisjointSets test2 = new MyDisjSets(12);
		test2.union(1, 2);
		test2.union(4, 8);
		test2.union(5, 6);
		test2.union(3, test2.find(1));
		test2.union(6, 7);
		test2.find(6);
		test2.union(2, 6);
		test2.union(test2.find(5), 8);
		test2.find(3);	// Check for path compression (use debugger)
		test2.find(4); 	// Check for path compression (use debugger)
		
		// Check for exceptions (comment out to test different cases)
//		test2.find(13);	// InvalidElementException
//		test2.union(5, 6);	// InvalidSetNameException
		
		DisjointSets test3 = new MyDisjSets(2);
		test3.union(0, 1);
		test3.find(1);
		
	}
	
	
	
}
