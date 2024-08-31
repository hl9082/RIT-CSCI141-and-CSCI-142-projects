package rit.stu;

import rit.cs.CircularList;

/**
 * An implementation of a cursor based circular list 
 * using the doubly linked node, DLNode.
 * 
 * @author Huy Le
 */

public class CircularListNode<E> implements CircularList<E> {
    // TODO - From problem solving.  Add the necessary private state to represent the head, cursor and size
    /**
     * The head of the circular list node.
     */
    private DLNode<E>head;
    
    /**
     * The cursor pointing to the current element of the circular list node.
     */
    
    private DLNode<E>cursor;
    
    /**
     * The number of elements of the circular list node. 
     *
     */
    
    private int size;
    
    /**
     * Initialize the list to be empty.  This means the head and cursor are both null
     * and the size is 0.
     */
    public CircularListNode() {
        this.head=null;
        this.cursor=null;
        this.size=0;
    }
    
    /**
     * Append a new element to the end of the list.
     * @param element - the new element to append
     */

    @Override
    public void append(E element) {
        DLNode<E>new_node=new DLNode<>(element);      
        if(size()==0){
        new_node.setNext(new_node);
        new_node.setPrev(new_node);
        this.head=new_node;
        this.cursor=head;
        this.size++;
        return;
        }
        //because it's a circular linked list,
        //the last is before the head
        DLNode<E>last=this.head.getPrev();
        last.setNext(new_node);
        new_node.setPrev(last);
        new_node.setNext(this.head);
        this.head.setPrev(new_node);
        this.size++;
    }
    
    /**
     * Returns the size of the list.
     * 
     * @return the number of elements in the list.
     */
    
    @Override
    public int size() {
        return this.size;
    }
    
    /**
     * Is the cursor pointing to a valid element in the list?
     * @return whether the cursor is valid or not.
     */
    
    @Override
    public boolean valid() {
        return this.cursor!=null;
    }
    
    /**
     * Reset the cursor to point to the head of the list 
     * (if one exists), 
     * otherwise set it to be off the list (invalid)
     */
    
    @Override
    public void reset() {
       this.cursor=(size()==0)?null:this.head;
    }
    
    /**
     * Move the cursor forward to the next element.
     */
    
    @Override
    public void forward() {
        assert this.cursor != null : 
        "can't forward cursor, the list is empty!";
        this.cursor=this.cursor.getNext();
    }
    
    /**
     * Move the cursor backward to the next element.
     */

    @Override
    public void backward() {
        assert this.cursor!=null : "can't backward cursor, the list is empty!";
        this.cursor=this.cursor.getPrev();
    }
    
    /**
     * Get the element at the cursor position.
     */

    @Override
    public E get() {
        assert this.cursor!=null: "can't get, cursor is off the list!";
        return this.cursor.getData();
    }
    
    /**
     * Remove the element at the cursor and then advance 
     * it forward to the next element. 
     * In the case the head is removed, 
     * the head moves forward to the next element, if possible.
     * 
     * @return the element at the cursor.
     */
    
    @Override
    public E removeForward() {
        assert this.cursor!=null : 
        "can't removeForward, cursor is off the list!";
        E res=this.cursor.getData();
        if(this.cursor==this.head){
        this.head=this.head.getNext();
        }
        if(size()==1){
        this.head=null;
        this.cursor=null;
        this.size--;
        return res;
        }
        this.cursor.getNext().setPrev(this.cursor.getPrev());
        this.cursor.getPrev().setNext(this.cursor.getNext());
        
        this.size--;
        forward();
        return res;
    }
    
    /**
     * Remove the element at the cursor and then advance it 
     * forward to the next element. In the case the head is removed, 
     * the head moves forward to the next element, if possible.
     * 
     * @return the element at the cursor.
     */

    @Override
    public E removeBackward() {
        assert this.cursor!=null : 
        "can't removeBackward, cursor is off the list!";
        E res=this.cursor.getData();
        if(this.cursor==this.head){
        this.head=this.head.getPrev();
        }
        if(size()==1){
        this.head=null;
        this.cursor=null;
        this.size--;
        return res;
        }
        this.cursor.getNext().setPrev(this.cursor.getPrev());
        this.cursor.getPrev().setNext(this.cursor.getNext());
        
        this.size--;
        backward();
        return res;
    }
    
    /**
     * Returns a string in the format:
     Player <-- CURSOR
     Player
     Player
     ...
 
    Where "<-- CURSOR" points to the player at the cursor.
    If the list is empty:
     Empty list!
 
    Very important! 
    Use System.lineSeparator() instead of "\n" when adding a new line!!!
     */

    @Override
    public String toString() {
        if(size()==0){
        return "Empty list!";
        }
        StringBuffer res=new StringBuffer(20*size());
        DLNode<E> ptr=this.head;
        do{
        res.append(ptr.getData());
        if(ptr==this.cursor){
        res.append(" <-- CURSOR");
        }
        res.append(System.lineSeparator());
        ptr=ptr.getNext();
        }while(ptr!=this.head);
        return res.toString();
    }
}
