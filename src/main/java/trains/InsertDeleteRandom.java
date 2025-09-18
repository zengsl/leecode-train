package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertDeleteRandom {
    static class RandomizedSet {
        Map<Integer, Integer> map;
        List<Integer> list;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                Integer currentIndex = map.get(val);
                Integer lastValue = list.getLast();
                list.set(currentIndex, lastValue);
                map.put(lastValue, currentIndex);

                list.removeLast();
                map.remove(val);
                return true;
            }
        }

        public int getRandom() {
            return list.get((int) (Math.random() * (list.size())));
        }
    }


}
