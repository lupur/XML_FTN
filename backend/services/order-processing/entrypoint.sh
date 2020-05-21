#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar order-processing-1.0.0.jar