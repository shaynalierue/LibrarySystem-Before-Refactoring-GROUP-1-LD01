import java.util.ArrayList;

public class LibrarySystem {
	public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<MeetingRoom> meetingRooms = new ArrayList<MeetingRoom>();
    public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public void initUser() {
        users.add(new Librarian("L001", "Albert", "asd@gmail.com", "09334234"));
        users.add(new Librarian("L002", "Jero", "asds@gmail.com", "09334234"));
        users.add(new Librarian("L003", "JeroD", "asds@gmail.com", "09334234"));
        users.add(new Librarian("L004", "JeroA", "asdsD@gmail.com", "09334234"));
        users.add(new Member("M001", "Nabati", "asddd@gmail.com", "09334234"));
        users.add(new Member("M002", "Nabati", "asddd@gmail.com", "09334234"));
        users.add(new Member("M003", "Nano", "asdasdas@gmail.com", "09334234"));
    }

    public void initBook() {
        ArrayList<String> authorsBooks1 = new ArrayList<String>();
        authorsBooks1.add("Nunung");
        books.add(new Book("ISBN001", "Matahari", authorsBooks1, 1, "Ayna"));
        ArrayList<String> authorsBooks2 = new ArrayList<String>();
        authorsBooks2.add("Nunung");
        authorsBooks2.add("Nunung c");
        books.add(new Book("ISBN002", "Sekolah", authorsBooks2, 2, "Ayna"));
        ArrayList<String> authorsBooks3 = new ArrayList<String>();
        authorsBooks3.add("Java");
        books.add(new Book("ISBN003", "Hutan", authorsBooks3, 3, "Ayna"));
    }

    public void initMeetingRoom() {
        meetingRooms.add(new MeetingRoom("ROOM A", false, 1, 30));
        meetingRooms.add(new MeetingRoom("ROOM B", true, 2, 30));
        meetingRooms.add(new MeetingRoom("ROOM C", true, 3, 30));
    }

    public void initTransaction() {
        transactions.add(new Transaction("TR001", "Matahari", null, "2024-01-19-18:00:00", "L001", true));
        transactions.add(new Transaction("TR002", null, "Sekolah", "2024-01-19-18:00:00", "L001", true));
        transactions.add(new Transaction("TR003", "Hutan", null, "2024-01-19-18:00:00", "L001", true));
    }
}
