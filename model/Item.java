package model;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private String itemId;                 // Unique identity
    private String name;                   // Item name
    private String type;                   // Item type (Mechanical, Electrical etc.)
    private List<ItemRevision> revisions;  // All revisions of this item

    public Item(String itemId, String name, String type) {
        this.itemId = itemId;
        this.name = name;
        this.type = type;
        this.revisions = new ArrayList<>();
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<ItemRevision> getRevisions() {
        return revisions;
    }

    public void addRevision(ItemRevision revision) {
        this.revisions.add(revision);
    }

    public ItemRevision getLatestRevision() {
        if (revisions.isEmpty()) {
            return null;
        }
        return revisions.get(revisions.size() - 1);
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId +
                " | Name: " + name +
                " | Type: " + type;
    }
}
