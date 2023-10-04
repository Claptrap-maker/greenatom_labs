package greenatom.projects.stream;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        StreamSort sort = new StreamSort();
        List<Developer> developers = List.of(dev1, dev2, dev3);
        List<String> developersWithUniqueLanguages = sort.uniqueLanguages(developers);
        System.out.println(developersWithUniqueLanguages);

    }
}
