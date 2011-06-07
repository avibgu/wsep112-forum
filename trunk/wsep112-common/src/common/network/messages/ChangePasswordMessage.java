package common.network.messages;

import java.io.Serializable;

import common.observation.RemoteObserver;

public class ChangePasswordMessage implements Message ,Serializable {
	
		private static final long serialVersionUID = -6144281381240070187L;

		private String _username;
		private String _password;
		private RemoteObserver _remoteObserver;

		/**
		 * @param username
		 * @param password
		 */
		public ChangePasswordMessage(String username, String password, RemoteObserver ro) {
			
			super();
			setUsername(username);
			setPassword(password);
			setRemoteObserver(ro);
		}

		/* (non-Javadoc)
		 * @see network.Message#getMessageType()
		 */
		@Override
		public MessageType getMessageType() {
			return MessageType.CHANGE_PASSWORD;
		}

		public String getUsername() {
			return _username;
		}

		public void setUsername(String username) {
			this._username = username;
		}

		public String getPassword() {
			return _password;
		}

		public void setPassword(String password) {
			this._password = password;
		}

		public void setRemoteObserver(RemoteObserver _remoteObserver) {
			this._remoteObserver = _remoteObserver;
		}
		
		public RemoteObserver getRemoteObserver() {
			return this._remoteObserver;
		}
	

}
