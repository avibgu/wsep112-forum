/**
 * 
 */
package common.observation;

import java.util.Observer;

/**
 * @author digmia
 *
 */
public interface Observable {

	void addObserver(Observer o) ;
	
	void notifyObservers(Object arg) ;

	void deleteObserver(Observer o);
}
