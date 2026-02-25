package service;

import model.ChangeRequest;
import model.Item;
import model.ItemRevision;
import repository.InMemoryDatabase;

public class ChangeManagementService {

    public ChangeRequest raiseChangeRequest(Item item, InMemoryDatabase database) {

        if (item == null) {
            System.out.println("Item not found.");
            return null;
        }

        // Generate unique Change Request ID
        String changeId = "CR-" + System.currentTimeMillis();

        ChangeRequest cr = new ChangeRequest(changeId, "Manual Change Request", item);

        // Simulate auto approval (for simplicity)
        cr.approve();

        database.saveChangeRequest(cr);

        System.out.println("Change Request " + changeId + " created and approved.");

        return cr;
    }

    public void approveChangeRequest(ChangeRequest changeRequest) {
        changeRequest.approve();
    }

    public ItemRevision createNewRevisionFromChange(String changeId, InMemoryDatabase database) {

        ChangeRequest cr = database.getChangeRequest(changeId);

        if (cr == null) {
            System.out.println("Change Request not found.");
            return null;
        }

        if (!cr.isApproved()) {
            System.out.println("Change Request is not approved.");
            return null;
        }

        Item item = cr.getAffectedItem();

        ItemRevision latest = item.getLatestRevision();

        if (latest == null) {
            System.out.println("No existing revision found.");
            return null;
        }

        char nextRevChar = (char) (latest.getRevisionId().charAt(0) + 1);

        ItemRevision newRev = new ItemRevision(
                String.valueOf(nextRevChar),
                "Revision created from approved Change Request"
        );

        item.addRevision(newRev);

        System.out.println("New Revision " + newRev.getRevisionId() +
                " created from approved Change Request.");

        return newRev;
    }
}
