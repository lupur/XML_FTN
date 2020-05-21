#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar soap-service-1.0.0.jar