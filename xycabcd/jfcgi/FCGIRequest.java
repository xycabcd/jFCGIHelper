package xycabcd.jfcgi;

import java.net.Socket;
import java.util.Properties;

public class FCGIRequest {
    public final static String version = "developing";

    public static void main(String[] args_) {
        System.out.println(version);
    }

    public static FCGIRequest reciveRequest(Socket givenSocket) throws java.io.IOException {
        FCGIRequest request = new FCGIRequest();
        request.socket = givenSocket;
        request.setInputStream(new FCGIInputStream(request.getSocket().getInputStream(),
                    8192, 0, request));
        request.getInputStream().fill();
        if (!request.isBeginProcessed()) {
            request.socket.close();
            return null;
        }
        request.setParameters(new Properties());
        request.parameters.put("ROLE", request.getRole().getRoleName());
        request.getInputStream().setReaderType(FCGIConstants.TYPE_PARAMS);
        if (!new FCGIMessage(request.getInputStream()).readParams(request.parameters)) {
            return null;
        }
        request.getInputStream().setReaderType(FCGIConstants.TYPE_STDIN);
        request.setOutputStream(new FCGIOutputStream(request.getSocket().
                getOutputStream(), 8192,
                FCGIConstants.TYPE_STDOUT, request));
        request.setErrorStream(new FCGIOutputStream(request.getSocket().
                getOutputStream(), 512,
                FCGIConstants.TYPE_STDERR, request));
        request.setNumWriters(2);
        return request;
    }

    /*
    * This class has no methods. Right now we are single threaded
    * so there is only one request object at any given time which
    * is referenced by an FCGIInterface class variable . All of this
    * object's data could just as easily be declared directly there.
    * When we thread, this will change, so we might as well use a
    * separate class. In line with this thinking, though somewhat
    * more perversely, we kept the socket here.
    */

    /*
     * class variables
     */
    /*public static Socket  socket; */
    // same for all requests

    /*
     * instance variables
     */
    Socket socket;
    boolean isBeginProcessed = false;
    int requestID;
    boolean keepConnection;
    ApplicationRole role;
    public int appStatus;
    int numWriters;
    FCGIInputStream inputStream;
    FCGIOutputStream outputStream;
    FCGIOutputStream errorStream;
    Properties parameters;
    boolean closed = false;
    int errno = 0;

    FCGIRequest() {}
    
    public void close() throws java.io.IOException {
        inputStream.close();
        outputStream.close(); // this set our closed variable
        errorStream.isClosed = true;
        socket.close();
    }

    public int getId() {
        return getRequestID();
    }


    Socket getSocket() {
        return socket;
    }

    void setSocket(final Socket socket) {
        this.socket = socket;
    }

    public boolean isBeginProcessed() {
        return isBeginProcessed;
    }

    void setBeginProcessed(final boolean beginProcessed) {
        isBeginProcessed = beginProcessed;
    }

    public int getRequestID() {
        return requestID;
    }

    void setRequestID(final int requestID) {
        this.requestID = requestID;
    }

    public boolean isKeepConnection() {
        return keepConnection;
    }

    void setKeepConnection(final boolean keepConnection) {
        this.keepConnection = keepConnection;
    }

    public ApplicationRole getRole() {
        return role;
    }

    void setRole(final int role) {
        this.role = ApplicationRole.getByValue(role);
    }

    public int getAppStatus() {
         
        return appStatus;
    }

    public void setAppStatus(final int appStatus) {
         
        this.appStatus = appStatus;
    }

    public int getNumWriters() {
         
        return numWriters;
    }

    void setNumWriters(final int numWriters) {
         
        this.numWriters = numWriters;
    }

    public FCGIInputStream getInputStream() {
         
        return inputStream;
    }

    void setInputStream(final FCGIInputStream inputStream) {
         
        this.inputStream = inputStream;
    }

    public FCGIOutputStream getOutputStream() {
         
        return outputStream;
    }

    void setOutputStream(final FCGIOutputStream outputStream) {
         
        this.outputStream = outputStream;
    }

    public FCGIOutputStream getErrorStream() {
         
        return errorStream;
    }

    void setErrorStream(final FCGIOutputStream errorStream) {
         
        this.errorStream = errorStream;
    }

    public Properties getParameters() {
        return (Properties) parameters.clone();
    }

    void setParameters(final Properties parameters) {
         
        this.parameters = parameters;
    }
    public int getFCGIError() {
        return errno;
    }
}


