package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    // Test the Event class
    Event event1;
    Event event2;

    @BeforeEach
    public void setUp() {
        event1 = new Event("event1");
        event2 = new Event("event2");
    }

    @Test
    public void testEquals() {
        assertTrue(event1.equals(event1));
        assertFalse(event1.equals(event2));
        assertFalse(event1.equals(null));
        assertFalse(event1.equals(new Date()));
    }

    @Test
    public void testHashCode() {
        assertTrue(event1.hashCode() == event1.hashCode());
        assertFalse(event1.hashCode() == event2.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(event1.getDate().toString() + "\n" + event1.getDescription(), event1.toString());
    }

    @Test
    public void testGetDate() {
        assertTrue(event1.getDate().equals(new Date()));
    }

    @Test
    public void testGetDescription() {
        assertTrue(event1.getDescription().equals("event1"));
    }

}
