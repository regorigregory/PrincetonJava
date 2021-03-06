/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package interviewQuestions;

public class Insertion<T extends Comparable>
        implements Sorter<T> {
    public void sort(T[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j - 1, j);
                }
                else {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        Integer[] testArray = { 66, 1, 4, 8, 3, 4, 22, 51, 17 };
        Sorter s = new Insertion();
        s.sort(testArray);
        s.printArray(testArray);


    }
}
