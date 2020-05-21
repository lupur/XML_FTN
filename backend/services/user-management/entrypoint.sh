#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar user-management-1.0.0.jar