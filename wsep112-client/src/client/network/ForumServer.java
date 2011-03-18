/**
 * 
 */
package client.network;

import client.network.messages.Message;

/**
 * @author Avi Digmi
 *
 */
public interface ForumServer {

	Message getInformation(Message whatToGet);
}
