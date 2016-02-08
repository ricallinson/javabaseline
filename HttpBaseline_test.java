//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpBaseline_test {

    public static void main(String[] args) {
        System.out.println("Server started...");
        try {
            int count = 0;
            ServerSocket listener = new ServerSocket(8080);
            while (true) {
                Socket socket = listener.accept();
                try {
                    InputStream reader = socket.getInputStream();
                    reader.read(new byte[1000]);
                    OutputStream writer = socket.getOutputStream();
                    String body = "<h1>Hello world</h1>";
                    writer.write("HTTP/1.1 200 OK\r\n".getBytes());
                    writer.write(("Content-Length: " + Integer.toString(body.length()) + "\r\n").getBytes());
                    writer.write("Content-Type: text/html\r\n".getBytes());
                    writer.write("Connection: Closed\r\n".getBytes());
                    writer.write("\r\n".getBytes());
                    writer.write(body.getBytes());
                    writer.flush();
                    socket.close();
                    count++;
                    System.out.println(count);
                } catch (IOException e) {
                    System.out.println(e);
                    socket.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
