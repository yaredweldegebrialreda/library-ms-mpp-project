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
	public User addUser(User user) {
		DataAccess da = new DataAccessFacade();
		da.saveUser(user);
		return user;
	}


	@Override
	public LibraryMember addMember(LibraryMember libraryMember) {
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(libraryMember);
		return libraryMember;
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
