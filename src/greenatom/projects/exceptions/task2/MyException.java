package greenatom.projects.exceptions.task2;

public class MyException extends Exception {

    private String message = "More than 10 elements cannot be added to ArrayList";

    private String cause;

    private StackTraceElement[] stack = super.getStackTrace();

    MyException(){}

    MyException(String cause) {
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public synchronized Throwable getCause() {
        if (super.getCause() == null) {
            this.initCause(new Throwable(this.cause));
        }
        return this;
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        if (message == null) {
            message = this.message;
        }
        return (message != null) ? (s + ": " + message) : s;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return this.stack;
    }
}
