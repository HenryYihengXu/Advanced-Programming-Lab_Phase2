package Graph;

import java.util.ArrayList;

/**
 * The main program for Graph
 * The data structure of the graph.
 * Use adjacency Matrix to represent it
 *
 * Generate a graph consisting of no less than 8 vertices and 15 edges.
 * And use DFS to traverse all vertices in the graph.
 *
 *  @author Henry Xu
 *  */
public class Graph {

    /** Type of the graph. */
    boolean isDirected;
    /** All vertices in the graph. */
    ArrayList<Vertex> vertexList;
    /** Represents the edges. */
    int[][] adjacencyMatrix;
    /** The maximum of the number of vertices. */
    int maxNum;
    /** Actual number of vertices. */
    int vertexNum;

    /** Constructor of Graph. */
    public  Graph(int maxNum, boolean isDirected) {
        this.isDirected = isDirected;
        this.maxNum = maxNum;
        vertexNum = 0;
        vertexList = new ArrayList<>();
        adjacencyMatrix = new int[maxNum][maxNum];
    }

    /** Add a new vertex to graph.
     * @param v the vertex to be added */
    public void addVertex(Vertex v) {
        if (vertexNum >= maxNum) {
            System.out.println("Error");
            return;
        }
        vertexNum += 1;
        vertexList.add(v);
    }

    /** Add a new edge.
     * @param i the beginning vertex of the edge.
     * @param j the endding vertex of the edge.
     * @param weight the weight of the edge.*/
    public void addEdge(int i, int j, int weight) {
        adjacencyMatrix[i][j] = weight;
        if (!isDirected) {
            adjacencyMatrix[j][i] = weight;
        }
    }

    /** Remove an edge.
     * @param i the beginning vertex of the edge.
     * @param j the endding vertex of the edge.*/
    public void removeEdge(int i, int j) {
        adjacencyMatrix[i][j] = 0;
        if (!isDirected) {
            adjacencyMatrix[j][i] = 0;
        }
    }

    /** Initialize the graph.*/
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            Vertex v = new Vertex(i);
            addVertex(v);
        }
        addEdge(0, 1, 1);
        addEdge(0, 2, 1);
        addEdge(0, 3, 1);
        addEdge(0, 6, 1);
        addEdge(1, 4, 1);
        addEdge(1, 5, 1);
        addEdge(1, 7, 1);
        addEdge(2, 5, 1);
        addEdge(2, 6, 1);
        addEdge(2, 8, 1);
        addEdge(3, 6, 1);
        addEdge(3, 9, 1);
        addEdge(4, 7, 1);
        addEdge(6, 8, 1);
        addEdge(7, 8, 1);
        addEdge(8, 9, 1);

    }

    /** Get the next neighbour vertex that is not visited.
     * @param i the current vertex.
     * @return the next neighbour vertex that is not visited.*/
    public int nextNeighbour(int i) {
        for (int j = 0; j < vertexNum; j++) {
            if (adjacencyMatrix[i][j] != 0 && !vertexList.get(j).isVisited) {
                return j;
            }
        }
        return -1;
    }

    /** Set the given vertex visited and print its label.
     * @param i the vertex to be visit. */
    public void visit(int i) {
        Vertex v = vertexList.get(i);
        v.isVisited = true;
        System.out.println(v.label);
    }

    /** Execute the depth first searching. */
    public void DFS() {
        for (int i = 0; i < vertexNum; i++) {
            DFS(i);
        }
    }

    /** Perform DFS from the current vertex.
     * @param current the current vertex.*/
    public void DFS(int current) {
        if (vertexList.get(current).isVisited) {
            return;
        }
        visit(current);
        int next = nextNeighbour(current);
        while (next != -1) {
            DFS(next);
            next = nextNeighbour(current);
        }
    }

    /** Vertex. */
    class Vertex {
        /** Indicate if the vertex has been visited. */
        boolean isVisited;
        /** Content of the vertex. */
        int label;

        /** Constructor of Vertex Class.
         * @param label label of the vertex. */
        public Vertex(int label) {
            isVisited = false;
            this.label = label;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(10, false);
        g.initialize();
        g.DFS();
    }
}
