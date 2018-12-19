package Graph;
/**
 * Created by rkhurana on 6/15/18.
 */

import java.util.*;
/**
 *
 * Given a directed acyclic graph, do a topological sort on this graph.
 *
 * Do DFS by keeping visited. Put the vertex which are completely explored into a stack.
 * Pop from stack to get sorted order.
 *
 * Space and time complexity is O(n).
 */
public class GraphQuestions<T> {


    /**
     * Main method to be invoked to do topological sorting.
     */
    public Deque<Vertex<Integer>> topSort(Graph<Integer> graph) {
        Deque<Vertex<Integer>> stack = new ArrayDeque<>();
        Set<Vertex<Integer>> visited = new HashSet<>();
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topSortUtil(vertex,stack,visited);
        }
        return stack;
    }

    private void topSortUtil(Vertex<Integer> vertex, Deque<Vertex<Integer>> stack, Set<Vertex<Integer>> visited) {
        visited.add(vertex);
        for(Vertex<Integer> childVertex : vertex.getAdjacentVertexes()){
            if(visited.contains(childVertex)){
                continue;
            }
            topSortUtil(childVertex,stack,visited);
        }

        //only when the vertex does not have any child, then it will put into the stack
        stack.offerFirst(vertex);
    }

    public void BFS(Graph<Integer> graph) {
        Set<Long> visited = new HashSet<Long>();
        Queue<Vertex<Integer>> q = new LinkedList<Vertex<Integer>>();

        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (!visited.contains(vertex.getId())) {
                q.add(vertex);
                visited.add(vertex.getId());
                while (q.size() != 0) {
                    Vertex<Integer> vq = q.poll();
                    System.out.print(vq.getId() + " ");
                    for (Vertex<Integer> v : vq.getAdjacentVertexes()) {
                        if (!visited.contains(v.getId())) {
                            q.add(v);
                            visited.add(v.getId());
                        }
                    }
                }
            }
        }
    }
    public void DFS(Graph<Integer> graph){
        Set<Long> visited = new HashSet<Long>();
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(!visited.contains(vertex.getId())){
                DFSUtil(vertex,visited);
            }
        }
    }

    private void DFSUtil(Vertex<Integer> v, Set<Long> visited){
        visited.add(v.getId());
        System.out.print(v.getId() + " ");
        for(Vertex<Integer> vertex : v.getAdjacentVertexes()){
            if(!visited.contains(vertex.getId()))
                DFSUtil(vertex,visited);
        }
    }

    //Cycle in undirected graph
    public boolean hasCycleDFS(Graph<Integer> graph){
        Set<Vertex<Integer>> visited = new HashSet<>();
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(visited.contains(vertex)){
                continue;
            }
            boolean flag = hasCycleDFSUtil(vertex, visited, null);
            if(flag){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleDFSUtil(Vertex<Integer> vertex, Set<Vertex<Integer>> visited, Vertex<Integer> parent){
        visited.add(vertex);
        for(Vertex<Integer> adj : vertex.getAdjacentVertexes()){
            if(adj.equals(parent)){
                continue;
            }
            if(visited.contains(adj)){
                return true;
            }
            boolean hasCycle = hasCycleDFSUtil(adj,visited,vertex);
            if(hasCycle){
                return true;
            }
        }
        return false;

    }


    public static void main(String args[]){
        Graph<Integer> graph = new Graph<Integer>(true);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
//        graph.addEdge(3, 8);
//        graph.addEdge(8, 11);

        //Topological Sort
        GraphQuestions<Integer> graphQuestions = new GraphQuestions<Integer>();
//        Deque<Vertex<Integer>> result = sort.topSort(graph);
//        while(!result.isEmpty()){
//            System.out.println(result.poll());
//        }

        //Cycle undirected graph
        boolean isCycle = graphQuestions.hasCycleDFS(graph);
        System.out.println(isCycle);

        //BFS Traversal Algorithm
       // graphQuestions1.BFS(graph);

    }
}