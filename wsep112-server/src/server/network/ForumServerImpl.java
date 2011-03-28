/**
 *
 */
package server.network;

import java.rmi.RemoteException;
import java.rmi.server.RemoteStub;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.logging.Logger;

import common.network.ForumServer;
import common.network.messages.AddFriendMessage;
import common.network.messages.AddPostMessage;
import common.network.messages.AddThreadMessage;
import common.network.messages.ErrorMessage;
import common.network.messages.LoginMessage;
import common.network.messages.LogoutMessage;
import common.network.messages.Message;
import common.network.messages.RegMessage;
import common.network.messages.RemoveFriendMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;

import domain.ForumController;

/**
 * @author Avi Digmi
 *
 */
public class ForumServerImpl extends RemoteStub implements ForumServer {

	private static final long serialVersionUID = 2555939371339195609L;

	private ForumController forumController;
	private ReentrantReadWriteLock rwLock;
	private ReadLock rdLock;
	private WriteLock wrLock;
	private Logger logger;

	public ForumServerImpl(ForumController forumController, Logger logger) throws RemoteException {

		super();
		setForumController(forumController);
		setRwLock(new ReentrantReadWriteLock(true));
		setRdLock(getRwLock().readLock());
		setWrLock(getRwLock().writeLock());
		setLogger(logger);
	}

	/* (non-Javadoc)
	 * @see network.ForumServer#getInformation(network.Message)
	 */
	@Override
	public Message getInformation(Message whatToGet) throws RemoteException {

		log("got a " + whatToGet.getMessageType() + " message");

		Message answer;

		getRdLock().lock();

		switch(whatToGet.getMessageType()){

			case SEE_FORUMS_LIST:

				SeeForumsListMessage sflm = (SeeForumsListMessage)whatToGet;

				answer = getForumController().getForumsList(sflm);

				break;

			case SEE_FORUM_THREADS:

				SeeForumThreadsMessage sftm = (SeeForumThreadsMessage)whatToGet;

				answer = getForumController().getThreadsList(sftm.getForumID(), sftm);

				break;

			case SEE_POSTS_OF_SOME_THREAD:

				SeeThreadPostsMessage stpm = (SeeThreadPostsMessage)whatToGet;

				answer = getForumController().getPostsList(stpm.getThreadID(), stpm);

				break;

			default:

				answer = new ErrorMessage("Message Type is unrecognized");
		}

		getRdLock().unlock();

		return answer;
	}

	@Override
	public Message setInformation(Message whatToSet){

		log("got a " + whatToSet.getMessageType() + " message");

		Message answer;

		getWrLock().lock();

		switch(whatToSet.getMessageType()){

			case REGISTRATION:

				RegMessage rm = (RegMessage)whatToSet;

				answer = getForumController().register(rm.getFirstName(), rm.getLastName(), rm.getUsername(),
						rm.getPassword(), rm.getEmail());

				break;

			case LOGIN:

				LoginMessage lim = (LoginMessage)whatToSet;

				answer = getForumController().login(lim.getUsername(), lim.getPassword());

				break;

			case LOGOUT:

				LogoutMessage lom = (LogoutMessage)whatToSet;

				answer = getForumController().logout(lom.getUsername());

				break;

			case ADD_FRIEND:

				AddFriendMessage afm = (AddFriendMessage)whatToSet;

				answer = getForumController().AddFriend(afm.getUsername(), afm.getFriendUsername());

				break;

			case REMOVE_FRIEND:

				RemoveFriendMessage rfm = (RemoveFriendMessage)whatToSet;

				answer = getForumController().RemoveFriend(rfm.getUsername(), rfm.getFriendUsername());

				break;

			case ADD_POST_TO_THREAD:

				AddPostMessage apttm = (AddPostMessage)whatToSet;

				answer = getForumController().replyToThread(apttm.getTitle(), apttm.getBody(), apttm.getThreadId(), apttm.getOwnerUsername());

				break;

			case ADD_THREAD:

				AddThreadMessage athm = (AddThreadMessage)whatToSet;

				answer = getForumController().addThread(athm.getTitle(), athm.getBody(), athm.getOwnerUsername());

				break;

			default:

				answer = new ErrorMessage("Message Type is unrecognized");
		}

		getWrLock().unlock();

		return answer;
	}

	private void setForumController(ForumController forumController) {
		this.forumController = forumController;
	}

	private ForumController getForumController() {
		return forumController;
	}

	private void setRwLock(ReentrantReadWriteLock rwLock) {
		this.rwLock = rwLock;
	}

	private ReentrantReadWriteLock getRwLock() {
		return rwLock;
	}

	private void setRdLock(ReadLock rdLock) {
		this.rdLock = rdLock;
	}

	private ReadLock getRdLock() {
		return rdLock;
	}

	private void setWrLock(WriteLock wrLock) {
		this.wrLock = wrLock;
	}

	private WriteLock getWrLock() {
		return wrLock;
	}

	private void setLogger(Logger logger) {
		this.logger = logger;
	}

	private Logger getLogger() {
		return logger;
	}

	private void log(String msg){
		getLogger().info(msg);
	}
}
