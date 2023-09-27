package greenatom.projects.exceptions.task1;

/**
 * В первом случае переменная e будет типа java.lang.ExceptionInInitializerError
 * ввиду того, что внутри статического инициализатора класса MyException возникает
 * непредвиденное исключение, и JVM автоматически оборачивает его внутри экземпляра
 * класса ExceptionInInitializerError, который в сою очередь поддерживает ссылку на
 * фактическое исключение в качестве основной причины.
 * <p>
 * Во втором случае переменная e будет типа java.lang.NoClassDefFoundError ввиду того,
 * что JVM пытается загрузить определение класса MyException через создание нового
 * экземпляра с использованием ключевого слова new во время выполнения программы и не
 * находит его, хотя его определение существовало на момент компиляции. Такая ошибка
 * возникает по причине того, что статический блок класса MyException генерирует исключение,
 * соответственно инициализации не происходит, и JVM перестает видеть определение класса
 * MyException.
 *
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            try {
                new MyException();
            } catch (Throwable e) {
                System.out.println("e = " + e);
                if (e.getCause() instanceof MyException) {
                    MyException ex = (MyException) e.getCause();
                    System.out.println("e instanceof " + MyException.class.getName() + ", s = " + ex.get());
                }
            }
        }
    }

}
