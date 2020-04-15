import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTest {

    //Using JUNG 2.0.1

    public static void main(String[] args) {
        SparseGraph<Integer, String> g = new SparseGraph<>();
        g.addEdge("12", 1, 2);
        g.addEdge("13", 1, 3);
        g.addEdge("34", 3, 4);
        g.addEdge("27", 2, 7);
        g.addEdge("37", 3, 7);
        g.addEdge("47", 4, 7);
        System.out.println(BreadthFirstTraversal(g, 1));
        System.out.println(DepthFirstTraversal(g, 1));
    }

    public static ArrayList<Integer> BreadthFirstTraversal(Graph<Integer, String> g, int startingPoint) {
        ArrayList<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingPoint);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            visited.add(current);
            for (int neighbor: g.getNeighbors(current)) {
                if (!queue.contains(neighbor) && !visited.contains(neighbor)) queue.add(neighbor);
            }
        }
        return visited;
    }

    public static ArrayList<Integer> DepthFirstTraversal(Graph<Integer, String> g, int startingPoint) {
        ArrayList<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startingPoint);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int neighbor: g.getNeighbors(current)) {
                if (!visited.contains(neighbor) && !stack.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
            visited.add(current);
        }

        return visited;
    }
}