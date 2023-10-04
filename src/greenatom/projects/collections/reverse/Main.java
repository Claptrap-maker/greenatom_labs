package greenatom.projects.collections.reverse;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Fine");
        map.put(2, "Julia");
        map.put(4, "Fine");
        map.put(5, "Julia");
        map.put(6, "Hello");
        map.put(8, "Exclusive");
        ReverseMap reverseMap = new ReverseMap();
        Map<String, Integer> newMap = reverseMap.reverse(map);
        System.out.println(newMap);
    }
}
