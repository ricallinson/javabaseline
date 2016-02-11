# javabaseline

This is a simple _hello world_ http server to find a baseline number for performance tests. Requires [Jmm](https://github.com/jminusminus/jmm).

## Install Jmm

	git clone git@github.com:jminusminus/jmm.git ~/.jmm
	source ~/.jmm/jmm.sh

## Install the Test

	mkdir ~/javabaseline
	cd ~/javabaseline
	jmm here .
	jmm get github.com/ricallinson/javabaseline
	jmm test ./src/github/com/ricallinson/javabaseline/HttpBaseline_test.java

Now run apache bench;

	ab -c 20 -n 20000 -k http://127.0.0.1:8080/

## Problems

The server pauses at about 16384 requests. Why? It's too fast.

This is because on OSX the default ephemeral port range only allows for 16384 ports. Once they are used up Apache Bench has to wait for them to be freed before it can continue. For more info see this [stackoverflow](http://stackoverflow.com/questions/1216267/ab-program-freezes-after-lots-of-requests-why/1217100#1217100) response.
