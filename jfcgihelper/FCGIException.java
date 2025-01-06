package jfcgihelper;
public final class FCGIException extends RuntimeException {
    private int errorNum;
    public FCGIException(int n) {
        super();
        errorNum = n;
    }
    public FCGIException(int n, String message) {
        super(message);
        errorNum = n;
    }
    public int getErrorNumber() {
        return errorNum;
    }
}