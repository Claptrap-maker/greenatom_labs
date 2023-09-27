package greenatom.projects.exceptions.task1;

public class MyException extends RuntimeException {
    private String s;

    static {
        init();
    }

    public MyException() {
        s = "Hello";
    }

    private static void init() {
        throw new MyException();
    }

    public String get() {
        return s;
    }

}
