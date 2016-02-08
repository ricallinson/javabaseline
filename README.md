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

	ab -c 20 -n 20000 http://127.0.0.1:8080/

## Problem

The server pauses at about 16000 requests. Why?
