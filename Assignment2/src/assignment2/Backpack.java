package assignment2;

public class Backpack//linked list
    {
        double maxWeight;
        double currWeight;
        int numItems;
        int maxItems;
        private LinkedListNode head;
        
        public Backpack(){
            maxWeight = 90;
            currWeight = 0;
            maxItems = 30;
            numItems= 0;
            head = null;
        }
        
//add front
        public boolean addFront(Weapon item){
            if(currWeight + item.weight <= maxWeight && numItems <= maxItems){                
                currWeight += item.weight;        
                LinkedListNode LNode = new LinkedListNode(item);
                if(head == null){
                    head = LNode;
                    return true;
                }
                LNode.next = head;
                head = LNode;
                return true;
            }
            else{
                return false;
            }
        }
//add last
        public boolean addLast(Weapon item){
            if(currWeight + item.weight <= maxWeight && numItems <= maxItems){
                currWeight += item.weight;
                LinkedListNode LNode = new LinkedListNode(item);
                if(head == null){
                    head = LNode;
                    return true;
                }
                LinkedListNode current = head;
                while(current.next != null){
                    current = current.next;
                }
                current.next = LNode;
                return true;
            }
            else{
                return false;
            }
        }
//remove weapon
        public Weapon removeFront(Weapon item){
            if(head != null){
                LinkedListNode current = head;
                head = head.next;
                return current.item;
            }
            return null;
        }
        
        public void printList(){
            LinkedListNode current = head;
            while(current != null){
                System.out.println(current.item.weaponName);
                current = current.next;
            }
        }
    }

