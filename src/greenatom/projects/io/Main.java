package greenatom.projects.io;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите абсолютный путь к файлу: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        FileValidation fileValidation = new FileValidation();
        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("1. Создать файл. ");
            System.out.println("2. Чтение из файла. ");
            System.out.println("3. Запись в файл. ");
            System.out.println("4. Удаление файла. ");
            System.out.println("5. Выход. ");
            String action = scanner.nextLine();
            switch (action) {
                case "1" -> {
                    while (fileValidation.exists(file)) {
                        System.out.println("Такой файл уже существует");
                        System.out.println("Введите корректный путь к файлу: ");
                        path = scanner.nextLine();
                        file = new File(path);
                        fileValidation = new FileValidation();
                    }
                    try {
                        file.createNewFile();
                        System.out.println("Файл создан");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "2", "3", "4" -> {
                    while (!fileValidation.exists(file)) {
                        System.out.println("Такого файла не существует");
                        System.out.println("Введите корректный путь к файлу: ");
                        path = scanner.nextLine();
                        file = new File(path);
                        fileValidation = new FileValidation();
                    }
                    String line;
                    switch (action) {
                        case "2":
                            try (BufferedReader bR = new BufferedReader(new FileReader(file))) {
                                do {
                                    line = bR.readLine();
                                    System.out.println(line);
                                } while (line != null);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            try (BufferedWriter bW = new BufferedWriter(new FileWriter(file))) {
                                System.out.println("Для окончания записи введите 0");
                                do {
                                    line = scanner.nextLine();
                                    if (!line.equals("0")) {
                                        bW.write(line);
                                    }
                                } while (!line.equals("0"));
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "4":
                            file.delete();
                            System.out.println("Файл удален!");
                            break;
                        default:
                            break;
                    }
                }
                case "5" -> System.exit(0);
                default -> System.out.println("Неверно введено действие");
            }
        }
    }
}
