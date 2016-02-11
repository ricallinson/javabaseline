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
        String LF = "\n";
        try {
            java.io.InputStream reader = this.socket.getInputStream();
            reader.read(new byte[1000]);
            java.io.OutputStream writer = this.socket.getOutputStream();
            String body = "<h1>Hello world</h1>" + LF;
            writer.write(("HTTP/1.1 200 OK" + LF).getBytes());
            writer.write(("Content-Length: " + Integer.toString(body.length()) + LF).getBytes());
            writer.write(("Content-Type: text/html" + LF).getBytes());
            writer.write(("Connection: close" + LF).getBytes());
            writer.write(LF.getBytes());
            writer.write(body.getBytes());
            writer.flush();
            writer.close();
            this.socket.close();
            // System.out.println(this.id);
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
