package DP.ED;

/**
* Implementation of the method for the List class.
*
* @version 2.0
* @author
* <b> Profesores DP </b><br>
* Program Development<br/>
* 16/17 Course
*/
public class List <tipoDato> {
	/** Reference to the first element in the list*/
	protected Node first;
	
	/** Reference to the last element in the list*/
	protected Node last;
	
	/** List size*/
	Integer size=0;
	
    public class Node {
    	/** Data stored in each node */
        private tipoDato Data;
    	/** Reference to the next node */
        private Node next;
    	/** Reference to the previous node */
        private Node prev;

        /**
    	 * Parametrized Constructor for the Node class
    	 */
        public Node(Node prev, tipoDato Data, Node next) {
            this.Data = Data;
            this.next = next;
            this.prev = prev;
        }
    	/**
    	 * Method that returns the next node in the list (for traversing the list)
    	 *
    	 * @return the next node in the list
    	 */
        public Node next() {
        		return next;
        }
    	/**
    	 * Method that returns the previous node in the list (for traversing the list)
    	 *
    	 * @return the previous node
    	 */
        public Node prev() {
        		return prev;
        }

    	/**
    	 * Method to obtain a data
    	 *
    	 * @return the data contained in the current node
    	 */        
        public tipoDato get() {
        		return Data;
        }
        
        public void setPrev(Node prev){
            this.prev=prev;
        }
        public void setNext(Node next){
            this.next=next;
        }
    }//class Node

		
	/**
	 * Default Constructor for the List class
	 */
	public List() {
		first = last = null;
		size = 0;
	}

	
	/**
	 * Parametrized method for the List class
	 *
	 * @param data the data that the List will store
	 */
	public List(tipoDato data) {
            try {
                addLast(data);
            } catch (OrderViolationException ex) {
                System.err.println("Esta parte del código no debería ser alcanzada jamás.");
            }
	}
	
	/**
	 * Method that returns the element that is stored at the beginning of the list
	 *
	 * @return the first element
	 */
	public tipoDato getFirst() {
		return first.Data;
	}

	/**
	 * Method that returns the data that is stored at the end of the list
	 *
	 * @return the last data
	 */
	public tipoDato getLast() {
		return last.Data;
	}
	/**
	 * Method that returns the first node 
	 *
	 * @return first node
	 */
	public Node first() {
		return first;
	}

	/**
	 * Method that returns the node at the end of the list
	 *
	 * @return last node
	 */
	public Node last() {
		return last;
	}
	/**
	 *  Method to check whether the list is empty
	 *
	 * @return true if the list is empty and otherwise false 
	 */
	public boolean estaVacia (){
		return (size == 0);
	}
	
	/**
	 * Method that returns the size of the list
	 *
	 * @return the size of the list
	 */
	public Integer size (){
		return size;
	}
	
	/**
	 * Method that returns the data contained in the position passed as parameter
	 * @param pos the position of the element to be returned 
	 * @return the data contained in the position passed as parameter
	 */
	public tipoDato get (Integer pos){
		tipoDato d = null; 
		Node iter=first;
		Integer i=0; 
		boolean encontrado = false;
		while(i<=pos && !encontrado && iter!= null) {
			if(i==pos) {
				encontrado = true;
				d=iter.Data;
			}
			i++;
			iter=iter.next;
		}
		return d;
	}
	
	public void delete (Integer pos){
		Node iter=first;
		Integer i=0;
		boolean encontrado=false;
		if(size!=0){
		while(i<=pos && !encontrado && iter!=null){
			if(i==pos){
				encontrado=true;
				if(iter.prev!=null)
					(iter.prev).next=iter.next;
				if(iter.next!=null)
					(iter.next).prev=iter.prev;
				if(first==iter)
					first=iter.next;
				if(last==iter)
					last=last.prev;
				if(last==null)
					first=null;
				size--;
			}
			i++;
			iter=iter.next;
		}
		}
	}
	
	
	/**
	 * Method to add a data by the end of the list
	 *
	 * @param Data value that is going to be added to the list
         * @throws DP.ED.OrderViolationException
	 */
	public void addLast(tipoDato Data) throws OrderViolationException {
        Node l = last;
        Node nodo = new Node(l, Data, null);
        last = nodo;
        if (l == null)
            first = nodo;
        else
            l.next = nodo;
        size++;
	}
	
	/**
	 * It removes the last data in the list
	 *
	 */
	public void removeLast() {
		if (last != null){
			last = last.prev();
		}	
		//there are not elements
		if (last == null) 	first = null;
		else last.next=null;
		size --;
	}
}



