import java.util.Scanner;

public class MainMenu {
	
	private Scanner scanner = new Scanner(System.in);
    LibrarySystem librarySystem = new LibrarySystem();
    
    /* NOTES !!
     * Member ID yang udah ada  untuk di input : M001 , M002, M003
     * Librarian ID yang udah ada untuk di input : L001, L002 , L002 , L004
    */
	public MainMenu() {
		
		librarySystem.initUser();
        librarySystem.initBook();
        librarySystem.initMeetingRoom();
        librarySystem.initTransaction();
		
		while(true)
		{
			System.out.print("Input your user ID [0 to exit]: ");
	        String userID = scanner.nextLine();
	        if (userID.equals("0")) {
	            System.exit(0);
	        } else {
	            User user = User.getUser(userID);
	            if (user == null) {
	                System.out.println("User not found!");
	                System.out.println();
	            } else {
	                if (user.getStatus() == "L") {
	                    ((Librarian) user).librarianMenu();
	                } else if (user.getStatus() == "M") {
	                    ((Member) user).memberMenu();
	                } else {
	                    System.out.println("User not found!");
	                    System.out.println();
	                }
	            }
	        }
		}
		
    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu();

	}

}
