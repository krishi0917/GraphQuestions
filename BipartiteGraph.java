package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by rkhurana on 6/15/18.
 */
public class BipartiteGraph {
    public boolean isBiparte(Graph<Integer> graph){

        Set<Vertex<Integer>> redSet = new HashSet<>();
        Set<Vertex<Integer>> blueSet = new HashSet<>();
        Queue<Vertex<Integer>> queue = new LinkedList<>();
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(!redSet.contains(vertex) && !blueSet.contains(vertex)){
                queue.add(vertex);
                redSet.add(vertex);
                while(!queue.isEmpty()){
                    vertex = queue.poll();
                    for(Vertex<Integer> v : vertex.getAdjacentVertexes()){
                        if(redSet.contains(vertex)){
                            if(redSet.contains(v)){
                                return false;
                            }if(blueSet.contains(v)){
                                continue;
                            }
                            blueSet.add(v);
                        }
                        else if(blueSet.contains(vertex)){
                            if(blueSet.contains(v)){
                                return false;
                            }if(redSet.contains(v)){
                                continue;
                            }
                            redSet.add(v);
                        }
                        queue.add(v);
                    }
                }
            }
        }
        System.out.println(redSet);
        System.out.println(blueSet);
        return true;
    }

    public static void main(String [] args) {
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(7, 9);
        graph.addEdge(7, 8);
        BipartiteGraph bi = new BipartiteGraph();
        boolean result = bi.isBiparte(graph);
        System.out.print(result);
    }
}
