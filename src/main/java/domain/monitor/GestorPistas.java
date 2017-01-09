package domain.monitor;

import domain.model.Flight;
import domain.model.Lane;
import domain.model.Plane;

public class GestorPistas {
	
	public final static int ATERRIZAJE = 1;
	public final static int FORK = 3;
	public final static int PISTATERMINAL = 4;
	public final static int LASTTERMINAL = 4;
	public final static int POSTTERMINAL = 5;
	public final static int CURVERIGHT = 2;
	public final static int CURVELEFT = 6;
	public final static int DESPEGUE = 7;
	
	public static Lane seeNextLane(Plane avion){
		Lane nextLane = new Lane();
		Lane currentLane =avion.getLane();
		if(currentLane == null){
			nextLane = //procedimiento que coge la pista de orden 1
		} else if (currentLane.getLaneType().getIdLaneType() == CURVERIGHT ||
		           currentLane.getLaneType().getIdLaneType() == POSTTERMINAL ||
		           currentLane.getLaneType().getIdLaneType() == CURVELEFT) {
			nextLane = getNextLane(avion);
		} else if (currentLane.getLaneType().getIdLaneType() == FORK) {
			nextLane = getNextLaneFromForkLanes(avion); 
		} else if (currentLane.getLaneType().getIdLaneType() == PISTATERMINAL) {
			nextLane = getNextLanePreTerminal(avion.getLane());
		}
		return nextLane;
	}
	
	private static Lane getNextLane(Plane avion){
		Lane nextLane = null;
		nextLane = //procedimiento para coger la siguiente pista en la lista.
		return nextLane;
	}
	
	private static Lane getNextLanePreTerminal(Lane currentLane){
		Lane nextLane = null;
		if(currentLane.getLaneOrder() == LASTTERMINAL){
			nextLane = //procedimiento que coge la primera pista 2 tipos mayor a la actual
		}else {
	        nextLane = //procedimiento que coge la pista de siguiente tipo y con el mismo orden.
		}
		return nextLane;
	}
	
	private static Lane getNextLaneFromForkLanes (Plane avion){
		Lane nextLane = null;
		if(avion.getLane().getLaneOrder() == avion.getTerminal()) {
	    	nextLane = //procedimiento que coge la pista de siguiente tipo y con el mismo orden.
	    } else {
	    	nextLane = //procedimiento que coge la pista del mismo tipo y con el siguiente orden
	    }
		return nextLane;
	}

}
