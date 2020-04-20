/**
 * Demo
 */
public class Demo {
    public static void main(String[] args) {
        Graph g = new Graph("io.txt");
        System.out.println();
        DFS df = new DFS();
        df.DFSAL(g);
        BFS bf = new BFS();
        bf.BFSAL(g);
    }
}