package greenatom.projects.collections.reverse;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReverseMap {
    public Map<String, Integer> reverse(Map<Integer, String> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors
                        .toMap(Map.Entry::getValue, Map.Entry::getKey, (a, b) -> b, HashMap::new));
    }
}
