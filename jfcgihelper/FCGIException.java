package jfcgihelper;
public final class FCGIException extends RuntimeException {
    private int errorNum;
    public FCGIException(int n) {
        if(n == 0) throw new IllegalArgumentException("invalid error number: 0");
        super();
        errorNum = n;
    }
    public FCGIException(int n, String message) {
        if(n == 0) throw new IllegalArgumentException("invalid error number: 0");
        super(message);
        errorNum = n;
    }
    public int getErrorNumber() {
        return errorNum;
    }
}