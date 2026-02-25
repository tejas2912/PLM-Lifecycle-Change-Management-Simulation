package service;

import model.ItemRevision;
import model.LifecycleState;

public class WorkflowService {

    public void submitForReview(ItemRevision revision) {

        if (revision.getState() != LifecycleState.WORKING) {
            System.out.println("Only WORKING revision can be submitted for review.");
            return;
        }

        revision.setState(LifecycleState.IN_REVIEW);
        System.out.println("Revision moved to IN_REVIEW.");
    }

    public void approveRevision(ItemRevision revision) {

        if (revision.getState() != LifecycleState.IN_REVIEW) {
            System.out.println("Revision must be in IN_REVIEW before approval.");
            return;
        }

        revision.setState(LifecycleState.RELEASED);
        System.out.println("Revision RELEASED.");
    }

    public void markObsolete(ItemRevision revision) {
        if (revision.getState() != LifecycleState.RELEASED) {
            throw new IllegalStateException("Only RELEASED revisions can be marked obsolete.");
        }
        revision.setState(LifecycleState.OBSOLETE);
    }
}
