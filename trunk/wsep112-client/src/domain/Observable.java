/**
 * 
 */
package domain;

import java.util.Observer;

/**
 * @author digmia
 *
 */
public interface Observable {

	void addObserver(Observer o) ;
	
	void notifyObservers(Object arg) ;
}
