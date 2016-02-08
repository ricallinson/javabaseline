# javabaseline

This is a simple _hello world_ http server to find a baseline number for performance tests. Requires [Jmm](https://github.com/jminusminus/jmm).

	jmm get github.com/ricallinson/javabaseline
	jmm test ./HttpBaseline_test.java

Now run apache bench;

	ab -c 20 -n 20000 http://127.0.0.1:8080/

## Problem

The server pauses at about 16000 requests. Why?
