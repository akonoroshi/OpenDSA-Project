package lists;
/* *** ODSATag: Freelink *** */
class FLink {         // Singly linked list node with freelist support
  private Object e;  // Value for this node
  private FLink n;    // Point to next node in list

  // Constructors
  FLink(Object it, FLink inn) { e = it; n = inn; }
  FLink(FLink inn) { e = null; n = inn; }

  Object element() { return e; }           // Return the value
  Object setElement(Object it) { return e = it; } // Set element value
  FLink next() { return n; }                // Return next link
  FLink setNext(FLink inn) { return n = inn; }      // Set next link

  // Extensions to support freelists
  static FLink freelist = null;     // Freelist for the class

  // Return a new link, from freelist if possible
  static FLink get(Object it, FLink inn) {
    if (freelist == null)
      return new FLink(it, inn);        // Get from "new"
    FLink temp = freelist;              // Get from freelist
    freelist = freelist.next();
    temp.setElement(it);
    temp.setNext(inn);
    return temp;
  }

  // Return a link node to the freelist
  void release() {
    e = null;   // Drop reference to the element
    n = freelist;
    freelist = this;
  }
}
/* *** ODSAendTag: Freelink *** */
