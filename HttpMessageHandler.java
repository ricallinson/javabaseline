//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

class HttpMessageHandler implements Runnable {

    protected java.net.Socket socket;
    protected int id;
    protected int keepAliveMax;

    HttpMessageHandler(java.net.Socket socket, int id, int keepAliveMax) {
        this.socket = socket;
        this.id = id;
        this.keepAliveMax = keepAliveMax;
    }

    public void run() {
        String CRLF = "\r\n";
        try {
            java.io.InputStream reader = this.socket.getInputStream();
            java.io.OutputStream writer = this.socket.getOutputStream();
            while (--this.keepAliveMax >= 0) {
                reader.read(new byte[1000]);
                String body = "<h1>Hello world</h1>" + CRLF;
                String header = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + Integer.toString(body.length()) + CRLF +
                    "Content-Type: text/html" + CRLF;
                if (this.keepAliveMax > 0) {
                    header += "Keep-Alive: timeout=15, max=" + Integer.toString(this.keepAliveMax) + CRLF;
                } else {
                    header += "Connection: close" + CRLF;
                }
                writer.write((header + CRLF + body).getBytes());
                writer.flush();
                // System.out.println(Integer.toString(this.id) + ":" + Integer.toString(this.keepAliveMax));
            }
            // writer.close();
            this.socket.close();
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
