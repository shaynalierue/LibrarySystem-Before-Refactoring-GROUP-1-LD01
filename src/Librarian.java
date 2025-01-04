import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Librarian extends User {
	private Scanner scan = new Scanner(System.in);

    public Librarian(String userID, String name, String email, String phoneNumber) {
        super(userID, name, email, phoneNumber);
    }


    public void addABook() {
        String isbn, title, publisher;
        ArrayList<String> authors = new ArrayList<>();
        int availableCopies;
        String currentAuthorInput = "";
        
        do {
        	System.out.print("Input ISBN : ");
            isbn = scan.nextLine();
            if(!isbn.startsWith("IS")) {
            	System.out.println("Must Start With 'IS'");
            }
        }while(!isbn.startsWith("IS"));
        
        System.out.print("Input title : ");
        title = scan.nextLine();
        
        System.out.println("Input authors [press 0 to exit]: ");
        
        do {
        	currentAuthorInput = scan.nextLine();
            if (currentAuthorInput.equals("0")) {
            	if(authors.size() == 0) {
            		currentAuthorInput = "";
                    System.out.println("You should add author name");
            	} else {
            		break;
            	}
            } else {
            	authors.add(currentAuthorInput);
            }
        } while (!currentAuthorInput.equals("0"));
        do {
            System.out.print("Input number of available copies [at least 1]: ");
            availableCopies = scan.nextInt();
            scan.nextLine();
            if (availableCopies < 1) {
                System.out.println("Number of available copies must be at least 1!");
            }
        } while (availableCopies < 1);
        
        System.out.print("Input publisher : ");
        publisher = scan.nextLine();
        
        LibrarySystem.books.add(new Book(isbn, title, authors, availableCopies, publisher));
        System.out.println("Book added!");
        System.out.print("Press enter to continue...");
        scan.nextLine();
        librarianMenu();
    }

    public void addALibrarian() {
        String userID, name, email, phoneNumber;
        
        do {
        	  System.out.print("Input user ID : ");
              userID = scan.nextLine();
              
              if(!userID.startsWith("L")) {
            	  System.out.println("start ID with L");
              }
        }while(!userID.startsWith("L"));
      
        System.out.print("Input name : ");
        name = scan.nextLine();
        do {
            System.out.print("Input email [must contain @ and .]: ");
            email = scan.nextLine();
        } while (!email.contains("@") || !email.contains("."));
        
        System.out.print("Input phone number: ");
        phoneNumber = scan.nextLine();
        LibrarySystem.users.add(new Librarian(userID, name, email, phoneNumber));
        System.out.println("Librarian added!");
        System.out.print("Press enter to continue...");
        scan.nextLine();
        librarianMenu();
    }

    public void addAMember() {
        String userID, name, email, phoneNumber;
        do{
        	System.out.print("Input user ID : ");
            userID = scan.nextLine();
            
            if(!userID.startsWith("M")) {
            	System.out.println("start ID with M");
            }
        }while(!userID.startsWith("M"));
        
        System.out.print("Input name : ");
        name = scan.nextLine();
        
        do {
            System.out.print("Input email [must contain @ and .]: ");
            email = scan.nextLine();
        } while (!email.contains("@") || !email.contains("."));
        
        System.out.print("Input phone number: ");
        phoneNumber = scan.nextLine();
        
        LibrarySystem.users.add(new Member(userID, name, email, phoneNumber));
        System.out.println("Member added!");
        System.out.print("Press enter to continue...");
        scan.nextLine();
        librarianMenu();
    }

    public void processBorrowingAMeetingRoomKey() {
        System.out.print("Input Member ID: ");
        String memberID = scan.nextLine();
        User user = User.getUser(memberID);
        if (user == null) {
            System.out.println("Member not found!");
            System.out.print("Press enter to continue...");
            scan.nextLine();
            librarianMenu();
        } else {
            if (user.getStatus() == "M") {
                System.out.println("Meeting Room Availables:");
                int no = 1;
                for (MeetingRoom meetingRoom : LibrarySystem.meetingRooms) {
                    System.out.printf("%d. %s Floor %d Capacity %d Status %s \n", no++, meetingRoom.getName(), meetingRoom.getFloorNumber(), meetingRoom.getCapacity(), (meetingRoom.isAvailable() ? "available" : "borrowed"));
                }
             
                System.out.printf("Input your choices [1 - %d] : ", no-1);
                int meetingRoomNo = scan.nextInt();
                MeetingRoom meetingRoom = MeetingRoom.getMeetingRoom(meetingRoomNo);
                if (meetingRoom == null) {
                    System.out.println("Meeting Room not found!");
                    System.out.print("Press enter to continue...");
                    scan.nextLine();
                    librarianMenu();
                } else {
                    if (meetingRoom.isAvailable()) {
                        String transactionID = "TR" + String.format("%03d", LibrarySystem.transactions.size() + 1);
                        String date = java.time.LocalDate.now().toString();
                        String time = java.time.LocalTime.now().toString();
                        String dateAndTime = date + "-" + time.substring(0, 8);
                        LibrarySystem.transactions.add(new Transaction(transactionID, null, meetingRoom.getName(), dateAndTime, memberID, true));
                        System.out.printf("User %s borrowed Meeting Room: %s\n", memberID, meetingRoom.getName());
                        meetingRoom.borrowItem();
                        System.out.print("Press enter to continue...");
                        scan.nextLine();
                        librarianMenu();
                    } else {
                        System.out.println("Meeting Room is not available!");
                        System.out.print("Press enter to continue...");
                        scan.nextLine();
                        librarianMenu();
                    }
                }
            } else {
                System.out.println("Member not found!");
                System.out.print("Press enter to continue...");
                scan.nextLine();
                
                librarianMenu();
            }
        }
    }

    public void processReturningAMeetingRoomKey() {
        System.out.print("Input Member ID: ");
        String memberID = scan.nextLine();
        User user = User.getUser(memberID);
        HashMap<Integer, Transaction> map = new HashMap<>();
        if (user == null) {
            System.out.println("Member not found!");
            System.out.print("Press enter to continue...");
            scan.nextLine();
            librarianMenu();
        } else {
            if (user.getStatus() == "M") {
                System.out.println("Meeting Room Borrowed:");
                int no = 1;
                boolean found = false;
                for (Transaction transaction : LibrarySystem.transactions) {
                    if (transaction.getUserID().equals(memberID) && transaction.getMeetingRoomName() != null && transaction.isBorrowed()) {
                        System.out.println(no + ". Transaction ID: " + transaction.getTransactionID());
                        System.out.println("   Meeting Room Name: " + transaction.getMeetingRoomName());
                        System.out.println("   Transaction Date: " + transaction.getTransactionDate());
                        System.out.println();
                        found = true;
                        map.put(no, transaction);
                        no++;
                    }
                }
                if (!found) {
                    System.out.println("No meeting room borrowed!");
                    System.out.print("Press enter to continue...");
                    scan.nextLine();
                    librarianMenu();
                }
                System.out.printf("Input your choices [1 - %d] : ", no-1);
                int choice = scan.nextInt();
                Transaction transaction = map.get(choice);
                if (transaction == null) {
                    System.out.println("Transaction not found!");
                    System.out.print("Press enter to continue...");
                    scan.nextLine();
                    librarianMenu();
                } else {
                    MeetingRoom meetingRoom = MeetingRoom.getMeetingRoomByName(transaction.getMeetingRoomName());
                    if (meetingRoom == null) {
                        System.out.println("Meeting Room not found!");
                        System.out.print("Press enter to continue...");
                        scan.nextLine();
                        librarianMenu();
                    } else {
                        if (!meetingRoom.isAvailable()) {
                            transaction.setBorrowed(false);
                            System.out.printf("User %s returned Meeting Room: %s\n", memberID, meetingRoom.getName());
                            MeetingRoom.getMeetingRoomByName(meetingRoom.getName()).returnItem();
                            System.out.print("Press enter to continue...");
                            scan.nextLine();
                            librarianMenu();
                        } else {
                            System.out.println("Meeting Room not found!");
                            System.out.print("Press enter to continue...");
                            scan.nextLine();
                            librarianMenu();
                        }
                    }
                }
            } else {
                System.out.println("Member not found!");
                System.out.print("Press enter to continue...");
                scan.nextLine();
                librarianMenu();
            }
        }
    }

    public void showTransactions() {
        if (LibrarySystem.transactions.size() == 0) {
            System.out.println("No transactions!");
            System.out.print("Press enter to continue...");
            scan.nextLine();
            librarianMenu();
        }
        int no = 1;
        for (Transaction transaction : LibrarySystem.transactions) {
        	String userId = transaction.getUserID();
        	User user = User.getUser(userId);
        	if(userId == "" || user == null) return;
            System.out.printf("%d. Transaction ID: %s\n", no++, transaction.getTransactionID());
            System.out.println("User ID: " + userId);
            System.out.println("User Name: " + user.getName());
            if (transaction.getBookName() != null) {
                System.out.println("Book Name: " + transaction.getBookName());
            }
            if (transaction.getMeetingRoomName() != null) {
                System.out.println("Meeting Room Name: " + transaction.getMeetingRoomName());
            }
            
            System.out.println("Status: " + (transaction.isBorrowed() ? "borrow": "return"));
            System.out.println("Transaction Date: " + transaction.getTransactionDate());
            System.out.println();
        }
        System.out.print("Press enter to continue...");
        scan.nextLine();
        librarianMenu();
    }

    public void librarianMenu() {
        System.out.println("Menu Librarian");
        System.out.println("1. Add a Book");
        System.out.println("2. Add a Librarian");
        System.out.println("3. Add a Member");
        System.out.println("4. Process borrowing a Meeting Room key");
        System.out.println("5. Process returning a Meeting Room key");
        System.out.println("6. Show Transactions");
        System.out.println("7. Exit to Main Menu");
        System.out.print("Choice: ");
        int choice = 0 ;
        try {
        	choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
	            case 1:
	                addABook();
	                break;
	            case 2:
	                addALibrarian();
	                break;
	            case 3:
	                addAMember();
	                break;
	            case 4:
	                processBorrowingAMeetingRoomKey();
	                break;
	            case 5:
	                processReturningAMeetingRoomKey();
	                break;
	            case 6:
	                showTransactions();
	                break;
	            case 7:
	            	return;
	            default:
	                System.out.println("Invalid choice!");
	                librarianMenu();
	                break;
	        }
        }catch(Exception e) {
        	System.out.println("input must be number");
        	scan.nextLine();
        }
        
        
    }
}
