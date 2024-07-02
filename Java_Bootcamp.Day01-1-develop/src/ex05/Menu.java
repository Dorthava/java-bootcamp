package ex05;

import java.util.Scanner;

public class Menu {
    private final TransactionsService service;

    public Menu() {
        service = new TransactionsService();
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running) {
            printMenu();
            String line = scanner.nextLine();
            boolean invalidRequest = true;
            while(invalidRequest) {
                if (line.equals("7")) {
                    running = false;
                } else if (line.equals("1")) {
                    System.out.println("Enter a user name and a balance");
                    String[] subLine = scanner.nextLine().split(" ");
                    int id = service.addUser(new User(subLine[0], Integer.parseInt(subLine[1])));
                    System.out.println("User with id = " + id + " is added");
                } else if (line.equals("2")) {
                    System.out.println("Enter a user ID");
                    try {
                        int userId = Integer.parseInt(scanner.nextLine());
                        service.retrievingUserBalance(userId);
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage() + " Попробуйте еще раз.");
                        continue;
                    }
                } else if (line.equals("3")) {
                    System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                    String[] subLine = scanner.nextLine().split(" ");
                    try {
                        service.performingTransferTransaction(Integer.parseInt(subLine[1]), Integer.parseInt(subLine[0]), Float.parseFloat(subLine[2]));
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage() + " Попробуйте еще раз.");
                    }
                    System.out.println("The transfer is completed");
                } else if (line.equals("4")) {
                    System.out.println("Enter a user ID");
                    int id = Integer.parseInt(scanner.nextLine());
                    Transaction[] transactions = service.retrievingTransfers(id);
                    for(Transaction i : transactions) {
                        User user = (i.getRecipient().getIdentifier() == id) ? i.getSender() : i.getRecipient();
                        System.out.println("To " + user.getName() + "(" + "id = " + id + ") " +  i.getTransferAmount() + " with id = " + i.getIdentifier());
                    }
                } else if (line.equals("5")) {
                    User otherUser = null;
                    float balance = 0.0f;
                    System.out.println("Enter a user ID and a transfer ID");
                    String[] subLine = scanner.nextLine().split(" ");
                    Transaction[] transactions = service.retrievingTransfers(Integer.parseInt(subLine[0]));
                    for(Transaction i : transactions) {
                        if(i.getIdentifier().toString().equals(subLine[1])) {
                            otherUser = (i.getRecipient().getIdentifier() == Integer.parseInt(subLine[0])) ? i.getSender() : i.getRecipient();
                            balance = i.getTransferAmount();
                            break;
                        }
                    }
                    if(!service.removingTransaction(Integer.parseInt(subLine[0]), subLine[1])) {
                        continue;
                    }
                    if(otherUser != null) System.out.println("Transfer To " + otherUser.getName() + "(" + "id = " + otherUser.getIdentifier() + ") " + -balance + " removed");
                } else {
                    service.checkAllTransaction();
                }
                invalidRequest = false;
            }
            System.out.println("---------------------------------------------------------");
        }
    }
    private void printMenu() {
        System.out.println("1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. DEV – remove a transfer by ID\n" +
                "6. DEV – check transfer validity\n" +
                "7. Finish execution");
    }
}
