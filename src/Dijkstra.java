// Java Program to Implement Dijkstra's Algorithm
// Using Priority Queue

// Importing utility classes
import java.util.*;

// Main class DPQ
public class Dijkstra {

    // Member variables of this class
    public int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    // Number of vertices
    private int V;
    List<List<Node> > adj;
    int[] parents;
    public ArrayList<Integer> path = new ArrayList<>();
    public int count = 0;

    // Constructor of this class
    public Dijkstra(int V)
    {

        // This keyword refers to current object itself
        this.V = V; // number of vertexes
        dist = new int[V]; // empty array for distances
        settled = new HashSet<Integer>(); // set for keeping track of visited nodes
        pq = new PriorityQueue<Node>(V, new Node()); // priority queue of nodes for iterating through
        this.parents = new int[V]; // this array is for backtracking the paths
        Arrays.fill(parents, -1); // keeps track of parents that have been discovered
    }
    
    // Dijkstra's Algorithm
    public void algorithm(List<List<Node> > adj, int src)
    {

        // adjacency matrix
        this.adj = adj;
        
        // set each distance to the max value
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        
        // iterates through queue until empty / all values are settled
        while (settled.size() != V) {

            // Terminating condition check when
            // the priority queue is empty, return
            if (pq.isEmpty()){
                return;}

            // Removing the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // Adding the node whose distance is
            // finalized
            if (settled.contains(u))

                // Continue keyword skips execution for
                // following check
                continue;

            // We don't have to call e_Neighbors(u)
            // if u is already present in the settled set.
            settled.add(u);

            e_Neighbours(u);
        }

    }

    // Process all the neighbour of the passed node
    private void e_Neighbours(int u)
    {

        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node]) {
                    parents[v.node] = u;
                    dist[v.node] = newDistance;
                }

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public void getPath(int lastVertex){
//        for (int i = 0; i < V; i++){
//            if (parents[i] == 5039){
//            System.out.println(parents[i]);
//        }}
        if (parents[lastVertex] == -1){
            return;
        }
        getPath(parents[lastVertex]);
        path.add(parents[lastVertex]);
    }

}

// Helper class implementing Comparator interface
// Representing a node in the graph
class Node implements Comparator<Node> {

    // Member variables of this class
    public int node;
    public int cost;

    // Constructors of this class

    // Constructor 1
    public Node() {}

    // Constructor 2
    public Node(int node, int cost)
    {

        // This keyword refers to current instance itself
        this.node = node;
        this.cost = cost;
    }

    // Method 1
    @Override public int compare(Node node1, Node node2)
    {

        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }

}

