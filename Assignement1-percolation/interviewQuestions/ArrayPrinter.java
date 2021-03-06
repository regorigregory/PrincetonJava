/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

public interface ArrayPrinter<T> {
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
