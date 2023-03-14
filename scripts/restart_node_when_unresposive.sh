#!/bin/bash

# Get status of node
status=$(curl -m 10 -Is http://127.0.0.1:11898/info | head -1 | grep -o '[0-9][0-9][0-9]')

# Check if status is 200 (OK)
if [ "$status" = 200 ]; then
    # Do nothing
    return
fi

date
echo "node is not responsive"

# Get process ID of kryptokrona node
pid=$(pidof kryptokronad)

# Try to kill node nicely and wait 10s
kill $pid
sleep 10

# Check if pid has changed
pid_new=$(pidof kryptokronad)
if [ "$pid_new" = "$pid" ]; then
    echo "Killing with -9"
    kill -9 $pid
fi
