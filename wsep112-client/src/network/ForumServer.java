/**
 * 
 */
package network;

import network.messages.Message;

/**
 * @author Avi Digmi
 *
 */
public interface ForumServer {

	Message getInformation(Message whatToGet);
}
