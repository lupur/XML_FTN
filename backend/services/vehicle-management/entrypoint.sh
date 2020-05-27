#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar vehicle-management-1.0.0.jar
