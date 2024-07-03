package dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import business.*;
import librarysystem.Util;


public class DataAccessFacade implements DataAccess {

    enum StorageType {
        BOOKS, MEMBERS, USERS, AUTH, CHECKOUT_RECORD;
    }

    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            + "\\dataaccess\\storage";
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    //implement: other save operations
    public void saveNewMember(LibraryMember member) throws RessourceException {
        LibraryMember aLibraryMember = readLibraryMemberById(member.getMemberId());
        if (aLibraryMember != null) throw new RessourceException("LibraryMember", member.getMemberId(), " exists");
        HashMap<String, LibraryMember> mems = readMemberMap();
        String memberId = member.getMemberId();
        mems.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, mems);
    }


    @Override
    public CheckoutRecord checkoutBook(String memberId, String isbn) throws LoginException, RessourceException {
        User loggedUser = readAuth();
        if (loggedUser == null) {
            throw new LoginException("Log in to checkout a book");
        }
        boolean canCheckoutBook = loggedUser.getAuthorization() == Auth.LIBRARIAN || loggedUser.getAuthorization() == Auth.BOTH;
        if (!canCheckoutBook) {
            throw new RessourceException("Book", isbn, "cannot be checked out by this user.");
        }
        Book book = readBooksMap().get(isbn);

        if (book ==null){
            throw new RessourceException("Book", isbn, "does not exist.");
        }

        if (!book.isAvailable()) {
            throw new RessourceException("Book", isbn, "has no copies available");
        }

        LocalDate checkoutDate = Util.convertToLocalDate(new Date());
        LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
        CheckoutEntry checkoutEntry = new CheckoutEntry(book.getNextAvailableCopy(), checkoutDate, dueDate);
        CheckoutRecord checkoutRecord = new CheckoutRecord(memberId, checkoutEntry);
        saveCheckoutRecord(checkoutRecord);
        return checkoutRecord;
    }

    public void saveCheckoutRecord(CheckoutRecord checkoutRecord) throws RessourceException {
        HashMap<String, CheckoutRecord> checkoutRecords = readCheckoutRecordMap();
        if(checkoutRecords == null){
            checkoutRecords=new  HashMap<>();
        }
        String memberId = checkoutRecord.getMemberId();
        checkoutRecords.put(memberId, checkoutRecord);
        saveToStorage(StorageType.CHECKOUT_RECORD, checkoutRecords);
    }

    public HashMap<String, CheckoutRecord>  readCheckoutRecordMap() {
        return (HashMap<String, CheckoutRecord> ) readFromStorage(StorageType.CHECKOUT_RECORD);
    }

    public void saveUser(User user) throws RessourceException {
        User aUser = readUserById(user.getId());
        if (aUser != null) throw new RessourceException("User", user.getId(), " exists");
        HashMap<String, User> users = readUserMap();
        String userId = user.getId();
        users.put(userId, user);
        saveToStorage(StorageType.USERS, users);
    }

    @Override
    public void saveAuth(User userAuth) {
        saveToStorage(StorageType.AUTH, userAuth);
    }


    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        //Returns a Map with name/value pairs being
        //   isbn -> Book
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        return (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
    }

    public User readUserById(String id) {
        //Returns a Map with name/value pairs being
        //   userId -> User
        HashMap<String, User> users = readUserMap();
        if (!users.containsKey(id)) {
            return null;
        }
        return users.get(id);
    }

    public LibraryMember readLibraryMemberById(String id) {
        //Returns a Map with name/value pairs being
        //   userId -> User
        HashMap<String, LibraryMember> members = readMemberMap();
        if (!members.containsKey(id)) {
            return null;
        }
        return members.get(id);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        return (HashMap<String, User>) readFromStorage(StorageType.USERS);
    }

    public User readAuth() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        return (User) readFromStorage(StorageType.AUTH);
    }


    /////load methods - these place test data into the storage area
    ///// - used just once at startup


    static void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    static void loadUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<String, User>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    static void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {

            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }


    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        public boolean equals(Object ob) {
            if (ob == null) return false;
            if (this == ob) return true;
            if (ob.getClass() != getClass()) return false;
            @SuppressWarnings("unchecked")
            Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        private static final long serialVersionUID = 5399827794066637059L;
    }

}
