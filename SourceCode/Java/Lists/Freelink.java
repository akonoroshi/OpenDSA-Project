package lists;
/* *** ODSATag: Freelink *** */
class Freelink {         // Singly linked list node with freelist support
  private Object e;  // Value for this node
  private Freelink n;    // Point to next node in list

  // Constructors
  Freelink(Object it, Freelink inn) { e = it; n = inn; }
  Freelink(Freelink inn) { e = null; n = inn; }

  Object element() { return e; }           // Return the value
  Object setElement(Object it) { return e = it; } // Set element value
  Freelink next() { return n; }                // Return next link
  Freelink setNext(Freelink inn) { return n = inn; }      // Set next link

  // Extensions to support freelists
  static Freelink freelist = null;     // Freelist for the class

  // Return a new link, from freelist if possible
  static Freelink get(Object it, Freelink inn) {
    if (freelist == null)
      return new Freelink(it, inn);        // Get from "new"
    Freelink temp = freelist;              // Get from freelist
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
