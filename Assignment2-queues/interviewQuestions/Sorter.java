/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package interviewQuestions;

public interface Sorter<T extends Comparable> {
    void sort(T[] arr);

    default void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    default void printArray(T[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + ", ");
        }
        String str = sb.toString();
        str = str.substring(0, str.length() - 2);
        str = str + " ]";

        System.out.println(str);

    }

}
