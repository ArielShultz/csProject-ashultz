/**
 * Implementation of the LinkedListInterface
 * For all public methods, see documentation in LinkedListInterface
 * @param <J>
 */

@SuppressWarnings("unchecked")
public class SingleLinkedList<J extends Comparable <J>> implements LinkedListInterface<J> {
    protected class Node<H> {
        public H data; // info in node
        public Node<H> next; // Next node
        public Node(H data)  {
            this.data = data;
            this.next = null;
        }  
    }  
    protected Node<J> head = null;
   
    public int size() {
        int siz = 0;
        Node<J> n = head;
        while (n != null) {
            siz++;
            n = n.next;
        }
        return siz;
    }
 
    public boolean isEmpty() {
        return head == null;
    }
 
    @Override
    public J first() {
        return head.data;
    }
 
    private Node<J> lastNode() {
        Node<J> n = head;
        if (n == null)
            return null;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }
 
    @Override
    public J last() {
        Node<J> n = lastNode();
        if (n == null)
            return null;
        return n.data;
    }
   
    @Override
    public void addLast(J c) {
        Node<J> n = lastNode();
        Node<J> newnode = new Node<>(c);
        if (n == null) {
            head = newnode;
            return;
        }
        n.next = newnode;
    }
 
    @Override
    public void addFirst(J c) {
        Node<J> newnode = new Node<>(c);
        if (head == null) {
            head = newnode;
            return;
        }
        newnode.next = head;
        head = newnode;
    }
 
    @Override
    public J removeFirst() {
        if (head == null)
            return null;
        Node<J> tmp = head;
        head = head.next;
        return tmp.data;
    }
 
    @Override
    public J removeLast() {
        if (head == null)
            return null;
        Node<J> prev = head;
        Node<J> here = head.next;
        if (here == null) {
            // only one item in list
            head = null;
            return prev.data;
        }
        while (here.next != null) {
            prev = here;
            here = here.next;
        }
        prev.next = null;
        return here.data;
    }
 
    @Override
    public boolean remove(J r) {
        if (head == null)
            return false;
        Node<J> prev = null;
        Node<J> here = head;
        while (here.next != null) {
            if (here == r)
                break;
            prev = here;
            here = here.next;
        }
        if (here.data == r) {
            if (prev == null) {
                head = head.next;
            }
            else {
                prev.next = here.next;
            }
            return true;
        }
        return false;
    }
 
    /**
     * Determine if the list contains the provided object.
     * @param babyName the object to be looked for.
     * @return true if the object is in the linked list.
    */
    public boolean contains (J babyName) {
        Node<J> node = head;
        for (int i = 0; i < size(); i++) {
            if (node == null) {
                return false;
            }
            else if (node.data.compareTo(babyName) == 0) {
                return true;
            }
            else {
                node = node.next;
            }
        }
        return false;
    }  
 
    public J find (J babyName) {
        Node<J> node = head;
        for (int i = 0; i < size(); i++) {
            if (node == null) {
                return null;
            }
            else if (node.data.compareTo(babyName) == 0) {
                return node.data;
            }
            else {
                node=node.next;
            }
        }
        return null;
    }  
 
    public int findPosition (J babyName) {
        Node<J> node = head;
        for (int i = 0; i < size(); i++) {
            if (node == null) {
                return -1;
            }
            else if (node.data.compareTo(babyName) == 0) {
                return i++;
            }
            else {
                node=node.next;
            }
        }
        return -1;
    } 
 
    /**
    * Given a linked list whose items are in sorted order,
    * add the new item, r, so that the items in the linked list are
    * still in sorted order after the addition.  
    * @param: r  the item to be added
    * @return true if the addition succeeded (it should always succeed).
    **/
    boolean addSorted(J r) {
        Node<J> newNode = new Node(r);// What I am adding
        Node<J> current = head; // The one that I am at
 
        if (head == null) {
            // insert at front of list
            addFirst(r);
            return true;
        }
        if (head.data.compareTo(newNode.data) > 0) {
            addFirst(r); // Add at the end
            return true;
        }
        while (current.next != null) {
            if (current.data.compareTo(r) == 0) { // Already in list
                return false;
            }
            if (current.data.compareTo(r) < 0 && current.next.data.compareTo(r) > 0) { // Insert in middle
                newNode.next = current.next; // Place after current
                current.next = newNode; // Setting equal to place in middle
                return true;
            }
            current = current.next; // Going to next node
        }
        addLast(r);
        return true;
 
    } 
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Node<J> node = head; node != null; node = node.next) {
            sb.append(node.data.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
