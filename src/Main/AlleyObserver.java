package Main;
/* AlleyObserver.java
 *
 *  Version
 *  $Id$
 * 
 *  Revisions:
 * 		$Log$
 * 
 */

/**
 * Interface for classes that observe control desk events
 *
 */

public interface AlleyObserver {

	public void receiveAlleyEvent(AlleyEvent ce);

}
