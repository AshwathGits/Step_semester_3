package week3.assigment_problems;

public class LibraryBook {

    private String title;
    private String isbn;

    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void printStatus() {

        boolean catalogued = !isbn.equals("PENDING");

        System.out.println(title + " | " + isbn
                + " | Catalogued: " + catalogued);
    }

    public static void main(String[] args) {

        String[] titles = {
            "Clean Code",
            "Untitled Draft",
            "1984",
            "Notes"
        };

        String[] isbns = {
            "978-0132350884",
            "",
            "9780451524935",
            ""
        };

        LibraryBook[] books = new LibraryBook[titles.length];

        for (int i = 0; i < books.length; i++) {

            if (isbns[i].isEmpty()) {
                books[i] = new LibraryBook(titles[i]);
            } else {
                books[i] = new LibraryBook(titles[i], isbns[i]);
            }

            books[i].printStatus();
        }
    }
}