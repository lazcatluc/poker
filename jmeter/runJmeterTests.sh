#!/bin/sh
failed=0
for test in *.jmx; do 
	logfile=$test".log"
	rm $logfile
	jmeter.sh -n -t $test -l $logfile
	if grep -q "false" $logfile; then
		echo "Failed: $test"
		failed=1
	fi
done
exit $failed
