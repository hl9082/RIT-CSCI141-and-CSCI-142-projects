package rit.stu;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * JUnit 5.8 tester for a generic DLNode.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDLNode {
    /**
     * standard oautput capturer
     */
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void testDLNodeInteger() {
        // 10
        DLNode<Integer> iNode10 = new rit.stu.DLNode<>(10);
        assertEquals(10, iNode10.getData());
        assertNull(iNode10.getPrev());
        assertNull(iNode10.getNext());
        String expected = "DLNode{data='10', prev=null, next=null}";
        assertEquals(expected, iNode10.toString().trim());
        outputStreamCaptor.reset();
        iNode10.setData(99);
        assertEquals(99, iNode10.getData());
        iNode10.setData(10);

        // 10 <- 20
        rit.stu.DLNode<Integer> iNode20 = new rit.stu.DLNode<>(20, iNode10, null);
        assertEquals(iNode10, iNode20.getPrev());
        assertNull(iNode20.getNext());
        expected = "DLNode{data='20', prev=10, next=null}";
        assertEquals(expected, iNode20.toString().trim());
        outputStreamCaptor.reset();

        //  10 <- 20 <- 30 ->...
        rit.stu.DLNode<Integer> iNode30 = new DLNode<>(30, iNode20, iNode10);
        assertEquals(30, iNode30.getData());
        assertEquals(iNode20, iNode30.getPrev());
        assertEquals(iNode10, iNode30.getNext());
        expected = "DLNode{data='30', prev=20, next=10}";
        assertEquals(expected, iNode30.toString().trim());
        outputStreamCaptor.reset();

        // ...<-> 10 <-> 20 <- 30 <->...
        iNode10.setNext(iNode20);
        expected = "DLNode{data='10', prev=null, next=20}";
        assertEquals(expected, iNode10.toString().trim());
        outputStreamCaptor.reset();

        // ...<-> 10 <-> 20 <- 30 <->...
        iNode20.setPrev(iNode10);
        expected = "DLNode{data='20', prev=10, next=null}";
        assertEquals(expected, iNode20.toString().trim());
        outputStreamCaptor.reset();

        // ...<-> 10 <-> 20 <-> 30 <->...
        iNode20.setNext(iNode30);
        expected = "DLNode{data='20', prev=10, next=30}";
        assertEquals(expected, iNode20.toString().trim());
        outputStreamCaptor.reset();
    }
}
