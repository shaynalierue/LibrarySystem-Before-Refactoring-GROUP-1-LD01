import java.util.HashMap;
import java.util.Scanner;

public class Member extends User {
	private Scanner scan = new Scanner(System.in);

    public Member(String userID, String name, String email, String phoneNumber) {
        super(userID, name, email, phoneNumber);
    }

    public void showMyBorrowedItems() {
        System.out.println("Book List:");
        int no = 1;
        boolean found = false;
        for (Transaction transaction : LibrarySystem.transactions) {
            if ((transaction.getUserID() == this.getUserID()) && (transaction.isBorrowed())) {
                System.out.println(no + ". Transaction ID: " + transaction.getTransactionID());
                if (transaction.getBookName() != null) {
                    System.out.println("   Book Title: " + transaction.getBookName());
                } else {
                    System.out.println("   Meeting Room Name: " + transaction.getMeetingRoomName());
                }
                System.out.println("   Transaction Date: " + transaction.getTransactionDate());
                System.out.println();
                found = true;
                no++;
            }
        }
        if (!found) {
            System.out.println("No book borrowed!");
        }
        System.out.print("Press enter to continue...");
        scan.nextLine();
        System.out.println();
        System.out.println();
        memberMenu();
    }

    public void borrowABook() {
        System.out.println("Book Availables:");
        int no = 1;
        for (Book book : LibrarySystem.books) {
        	System.out.println(no + ". ISBN: " + book.getIsbn());
            System.out.println("   Title: " + book.getTitle());
            System.out.println("   Authors: ");
            for (String author : book.getAuthors()) {
                System.out.println("   => " + author);
            }
            System.out.println("   Available Copies: " + book.getAvailableCopies());
            System.out.println("   Publisher : " + book.getPublisher());
            System.out.println();
            no++;
        }
        System.out.printf("Input your choices [1 - %d] : ", no-1);
        int bookNumber = scan.nextInt();
        Book book = Book.getBook(bookNumber);
        if (book == null) {
            System.out.println("Book not found!");
            System.out.println();
            System.out.println();
            borrowABook();
        } else {
            if (book.getAvailableCopies() > 0) {
                book.borrowItem();
                String transactionID = "TR" + String.format("%03d", LibrarySystem.transactions.size() + 1);
                String date = java.time.LocalDate.now().toString();
                String time = java.time.LocalTime.now().toString();
                String dateAndTime = date + "-" + time.substring(0, 8);
                LibrarySystem.transactions.add(new Transaction(transactionID, book.getTitle(), null, dateAndTime, this.getUserID(), true));
                System.out.printf("Book %s successfully borrowed!\n", book.getTitle());
                System.out.print("Press enter to continue...");
                scan.nextLine();
                System.out.println();
                System.out.println();
                memberMenu();
            } else {
                System.out.println("Book is not available!");
                System.out.println();
                System.out.println();
                borrowABook();
            }
        }
        System.out.println();
        System.out.println();
    }

    public void returnABook() {
        System.out.println("Book Borrowed:");
        int no = 1;
        boolean found = false;
        HashMap<Integer, Transaction> map = new HashMap<>();
        
        for (Transaction transaction : LibrarySystem.transactions) {
            if (transaction.getUserID().equals(this.getUserID()) && transaction.getBookName() != null && transaction.isBorrowed()) {
                System.out.println(no + ". Transaction ID: " + transaction.getTransactionID());
                System.out.println("   Book Title: " + transaction.getBookName());
                System.out.println("   Transaction Date: " + transaction.getTransactionDate());
                System.out.println();
                found = true;
                map.put(no, transaction);
                no++;
            }
        }
        if (!found) {
            System.out.println("No book borrowed!");
            System.out.print("Press enter to continue...");
            scan.nextLine();
            System.out.println();
            System.out.println();
            memberMenu();
        }
        System.out.printf("Input your choices [1 - %d] : ", no-1);
        int listId = scan.nextInt();
        Transaction transaction = map.get(listId);
        if (transaction == null) {
            System.out.println("Transaction not found!");
            System.out.println();
            System.out.println();
            returnABook();
        } else {
            Book book = Book.getBookByName(transaction.getBookName());
            if (book == null) {
                System.out.println("Book not found!");
                System.out.println();
                System.out.println();
                returnABook();
            } else {
                book.returnItem();;
                transaction.setBorrowed(false);;
                System.out.printf("Book %s successfully returned!\n", transaction.getBookName());
                System.out.print("Press enter to continue...");
                scan.nextLine();
                System.out.println();
                System.out.println();
                memberMenu();
            }
        }
        System.out.println();
        System.out.println();
    }

    public void memberMenu() {
        System.out.println("Menu Member (Self-Service)");
        System.out.println("1. Show my borrowed item(s)");
        System.out.println("2. Borrow a Book");
        System.out.println("3. Return a Book");
        System.out.println("4. Exit to Main Menu");
        System.out.print("Choice: ");
        int choice = 0;
        try {
        	 
        	choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
	            case 1:
	                showMyBorrowedItems();
	                break;
	            case 2:
	                borrowABook();
	                break;
	            case 3:
	                returnABook();
	                break;
	            case 4:
	            	return;
	            default:
	                System.out.println("Invalid choice!");
	                memberMenu();
	                break;
            }
        }catch(Exception e) {
        	System.out.println("input must be number");
        	scan.nextLine();
        	
        }
        
        System.out.println();
    }
}
