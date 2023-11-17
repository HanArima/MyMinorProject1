package AStarSearchForGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    public static Node aStar(Node start, Node target) {
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic(target);
        openList.add(start);

        while (!openList.isEmpty()) {
            Node n = openList.peek();
            if (n == target) {
                return n;
            }

            for (Node.Edge edge : n.neighbors) {
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if (!openList.contains(m) && !closedList.contains(m)) {
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if (totalWeight < m.g) {
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if (closedList.contains(m)) {
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }
    public static void printPath( Node target) {
        Node n = target;

        if (n == null)
            return;

        List<String> path = new ArrayList<>();
        double totalCost = 0.0;

        while (n.parent != null) {
            Node parentNode = n.parent;

            // Find the edge connecting the current node and its parent
            for (Node.Edge edge : parentNode.neighbors) {
                if (edge.node == n) {
                    totalCost += edge.weight; // Accumulate edge weight
                    break;
                }
            }

            path.add(n.name);
            n = n.parent;
        }
        path.add(n.name);
        Collections.reverse(path);

        for (String name : path ) {
            System.out.print(name + " --> ");
            if(name.equals(target.name)){
                System.out.println( "END ");
            }
        }
        System.out.println("");
        System.out.println("\nTotal cost: " + totalCost);
    }
}
