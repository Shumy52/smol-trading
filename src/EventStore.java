import Events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * The notebook responsible for keeping all the events in one place.
 * This is your bank statement, telling ya what you spent your crap on.
 */
class EventStore {
    private final List<Event> events = new ArrayList<>();

    public void append(Event event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
