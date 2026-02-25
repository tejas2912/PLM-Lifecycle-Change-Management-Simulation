import model.*;
import service.*;
import repository.*;

import java.util.Scanner;

public class PLMApplication {

    public static void main(String[] args) {

        InMemoryDatabase database = new InMemoryDatabase();
        WorkflowService workflowService = new WorkflowService();
        ChangeManagementService changeService = new ChangeManagementService();

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n===== PLM SYSTEM =====");
            System.out.println("1. Create Item");
            System.out.println("2. Create Revision");
            System.out.println("3. Submit for Review");
            System.out.println("4. Approve Revision");
            System.out.println("5. Raise Change Request (ECR)");
            System.out.println("6. Implement Approved Change (ECO)");
            System.out.println("7. Exit");
            System.out.println("8. View Item Details");
            System.out.println("9. View Latest Revision State");
            System.out.print("Select option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Item ID: ");
                    String itemId = sc.nextLine();

                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Item Type: ");
                    String type = sc.nextLine();

                    Item item = new Item(itemId, name, type);
                    database.saveItem(item);

                    System.out.println("Item created successfully.");
                    break;

                case 2:
                    System.out.print("Enter Item ID: ");
                    String revItemId = sc.nextLine();

                    Item itemForRev = database.getItem(revItemId);

                    if (itemForRev == null) {
                        System.out.println("Item not found.");
                        break;
                    }

                    // If no revision exists, allow first revision
                    if (itemForRev.getRevisions().isEmpty()) {
                        ItemRevision firstRev = new ItemRevision("A", "Initial Revision");
                        itemForRev.addRevision(firstRev);
                        System.out.println("Revision A created.");
                        break;
                    }

                    ItemRevision latest = itemForRev.getLatestRevision();

                    // Block direct creation if latest revision is RELEASED
                    if (latest.getState() == LifecycleState.RELEASED) {
                        System.out.println("Cannot create new revision directly.");
                        System.out.println("Please raise a Change Request first.");
                        break;
                    }

                    // Allow revision creation if not RELEASED
                    char nextRevChar = (char) (latest.getRevisionId().charAt(0) + 1);
                    ItemRevision newRev = new ItemRevision(String.valueOf(nextRevChar), "New Revision");
                    itemForRev.addRevision(newRev);

                    System.out.println("Revision " + newRev.getRevisionId() + " created.");
                    break;

                case 3:
                    System.out.print("Enter Item ID: ");
                    String submitItemId = sc.nextLine();

                    Item submitItem = database.getItem(submitItemId);

                    if (submitItem == null || submitItem.getLatestRevision() == null) {
                        System.out.println("Item or Revision not found.");
                        break;
                    }

                    workflowService.submitForReview(submitItem.getLatestRevision());
                    break;

                case 4:
                    System.out.print("Enter Item ID: ");
                    String approveItemId = sc.nextLine();

                    Item approveItem = database.getItem(approveItemId);

                    if (approveItem == null || approveItem.getLatestRevision() == null) {
                        System.out.println("Item or Revision not found.");
                        break;
                    }

                    workflowService.approveRevision(approveItem.getLatestRevision());
                    break;

                case 5:
                    System.out.print("Enter Item ID: ");
                    String crItemId = sc.nextLine();

                    Item crItem = database.getItem(crItemId);

                    if (crItem == null) {
                        System.out.println("Item not found.");
                        break;
                    }

                    changeService.raiseChangeRequest(crItem, database);
                    break;

                case 6:
                    System.out.print("Enter Change Request ID: ");
                    String crId = sc.nextLine();

                    ItemRevision createdRev = changeService.createNewRevisionFromChange(crId, database);
                    
                    if (createdRev != null) {
                        System.out.println("New Revision Created: " + createdRev.getRevisionId());
                    }
                    break;

                case 7:
                    running = false;
                    System.out.println("Exiting system.");
                    break;

                case 8:
                    System.out.print("Enter Item ID: ");
                    String viewItemId = sc.nextLine();

                    Item viewItem = database.getItem(viewItemId);

                    if (viewItem == null) {
                        System.out.println("Item not found.");
                        break;
                    }

                    System.out.println("\nItem Details:");
                    System.out.println(viewItem);

                    if (viewItem.getRevisions().isEmpty()) {
                        System.out.println("No revisions found.");
                    } else {
                        System.out.println("Revisions:");
                        for (ItemRevision rev : viewItem.getRevisions()) {
                            System.out.println(" - " + rev);
                        }
                    }
                    break;

                case 9:
                    System.out.print("Enter Item ID: ");
                    String stateItemId = sc.nextLine();

                    Item stateItem = database.getItem(stateItemId);

                    if (stateItem == null || stateItem.getLatestRevision() == null) {
                        System.out.println("Item or Revision not found.");
                        break;
                    }

                    ItemRevision latestRev = stateItem.getLatestRevision();

                    System.out.println("\nLatest Revision: " + latestRev.getRevisionId());
                    System.out.println("Current State: " + latestRev.getState());
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
