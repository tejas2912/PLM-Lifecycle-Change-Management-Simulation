package model;

public class ChangeRequest {

    private String changeId;
    private String reason;
    private boolean approved;
    private Item affectedItem;

    public ChangeRequest(String changeId, String reason, Item affectedItem) {
        this.changeId = changeId;
        this.reason = reason;
        this.affectedItem = affectedItem;
        this.approved = false;
    }

    public String getChangeId() {
        return changeId;
    }

    public String getReason() {
        return reason;
    }

    public boolean isApproved() {
        return approved;
    }

    public void approve() {
        this.approved = true;
    }

    public Item getAffectedItem() {
        return affectedItem;
    }

    @Override
    public String toString() {
        return "ChangeRequest ID: " + changeId +
                " | Reason: " + reason +
                " | Approved: " + approved;
    }
}
