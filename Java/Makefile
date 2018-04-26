# Java version of Makefile
JAVA= java
CFLAGS= -Xlint
RFLAGS= 

all: StackTest

clean:
	-rm -rf build
	mkdir build

StackTest: build/StackTest/success

build/StackTest/success: Lists/Stack.java Lists/AStack.java Lists/Link.java Lists/LStack.java Lists/StackTest.java ../Java_Generic/errorInfo/ErrorRec.java
	@echo "Array-based and Linked Stack Test"
	-@rm -rf build/StackTest
	@mkdir build/StackTest
	@mkdir build/StackTest/lists build/StackTest/errorInfo
	@cp Lists/Stack.java build/StackTest/lists
	@cp Lists/AStack.java build/StackTest/lists
	@cp Lists/LStack.java build/StackTest/lists
	@cp Lists/Link.java build/StackTest/lists
	@cp ../Java_Generic/errorInfo/ErrorRec.java build/StackTest/errorInfo
	@cp Lists/StackTest.java build/StackTest/lists
	cd build/StackTest; \
	javac $(CFLAGS) lists/StackTest.java; \
	$(JAVA) $(RFLAGS) lists/StackTest
