public class List {
  public int length;
  public ListNode head;

  // insertNewFirstNode: Exercise 1
  public void insertNewFirstNode (String airport)
  {
    head = new ListNode (airport, head);
    length++;
  }

  // insertNewSecondNode: add a new node to the list, putting it
  // in the second position
  public void insertNewSecondNode (String airport)
  {
    if (length == 0) return;

    head.next = new ListNode (airport, head.next);
    length++;
  }

  // deleteFirst: Exercise 2
  public void deleteFirst ()
  {
    head = head.next;
    length--;
  }

  // insertBefore: insert newNode into the list to which node belongs,
  // so that new appears in the list before node
  public void insertBefore (ListNode node, ListNode newNode)
  {
    insertAfter (node, newNode);
    String temp = node.airport;
    node.airport = newNode.airport;
    newNode.airport = temp;
  }

  public boolean inList (ListNode goal)
  {
    for (ListNode node = head; node != null; node = node.next) {
      // in this case, shallow equality is the right test
      if (node == goal) return true;
    }
    return false;
  }

  // insertAfter: insert newNode into the list to which node belongs,
  // so that newNode appears in the list after node
  public void insertAfter (ListNode node, ListNode newNode)
  {
    if (!inList (node)) {
      System.out.println ("Error: precondition violated in insertAfter");
      return;
    }
    newNode.next = node.next;
    node.next = newNode;
    length++;
  }

  // insertNewLastNode
  public void insertNewLastNode (String airport)
  {
    ListNode newNode = new ListNode (airport, null);

    if (head == null) {
      head = newNode;
    } else {
      ListNode node = head;
      while (node.next != null) {
	node = node.next;
      }
      node.next = newNode;
    }
    length++;
  }

  // cloneList: Exercise 4
  public List cloneList ()
  {
    List newList = new List ();

    for (ListNode node = head; node != null; node = node.next)
    {
      newList.insertNewLastNode (node.airport);
    }
    return newList;
  }

  // reverseList: helper function for Exercise 5
  public List reverseList ()
  {
    List newList = new List ();

    for (ListNode node = head; node != null; node = node.next)
    {
      newList.insertNewFirstNode (node.airport);
    }
    return newList;
  }

  // reverse: Exercise 5
  public void reverse ()
  {
    List newList = this.reverseList ();
    head = newList.head;
  }

  // concat: Exercise 7
  public void concat (List two)
  {
    for (ListNode node = two.head; node != null; node = node.next)
    {
      insertNewLastNode (node.airport);
    }
  }

  // print: print the list
  public void print () {
    ListNode node;

    // remember that things that get printed get buffered until
    // we print a newline or use println
    System.out.print ("(");

    // start at the beginning of the list
    node = head;

    // traverse the list, printing each element
    while (node != null) {
      System.out.print (node.airport);
      node = node.next;
      if (node != null) {
	System.out.print (", ");
      }
    }

    System.out.println (")");
  }

  public void printBackwards ()
  {
    System.out.print ("(");

    if (head.next != null) {
      head.next.printNodeBackwards ();
    }
    
    System.out.println (head.airport + ")");
  }

  // WARNING: the following two methods are WRONG WRONG WRONG!
  public ListNode findNode (String airport)
  {
    ListNode node = head;
    while (!airport.equals (node.airport) && node != null) {
      node = node.next;
    }
    return node;
  }

  public ListNode lastNode ()
  {
    ListNode node = head;
    if (node != null) {
      do {
	node = node.next;
      } while (node.next != null);
    }
    return node;
  }    

  public static void main (String[] args) {

    // Now that we have methods for adding nodes to a list,
    // we can create lists much more concisely and safely
    // (compare this code with last week's)
    List list = new List ();
    list.insertNewLastNode ("PWM");
    list.insertNewLastNode ("ORD");
    list.insertNewLastNode ("OAK");
    list.print ();

    // in general it's not a good idea to have pointers into
    // the middle of a list, but I'm going to use node2 to
    // test the insert... methods
    ListNode node2 = list.head.next;

    list.insertNewFirstNode ("BOS");
    list.print ();

    list.deleteFirst ();
    list.print ();

    ListNode frisco = new ListNode ("SFO", null);
    list.insertAfter (node2, frisco);
    list.print ();

    ListNode austin = new ListNode ("AUS", null);
    list.insertBefore (node2, austin);
    list.print ();

    List copy = list.cloneList ();
    copy.print ();
    copy.reverse ();
    copy.print ();

    // calling findNode on an empty list causes a NullPointerException!
    List empty = new List ();
    //empty.findNode ("BOS");

    // calling lastNode on a singleton causes a NullPointerException!
    List singleton = new List ();
    singleton.insertNewFirstNode ("BOS");
    //ListNode last = singleton.lastNode ();

    list.concat (copy);
    list.print ();
    copy.print ();

    copy.printBackwards();

    // node2 is not in copy, so this should cause an error
    ListNode disney = new ListNode ("ORL", null);
    //copy.insertAfter (node2, disney);

    list.head = ListNode.concat (list.head, copy.head);
    list.print ();
  }
}
