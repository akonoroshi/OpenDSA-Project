# Java version of Makefile
JAVA= java
CFLAGS= -Xlint
RFLAGS= 

all: Demo Misc Lists Binary Sorting Searching

clean:
	-rm -rf build
	mkdir build

Demo: UpDown

UpDown: Demo/UpDown.java
	@echo "UpDown Test"
	-@rm -rf build/UpDown
	@mkdir build/UpDown
	cp Demo/UpDown.java build/UpDown
	cd build/UpDown; \
	javac $(CFLAGS) UpDown.java; \
	$(JAVA) $(RFLAGS) UpDown

Misc: LargestTest Anal

LargestTest: build/LargestTest/success

build/LargestTest/success: Misc/LargestTest.java
	@echo "Largest Test"
	-@rm -rf build/LargestTest
	@mkdir build/LargestTest
	cp Misc/LargestTest.java build/LargestTest
	cd build/LargestTest; \
	javac $(CFLAGS) LargestTest.java; \
	$(JAVA) $(RFLAGS) LargestTest

Anal: build/Anal/success

build/Anal/success: Misc/Anal.java
	@echo "Analysis Code Test"
	-@rm -rf build/Anal
	@mkdir build/Anal
	cp Misc/Anal.java build/Anal
	cd build/Anal; \
	javac $(CFLAGS) Anal.java; \
	$(JAVA) $(RFLAGS) Anal

Lists: ListTest StackTest QueueTest

ListTest: build/ListTest/success

build/ListTest/success: Lists/ListTest.java Lists/List.java Lists/AList.java Lists/LList.java Lists/Link.java Lists/DList.java Lists/DLink.java Lists/Freelink.java Lists/Freelist.java Utils/ErrorRec.java
	@echo "Array-based, Singly and Doubly Linked, and Free List Test"
	-@rm -rf build/ListTest
	@mkdir build/ListTest
	@cp Lists/List.java build/ListTest
	@cp Lists/AList.java build/ListTest
	@cp Lists/LList.java build/ListTest
	@cp Lists/Link.java build/ListTest
	@cp Lists/DList.java build/ListTest
	@cp Lists/DLink.java build/ListTest
	@cp Lists/Freelist.java build/ListTest
	@cp Lists/Freelink.java build/ListTest
	@cp Utils/ErrorRec.java build/ListTest
	@cp Lists/ListTest.java build/ListTest
	cd build/ListTest; \
	javac $(CFLAGS) ListTest.java; \
	$(JAVA) $(RFLAGS) ListTest

StackTest: build/StackTest/success

build/StackTest/success: Lists/Stack.java Lists/AStack.java Lists/Link.java Lists/LStack.java Lists/StackTest.java Utils/ErrorRec.java
	@echo "Array-based and Linked Stack Test"
	-@rm -rf build/StackTest
	@mkdir build/StackTest
	@cp Lists/Stack.java build/StackTest
	@cp Lists/AStack.java build/StackTest
	@cp Lists/LStack.java build/StackTest
	@cp Lists/Link.java build/StackTest
	@cp Utils/ErrorRec.java build/StackTest
	@cp Lists/StackTest.java build/StackTest
	cd build/StackTest; \
	javac $(CFLAGS) StackTest.java; \
	$(JAVA) $(RFLAGS) StackTest

QueueTest: build/QueueTest/success

build/QueueTest/success: Lists/Queue.java Lists/AQueue.java Lists/Link.java Lists/LQueue.java Lists/QueueTest.java Utils/ErrorRec.java
	@echo "Array-based and Linked Queue Test"
	-@rm -rf build/QueueTest
	@mkdir build/QueueTest
	@cp Lists/Queue.java build/QueueTest
	@cp Lists/AQueue.java build/QueueTest
	@cp Lists/LQueue.java build/QueueTest
	@cp Lists/Link.java build/QueueTest
	@cp Utils/ErrorRec.java build/QueueTest
	@cp Lists/QueueTest.java build/QueueTest
	cd build/QueueTest; \
	javac $(CFLAGS) QueueTest.java; \
	$(JAVA) $(RFLAGS) QueueTest

Binary: Traverse BST

Traverse: build/Traverse/success

build/Traverse/success: Binary/BinNode.java Binary/BSTNode.java Binary/Traverse1.java Binary/checkBST.java Binary/Preorder.java Binary/Traverse.java Utils/KVPair.java
	@echo "Traversal Test"
	-@rm -rf build/Traverse
	@mkdir build/Traverse
	@cp Binary/BinNode.java build/Traverse
	@cp Binary/BSTNode.java build/Traverse
	@cp Utils/KVPair.java build/Traverse
	@cat Binary/Traverse1.java Binary/checkBST.java Binary/Preorder.java Binary/Traverse.java > build/Traverse/Traverse.java
	cd build/Traverse; \
	javac $(CFLAGS) Traverse.java; \
	$(JAVA) $(RFLAGS) Traverse

BST: build/BSTTest/success

build/BSTTest/success: Binary/BST.java Binary/checkBST.java Binary/Preorder.java Binary/BSTTest.java Binary/BSTNode.java Binary/BinNode.java Utils/Permute.java Utils/Swap.java Binary/Traverse.java Utils/KVPair.java errorInfo/ErrorRec.java
	@echo "BST Test"
	-@rm -rf build/BSTTest
	@mkdir build/BSTTest
	@mkdir build/BSTTest/Binary build/BSTTest/errorInfo build/BSTTest/Utils
	@cp Binary/BST.java build/BSTTest/Binary
	@cp Binary/BSTNode.java build/BSTTest/Binary
	@cp Binary/BinNode.java build/BSTTest/Binary
	@cp Binary/checkBST.java build/BSTTest/Binary
	@cp Binary/Preorder.java build/BSTTest/Binary
	@cp Utils/KVPair.java build/BSTTest/Utils
	@cp Utils/Permute.java build/BSTTest/Utils
	@cp Utils/Swap.java build/BSTTest/Utils
	@cp errorInfo/ErrorRec.java build/BSTTest/errorInfo
	@cp Binary/BSTTest.java  build/BSTTest/Binary
	cd build/BSTTest; \
	javac $(CFLAGS) Binary/BSTTest.java; \
	$(JAVA) $(RFLAGS) Binary/BSTTest


Sorting: Inssort Bubblesort Selsort

Sorttime: InssortTime BubblesortTime SelsortTime

Inssort: build/InssortTest/success

build/InssortTest/success: Sorting/Insertionsort.java Sorting/SortTest.1 Sorting/SortTest.2 Utils/Swap.java Utils/Processing.java Utils/Checkorder.java Utils/KVPair.java
	@echo "Insertionsort Test"
	-@rm -rf build/InssortTest
	@mkdir build/InssortTest
	cat Sorting/SortTest.1 Utils/Swap.java Utils/Processing.java Utils/Checkorder.java Sorting/Insertionsort.java Sorting/SortTest.2 > build/InssortTest/SortTest.java
	cp Utils/KVPair.java build/InssortTest
	cd build/InssortTest; \
	javac $(CFLAGS) SortTest.java; \
	$(JAVA) $(RFLAGS) SortTest

Bubblesort: build/BubblesortTest/success

build/BubblesortTest/success: Sorting/Bubblesort.java Sorting/SortTest.1 Sorting/SortTest.2 Utils/Swap.java Utils/Processing.java Utils/Checkorder.java Utils/KVPair.java
	@echo "Bubblesort Test"
	-@rm -rf build/BubblesortTest
	@mkdir build/BubblesortTest
	cat Sorting/SortTest.1 Utils/Swap.java Utils/Processing.java Utils/Checkorder.java Sorting/Bubblesort.java Sorting/SortTest.2 > build/BubblesortTest/SortTest.java
	cp Utils/KVPair.java build/BubblesortTest
	cd build/BubblesortTest; \
	javac $(CFLAGS) SortTest.java; \
	$(JAVA) $(RFLAGS) SortTest

Selsort: build/SelsortTest/success

build/SelsortTest/success: Sorting/Selectionsort.java Sorting/SortTest.1 Sorting/SortTest.2 Utils/Swap.java Utils/Processing.java Utils/Checkorder.java Utils/KVPair.java
	@echo "Selectionsort Test"
	-@rm -rf build/SelsortTest
	@mkdir build/SelsortTest
	cat Sorting/SortTest.1 Utils/Swap.java Utils/Processing.java Utils/Checkorder.java Sorting/Selectionsort.java Sorting/SortTest.2 > build/SelsortTest/SortTest.java
	cp Utils/KVPair.java build/SelsortTest
	cd build/SelsortTest; \
	javac $(CFLAGS) SortTest.java; \
	$(JAVA) $(RFLAGS) SortTest

InssortTime: Sorting/Insertionsort.java Sorting/SortTime.1 Sorting/SortTime.2 Utils/Swap.java Utils/Processing.java
	@echo "Inssort Timing Test"
	-@rm -rf build/InssortTime
	@mkdir build/InssortTime
	cat Sorting/SortTime.1 Utils/Swap.java Utils/Processing.java Sorting/Insertionsort.java Sorting/SortTime.2 > build/InssortTime/SortTime.java
	cd build/InssortTime; \
	javac $(CFLAGS) SortTime.java; \
	$(JAVA) $(RFLAGS) SortTime

BubblesortTime: Sorting/Bubblesort.java Sorting/SortTime.1 Sorting/SortTime.2 Utils/Swap.java Utils/Processing.java
	@echo "Bubble Sort Timing Test"
	-@rm -rf build/BubblesortTime
	@mkdir build/BubblesortTime
	cat Sorting/SortTime.1 Utils/Swap.java Utils/Processing.java Sorting/Bubblesort.java Sorting/SortTime.2 > build/BubblesortTime/SortTime.java
	cd build/BubblesortTime; \
	javac $(CFLAGS) SortTime.java; \
	$(JAVA) $(RFLAGS) SortTime

SelsortTime: Sorting/Selectionsort.java Sorting/SortTime.1 Sorting/SortTime.2 Utils/Swap.java Utils/Processing.java
	@echo "Selection Sort Timing Test"
	-@rm -rf build/SelsortTime
	@mkdir build/SelsortTime
	cat Sorting/SortTime.1 Utils/Swap.java Utils/Processing.java Sorting/Selectionsort.java Sorting/SortTime.2 > build/SelsortTime/SortTime.java
	cd build/SelsortTime; \
	javac $(CFLAGS) SortTime.java; \
	$(JAVA) $(RFLAGS) SortTime

Searching: Bsearch Sequential

Bsearch: build/Bsearch/success

build/Bsearch/success: Searching/Bsearch.java
	@echo "Binary Search Test"
	-@rm -rf build/Bsearch
	@mkdir build/Bsearch
	cp Searching/Bsearch.java build/Bsearch
	cd build/Bsearch; \
	javac $(CFLAGS) Bsearch.java; \
	$(JAVA) $(RFLAGS) Bsearch

Sequential: build/Sequential/success

build/Sequential/success: Searching/Sequential.java
	@echo "Binary Search Test"
	-@rm -rf build/Sequential
	@mkdir build/Sequential
	cp Searching/Sequential.java build/Sequential
	cd build/Sequential; \
	javac $(CFLAGS) Sequential.java; \
	$(JAVA) $(RFLAGS) Sequential
