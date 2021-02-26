/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public interface DynamicConnectivityObject {
    boolean isConnected(int a, int b);

    void union(int a, int b);

    int find(int a);

    int count();

    void printNumberOfUnionLoops();

    void printNumberOfFindLoops();

    default void printStatus() {
        StringBuilder top = new StringBuilder();
        StringBuilder bottom = new StringBuilder();
        top.append("el[] ");
        bottom.append("id[] ");
        for (int i = 0; i < this.count(); i++) {
            top.append(i + " ");
            bottom.append(this.find(i) + " ");
        }
        System.out.println("***************************************************");
        System.out.println(top);

        System.out.println(bottom);

        System.out.println("***************************************************");


    }
}
