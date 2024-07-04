package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public User login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		return map.get(id);
	}

	@Override
	public LibraryMember addMember(LibraryMember libraryMember) throws RessourceException {
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(libraryMember);
		return libraryMember;
	}


	public CheckoutEntry checkoutBook(String memberId, String isbnNumber){
		DataAccess da = new DataAccessFacade();
        try {
            return da.checkoutBook(memberId, isbnNumber);

        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (RessourceException e) {
            throw new RuntimeException(e);
        }
    }

	public List<CheckoutEntry> getCheckoutEntries() {
		// Implement this method to retrieve the checkout entries for a given member ID
		// This might involve fetching the CheckoutRecord for the member and returning the list of entries
		// Example:
		DataAccess da = new DataAccessFacade();

		HashMap<String, CheckoutRecord> records = da.readCheckoutRecordMap();
		List<CheckoutEntry> entriesList= new ArrayList<>();
		for(CheckoutRecord record : records.values()) {
			entriesList.addAll(record.getEntries());
		}
		return entriesList;
	}


	public Book readBookByIsbn(String isbn) {
		DataAccess da = new DataAccessFacade();
		return da.readBookByIsbn(isbn);
	}
	public void addBookCopy(String isbn) throws RessourceException {
		DataAccess da = new DataAccessFacade();
		da.addBookCopy(isbn);
	}


	@Override
	public void addAuthUser(User user) {
		DataAccess da = new DataAccessFacade();
		da.saveAuth(user);
	}

	@Override
	public User readAuthUser() {
		DataAccess da = new DataAccessFacade();
		return da.readAuth();
	}

	@Override
	public User addUser(User user) throws RessourceException {
		DataAccess da = new DataAccessFacade();
		da.saveUser(user);
		return user;
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	public List<String> allUsersIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readUserMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	
}
