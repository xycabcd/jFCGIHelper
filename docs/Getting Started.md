# Getting Started

Get jfcgihelper.FCGIRequest, give FCGIRequest.reciveRequest(Socket) a Socket that you are sure it is to a FastCGI client (webserver).

Store this FCGIRequest. Get parameters from `request.getParameters()` as a java.uil.Properties, input stream from `request.getInputStream`, output stream from `request.getOutputStream()`, and error output stream from `request.getErrorStream()`. Send data to output stream and send text you want the web server to keep to error output stream. Set `appStatus` by using either `request.appStatus` or `request.setAppStatus()`. When you are done, do `request.close()`. Here is a little example:

```java
import java.net.*;
import java.io.*;
import jfcgihelper.*;
import java.util.Properties;
public final class Test {
    private Test() {}
    public static void main(String[] args_) throws java.io.IOException {
        ServerSocket inputSocket = new ServerSocket(50002);
        while(true) {
            Socket aSocket = inputSocket.accept();
            FCGIRequest request = FCGIRequest.reciveRequest(aSocket);
            Properties parameters = request.getParameters();
            PrintStream output = new PrintStream(request.getOutputStream());
            output.println("Content-type: text/plain \n");
            for(java.util.Map.Entry thisProperty : parameters.entrySet()) {
                output.println("\"" + thisProperty.getKey() + "\"	:	\"" + thisProperty.getValue() + "\"" );
            }
            request.close();
        }
    }
}
```