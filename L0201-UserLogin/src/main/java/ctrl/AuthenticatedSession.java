package ctrl;

import dal.UserDAO;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class AuthenticatedSession implements jakarta.servlet.http.HttpSessionListener {

	HttpSession session;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		this.session = se.getSession();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
		if(this.session!=null) {
			UserDAO user = (UserDAO)this.session.getAttribute("user");
			if(user!=null)
				user.setLogged(false);
		}
	}

	
}
