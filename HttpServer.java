//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

class HttpServer {

    HttpServer(int port) {
        int cores = Runtime.getRuntime().availableProcessors();
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(cores);
        System.out.println("Server started, using " + Integer.toString(cores) + " cores...");
        try {
            java.net.ServerSocket listener = new java.net.ServerSocket(port);
            int count = 0;
            while (true) {
                executor.execute(new HttpMessageHandler(listener.accept(), ++count));
            }
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
