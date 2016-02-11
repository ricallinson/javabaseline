//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

class HttpMessageHandler implements Runnable {

    protected java.net.Socket socket;
    protected int id;

    HttpMessageHandler(java.net.Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    public void run() {
        String CRLF = "\r\n";
        int keepAliveMax = 1000;
        try {
            java.io.InputStream reader = this.socket.getInputStream();
            java.io.OutputStream writer = this.socket.getOutputStream();
            while (--keepAliveMax >= 0) {
                reader.read(new byte[1000]);
                String body = "<h1>Hello world</h1>" + CRLF;
                writer.write(("HTTP/1.1 200 OK" + CRLF).getBytes());
                writer.write(("Content-Length: " + Integer.toString(body.length()) + CRLF).getBytes());
                writer.write(("Content-Type: text/html" + CRLF).getBytes());
                if (keepAliveMax > 0) {
                    writer.write(("Keep-Alive: timeout=15, max=" + Integer.toString(keepAliveMax) + CRLF).getBytes());
                } else {
                    writer.write(("Connection: close" + CRLF).getBytes());
                }
                writer.write(CRLF.getBytes());
                writer.write(body.getBytes());
                writer.flush();
                // System.out.println(Integer.toString(this.id) + ":" + Integer.toString(keepAliveMax));
            }
            // writer.close();
            this.socket.close();
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
