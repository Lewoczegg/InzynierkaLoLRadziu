#!/bin/bash
cat > Main.java

javac Main.java

if [ $? -eq 0 ]; then
    java Main
else
    echo "Compilation failed"
fi

##!/bin/bash
#
#cat > Task1Main.java
#
#
#javac Task1Main.java
#
#
#if [ $? -eq 0 ]; then
#
#    java Main
#else
#
#    echo "Compilation failed"
#fi

