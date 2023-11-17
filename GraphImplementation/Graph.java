package GraphImplementation;

import java.util.ArrayList;

public class Graph {

    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public Vertex addVertex(String data) {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
        if (!this.isWeighted) {
            weight = null;
        }
        vertex1.addEdge(vertex2, weight);
        if (!this.isDirected) {
            vertex2.addEdge(vertex1, weight);
        }
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2) {
        vertex1.removeEdge(vertex2);
        if (!this.isDirected) {
            vertex2.removeEdge(vertex1);
        }
    }

    public void removeVertex(Vertex vertex) {
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public boolean isWeighted() {
        return this.isWeighted;
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public Vertex getVertexByValue(String value) {
        for(Vertex v: this.vertices) {
            if (v.getData() == value) {
                return v;
            }
        }

        return null;
    }

    public void print() {
        for(Vertex v: this.vertices) {
            v.print(isWeighted);
        }
    }

    public static void main(String[] args){
        Graph aStar = new Graph(true, true);
        Vertex s = aStar.addVertex("S(5)");
        Vertex a = aStar.addVertex("A(7)");
        Vertex b = aStar.addVertex("B(3)");
        Vertex c = aStar.addVertex("C(4)");
        Vertex d = aStar.addVertex("D(6)");
        Vertex e = aStar.addVertex("E(5)");
        Vertex f = aStar.addVertex("F(6)");
        Vertex g1 = aStar.addVertex("G1(0)");
        Vertex g2 = aStar.addVertex("G2(0)");
        Vertex g3 = aStar.addVertex("G3(0)");

        aStar.addEdge(s, a, 5);
        aStar.addEdge(s, b, 9);
        aStar.addEdge(s,d,6);
        aStar.addEdge(c,s,6);
        aStar.addEdge(d,s,1);
        aStar.addEdge(a,g1,9);
        aStar.addEdge(a,b,3);
        aStar.addEdge(b,a,2);
        aStar.addEdge(b,c,1);
        aStar.addEdge(c,g2,5);
        aStar.addEdge(c,f,7);
        aStar.addEdge(d,c,2);
        aStar.addEdge(f,d,2);
        aStar.addEdge(d,e,2);
        aStar.addEdge(f,g3,8);
        aStar.addEdge(e,g3,7);

        aStar.print();
    }
}
