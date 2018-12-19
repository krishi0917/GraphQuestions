package Graph;

import java.util.*;
public class graphQuestions1 {
    /**
     * Given a directed graph clone it in O(n) time where n is total number of edges
     * Test cases
     * Graph is directed/non directed
     * Graph has 0 edges
     * Graph has cycle
     * Graph is linear
     * Graph is dense
     * Graph is sparse
     */

//	Clone Directed Graph
//    public Graph<T> clone(Graph<T> graph){
//        if(graph == null){
//            return null;
//        }
//        if(!graph.isDirected){
//            throw new IllegalArgumentException("Cloning non directed graph");
//        }
//        if(graph.getAllVertex().size() == 0){
//            throw new IllegalArgumentException("No vertex in the graph");
//        }
//        GenericHashMap<Vertex<T>,Vertex<T>> cloneMap = new HashTable<Vertex<T>,Vertex<T>>();
//        for(Vertex<T> vertex : graph.getAllVertex()){
//            clone(vertex,cloneMap);
//        }
//        Graph<T> clonedGraph = new Graph<>(true);
//        for(Vertex<T> vertex : cloneMap.values()){
//            clonedGraph.addVertex(vertex);
//        }
//        return clonedGraph;
//    }
//
//    private void clone(Vertex<T> origVertex,GenericHashMap<Vertex<T>,Vertex<T>> cloneMap){
//        Vertex<T> cloneVertex = null;
//        if(cloneMap.containsKey(origVertex)){
//            cloneVertex = cloneMap.get(origVertex);
//        }else{
//            cloneVertex = new Vertex<T>(origVertex.getId()*10);
//            cloneMap.put( origVertex, cloneVertex);
//        }
//        for(Edge<T> edge : origVertex.getEdges()){
//            if(edge.getVertex1().equals(origVertex)){
//                Vertex<T> adjVertex = edge.getVertex2();
//                updateMap(cloneMap,cloneVertex,adjVertex,edge);
//            }else{
//                Vertex<T> adjVertex = edge.getVertex1();
//                updateMap(cloneMap,cloneVertex,adjVertex,edge);
//            }
//        }
//    }
//
//    private void updateMap(GenericHashMap<Vertex<T>,Vertex<T>> cloneMap, Vertex<T> cloneVertex,
//                           Vertex<T> adjVertex, Edge<T> edge){
//        if(cloneMap.containsKey(adjVertex)){
//            Vertex<T> adjVertexClone = cloneMap.get(adjVertex);
//            Edge<T> newEdge = new Edge<T>(cloneVertex, adjVertexClone, edge.isDirected(), edge.getWeight());
//            cloneVertex.addAdjacentVertex(newEdge, adjVertexClone);
//        }else{
//            Vertex<T> adjVertexClone = new Vertex<T>(adjVertex.getId());
//            cloneMap.put(adjVertex, adjVertexClone);
//            Edge<T> newEdge = new Edge<T>(cloneVertex, adjVertexClone, edge.isDirected(), edge.getWeight());
//            cloneVertex.addAdjacentVertex(newEdge, adjVertexClone);
//        }
//    }
//
    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {

        return clone(node, new HashMap<>());
    }

    UndirectedGraphNode clone(UndirectedGraphNode src, HashMap<UndirectedGraphNode, UndirectedGraphNode> visitedBag) {
        if (src == null) {
            return null;
        }
        if (visitedBag.containsKey(src)) {
            return visitedBag.get(src);
        }

        UndirectedGraphNode n = new UndirectedGraphNode(src.label);
        visitedBag.put(src, n);
        for (UndirectedGraphNode child : src.neighbors) {
            if (visitedBag.containsKey(child)) {
                n.neighbors.add(visitedBag.get(child));
            } else {
                UndirectedGraphNode childCopy = clone(child, visitedBag);
                n.neighbors.add(childCopy);
            }
        }
        return n;
    }


    //below is the second solution for the same
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;

        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }

    //Tushar roy method
    public UndirectedGraphNode cloneGraphthirdmethod(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        Set<Integer> visited = new HashSet<>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(clone.label, clone);
        dfs(node, clone, map, visited);
        return clone;
    }

    private void dfs(UndirectedGraphNode current, UndirectedGraphNode clone, Map<Integer, UndirectedGraphNode> map, Set<Integer> visited) {
        if (visited.contains(current.label)) {
            return;
        }
        visited.add(current.label);
        for (UndirectedGraphNode adj : current.neighbors) {
            if (adj.label != current.label) {
                UndirectedGraphNode adjClone = map.get(adj.label);
                if (adjClone == null) {
                    adjClone = new UndirectedGraphNode(adj.label);
                    map.put(adjClone.label, adjClone);
                }
                clone.neighbors.add(adjClone);
                dfs(adj, adjClone, map, visited);
            } else {
                clone.neighbors.add(clone);
            }
        }
    }

    //topological sort
    public Deque<Vertex<Integer>> topSort(Graph<Integer> graph) {
        Deque<Vertex<Integer>> stack = new ArrayDeque<>();
        Set<Vertex<Integer>> visited = new HashSet<>();
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topSortUtil(vertex, stack, visited);
        }
        return stack;
    }

    private void topSortUtil(Vertex<Integer> vertex, Deque<Vertex<Integer>> stack, Set<Vertex<Integer>> visited) {
        visited.add(vertex);
        for (Vertex<Integer> childVertex : vertex.getAdjacentVertexes()) {
            if (visited.contains(childVertex)) {
                continue;
            }
            topSortUtil(childVertex, stack, visited);
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

    public void DFS(Graph<Integer> graph) {
        Set<Long> visited = new HashSet<Long>();
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (!visited.contains(vertex.getId())) {
                DFSUtil(vertex, visited);
            }
        }
    }

    private void DFSUtil(Vertex<Integer> v, Set<Long> visited) {
        visited.add(v.getId());
        System.out.print(v.getId() + " ");
        for (Vertex<Integer> vertex : v.getAdjacentVertexes()) {
            if (!visited.contains(vertex.getId()))
                DFSUtil(vertex, visited);
        }
    }

    //Cycle in undirected graph
    public boolean hasCycleDFS(Graph<Integer> graph) {
        Set<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            boolean flag = hasCycleDFSUtil(vertex, visited, null);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleDFSUtil(Vertex<Integer> vertex, Set<Vertex<Integer>> visited, Vertex<Integer> parent) {
        visited.add(vertex);
        for (Vertex<Integer> adj : vertex.getAdjacentVertexes()) {
            if (adj.equals(parent)) {
                continue;
            }
            if (visited.contains(adj)) {
                return true;
            }
            boolean hasCycle = hasCycleDFSUtil(adj, visited, vertex);
            if (hasCycle) {
                return true;
            }
        }
        return false;

    }

    public static void main(String args[]) {
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
        graphQuestions1 graphQuestions = new graphQuestions1();
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