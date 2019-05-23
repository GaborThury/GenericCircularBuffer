import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircularBufferTest {

    private CircularBufferImpl<Integer> circularBuffer;

    @BeforeEach
    void beforeEach() {
        circularBuffer = new CircularBufferImpl<>(3);
    }

    @Test
    void testPutIfBufferIsFull() {
        circularBuffer.put(1);
        circularBuffer.put(1);
        circularBuffer.put(1);
        assertThrows(RuntimeException.class, () -> circularBuffer.put(1)) ;
    }

    @Test
    void testGetToReturnLastElement() {
        circularBuffer.put(32);
        circularBuffer.put(2);
        circularBuffer.put(1);
        assertEquals(32, circularBuffer.get());
    }

    @Test
    void testGetIfBufferIsEmpty() {
        assertThrows(RuntimeException.class, () -> circularBuffer.get());
    }

    @Test
    void testToObjectArrayLength() {
        circularBuffer.put(1);
        circularBuffer.put(2);
        assertEquals(2, circularBuffer.toObjectArray().length);
    }

    @Test
    void testToArray() {
        circularBuffer.put(0);
        circularBuffer.put(-3);
        circularBuffer.put(5);

        Integer[] returnArray = circularBuffer.toArray();

        assertEquals(0, returnArray[0]);
        assertEquals(-3, returnArray[1]);
        assertEquals(5, returnArray[2]);
    }

    @Test
    void testAsList() {
        circularBuffer.put(0);
        circularBuffer.put(-3);
        circularBuffer.put(5);

        List<Integer> returnList = circularBuffer.asList();

        assertEquals(0, returnList.get(0));
        assertEquals(-3, returnList.get(1));
        assertEquals(5, returnList.get(2));
    }

    @Test
    void testAddAll() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        circularBuffer.addAll(integers);

        assertEquals(1, circularBuffer.get());
        assertEquals(2, circularBuffer.get());
        assertEquals(3, circularBuffer.get());
    }

    @Test
    void testIsEmpty() {
        assertTrue(circularBuffer.isEmpty());
        circularBuffer.put(1);
        assertFalse(circularBuffer.isEmpty());
    }

    @Test
    void testSort() {
        Comparator<Integer> comparator = Integer::compareTo;
        circularBuffer.put(3);
        circularBuffer.put(5);
        circularBuffer.put(1);
        circularBuffer.sort(comparator);
    }

    @AfterEach
    void afterEach() {
        circularBuffer = null;
    }
}
