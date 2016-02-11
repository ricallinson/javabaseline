//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

class HttpServer {

    HttpServer(int port) {
        int cores = Runtime.getRuntime().availableProcessors();
        int threads = cores * 2;
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(threads);
        System.out.println("Server started, using " + Integer.toString(cores) + " cores and " + Integer.toString(threads) + " threads...");
        try {
            java.net.ServerSocket listener = new java.net.ServerSocket(port);
            int count = 0;
            while (true) {
                executor.execute(new HttpMessageHandler(listener.accept(), ++count, 500));
            }
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
