//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

public class HttpBaseline_test {

    public static void main(String[] args) {
        System.out.println("Server started...");
        try {
            String LF = "\n";
            String CRLF = "\r";
            int count = 0;
            java.net.ServerSocket listener = new java.net.ServerSocket(8080);
            while (true) {
                java.net.Socket socket = listener.accept();
                try {
                    java.io.InputStream reader = socket.getInputStream();
                    reader.read(new byte[1000]);
                    java.io.OutputStream writer = socket.getOutputStream();
                    String body = "<h1>Hello world</h1>" + LF;
                    writer.write(("HTTP/1.1 200 OK" + LF).getBytes());
                    writer.write(("Content-Length: " + Integer.toString(body.length()) + LF).getBytes());
                    writer.write(("Content-Type: text/html" + LF).getBytes());
                    writer.write(("Connection: close" + LF).getBytes());
                    writer.write(LF.getBytes());
                    writer.write(body.getBytes());
                    writer.flush();
                    writer.close();
                    socket.close();
                    count++;
                    System.out.println(count);
                } catch (java.io.IOException e) {
                    System.out.println(e);
                    socket.close();
                }
            }
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
