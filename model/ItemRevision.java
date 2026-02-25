package model;

public class ItemRevision {

    private String revisionId;
    private String description;
    private LifecycleState state;

    public ItemRevision(String revisionId, String description) {
        this.revisionId = revisionId;
        this.description = description;
        this.state = LifecycleState.WORKING;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (state == LifecycleState.RELEASED) {
            throw new IllegalStateException("Cannot modify a RELEASED revision.");
        }
        this.description = description;
    }

    public LifecycleState getState() {
        return state;
    }

    public void setState(LifecycleState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Revision " + revisionId +
                " | State: " + state +
                " | Description: " + description;
    }
}
