/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

public interface StatusReporter {
    int getSize();

    int getElementById(int id);

    default void printStatus() {
        StringBuilder top = new StringBuilder();
        StringBuilder bottom = new StringBuilder();
        top.append("el[] ");
        bottom.append("id[] ");
        for (int i = 0; i < this.getSize(); i++) {
            top.append(i + " ");
            bottom.append(this.getElementById(i) + " ");
        }
        System.out.println("***************************************************");
        System.out.println(top);

        System.out.println(bottom);

    }
}
