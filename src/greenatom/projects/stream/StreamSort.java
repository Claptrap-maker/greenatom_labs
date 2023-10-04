package greenatom.projects.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSort {
    public List<String> uniqueLanguages(List<Developer> developers) {

        Map<String, Integer> map = mapWithRepetitions(developers.stream());
        return developers.stream().filter(x -> x.getLanguages().stream()
                .anyMatch((y) -> map.containsKey(y) && map.get(y) == 1))
                .map(Developer::getName)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> mapWithRepetitions(Stream<Developer> stream) {
        Map<String, Integer> map = new HashMap<>();
        stream
                .map(Developer::getLanguages)
                .flatMap(Collection::stream)
                .toList()
                .forEach(e -> map.merge(e, 1, Integer::sum));
        return map;
    }
}
