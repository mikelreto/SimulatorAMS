package domain.monitor;

import domain.model.Lane;
import domain.model.Plane;


/**
 * The monitor class.
 * @author PBL5
 *
 */
public class Monitor {
	

    /**
     * Number of milisencods to wait.
     */
    private static final int ONESECOND = 1000;
    private static final int AIRPORTSPEED = 20;

    /**
     * Function to enter to a new Line.
     * @param currentLine is the actual line.
     * @param nextLine is the next line.
     * @param vuelo is the flight that needs to see the lines.
     * @throws InterruptedException if there is any problem.
     */
    public synchronized static void enterPista(final Lane currentLine,
                                        final Lane nextLine,
                                        final Plane avion)
                                        throws InterruptedException {
         while (!nextLine.getTaken().isEmpty()) {
           if (inTheWaitingArea(currentLine, avion)) {
        	   System.out.println("Deteniedo avion" + avion.getIdPlane() + "en pista "+ currentLine.getIdLane());
        	   nextLine.getSemaforo().acquire();
           } else {
        	   seeSpeed(avion);
        	   move(currentLine, avion);
           }
         }
         while (!inTheWaitingArea(currentLine, avion)) {
               move(currentLine, avion);
		   try {
		      Thread.sleep(ONESECOND);
		    } catch (InterruptedException ex) {
		           Thread.currentThread().interrupt();
		    }
         }
   nextLine.setTaken("Y");
   setPlaneInNewLane(avion, nextLine);
   currentLine.setTaken("N");
   System.out.println("Devolviendo token con avion" + avion.getIdPlane() + "en pista "+ currentLine.getIdLane());
   currentLine.getSemaforo().release();
    }
    
    private static void setPlaneInNewLane(Plane avion, Lane nextLine) {
    	   avion.setPosX(nextLine.getPosXInitLane());
    	   avion.setPosY(nextLine.getPosYInitLane());
	}

	private static void move(Lane currentLane, Plane avion) {
    	if(currentLane.getLaneType().getIdLaneType() == 1 ||
    	   currentLane.getLaneType().getIdLaneType() == 7) {
    		moveRight(avion);
    	    		
    	}else if (currentLane.getLaneType().getIdLaneType() == 2 ||
    	   currentLane.getLaneType().getIdLaneType() == 6 ||
    	   currentLane.getLaneType().getIdLaneType() == 4){
    		moveDown(avion);
    		
    	}else  {
    		moveLeft(avion);
    	    		
    	}
	}

	private static void moveDown(Plane avion) {
		//procedimiento para mover el avion en y - la velocidad actual del avion
		avion.setPosX(avion.getPosY() -  avion.getSpeed());
		
	}

	private static void moveLeft(Plane avion) {
		//procedimiento para mover el avion en x - la velocidad actual del avion
				avion.setPosX(avion.getPosX() -  avion.getSpeed());
		
	}

	private static void moveRight(Plane avion) {

		//procedimiento para mover el avion en x + la velocidad actual del avion
		avion.setPosX(avion.getPosX() +  avion.getSpeed());
		
	}

	private static void seeSpeed (Plane avion){
    	if(avion.getSpeed() > AIRPORTSPEED){
    		//procedimiento que aumenta la velocidad
    		avion.setSpeed(avion.getSpeed() + 5);
    	}else {
    		//procedimiento que disminuie la velocidad
    		avion.setSpeed(avion.getSpeed() - 5);
    	}
    }
   

	private static Boolean inTheWaitingArea(Lane currentLane, Plane avion){
    	Boolean answer = false;
    	if(currentLane.getLaneType().getIdLaneType() == 1 ||
    	   currentLane.getLaneType().getIdLaneType() == 7) {
    		answer = lookWaitingPlaceRight(currentLane, avion);
    		
    	}else if (currentLane.getLaneType().getIdLaneType() == 2 ||
    			  currentLane.getLaneType().getIdLaneType() == 6 ||
    			  currentLane.getLaneType().getIdLaneType() == 4){
    		answer = lookWaitingPlaceLeft(currentLane, avion);
    	}else  {
    		answer = lookWaitingPlaceDown(currentLane, avion);
    	}
    	return answer;
    }

    private static Boolean lookWaitingPlaceRight(Lane currentLane, Plane avion) {
    	Boolean answer = false;
    	if(avion.getPosX() > currentLane.getPosXInitWait()){
    		answer = true;
    	}
		return answer;
	}
    
    private static Boolean lookWaitingPlaceLeft(Lane currentLane, Plane avion){
    	Boolean answer = false;
    	if(avion.getPosX() < currentLane.getPosXInitWait()){
    		answer = true;
    	}
    	return answer;
    }
    
    private static Boolean lookWaitingPlaceDown(Lane currentLane, Plane avion){
    	Boolean answer = false;
    	if(avion.getPosY() < currentLane.getPosYInitWait()){
    		answer = true;
    	}
    	return answer;
    }

}
