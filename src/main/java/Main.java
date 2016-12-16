import java.util.List;

import domain.dao.DaoUserHibernate;
import domain.model.User;

public class Main {
	
	private static List<User> userList;
    private static DaoUserHibernate userDao;

	public static void main(String[] args) {
		
	    userDao = new DaoUserHibernate();
	    System.out.println("LLEGA 1");

        userList = userDao.loadUser();

        System.out.println("LLEGA 2");

        System.out.println("Hay " + userList.size() + " usuarios");
	}

}
