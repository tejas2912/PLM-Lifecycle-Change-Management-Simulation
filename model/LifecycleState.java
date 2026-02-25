package model;

/**
 * Represents the lifecycle states of an Item Revision in a PLM system.
 * This enum defines the valid states that a revision can transition through
 * during its lifecycle, following typical PLM workflow patterns.
 */
public enum LifecycleState {
    /**
     * Initial state when a revision is being created and modified.
     * In this state, the revision can be edited freely.
     */
    WORKING,
    
    /**
     * Revision has been submitted for review and approval.
     * In this state, the revision cannot be modified.
     */
    IN_REVIEW,
    
    /**
     * Revision has been approved and released for production use.
     * Released revisions cannot be modified and become read-only.
     */
    RELEASED,
    
    /**
     * Revision is no longer in use, typically replaced by a newer version.
     * Obsolete revisions cannot be used in new designs or BOMs.
     */
    OBSOLETE
}
