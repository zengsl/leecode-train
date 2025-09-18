package trains;

import java.util.*;

public class InsertDeleteRandomDuplicatesAllowed {

    static class RandomizedSet {
        Map<Integer, Set<Integer>> map;
        List<Integer> list;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            Set<Integer> set = map.get(val);
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            Set<Integer> valueSet = map.get(val);
            if (valueSet == null) {
                return false;
            }
            Integer lastValue = list.getLast();
            if (val == lastValue) {
                valueSet.remove(list.size() - 1);
            } else {
                // 取出一个索引值
                Integer index = valueSet.iterator().next();
                // 将最后一个数据放到索引位置，等价于删除索引位置的元素
                list.set(index, lastValue);
                // 取出最后一个元素所在的Set
                Set<Integer> lastValueSet = map.get(lastValue);
                // 移除无效索引值
                lastValueSet.remove(list.size() - 1);
                // 添加新索引值
                lastValueSet.add(index);
                // 原始Set移除索引值
                valueSet.remove(index);
            }
            // 列表最后一个元素移除
            list.removeLast();
            // 如果set为空则表示val对应的内容都移除了，直接移除map中val数据
            if (valueSet.isEmpty()) {
                map.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return list.get((int) (Math.random() * (list.size())));
        }
    }
}
