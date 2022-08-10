package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventLogTest {
    Event event1;
    Event event2;

    @BeforeEach
    public void setUp() {
        event1 = new Event("event1");
        event2 = new Event("event2");
    }

    @Test
    public void testGetInstance() {
        assertTrue(EventLog.getInstance() instanceof EventLog);
    }

    @Test
    public void testLogEvent() {
        EventLog.getInstance().logEvent(event1);
        assertTrue(EventLog.getInstance().iterator().next().equals(event1));
    }

    @Test
    public void testClear() {
        EventLog.getInstance().logEvent(event1);
        EventLog.getInstance().logEvent(event2);
        EventLog.getInstance().clear();
        assertTrue(EventLog.getInstance().iterator()
                .next().equals(new Event("Event log cleared.")));
    }
}
