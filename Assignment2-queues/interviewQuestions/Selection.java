/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package interviewQuestions;

public class Selection<T extends Comparable> implements Sorter<T> {
    public void sort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[i]) < 0) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }

    }

    public static void main(String[] args) {
        Integer[] testArray = { 1, 4, 8, 3, 4, 22, 51, 17 };
        Sorter s = new Selection();
        s.sort(testArray);
        s.printArray(testArray);


    }
}
