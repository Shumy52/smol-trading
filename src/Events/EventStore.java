package Events;


import java.io.*;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/**
 * The notebook responsible for keeping all the events in one place.
 * This is your bank statement, telling ya what you spent your crap on.
 */
public class EventStore {
    private final List<Event> events = new ArrayList<>();
    private long nextSequence = 1;
    
    public void append(Event event) {
        event.setSequenceNumber(nextSequence++);
        events.add(event);

        // I've thought about the risk of losing the data on unexpected shutdown
        // Maybe I'll change into direct-to-disk after I get this one working
    }
    
    public List<Event> getAllEvents() {
        return Collections.unmodifiableList(events);
    }
    
    public List<Event> getEventsAfter(long sequenceNumber) {
        return events.stream()
                .filter(e -> e.getSequenceNumber() > sequenceNumber)
                .collect(Collectors.toList());

        // Useful for restoring after snapshot
    }
    
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }
    
    public static EventStore loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (EventStore) in.readObject();
        }
    }
    
    public long getLastSequenceNumber() {
        return nextSequence - 1;
    }
}