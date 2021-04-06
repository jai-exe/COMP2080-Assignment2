package assignment2;

public class LinkedListNode {


    public Node head;
    public int numItems;


    public LinkedListNode() {
        head = null;
    }


    public void addFront(Weapon data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        numItems++;
    }


    public void addLast(Weapon data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            numItems++;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        numItems++;
        curr.next = newNode;
    }
}


