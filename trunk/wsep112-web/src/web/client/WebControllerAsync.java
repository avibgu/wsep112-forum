package web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WebControllerAsync {

	void login(String username, String password, AsyncCallback<Boolean> callback);

}
