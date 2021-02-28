package interviewQuestions;

public interface UF {
    void union(int a, int b);

    int find(int a);

    boolean isConnected(int a, int b);
}
