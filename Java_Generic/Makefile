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

build/ListTest/success: genericLists/ListTest.java genericLists/List.java genericLists/AList.java genericLists/LList.java genericLists/Link.java genericLists/DList.java genericLists/DLink.java genericLists/Freelink.java genericLists/Freelist.java errorInfo/ErrorRec.java
	@echo "Array-based, Singly and Doubly Linked, and Free List Test"
	-@rm -rf build/ListTest
	@mkdir build/ListTest
	@mkdir build/ListTest/genericLists build/ListTest/errorInfo
	@cp genericLists/List.java build/ListTest/genericLists
	@cp genericLists/AList.java build/ListTest/genericLists
	@cp genericLists/LList.java build/ListTest/genericLists
	@cp genericLists/Link.java build/ListTest/genericLists
	@cp genericLists/DList.java build/ListTest/genericLists
	@cp genericLists/DLink.java build/ListTest/genericLists
	@cp genericLists/Freelist.java build/ListTest/genericLists
	@cp genericLists/Freelink.java build/ListTest/genericLists
	@cp errorInfo/ErrorRec.java build/ListTest/errorInfo
	@cp genericLists/ListTest.java build/ListTest/genericLists
	cd build/ListTest; \
	javac $(CFLAGS) genericLists/ListTest.java; \
	$(JAVA) $(RFLAGS) genericLists/ListTest

StackTest: build/StackTest/success

build/StackTest/success: genericLists/Stack.java genericLists/AStack.java genericLists/Link.java genericLists/LStack.java genericLists/StackTest.java errorInfo/ErrorRec.java
	@echo "Array-based and Linked Stack Test"
	-@rm -rf build/StackTest
	@mkdir build/StackTest
	@mkdir build/StackTest/genericLists build/StackTest/errorInfo
	@cp genericLists/Stack.java build/StackTest/genericLists
	@cp genericLists/AStack.java build/StackTest/genericLists
	@cp genericLists/LStack.java build/StackTest/genericLists
	@cp genericLists/Link.java build/StackTest/genericLists
	@cp errorInfo/ErrorRec.java build/StackTest/errorInfo
	@cp genericLists/StackTest.java build/StackTest/genericLists
	cd build/StackTest; \
	javac $(CFLAGS) genericLists/StackTest.java; \
	$(JAVA) $(RFLAGS) genericLists/StackTest

QueueTest: build/QueueTest/success

build/QueueTest/success: genericLists/Queue.java genericLists/AQueue.java genericLists/Link.java genericLists/LQueue.java genericLists/QueueTest.java errorInfo/ErrorRec.java
	@echo "Array-based and Linked Queue Test"
	-@rm -rf build/QueueTest
	@mkdir build/QueueTest
	@mkdir build/QueueTest/genericLists build/QueueTest/errorInfo
	@cp genericLists/Queue.java build/QueueTest/genericLists
	@cp genericLists/AQueue.java build/QueueTest/genericLists
	@cp genericLists/LQueue.java build/QueueTest/genericLists
	@cp genericLists/Link.java build/QueueTest/genericLists
	@cp errorInfo/ErrorRec.java build/QueueTest/errorInfo
	@cp genericLists/QueueTest.java build/QueueTest/genericLists
	cd build/QueueTest; \
	javac $(CFLAGS) genericLists/QueueTest.java; \
	$(JAVA) $(RFLAGS) genericLists/QueueTest

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

build/BSTTest/success: Binary/BST.java Binary/BSTTest1.java Binary/checkBST.java Binary/Preorder.java Binary/BSTTest.java Binary/BSTNode.java Binary/BinNode.java Utils/Permute.java Utils/Swap.java Binary/Traverse.java Utils/KVPair.java
	@echo "BST Test"
	-@rm -rf build/BSTTest
	@mkdir build/BSTTest
	@cp Binary/BST.java build/BSTTest
	@cp Binary/BSTNode.java build/BSTTest
	@cp Binary/BinNode.java build/BSTTest
	@cat Binary/BSTTest1.java Utils/Permute.java Utils/Swap.java Binary/checkBST.java Binary/Preorder.java Binary/BSTTest.java > build/BSTTest/BSTTest.java
	@cp Utils/KVPair.java build/BSTTest
	cd build/BSTTest; \
	javac $(CFLAGS) BSTTest.java; \
	$(JAVA) $(RFLAGS) BSTTest


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
