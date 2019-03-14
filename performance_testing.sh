#!/bin/bash
export CLASSPATH=/home/wyes/env/protobuf_java_env/protobuf-java-3.7.0-rc1.jar

cd examples
make java
avg_time=0

echo "Enter number of iterations : "
read n
for i in `seq 1 $n`;
do
	./add_person_java enc_file_for_protobuf.dat > out
	B="$(cat out)"
	echo "$B milliseconds"
	A="$(cut -d':' -f2 <<< $B )"
	#echo "$A"
	avg_time="$(($avg_time + $A))"
done
avg_time="$(($avg_time/$n))";

echo "Avg time : $avg_time milliseconds"

stat --printf="Encoding size : %s bytes\n" enc_file_for_protobuf.dat