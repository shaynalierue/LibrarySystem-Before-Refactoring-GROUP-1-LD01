
public abstract class User {
	private String userID;
    private String name;
    private String email;
    private String phoneNumber;
    
    public User(String userID, String name, String email, String phoneNumber) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User getUser(String userID) {
        for (User user : LibrarySystem.users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        if (this instanceof Librarian) {
            return "L";
        } else if (this instanceof Member) {
            return "M";
        } else {
            return "";
        }
    }
    
}
