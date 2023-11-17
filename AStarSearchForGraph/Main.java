package AStarSearchForGraph;

public class Main extends AStar {
    public static void main(String[] args) {
        Node start = new Node("S", 5);
        start.g = 0;

        Node a = new Node("A", 7);
        Node b = new Node("B", 3);
        Node d = new Node("D", 6);
        Node c = new Node("C", 4);

        start.addBranch(5, a);
        start.addBranch(9, b);
        start.addBranch(6, d);
        c.addBranch(6,start);
        a.addBranch(3,b);
        b.addBranch(2,a);
        b.addBranch(1,c);
        d.addBranch(2,c);

        Node e = new Node("E",5);

        d.addBranch(2,e);

        Node g1 = new Node("G1", 0);
        Node target = new Node("G2", 0);
        Node f = new Node("F", 6);
        Node g3 = new Node("G3", 0);

        a.addBranch(9,g1);
        c.addBranch(5,target);
        c.addBranch(7,f);
        f.addBranch(2,d);
        f.addBranch(8,g3);
        e.addBranch(7,g3);

        Node res = aStar(start, target);
        printPath(target);
    }
}
