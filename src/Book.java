import java.util.ArrayList;

public class Book implements Borrowable{
	private String isbn;
    private String title;
    private String publisher;
    private ArrayList<String> authors;
    private int availableCopies;
    
    public Book(String isbn, String title, ArrayList<String> authors, int availableCopies, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.availableCopies = availableCopies;
        this.setPublisher(publisher);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public static Book getBook(int num) {

    	if(LibrarySystem.books.size() < num) {
    		return null;
    	}
    	return LibrarySystem.books.get(num-1);
    }
    
    public static Book getBookByName(String bookTitle) {
      for (Book book : LibrarySystem.books) {
          if (book.getTitle().equals(bookTitle)) {
              return book;
          }
      }
      return null;
  	
  }

    @Override
    public void returnItem() {
    	this.setAvailableCopies(this.availableCopies+1);
    }

    @Override
    public void borrowItem() {
    	this.setAvailableCopies(this.availableCopies-1);
    }

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
