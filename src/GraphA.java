import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class GraphA<T> implements Graph<T> {

    private List<T> V;
    private List<List<Boolean>> ady;
    private int alpha;

    GraphA() {
        V = new ArrayList<>();
        ady = new ArrayList<>();
        alpha = 0;
    }
    GraphA(int capacity) {
        V = new ArrayList<>(capacity);
        ady = new ArrayList<>(new ArrayList<>(capacity));
        alpha = 0;
    }

    @Override
    public void addVertix(T x) {
        V.add(x);
    }

    @Override
    public void addEdge(int v, int w) {
        if(ady.get(v).get(w)) return;
        ady.get(v).set(w,true);
        ady.get(w).set(v,true);
        alpha++;
    }

    @Override
    public void deleteEdge(int v, int w) {
        if(!ady.get(v).get(w)) return;
        ady.get(v).set(w,false);
        ady.get(w).set(v,false);
        alpha--;
    }

    @Override
    public void deleteVertix(int v) {
        V.remove(v);
        ady.remove(v);
        for (int i = 0; i < ady.size(); i++) {
            ady.get(i).remove(v);
        }
    }

    @Override
    public boolean existsEdge(int v, int w) {
        return ady.get(v).get(w);
    }

    @Override
    public int order() {
        return ady.size();
    }

    @Override
    public int edges() {
        return alpha;
    }

    @Override
    public T getVertix(int v) {
        return V.get(v);
    }

    @Override
    public List<Integer> getAdyList(int v) {
        List<Integer> newList = new ArrayList<>();
        for(int w = 0; w< ady.size(); w++){
            if(ady.get(v).get(w)){
                newList.add(w);
            }
        }
        return newList;
    }

    public int getIndex(T t){
        for (int i = 0; i < V.size(); i++) {
            if(V.get(i).equals(t)) return i;
        }
        return -1;
    }
}
