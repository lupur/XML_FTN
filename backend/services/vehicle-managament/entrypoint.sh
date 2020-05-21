#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar vehicle-managament-1.0.0.jar