package greenatom.projects.collections.comparison;

import java.util.HashSet;
import java.util.TreeSet;

public class Sets {
    private TreeSet<Integer> tree = new TreeSet<>();
    private HashSet<Integer> hashSet = new HashSet<>();

    public void add() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            tree.add(i);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При добавлении 500000 элементов прошло времени для TreeSet, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            hashSet.add(i);
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При добавлении 500000 элементов прошло времени для HashSet, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public void searchItem() {
        long start = System.currentTimeMillis();
        long finish = 0L;
        for (Integer i : tree) {
            if (i == 350000) {
                finish = System.currentTimeMillis();
                break;
            }
        }
        long elapsed = finish - start;
        System.out.println("При поиске элемента прошло времени для TreeSet, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        long finish1 = 0L;
        for (Integer i : hashSet) {
            if (i == 350000) {
                finish1 = System.currentTimeMillis();
                break;
            }
        }
        long elapsed1 = finish1 - start1;
        System.out.println("При поиске элемента прошло времени для HashSet, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public void removeItem() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            tree.remove(i);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При удалении элементов прошло времени для TreeSet, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            hashSet.remove(i);
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При удалении элементов прошло времени для HashSet, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public static void main(String[] args) {
        Sets sets = new Sets();
        sets.add(); // 139 vs 46
        sets.searchItem(); // 13 vs 7
        sets.removeItem(); // 98 vs 22
    }
}
