/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class AddThreadMessage implements Message, Serializable {

	private static final long serialVersionUID = 5581097442389236130L;
	 
	private String forumId;
	private String title;
	private String body;
	private String ownerUsername;
	
	/**
	 * 
	 * @param title
	 * @param body
	 */
	public AddThreadMessage(String forumID,String title, String body, String ownerUsername) {
		
		super();
		setForumId(forumID);
		setTitle(title);
		setBody(body);
		setOwnerUsername(ownerUsername);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ADD_THREAD;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getForumId() {
		return forumId;
	}
}
