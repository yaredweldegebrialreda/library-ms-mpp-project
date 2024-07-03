package dataaccess;

import java.util.HashMap;

import business.*;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member) throws RessourceException;
	public void saveUser(User user) throws RessourceException;
	public void saveAuth(User user);
	public User readAuth();


	public User readUserById(String id);
	public CheckoutRecord checkoutBook(String memberId, String isbnNumber) throws LoginException, RessourceException;
}
