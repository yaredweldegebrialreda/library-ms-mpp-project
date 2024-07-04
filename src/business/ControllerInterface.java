package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public interface ControllerInterface {
	public User login(String id, String password) throws LoginException;
	public User addUser(User user) throws RessourceException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public LibraryMember addMember(LibraryMember libraryMember) throws RessourceException;
	public void addAuthUser(User user);
	public User readAuthUser();
	public CheckoutEntry checkoutBook(String memberId, String isbnNumber) throws RessourceException;
	public Book readBookByIsbn(String isbn);
	public void addBookCopy(String isbn) throws RessourceException;
	
}
