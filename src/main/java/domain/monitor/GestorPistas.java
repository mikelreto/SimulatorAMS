package domain.monitor;

import javax.persistence.Query;

import org.hibernate.Session;

import domain.dao.DaoLane;
import domain.model.Flight;
import domain.model.Lane;
import domain.model.Plane;
import domain.model.SimulatorLane;

public class GestorPistas {
	
	public final static int ATERRIZAJE = 1;
	public final static int FORK = 3;
	public final static int PISTATERMINAL = 4;
	public final static int LASTTERMINAL = 4;
	public final static int POSTTERMINAL = 5;
	public final static int CURVERIGHT = 2;
	public final static int CURVELEFT = 6;
	public final static int DESPEGUE = 7;
	public final static int NOHAY = -1;
	
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
	
	private static int getNextLane(Lane currentLane){
		System.out.println("En la funcion getNextLane GestorPistas");
		int nextLaneId = 0;
		nextLaneId = DaoLane.getnextLane(currentLane.getLaneType().getIdLaneType(), currentLane.getLaneOrder());
		return nextLaneId;
	}
	
	private static int getNextLanePostTerminal(Lane currentLane){
		System.out.println("En la funcion getNextLanePostTerminal GestorPistas");
		int nextLaneId = 0;
		if(currentLane.getLaneOrder() == LASTTERMINAL){
			//nextLane = DaoLane.getTypeSix();
			System.out.println("getTypeSix from lane type "+ currentLane.getLaneType().getIdLaneType()+ "and order " + currentLane.getLaneOrder());
		}else {
	        //nextLane = DaoLane.getNextLaneSameOrder(currentLane.getLaneOrder(), currentLane.getLaneType().getIdLaneType());
	        System.out.println("getNextLaneSameOrder from lane type "+ currentLane.getLaneType().getIdLaneType()+ "and order " + currentLane.getLaneOrder());
		}
		return nextLaneId;
	}
	
	private static int getNextLaneFromForkLanes (Plane avion){
		System.out.println("En la funcion getNextLaneFromForkLanes GestorPistas");
		int nextLaneId = 0;
		if(avion.getLane().getLaneOrder() == avion.getTerminal()) {
			System.out.println("getNextLaneSameOrder from lane type "+ avion.getLane().getLaneType().getIdLaneType()+ "and order " + avion.getLane().getLaneOrder());
	    	//nextLane = DaoLane.getNextLaneSameOrder(avion.getLane().getLaneOrder(), avion.getLane().getLaneType().getIdLaneType());
	    } else {
	    	//nextLane = DaoLane.getNextLaneWithNextOrder(avion.getLane().getLaneType().getIdLaneType(), avion.getLane().getLaneOrder() );
	    	System.out.println("getNextLaneWithNextOrder from lane type "+ avion.getLane().getLaneType().getIdLaneType()+ "and order " + avion.getLane().getLaneOrder());
	    }
		return nextLaneId;
	}

}
