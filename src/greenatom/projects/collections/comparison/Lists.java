package greenatom.projects.collections.comparison;

import java.util.*;

public class Lists {

    private ArrayList<Integer> array = new ArrayList<>();
    private LinkedList<Integer> list = new LinkedList<>();
    public void addToEnd () {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            array.add(i);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При добавлении в конец 500000 элементов прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.add(i);
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При добавлении в конец 500000 элементов прошло времени для LinkedList, мс: " + elapsed1);
//        array.clear();
//        list.clear();
        System.out.println("**************************************");
    }

    public void addToHead() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            array.add(0, i);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При добавлении в начало 500000 элементов прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.addFirst(i);
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При добавлении в начало 500000 элементов прошло времени для LinkedList, мс: " + elapsed1);
//        array.clear();
//        list.clear();
        System.out.println("**************************************");
    }

    public void addToMiddle() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            array.add(array.size() / 2, i);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При добавлении в середину 500000 элементов прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.add(list.size() / 2, i);
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При добавлении в середину 500000 элементов прошло времени для LinkedList, мс: " + elapsed1);
//        array.clear();
//        list.clear();
        System.out.println("**************************************");
    }

    public void search() {
        long start = System.currentTimeMillis();
        long finish = 0;
        for (Integer i : array) {
            if (i == 350000) {
                finish = System.currentTimeMillis();
                break;
            }
        }
        long elapsed = finish - start;
        System.out.println("При поиске элемента из 500000 прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        long finish1 = 0;
        for (Integer i : list) {
            if (i == 350000) {
                finish1 = System.currentTimeMillis();
                break;
            }
        }
        long elapsed1 = finish1 - start1;
        System.out.println("При поиске элемента из 500000 прошло времени для LinkedList, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public void removeFromHead() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            array.remove(0);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При удалении из начала 500000 элементов прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.pollFirst();
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При удалении из начало 500000 элементов прошло времени для LinkedList, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public void removeFromTail() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            array.remove(array.size() - 1);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При удалении с конца 500000 элементов прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.pollLast();
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При удалении с конца начало 500000 элементов прошло времени для LinkedList, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public void removeFromMiddle() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            array.remove(array.size() / 2);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("При удалении из середины 50000 элементов прошло времени для ArrayList, мс: " + elapsed);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.remove(list.size() / 2);
        }
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("При добавлении из середины 50000 элементов прошло времени для LinkedList, мс: " + elapsed1);
        System.out.println("**************************************");
    }

    public static void main(String[] args) {
        Lists lists = new Lists();
        lists.addToEnd(); //19 vs 90
        //lists.addToHead(); //20391 vs 74
        //lists.addToMiddle(); // 9300 vs 340910
        lists.search(); // 9 vs 4
        //lists.removeFromHead(); // 14404 vs 11
        lists.removeFromTail(); // 8 vs 15
        //lists.removeFromMiddle(); // 9001 vs 519619
    }
}
