package xycabcd.jfcgi;
public final class FCGIException extends RuntimeException {
    private int errorNum;
    FCGIException(int n) {
        super();
        if(n == 0) throw new IllegalArgumentException("invalid error number: 0");
        errorNum = n;
    }
    FCGIException(int n, String message) {
        super(message);
        if(n == 0) throw new IllegalArgumentException("invalid error number: 0");
        errorNum = n;
    }
    public int getErrorNumber() {
        return errorNum;
    }
}
