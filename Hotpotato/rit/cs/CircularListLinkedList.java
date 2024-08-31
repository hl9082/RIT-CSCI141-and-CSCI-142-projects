package rit.cs;

import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of a cursor based circular list using the built in
 * JCF LinkedList.
 *
 * @param <E> the data type of the elements in the list
 * @author RIT CS
 */
public class CircularListLinkedList<E> implements CircularList<E> {
    /** to indicate the cursor is off the list and invalid */
    private final static int OFF = -1;
    /** the head of the list is always at index 0 */
    private final static int HEAD = 0;
    /** the actual collection of elements */
    private List<E> list;
    /** the cursor pointer */
    private int cursor;

    /**
     * Create a new list that is empty with the cursor off the list.
     */
    public CircularListLinkedList() {
        this.list = new LinkedList<>();
        this.cursor = OFF;
    }

    @Override
    public void append(E element) {
        if (this.list.isEmpty()) {
            this.cursor = HEAD;
        }
        this.list.add(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean valid() {
        return this.cursor != OFF;
    }

    @Override
    public void reset() {
        if (this.list.isEmpty()) {
            this.cursor = OFF;
        } else {
            this.cursor = HEAD;
        }
    }

    @Override
    public void forward() {
        assert this.cursor != OFF : "can't forward cursor, the list is empty!";
        this.cursor = (this.cursor + 1) % size();
    }

    @Override
    public void backward() {
        assert this.cursor != OFF : "can't backward cursor, the list is empty!";
        if (this.cursor == 0) {
            this.cursor = size()-1;
        } else {
            --this.cursor;
        }
    }

    @Override
    public E get() {
        assert this.cursor != OFF : "can't get, cursor is off the list!";
        return this.list.get(this.cursor);
    }

    @Override
    public E removeForward() {
        assert this.cursor != OFF : "can't removeForward, cursor is off the list!";
        E element = this.list.remove(this.cursor);
        // after removing, cursor stays at same index unless it was the last element
        // (set to beginning)
        if (this.cursor == list.size()) {
            this.cursor = HEAD;
        }
        // if removed last element set cursor off
        if (size() == 0) {
            this.cursor = OFF;
        }
        return element;
    }

    @Override
    public E removeBackward() {
        assert this.cursor != OFF : "can't removeBackward, cursor is off the list!";
        E element = this.list.remove(this.cursor);
        // after removing, if cursor is > 0 move it back one
        if (this.cursor > 0) {
            --this.cursor;
        } else {  // cursor was 0 (beginning), move cursor to end
            this.cursor = this.list.size() - 1;
        }
        // if removed last element set cursor off
        if (size() == 0) {
            this.cursor = OFF;
        }
        return element;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.list.isEmpty()) {
            result = "Empty list!";
        } else {
            for (int i = 0; i < this.list.size(); ++i) {
                result += this.list.get(i);
                if (i == cursor) {
                    result += " <-- CURSOR";
                }
                if (i != this.list.size() - 1) {
                    result += System.lineSeparator();
                }
            }
        }
        return result;
    }
}
