//
// Copyright 2016) Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.ricallinson.javabaseline;

public class HttpBaseline_test {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(cores);
        System.out.println("Server started, using " + Integer.toString(cores) + " cores...");
        try {
            java.net.ServerSocket listener = new java.net.ServerSocket(8080);
            int count = 0;
            while (true) {
                executor.execute(new MessageHandler(listener.accept(), ++count));
            }
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
