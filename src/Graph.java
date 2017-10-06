import java.util.List;

public interface Graph<T> {
    void addVertix(T x);
    void addEdge(int v, int w);
    void deleteEdge(int v, int w);
    void deleteVertix(int v);
    boolean existsEdge(int v, int w);
    int order();
    int edges();
    T getVertix(int v);
    List<Integer> getAdyList(int v);
}
