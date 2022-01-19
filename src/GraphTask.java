import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Container class to different classes, that makes the whole
 * set of classes one class formally.
 */
public class GraphTask {

    /**
     * Main method.
     */
    public static void main(String[] args) {
        GraphTask a = new GraphTask();
        try {
            a.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actual main method to run examples and everything.
     */
    public void run() throws CloneNotSupportedException {
        Graph g = new Graph("Graph_1");
        g.createRandomSimpleGraph(6, 8);
        Graph g2 = null;
        g2 = (Graph) g.clone();
        System.out.println(g);
        System.out.println(g2);
        boolean test;
        test = g.isItEqual(g2);
        System.out.println("Are the graphs equal? ---> " + test);
        g.createVertex("v42");
        System.out.println(g);
        System.out.println(g2);
        test = g.isItEqual(g2);
        System.out.println("Are the graphs equal? ---> " + test);


          Graph g3 = new Graph("Graph_2");
          g3.createRandomSimpleGraph(3,2);
          Graph g4 = null;
          g4 = (Graph) g3.clone();
          System.out.println(g3);
          System.out.println(g4);
          test = g3.isItEqual(g4);
          System.out.println("Are the graphs equal? ---> " + test);

          Graph g5 = new Graph("Graph_3");
          g5.createRandomSimpleGraph(0,0);
          Graph g6 = null;
          g6 = (Graph) g5.clone();
          System.out.println(g5);
          System.out.println(g6);
          test = g5.isItEqual(g6);
          System.out.println("Are the graphs equal? ---> " + test);

          Graph g7 = new Graph("Graph_4");
          g7.createRandomSimpleGraph(1,0);
          Graph g8 = null;
          g8 = (Graph) g7.clone();
          System.out.println(g7);
          System.out.println(g8);
          test = g7.isItEqual(g8);
          System.out.println("Are the graphs equal? ---> " + test);
          System.out.println();

          Graph g9 = new Graph("GRAPH_5");
          g9.createRandomSimpleGraph(2000,2000);
          Graph g10 = null;
          long stime = System.nanoTime();
          g10 = (Graph) g9.clone();
          long ftime = System.nanoTime();
          long diff = ftime - stime;
          test = g9.isItEqual(g10);
          System.out.println("Graph 5 (2000 vertices, 2000 arcs) clone time test in ms: " + diff / 1000000);
          System.out.println("Are the graphs equal? ---> " + test);


    }


    class Vertex {

        private String id;
        private Vertex next;
        private Arc first;
        private int info = 0;
        // You can add more fields, if needed

        Vertex(String s, Vertex v, Arc e) {
            id = s;
            next = v;
            first = e;
        }

        Vertex(String s) {
            this(s, null, null);
        }

        @Override
        public String toString() {
            return id;
        }

        @Override
        public boolean equals(Object o) {  // check for  vertices id and arc
            Vertex v = (Vertex) o;
            String originalId = this.id;
            String cloneId = v.id;
            if (originalId.equals(cloneId)) {
                Arc originalArc = this.first;
                Arc cloneArc = v.first;
                if (originalArc == null) {
                    return cloneArc == null;
                } else return originalArc.isItEqual(cloneArc);
            }
            return false;
        }

        public boolean isItEqual(Vertex v) {   // check vertices
            Vertex cloneVertex = v;
            Vertex originalVertex = this;
            while (originalVertex != null && cloneVertex != null) {
                if (!originalVertex.equals(cloneVertex)) {
                    return false;
                }
                originalVertex = originalVertex.next;
                cloneVertex = cloneVertex.next;
            }
            return originalVertex == cloneVertex;
        }

    }


    /**
     * Arc represents one arrow in the graph. Two-directional edges are
     * represented by two Arc objects (for both directions).
     */
    class Arc {

        private String id;
        private Vertex target;
        private Arc next;
        private int info = 0;
        // You can add more fields, if needed

        Arc(String s, Vertex v, Arc a) {
            id = s;
            target = v;
            next = a;
        }

        Arc(String s) {
            this(s, null, null);
        }

        @Override
        public String toString() {
            return id;
        }

        @Override
        public boolean equals(Object o) {   // check arc id
            Arc clone = (Arc) o;
            String originalId = this.id;
            String cloneId = clone.id;
            return originalId.equals(cloneId);
        }

        public boolean isItEqual(Arc a) {    // check arcs
            Arc cloneArc = a;
            Arc originalArc = this;
            while (originalArc != null && cloneArc != null) {
                if (!originalArc.equals(cloneArc)) {
                    return false;
                }
                originalArc = originalArc.next;
                cloneArc = cloneArc.next;
            }
            return originalArc == cloneArc;
        }

    }


    class Graph {

        private String id;
        private Vertex first;
        private int info = 0;
        // You can add more fields, if needed

        Graph(String s, Vertex v) {
            id = s;
            first = v;
        }

        Graph(String s) {
            this(s, null);
        }

        @Override
        public String toString() {
            String nl = System.getProperty("line.separator");
            StringBuffer sb = new StringBuffer(nl);
            sb.append(id);
            sb.append(nl);
            Vertex v = first;
            while (v != null) {
                sb.append(v.toString());
                sb.append(" -->");
                Arc a = v.first;
                while (a != null) {
                    sb.append(" ");
                    sb.append(a.toString());
                    sb.append(" (");
                    sb.append(v.toString());
                    sb.append("->");
                    sb.append(a.target.toString());
                    sb.append(")");
                    a = a.next;
                }
                sb.append(nl);
                v = v.next;
            }
            return sb.toString();
        }

        public Vertex createVertex(String vid) {
            Vertex res = new Vertex(vid);
            res.next = first;
            first = res;
            return res;
        }

        public Arc createArc(String aid, Vertex from, Vertex to) {
            Arc res = new Arc(aid);
            res.next = from.first;
            from.first = res;
            res.target = to;
            return res;
        }

        /**
         * Create a connected undirected random tree with n vertices.
         * Each new vertex is connected to some random existing vertex.
         *
         * @param n number of vertices added to this graph
         */
        public void createRandomTree(int n) {
            if (n <= 0)
                return;
            Vertex[] varray = new Vertex[n];
            for (int i = 0; i < n; i++) {
                varray[i] = createVertex("v" + String.valueOf(n - i));
                if (i > 0) {
                    int vnr = (int) (Math.random() * i);
                    createArc("a" + varray[vnr].toString() + "_"
                            + varray[i].toString(), varray[vnr], varray[i]);
                    createArc("a" + varray[i].toString() + "_"
                            + varray[vnr].toString(), varray[i], varray[vnr]);
                } else {
                }
            }
        }

        /**
         * Create an adjacency matrix of this graph.
         * Side effect: corrupts info fields in the graph
         *
         * @return adjacency matrix
         */
        public int[][] createAdjMatrix() {
            info = 0;
            Vertex v = first;
            while (v != null) {
                v.info = info++;
                v = v.next;
            }
            int[][] res = new int[info][info];
            v = first;
            while (v != null) {
                int i = v.info;
                Arc a = v.first;
                while (a != null) {
                    int j = a.target.info;
                    res[i][j]++;
                    a = a.next;
                }
                v = v.next;
            }
            return res;
        }

        /**
         * Create a connected simple (undirected, no loops, no multiple
         * arcs) random graph with n vertices and m edges.
         *
         * @param n number of vertices
         * @param m number of edges
         */
        public void createRandomSimpleGraph(int n, int m) {
            if (n <= 0)
                return;
            if (n > 2500)
                throw new IllegalArgumentException("Too many vertices: " + n);
            if (m < n - 1 || m > n * (n - 1) / 2)
                throw new IllegalArgumentException
                        ("Impossible number of edges: " + m);
            first = null;
            createRandomTree(n);       // n-1 edges created here
            Vertex[] vert = new Vertex[n];
            Vertex v = first;
            int c = 0;
            while (v != null) {
                vert[c++] = v;
                v = v.next;
            }
            int[][] connected = createAdjMatrix();
            int edgeCount = m - n + 1;  // remaining edges
            while (edgeCount > 0) {
                int i = (int) (Math.random() * n);  // random source
                int j = (int) (Math.random() * n);  // random target
                if (i == j)
                    continue;  // no loops
                if (connected[i][j] != 0 || connected[j][i] != 0)
                    continue;  // no multiple edges
                Vertex vi = vert[i];
                Vertex vj = vert[j];
                createArc("a" + vi.toString() + "_" + vj.toString(), vi, vj);
                connected[i][j] = 1;
                createArc("a" + vj.toString() + "_" + vi.toString(), vj, vi);
                connected[j][i] = 1;
                edgeCount--;  // a new edge happily created
            }
        }

        /**
         * We have  original graph and need to make a deep clone of it.
         *
         * @return Graph of type object
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            Graph clone = null;
            Vertex originalVertex = null;
            Vertex clonedVertex = null;

            // Turns out Stack is not optimal for this case
            // https://stackoverflow.com/questions/12524826/why-should-i-use-deque-over-stack
            ArrayDeque<Vertex> vertexStack = null;
            ArrayDeque<Vertex> vertexStackCopy = null;
            Map<String, Vertex> cloneVertexMap = null;
            clone = new Graph(this.id);
            cloneVertexMap = new HashMap<>();
            vertexStack = new ArrayDeque<>();
            originalVertex = this.first;
            while (originalVertex != null) {       //traverse through all the vertices we have
                vertexStack.push(originalVertex);
                originalVertex = originalVertex.next;
            }

            vertexStackCopy = new ArrayDeque<>(vertexStack);

            while (!vertexStackCopy.isEmpty()) {    // inserting vertex copies
                Vertex v = null;
                String s = null;
                originalVertex = vertexStackCopy.pop();
                s = originalVertex.id;
                v = clone.createVertex(s);
                cloneVertexMap.put(s, v);
            }
            vertexStackCopy = new ArrayDeque<>(vertexStack);

            while (!vertexStackCopy.isEmpty()) {     // inserting arc copies
                Arc a = null;
                Deque<Arc> arcDeque = new ArrayDeque<>();
                originalVertex = vertexStackCopy.pop();
                a = originalVertex.first;
                clonedVertex = cloneVertexMap.get(originalVertex.id);

                while (a != null) {     // inserting arc to deque
                    arcDeque.push(a);
                    a = a.next;
                }

                while (!arcDeque.isEmpty()) {
                    String arcId = null;
                    Vertex from = null;
                    Vertex to = null;
                    a = arcDeque.pop();
                    arcId = a.id;
                    from = clonedVertex;
                    to = cloneVertexMap.get(a.target.id);
                    clone.createArc(arcId, from, to);
                }
            }
            return clone;
        }

        public boolean isItEqual(Graph graph) {   // check graphs
            Vertex originalVertex = this.first;
            Vertex cloneVertex = graph.first;
            if (originalVertex == null){
                return true;
            }
            return originalVertex.isItEqual(cloneVertex);
        }
    }

} 

