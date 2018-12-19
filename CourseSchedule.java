package Graph;

import java.util.*;

/**
 * Created by rkhurana on 7/10/18.
 */
public class CourseSchedule {
    //Stack<String> findBuildOrder(String[] projects, String[][] dependencies) {
        void findBuildOrder(String[] projects, String[][] dependencies) {
        int count =0;
        Map<String , Set<String>> graph  =  new HashMap<String,Set<String>>();
        for(String project : projects){
            graph.put(project , new HashSet<>());
            count++;
        }

        for(int i = 0 ; i < dependencies.length ; i++){
            graph.get(dependencies[i][1]).add(dependencies[i][0]); // make sure this change in the question 
        }

        Queue<String> queue = new LinkedList<>();
        int courseRemaining = count;

        for(Map.Entry<String, Set<String>> entry  : graph.entrySet()){
            if(entry.getValue().size() == 0){
                queue.offer(entry.getKey());
                courseRemaining--;
            }
        }
        while(!queue.isEmpty()){
            String key = queue.poll();
            System.out.println("Element polled is " + key);
            for(Map.Entry<String, Set<String>> entry  : graph.entrySet()){
                if(entry.getValue().contains(key)){
                    entry.getValue().remove(key);
                    if(entry.getValue().size()==0){
                        queue.offer(entry.getKey());
                        courseRemaining--;
                    }
                }
            }
        }
    }

    public boolean canFinish(int numCourses , int [][]prerequisites){
        if(numCourses <= 1)
            return true;
        if(prerequisites.length == 0 || prerequisites[0].length == 0)
            return true;

        //Building a graph
        Map<Integer, Set<Integer>> graph  =  new HashMap<Integer,Set<Integer>>();
        for(int i = 0 ; i < numCourses ; i++)
            graph.put( i , new HashSet<Integer>());
        for(int i = 0 ; i < prerequisites.length;i++)
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);

        Queue<Integer> queue = new LinkedList<Integer>();
        int courseRemaining = numCourses;

        for(Map.Entry<Integer, Set<Integer>> entry  : graph.entrySet()){
            if(entry.getValue().size() == 0){
                System.out.println(entry.getKey());
                queue.offer(entry.getKey());
                courseRemaining--;
            }
        }
        while(!queue.isEmpty()){
            int key = queue.poll();
            for(Map.Entry<Integer, Set<Integer>> entry  : graph.entrySet()){
                if(entry.getValue().contains(key)){
                    entry.getValue().remove(key);
                    if(entry.getValue().size()==0){
                        queue.offer(entry.getKey());
                        courseRemaining--;
                    }
                }
            }
        }
    return courseRemaining ==0;
    }

    public static void main(String [] args){
        CourseSchedule o = new CourseSchedule();
 //       int [][] test1 = {{0,1}, {1,2}};
//        System.out.println(o.canFinish(3,test1));
//        int [][] test2 = {{0,1}, {1,0}};
//        System.out.println(o.canFinish(2,test2));
//        int [][] test3 = {{1,2},{2,3}};
//      System.out.println(o.canFinish(3,test1));


        String projects[] = {"a" , "b" , "c" , "d" , "e","f" };
        String [][]dependencies = {{"a","d"},{"f","b"},{"b","d"} , {"f","a"} , {"d","c"}};

        o.findBuildOrder(projects,dependencies);
    }
}
