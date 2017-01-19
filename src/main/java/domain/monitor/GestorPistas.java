package domain.monitor;

import domain.dao.DaoLane;
import domain.model.Lane;
import domain.model.Plane;

// TODO: Auto-generated Javadoc
/**
 * The Class GestorPistas.
 */
public class GestorPistas {
	
	/** The Constant ATERRIZAJE. */
	public final static int ATERRIZAJE = 1;
	
	/** The Constant FORK. */
	public final static int FORK = 3;
	
	/** The Constant PISTATERMINAL. */
	public final static int PISTATERMINAL = 4;
	
	/** The Constant LASTTERMINAL. */
	public final static int LASTTERMINAL = 4;
	
	/** The Constant POSTTERMINAL. */
	public final static int POSTTERMINAL = 5;
	
	/** The Constant CURVERIGHT. */
	public final static int CURVERIGHT = 2;
	
	/** The Constant CURVELEFT. */
	public final static int CURVELEFT = 6;
	
	/** The Constant DESPEGUE. */
	public final static int DESPEGUE = 7;
	
	/** The Constant NOHAY. */
	public final static int NOHAY = -1;
	
	/**
	 * See next lane.
	 *
	 * @param avion the avion
	 * @return the int
	 */
	public static int seeNextLane(Plane avion){
		int nextLaneId = 0;
		Lane currentLane =avion.getLane();
		if(currentLane == null){
			System.out.println("-1 getNextLane avion"+ avion.getIdPlane());
			nextLaneId = DaoLane.getnextLane(NOHAY, NOHAY);
		} else if (currentLane.getLaneType().getIdLaneType() == ATERRIZAJE ||
				   currentLane.getLaneType().getIdLaneType() == CURVERIGHT ||
		           currentLane.getLaneType().getIdLaneType() == POSTTERMINAL ||
		           currentLane.getLaneType().getIdLaneType() == CURVELEFT) {
			System.out.println("1-2-5-6 getNextLane avion"+ avion.getIdPlane());
			nextLaneId = getNextLane(currentLane);
		} else if (currentLane.getLaneType().getIdLaneType() == FORK) {
			nextLaneId = getNextLaneFromForkLanes(avion);
			System.out.println("3 getNextLaneFromForkLanes avion"+ avion.getIdPlane());
		} else if (currentLane.getLaneType().getIdLaneType() == PISTATERMINAL) {
			nextLaneId = getNextLanePostTerminal(avion.getLane());
			System.out.println("4 GetNextLanePostTerminal avion"+ avion.getIdPlane());
		}
		return nextLaneId;
	}
	
	/**
	 * Gets the next lane.
	 *
	 * @param currentLane the current lane
	 * @return the next lane
	 */
	private static int getNextLane(Lane currentLane){
		int nextLaneId = 0;
		nextLaneId = DaoLane.getnextLane(currentLane.getLaneType().getIdLaneType(), currentLane.getLaneOrder());
		return nextLaneId;
	}
	
	/**
	 * Gets the next lane post terminal.
	 *
	 * @param currentLane the current lane
	 * @return the next lane post terminal
	 */
	private static int getNextLanePostTerminal(Lane currentLane){
		int nextLaneId = 0;
		if(currentLane.getLaneOrder() == LASTTERMINAL){
			nextLaneId = DaoLane.getTypeSix();
		}else {
			nextLaneId = DaoLane.getNextLaneSameOrder(currentLane.getLaneType().getIdLaneType(), currentLane.getLaneOrder());
		}
		return nextLaneId;
	}
	
	/**
	 * Gets the next lane from fork lanes.
	 *
	 * @param avion the avion
	 * @return the next lane from fork lanes
	 */
	private static int getNextLaneFromForkLanes (Plane avion){
		int nextLaneId = 0;
		if(avion.getLane().getLaneOrder() == avion.getTerminal()) {
	    	nextLaneId = DaoLane.getNextLaneSameOrder(avion.getLane().getLaneType().getIdLaneType(), avion.getLane().getLaneOrder());
	    } else {
	    	nextLaneId = DaoLane.getNextLaneWithNextOrder(avion.getLane().getLaneType().getIdLaneType(), avion.getLane().getLaneOrder());
	    }
		return nextLaneId;
	}
}
