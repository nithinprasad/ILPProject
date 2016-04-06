package vendor.service;

import java.sql.SQLException;

import vendor.bean.User;
import vendor.dao.LoginDao;

public class LoginService {
	/**
	 * @author shweta
	 * @class name LoginService
	 * @Creation date 17july2015
	 */

	public String loginUser(String userId, String password)
			throws SQLException {
		/**
		 * @param <String><userId>
		 * @param <String><password>
		 * @return <boolean><return true or false>
		 * @throws <SQLException>
		 * @see calling to Dao class function(getLogin)
		 */

		LoginDao loginDao = new LoginDao();
		return loginDao.getLogin(userId, password);
	

	}

}
