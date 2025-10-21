import java.util.List;
import java.util.ArrayList;
public class EdmondsKarp {

    Kattio io;
    int v;
    int s, t;
    int e;
    List<List<Integer>> edges;
    List<List<Integer>> adjecencyList;

    public void readFlowGraph(){
        v = io.getInt();
        s = io.getInt();
        t = io.getInt();
        e = io.getInt();
        edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            List<Integer> edge = new ArrayList<>();
            int a = io.getInt();
            int b = io.getInt();
            int c = io.getInt();
            edge.add(a);
            edge.add(b);
            edge.add(c);
            edges.add(edge);
        }
    }

    public void 
    public boolean BFS(int s, int t, int [] edges) {}

    public int fordFulkerson() {

    }
}
