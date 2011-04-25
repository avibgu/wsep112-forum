/**
 * 
 */
package web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Avi Digmi
 *
 */
@RemoteServiceRelativePath("WebController")
public interface WebController extends RemoteService {

	public boolean login(String username, String password);
}
