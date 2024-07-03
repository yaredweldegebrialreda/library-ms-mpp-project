package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveUser(User user);
	public void saveAuth(User user);
	public User readAuth();
}
