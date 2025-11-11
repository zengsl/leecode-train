package trains.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateGraph2 {


    // 点的最大数量
    public static int MAX_POINT = 11;

    // 边的最大数量
    // 只有链式前向星方式建图需要这个数量
    // 注意如果无向图的最大数量是m条边，数量要准备m*2
    // 因为一条无向边要加两条有向边
    public static int MAX_EDGE = 21;

    public static final int[][] GRAPH1 = new int[MAX_POINT][MAX_POINT];

    public static final List<List<int[]>> GRAPH2 = new ArrayList<>();

    // 每一节点的第一条边
    public static final int[] HEAD = new int[MAX_POINT];
    // 每条边的下一个兄弟边
    public static final int[] NEXT = new int[MAX_EDGE];
    // 每条边对应的目标点
    public static final int[] TO = new int[MAX_EDGE];
    // 边ID
    public static int edgeId;
    // 边权重
    public static final int[] WEIGHT = new int[MAX_EDGE];


    static void build(int n) {
        // 邻接矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                GRAPH1[i][j] = 0;
            }
        }

        // 邻接表
        GRAPH2.clear();
        for (int i = 0; i <= n; i++) {
            GRAPH2.add(new ArrayList<>());
        }

        // 链式前向星
        edgeId = 1;
        Arrays.fill(HEAD, 1, n + 1, 0);
    }

    public static void addEdge(int from, int to, int weight) {
        NEXT[edgeId] = HEAD[from];
        HEAD[from] = edgeId;
        TO[edgeId] = to;
        WEIGHT[edgeId] = weight;
        edgeId++;
    }

    // 三种方式建立有向图带权图
    public static void directGraph(int[][] edges) {
        // 邻接矩阵建图
        for (int[] edge : edges) {
            GRAPH1[edge[0]][edge[1]] = edge[2];
        }

        // 邻接表建图
        for (int[] edge : edges) {
            GRAPH2.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        // 链式前向星建图
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }
    }

    // 三种方式建立无向图带权图
    public static void undirectGraph(int[][] edges) {
        // 邻接矩阵建图
        for (int[] edge : edges) {
            GRAPH1[edge[0]][edge[1]] = edge[2];
            GRAPH1[edge[1]][edge[0]] = edge[2];
        }
        // 邻接表建图
        for (int[] edge : edges) {
            GRAPH2.get(edge[0]).add(new int[]{edge[1], edge[2]});
            GRAPH2.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }


        // 链式前向星建图
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
            addEdge(edge[1], edge[0], edge[2]);
        }
    }

    public static void traversal(int n) {
        System.out.println("邻接矩阵遍历 :");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(GRAPH1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("邻接表遍历 :");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居、边权) : ");
            for (int[] edge : GRAPH2.get(i)) {
                System.out.print("(" + edge[0] + "," + edge[1] + ") ");
            }
            System.out.println();
        }

        System.out.println("链式前向星 :");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居、边权) : ");
            for (int curEdge = HEAD[i]; curEdge > 0; curEdge = NEXT[curEdge]) {
                System.out.print("(" + TO[curEdge] + "," + WEIGHT[curEdge] + ") ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
// 理解了带权图的建立过程，也就理解了不带权图
        // 点的编号为1...n
        // 例子1自己画一下图，有向带权图，然后打印结果
        int n1 = 4;
        int[][] edges1 = {{1, 3, 6}, {4, 3, 4}, {2, 4, 2}, {1, 2, 7}, {2, 3, 5}, {3, 1, 1}};
        build(n1);
        directGraph(edges1);
        traversal(n1);
        System.out.println("==============================");
        // 例子2自己画一下图，无向带权图，然后打印结果
        int n2 = 5;
        int[][] edges2 = {{3, 5, 4}, {4, 1, 1}, {3, 4, 2}, {5, 2, 4}, {2, 3, 7}, {1, 5, 5}, {4, 2, 6}};
        build(n2);
        undirectGraph(edges2);
        traversal(n2);
    }
}
