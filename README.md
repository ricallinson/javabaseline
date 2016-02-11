# javabaseline

This is a simple _hello world_ http server to find a baseline number for performance tests. Requires [Jmm](https://github.com/jminusminus/jmm).

## Install Jmm

	git clone git@github.com:jminusminus/jmm.git ~/.jmm
	source ~/.jmm/jmm.sh

## Run Test

Install and start the server with the following;

	mkdir ~/javabaseline
	cd ~/javabaseline
	jmm here .
	jmm get github.com/ricallinson/javabaseline
	jmm test ./src/github/com/ricallinson/javabaseline/HttpBaseline_test.java

The test will print the number of cores and threads the server is using;

	Server started, using 4 cores and 8 threads...

Now run apache bench;

	ab -n 1000000 -c 8 -k http://127.0.0.1:8080/

## Performance Results

The test was run on a MacBook Pro (Retina, 13-inch, Mid 2014), 3 GHz Intel Core i7 with 16 GB 1600 MHz DDR3 memory.

	Concurrency Level:      8
	Time taken for tests:   12.464 seconds
	Complete requests:      1000000
	Failed requests:        0
	Keep-Alive requests:    998004
	Total transferred:      118756414 bytes
	HTML transferred:       22000000 bytes
	Requests per second:    80228.88 [#/sec] (mean)
	Time per request:       0.100 [ms] (mean)
	Time per request:       0.012 [ms] (mean, across all concurrent requests)
	Transfer rate:          9304.39 [Kbytes/sec] received

## Problems Found

The server use to pause at about 16384 requests. Why? It's too fast.

This was because on OSX the default ephemeral port range only allows for 16384 ports. Once they are used up Apache Bench has to wait for them to be freed before it can continue. For more info see this [stackoverflow](http://stackoverflow.com/questions/1216267/ab-program-freezes-after-lots-of-requests-why/1217100#1217100) response.

To combat this I've used "keep-alive" so the ports are resued allowing for much greater test runs.
