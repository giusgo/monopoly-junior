package monopoly;

public class CircularLinkedList {

    Node head = null;
    Node tail = null;

    public class Node {
            
        Property property;
        int pos_x;
        int pos_y;
        Node next;
        Node prev;

        public Node(int pos_x, int pos_y, Property property) {

            this.pos_x = pos_x;
            this.pos_y = pos_y;
            this.property = property;

            this.next = this;
            this.prev = this;
        }
    }

    public void addNode(int pos_x, int pos_y, Property property) {

        Node node = new Node(pos_x, pos_y, property);

        if (head == null) {

            this.head = node;
            this.tail = node;

            return;

        } else {

            Node last = this.head;

            while (last.next != this.head) {

                last = last.next;
            }

            last.next = node; 
            node.prev = last; 
            this.tail = node;

            this.tail.next = this.head;
            this.head.prev = this.tail;
        }
    }
}
