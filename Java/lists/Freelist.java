package lists;
// Linked list implementation that uses a Freelist
class FList implements List {
  private FLink head;         // Pointer to list header
  private FLink tail;         // Pointer to last element
  private FLink curr;         // Access to current element
  private int listSize;      // Size of list

  // Constructors
  FList(int size) { this(); }   // Constructor -- Ignore size
  FList() { clear(); }

  // Remove all elements
  public void clear() {
    while (head != null) {
      FLink temp = head.next();
      head.release();
      head = temp;
    }
    curr = tail = FLink.get(null, null); // Create trailer
    head = FLink.get(null, tail);        // Create header
    listSize = 0;
  }
  
/* *** ODSATag: Freelist *** */
  // Insert "it" at current position
  public boolean insert(Object it) {
    curr.setNext(FLink.get(curr.element(), curr.next())); // Get link
    curr.setElement(it);
    if (tail == curr) tail = curr.next();    // New tail
    listSize++;
    return true;
  }

  // Append "it" to list
  public boolean append(Object it) {
    tail.setNext(FLink.get(null, null));
    tail.setElement(it);
    tail = tail.next();
    listSize++;
    return true;
  }

  // Remove and return current element
  public Object remove () {
    if (curr == tail) return null;          // Nothing to remove
    Object it = curr.element();             // Remember value
    curr.setElement(curr.next().element()); // Pull forward the next element
    if (curr.next() == tail) tail = curr;   // Removed last, move tail
    FLink tempptr = curr.next();             // Remember the link
    curr.setNext(curr.next().next());       // Point around unneeded link
    tempptr.release();                      // Release the link
    listSize--;                             // Decrement element count
    return it;                              // Return value
  }
  /* *** ODSAendTag: Freelist *** */

  public void moveToStart() { curr = head.next(); } // Set curr at list start
  public void moveToEnd() { curr = tail; }          // Set curr at list end

  // Move curr one step left; no change if now at front
  public void prev() {
    if (head.next() == curr) return;         // No previous element
    FLink temp = head;
    // March down list until we find the previous element
    while (temp.next() != curr) temp = temp.next();
    curr = temp;
  }

  // Move curr one step right; no change if now at end
  public void next() { if (curr != tail) curr = curr.next(); }

  public int length() { return listSize; }          // Return list length


  // Return the position of the current element
  public int currPos() {
    FLink temp = head.next();
    int i;
    for (i=0; curr != temp; i++)
      temp = temp.next();
    return i;
  }
  
  // Move down list to "pos" position
  public boolean moveToPos(int pos) {
    if ((pos < 0) || (pos > listSize)) return false;
    curr = head.next();
    for(int i=0; i<pos; i++) curr = curr.next();
    return true;
  }

  // Return true if current position is at end of the list
  public boolean isAtEnd() { return curr == tail; }

  // Return current element value
  public Object getValue() {
    if(curr == tail) return null;
    return curr.element();
  }

  public boolean isEmpty() {
	return listSize == 0;
}
}
