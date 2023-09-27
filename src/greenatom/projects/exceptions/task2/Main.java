package greenatom.projects.exceptions.task2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            for (int i = 0; i < 12; i++) {
                if (list.size() == 10) {
                    throw new MyException();
                }
                list.add(i);
            }
        } catch (MyException e) {
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("getCause(): " + e.getCause());
            System.out.println("toString(): " + e);
            System.out.println("getStackTrace(): ");
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            System.out.println("printStackTrace(): ");
            e.printStackTrace();
        }
    }
}
