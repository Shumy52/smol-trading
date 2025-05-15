package Events;

import java.io.Serializable;

public abstract class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private long sequenceNumber;
    
    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public long getSequenceNumber() {
        return sequenceNumber;
    }
}