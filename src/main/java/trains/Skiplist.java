package trains;

// https://leetcode.cn/problems/design-skiplist/
public class Skiplist {

    public static void main(String[] args) {
        /*Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0));   // 返回 false
        skiplist.add(4);
        System.out.println(skiplist.search(1));   // 返回 true
        System.out.println(skiplist.erase(0));    // 返回 false，0 不在跳表中
        System.out.println(skiplist.erase(1));    // 返回 true
        System.out.println(skiplist.search(1));   // 返回 false，1 已被擦除*/


        Skiplist skiplist = new Skiplist();
        skiplist.add(0);
        skiplist.add(5);
        skiplist.add(2);
        skiplist.add(1);
        System.out.println(skiplist.search(0));
        System.out.println(skiplist.erase(5));
        System.out.println(skiplist.search(2));
        System.out.println(skiplist.search(3));
        System.out.println(skiplist.search(2));


    }

    private static final int MAX_LEVEL = 6;

    static class Node {
        public int val;
        public int count;
        public int level;
        public Node[] levels;

        public Node(int val, int level, int count) {
            this.val = val;
            this.level = level;
            this.count = count;
            levels = new Node[MAX_LEVEL];
        }
    }

    private final Node root;

    public Skiplist() {
        root = new Node(-1, MAX_LEVEL - 1, 0);
    }

    public boolean search(int target) {
        return find(target) != null;
    }

    private Node find(int target) {
        int currLevel = MAX_LEVEL - 1;
        Node currNode = root;
        while (currLevel >= 0) {
            while (currNode.levels[currLevel] != null && currNode.levels[currLevel].val < target) {
                currNode = currNode.levels[currLevel];
            }
            if (currNode.levels[currLevel] != null && currNode.levels[currLevel].val == target) {
                return currNode.levels[currLevel];
            }
            currLevel--;
        }
        return null;
    }

    public void add(int num) {
        Node node = find(num);
        if (node != null) {
            node.count++;
            return;
        }

        // 0 ~ MAX_LEVEL - 1
        int level = 0;
        while (Math.random() > 0.5 && level < MAX_LEVEL - 1) {
            level++;
        }
        // 0 ~ MAX_LEVEL - 1
        int currLevel = MAX_LEVEL - 1;
        Node newNode = new Node(num, currLevel, 1);
        Node currNode = root, next;
        while (currLevel >= 0) {
            while (currNode.levels[currLevel] != null && currNode.levels[currLevel].val < num) {
                currNode = currNode.levels[currLevel];
            }
            if (currLevel <= level) {
                next = currNode.levels[currLevel];
                currNode.levels[currLevel] = newNode;
                newNode.levels[currLevel] = next;
            }
            currLevel--;
        }
    }

    public boolean erase(int num) {
        Node node = find(num);
        if (node == null) {
            return false;
        }
        if (--node.count == 0) {
            removeNode(num);
        }
        return true;
    }

    private void removeNode(int num) {
        int currLevel = MAX_LEVEL - 1;
        Node currNode = root;

        while (currLevel >= 0) {
            while (currNode.levels[currLevel] != null && currNode.levels[currLevel].val < num) {
                currNode = currNode.levels[currLevel];
            }
            if (currNode.levels[currLevel] != null && currNode.levels[currLevel].val == num) {
                Node removeNode = currNode.levels[currLevel];
                currNode.levels[currLevel] = removeNode.levels[currLevel];
                removeNode.levels[currLevel] = null;
            }
            currLevel--;
        }
    }
}
