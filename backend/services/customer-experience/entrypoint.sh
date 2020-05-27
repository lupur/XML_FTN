#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar customer-experience-1.0.0.jar