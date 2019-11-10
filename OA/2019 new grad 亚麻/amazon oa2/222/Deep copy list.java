138. Copy List with Random Pointer


A linked list is given such that 
    each node contains an Additional Random Pointer which could point to any node in the list or null.

Return a deep copy of the list.

-----------  

hint 1
Just iterate the linked list and create copies of the nodes on the go. 
    Since a node can be referenced from multiple nodes due to the random pointers, 
    make sure you are Not making multiple copies of the same node.

hint 2
You may want to use Extra-Space to keep 
    old node ---> new node mapping to prevent creating multiples copies of same node.

hint 3
We can Avoid using extra space for old node ---> new node mapping, 
    by Tweaking调整 the original linked list. Simply interweave交织 the nodes of the old and copied list.
    
    Old List: A --> B --> C --> D
    InterWeaved List: A --> A‘ --> B --> B’ --> C --> C‘ --> D --> D’

hint 4
The interweaving is done using next pointers and we can make use of interweaved structure to get the correct reference nodes for random pointers.








class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        //copy all the nodes
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        //copy the next and random pointer
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}




