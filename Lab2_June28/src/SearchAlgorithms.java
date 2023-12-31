import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
/**
 * A class to implement linear search algorithm and interpolation algorithm for an integer
 * array and a search key in the array. Users will be prompted for all inputs.
 * A random generated array of size 1000 is used to determine the fasted search method
 * 
 * @author olayinkaafolabi
 *
 */
public class SearchAlgorithms 
{
	//create a method for the linear search algorithm
	/**
	 * A search algorithm that sequentially checks each element in a list
	 * or array until it find the target which is a key in this case or reaches the end of the list
	 * 
	 * @param array
	 * @param key
	 * @return
	 */
	public static int linearSearch( int[] array, int key)
	{
		for (int i = 0; i < array.length; i++)
		{
			// check to see the key is found
			if (array[i] == key)
			{
				return i;
			}
		}
		//if the key is not found
		return -1;
	}
	/**
	 * A method of search using interpolation formula to estimate a target key
	 * within a sorted array. It is efficient when the array is sorted
	 * This algorithm assumes the array is sorted and uniformly distributed
	 * 
	 * @param array
	 * @param key
	 * @return
	 */
	public static int interpolationAlgorithm( int [] array, int key)
	{
		// initialize the lowest value
		int low = 0;
		
		//initialize the last value
		int high = array.length - 1;
		
		while(low <= high && key >= array[low] && key <= array[high])
		{
			//use the interpolation formula to calculate the position
			int position = low + (((key - array[low]) * (high - low )) / (array[high] - array[low]));
			
			if (array[position] == key)
			{
				return position;
			}
			
			if (array[position] < key)
			{
				low = position + 1;
			}
			else
			{
				high = position - 1;
			}
		}
		
		return -1;
	}
	/**
	 * This method is the standard bubble sort algorithm to sort the 
	 * array in ascending order. it takes an array and uses a nested for loop
	 * to compare adjacent elements and swap them if they are in wrong order.
	 * The largest element bubbles up to its correct position in each iteration
	 * 
	 * @param array
	 */
	public static int[] bubbleSortAlgorithm(int[] array)
	{
		//initialize the total element in the array
		int m = array.length;
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < m - i - 1; j++ )
			{
				if (array[j] > array[j + 1])
				{
					// swap the position found in this array comparison
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		return array;
	}
	
	/**
     * Generate a large sorted array.
     *
     * @param arraySize the size of the array
     * @return the generated sorted array
     */
    private static int[] generateSortedArray(int arraySize) 
    {
        int[] array = new int[arraySize];
        
        Random random = new Random();
        
        for (int i = 0; i < arraySize; i++) 
        {
            array[i] = random.nextInt(1000); // Generate random numbers between 0 and 99
        }
        
        Arrays.sort(array); // Sort the array in ascending order
        
        return array;
    }
    
    
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		//initialize the array size
		int arraySize = 0;
		
		//use this as a check
		boolean validInput = false;
		
		// prompt user for the number of elements in the array
		
		while (!validInput)
		{
			try
			{
				System.out.print("Enter the number of element in the array: ");
				arraySize = scanner.nextInt();
				validInput = true;
			}
			catch (Exception e)
			{
				System.out.println("Invalid input. Please enter a valid number");
				scanner.nextLine(); //clear the input buffer
			}
		}
		
		int[] array = new int[arraySize];
		
		// Prompt user to enter the elements of the array
		System.out.println("\nEnter the element of the array: ");
		
		for (int i = 0; i < arraySize; i++)
		{
			validInput = false;
			while (!validInput) 
			{
				try
				{
					System.out.print("Element " + (i + 1) + ": ");
					array[i] = scanner.nextInt();
					validInput = true;
				}
				catch (Exception e)
				{
					System.out.println("Invalid input. Please enter a valid number. ");
					scanner.nextLine(); //clear the input buffer
				}
			}
		}
		// Prompt user to enter the search key
		 validInput = false;
		 
		 // initialize the key
		 int key = 0;
		 // Keep asking user until the right value is entered
		 while (!validInput)
		 {
			 try
			 {
				 System.out.print("\nEnter the search key: ");
				 key = scanner.nextInt();
				 validInput = true;
			 }
			 catch (Exception e)
			 {
				 System.out.println("Invalid input. Please enter a valid number. ");
				 scanner.nextLine();
			 }
		 }
		 
		 // call the bubble sort method to sort the array
		 
		int[] sortedArray = bubbleSortAlgorithm(array);
		
		
		long linearStartTime = System.nanoTime();
		int linearSearching = linearSearch( sortedArray, key);
		long linearStopTime = System.nanoTime();
		long linearExecutionTime = linearStopTime - linearStartTime;
				
		if (linearSearching != -1)
		{
			System.out.println("\nUsing Linear Search");
			System.out.println("Search key " + key + " FOUND at index " + linearSearching);
			
		}
		else
		{
			System.out.println("\nUsing Linear Search ");
			System.out.println("Search key " + key + " NOT FOUND");
		}
		
	
		long interpolationStartTime = System.nanoTime();
		int interpolationSearching = interpolationAlgorithm(sortedArray, key);
		long interpolationStopTime = System.nanoTime();
		long interpolationExecutionTime = interpolationStopTime - interpolationStartTime;
		
		
		if (interpolationSearching != -1)
		{
			System.out.println("\nUsing Interpolation Search");
			System.out.println("Search key " + key + " FOUND at index " + interpolationSearching);
		}
		else
		{
			System.out.println("\nUsing Interpolation Search");
			System.out.println("Search key " + key + " NOT FOUND");
			
		}
		
		System.out.println("\nExecution time for linear Search is " + linearExecutionTime + " ns");
		System.out.println("Execution time for Interpolation Search is " + interpolationExecutionTime + " ns");
		
		
		
		
		
		System.out.println("\nQuestion 2 ");
		System.out.println("\nI have to use a random generated large array of size 1000 to show significant time difference.") ;
		
		// call the method to generate random array of size 1000
		int[] sortArray = generateSortedArray(arraySize);
		
		// sort the generated arrays
		sortArray = bubbleSortAlgorithm(sortArray);
		
		//start the time it starts to execute the linear search
		long linearStartTime1 = System.nanoTime(); 
		
		int linearSearching1 = linearSearch(sortArray, key);
		
		long linearStopTime1 = System.nanoTime();
		
		long linearExecutionTime1 = linearStopTime1 - linearStartTime1;
		
		long interpolationStartTime1 = System.nanoTime();
		
		int interpolationSearching1 = interpolationAlgorithm(sortArray, key);
		
		long interpolationStopTime1 = System.nanoTime();
		
		long interpolationExecutionTime1 = interpolationStopTime1 - interpolationStartTime1;
		
		System.out.println("Execution time for linear Search is " + linearExecutionTime1 + " ns");
		System.out.println("Execution time for Interpolation Search is " + interpolationExecutionTime1 + " ns");
		
		System.out.println("\nThe interpolation search seems to be faster in execution");
		System.out.println(" \nThe interpolation search algorithm perform better because it makes intelligent guesses about the position of the key element in the sorted array ");
		
		System.out.println("To improve the linear Search by 20%, I suggest that we may need to look at the iterative approach. When a key is found at index i, swap the element at the index 'i' with");
		System.out.println("an element at index i - 1. This moves the found element one position closer to the beginning of the array. As such, if the same key needs to be searched again, it will be found");
		System.out.println("more quickly in subsequent searches.");	
		
	}

}
