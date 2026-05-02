package other.batch.demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashRing2 {
    // 使用 TreeMap 替代 HashMap + TreeSet，代码更简洁，且天然保证 key 有序
    private final TreeMap<Long, String> ring = new TreeMap<>();
    private final int replicas;

    /**
     * 优化点 1：使用 FNV-1a 32-bit 哈希算法（或可替换为 MurmurHash）
     * 相比 String.hashCode()，它的分布更均匀，碰撞率更低，对 IP 地址更友好。
     */
    private long getHash(String value) {
        final int p = 16777619;
        long hash = 2166136261L;
        for (int i = 0; i < value.length(); i++) {
            hash = (hash ^ value.charAt(i)) * p;
        }
        // 加上无符号右移，混合高位和低位，防止在 TreeMap 中树退化
        hash += hash >>> 16;
        return hash < 0 ? hash + Integer.MAX_VALUE : hash; // 保证非负（虽然 long 范围够大，这里做保守处理）
    }

    public ConsistentHashRing2(int replicas) {
        if (replicas <= 0) {
            throw new IllegalArgumentException("Replicas must be greater than 0");
        }
        this.replicas = replicas;
    }

    public void addNode(String node) {
        if (node == null || node.isEmpty()) return;
        for (int i = 0; i < replicas; ++i) {
            // 优化点 2：拼接符建议使用特定字符（如 "|" 或 "-"），避免与节点名本身的数字产生巧合拼接
            long replicaKey = getHash(node + "|VN" + i);
            ring.put(replicaKey, node);
        }
    }

    public void removeNode(String node) {
        if (node == null || node.isEmpty()) return;
        for (int i = 0; i < replicas; ++i) {
            long replicaKey = getHash(node + "|VN" + i);
            ring.remove(replicaKey);
        }
    }

    /**
     * 优化点 3：规范化返回值，避免调用方 NPE
     */
    public String getNode(String key) {
        if (ring.isEmpty()) {
            throw new IllegalStateException("Hash ring is empty, no nodes available");
        }
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        long hashValue = getHash(key);
        // TreeMap.ceilingKey 等同于 TreeSet.ceiling
        Long ceilingKey = ring.ceilingKey(hashValue);

        // 环的闭环：如果没找到更大的，说明到了末尾，取环的第一个节点
        if (ceilingKey == null) {
            ceilingKey = ring.firstKey();
        }

        return ring.get(ceilingKey);
    }

    // --- 测试用例 ---
    public static void main(String[] args) {
        // 100个虚拟节点，模拟生产环境
        ConsistentHashRing2 hashRing = new ConsistentHashRing2(100);

        hashRing.addNode("192.168.1.10:8080");
        hashRing.addNode("192.168.1.11:8080");
        hashRing.addNode("192.168.1.12:8080");

        // 验证分布均匀度
        Map<String, Integer> stats = new HashMap<>();
        int totalKeys = 100000;
        for (int i = 0; i < totalKeys; i++) {
            String key = "user_" + i;
            String node = hashRing.getNode(key);
            stats.put(node, stats.getOrDefault(node, 0) + 1);
        }

        System.out.println("=== 节点负载分布 (总请求数: " + totalKeys + ") ===");
        stats.forEach((node, count) -> {
            double percent = (count * 100.0) / totalKeys;
            System.out.printf("节点 %s 承担了: %d 个请求 (%.2f%%)%n", node, count, percent);
        });
    }
}

