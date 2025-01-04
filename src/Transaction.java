
public class Transaction {
	private String transactionID;
    private String bookName;
    private String meetingRoomName;
    private String transactionDate;
    private String userID;
    private boolean isBorrowed;

    public Transaction(String transactionID, String bookName, String meetingRoomName, String transactionDate, String userID, boolean isBorrowed) {
        this.transactionID = transactionID;
        this.bookName = bookName;
        this.meetingRoomName = meetingRoomName;
        this.transactionDate = transactionDate;
        this.userID = userID;
        this.setBorrowed(isBorrowed);
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public static Transaction getTransaction(String transactionID) {
        for (Transaction transaction : LibrarySystem.transactions) {
            if (transaction.getTransactionID().equals(transactionID)) {
                return transaction;
            }
        }
        return null;
    }

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
}
