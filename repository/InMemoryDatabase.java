package repository;

import model.Item;
import model.ChangeRequest;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase {

    private Map<String, Item> items;
    private Map<String, ChangeRequest> changeRequests;

    public InMemoryDatabase() {
        this.items = new HashMap<>();
        this.changeRequests = new HashMap<>();
    }

    public void saveItem(Item item) {
        items.put(item.getItemId(), item);
    }

    public Item getItem(String itemId) {
        return items.get(itemId);
    }

    public void saveChangeRequest(ChangeRequest changeRequest) {
        changeRequests.put(changeRequest.getChangeId(), changeRequest);
    }

    public ChangeRequest getChangeRequest(String changeId) {
        return changeRequests.get(changeId);
    }
}
