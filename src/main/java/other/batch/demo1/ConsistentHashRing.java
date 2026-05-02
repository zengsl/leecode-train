package other.batch.demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class ConsistentHashRing {
    private Map<Integer, String> ring = new HashMap<>();
    private TreeSet<Integer> sortedKeys = new TreeSet<>();
    private int replicas;

    private int getHash(String value) {
        return value.hashCode();
    }

    public ConsistentHashRing(int replicas) {
        this.replicas = replicas;
    }

    public void addNode(String node) {
        for (int i = 0; i < replicas; ++i) {
            int replicaKey = getHash(node + "_" + i);
            ring.put(replicaKey, node);
            sortedKeys.add(replicaKey);
        }
    }

    public void removeNode(String node) {
        for (int i = 0; i < replicas; ++i) {
            int replicaKey = getHash(node + "_" + i);
            ring.remove(replicaKey);
            sortedKeys.remove(replicaKey);
        }
    }

    public String getNode(String key) {
        if (ring.isEmpty()) {
            return "";
        }

        int hashValue = getHash(key);
        Integer it = sortedKeys.ceiling(hashValue);

        if (it == null) {
            it = sortedKeys.first();
        }

        return ring.get(it);
    }

    public static void main(String[] args) {
        ConsistentHashRing hashRing = new ConsistentHashRing(3);

        hashRing.addNode("Node_A");
        hashRing.addNode("Node_B");
        hashRing.addNode("Node_C");

        String key = "first_key";
        String node = hashRing.getNode(key);

        System.out.println("The key '" + key + "' is mapped to node: " + node);
    }
}
