package domain.monitor;

import domain.dao.DaoAirplane;
import domain.dao.DaoLane;
import domain.model.Lane;
import domain.model.Plane;
import domain.model.SimulatorLane;
import main.Main;

public class Monitor {
	
    private static final int ONESECOND = 1000;
    private static final int AIRPORTSPEED = 50;
    private static final int SPEEDCHANGE = 5;
    private static final int DISTANCIAREAL= 100000;
    private static final int DESPEGUE = 0;
    private static final int LEFT = 180;
    private static final int RIGHT = 0;
    private static final int DOWN = 270;
    private static DaoAirplane planeDao = new DaoAirplane();
    private static DaoLane laneDao = new DaoLane();
    private static Boolean tokenYaCogido = false;

    public static String enterPista(final int nextLineId, Plane avion){    	
    	if(nextLineId != DESPEGUE){
    		moveInLanes(avion, nextLineId);
    	} else {
    		move(avion.getLane(), avion);
    	}	
    	return "update success";
   }
    
    private static Plane moveInLanes(Plane avion, final int nextLineId) {
    	Lane nextLane = getLaneFromId(nextLineId);
    	Lane currentLane = avion.getLane();
		while (nextLane.getTaken().equals("Y")){
    		avion = avanceMientrasLaPistaEstaOcupada(avion, currentLane, nextLane);
    	}
		comprobacionToken(nextLineId);
		avion = avanceSiNoEsAterrizaje(currentLane, avion);
		avion = setNuevosValoresAvionLane(avion, currentLane, nextLane);
	    return avion;
	}
    
    private static Plane setNuevosValoresAvionLane(Plane avion, Lane currentLane, Lane nextLane) {
    	setTaken(nextLane.getIdLane(), "Y");
	    avion = setPlaneInNewLane(avion, nextLane);
	    if(currentLane != null){
		   setTaken(currentLane.getIdLane(), "N");
		   System.out.println("Devolviendo token con avion" + avion.getIdPlane() + "en pista "+ currentLane.getIdLane());
		   giveBackToken(currentLane.getIdLane());
		   System.out.println("El avion "+ avion.getIdPlane() + " se ha movido de la pista "+ currentLane.getIdLane() +" a " + nextLane.getIdLane());
	    }
	    return avion;
	}

	private static Plane avanceSiNoEsAterrizaje(Lane currentLane, Plane avion) {
    	if(currentLane != null){
    		avion = avanceEnPista(avion);
    	}
    	return avion;
	}

	private static void comprobacionToken(int nextLineId) {
    	if(tokenYaCogido == false){
			takeToken(nextLineId);
		}
	}

	private static Plane avanceMientrasLaPistaEstaOcupada(Plane avion, Lane currentLane, Lane nextLane){
		if(currentLane != null){
    		if (inTheWaitingArea(currentLane, avion)) {
          	   System.out.println("Deteniedo avion " + avion.getIdPlane() + " en pista "+ avion.getLane().getIdLane());
          	   takeToken(nextLane.getIdLane());
          	   tokenYaCogido = true;
            } else {
          	   avion = seeSpeed(avion);
          	   avion = move(avion.getLane(), avion);
            }
		} else {
			System.out.println("Deteniedo avion " + avion.getIdPlane() +  "mientras intenta aterrizar");
			takeToken(nextLane.getIdLane());
			tokenYaCogido = true;
		}
		waitThread();
		nextLane = getLaneFromId(nextLane.getIdLane());
		return avion;
    }

	private static Plane avanceEnPista(Plane avion) {
    	while (!inTheWaitingArea(avion.getLane(), avion)) {
    		System.out.println("Avion "+ avion.getIdPlane() + " en pista "+ avion.getLane().getIdLane());
    		avion = seeSpeed(avion);
            move(avion.getLane(), avion);
            waitThread();
    	}
    	return avion;
	}

	private static void waitThread(){
    	 try {
      	   Thread.sleep(ONESECOND);
         } catch (InterruptedException ex) {
	           Thread.currentThread().interrupt();
         }
    }
    
    private static void giveBackToken(Integer id) {
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			i.getSemaforo().release();
    		}
    	}	
	}

	private static void takeToken(int id) {
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			try {
					i.getSemaforo().acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
	}

	private static void setTaken(int id, String taken){
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			i.getLane().setTaken(taken);
    			laneDao.updateLane(i.getLane());
    		}
    	}
    }
    
    public static Lane getLaneFromId(int id){
    	Lane erantzuna = null;
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			erantzuna = i.getLane();
    		}
    	}
    	return erantzuna;
    }
    
    private static Plane setPlaneInNewLane(Plane avion, Lane nextLine) {
    	   avion.setAngle(getAngle(nextLine));
    	   avion.setPosX(nextLine.getPosXInitLane());
    	   avion.setPosY(nextLine.getPosYInitLane());
    	   avion.setLane(nextLine);
    	   planeDao.updatePlane(avion);
    	   return avion;
	}

	private static Integer getAngle(Lane nextLine) {
		Integer newAngle = 0;
		if(nextLine.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE ||
		   nextLine.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
		        newAngle = RIGHT;
		    	    		
		}else if (nextLine.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT ||
		    	  nextLine.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT ||
		    	  nextLine.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL){
		    	newAngle = DOWN;
		    		
	    }else  {
		    	newAngle = LEFT;   		
		    	}
		return newAngle;
	}

	private static Plane move(Lane currentLane, Plane avion) {
    	if(currentLane.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE ||
    	   currentLane.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		avion = moveRight(avion);
    	    		
    	}else if (currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT ||
    	   currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT ||
    	   currentLane.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL){
    		avion = moveDown(avion);
    		
    	}else  {
    		avion = moveLeft(avion);   		
    	}
    	return avion;
	}

	private static Plane moveDown(Plane avion) {
		float newSpeed = (float)avion.getSpeed()/DISTANCIAREAL;
		float newPosY = (float) (avion.getPosY() - newSpeed);
		avion.setPosY(newPosY);
		planeDao.updatePlane(avion);
		return avion;
		
	}

	private static Plane moveLeft(Plane avion) {
		float newSpeed = (float)avion.getSpeed()/DISTANCIAREAL;
		float newPosX = (float) (avion.getPosX() - newSpeed);
		avion.setPosX(newPosX);
		planeDao.updatePlane(avion);
		return avion;
	}

	private static Plane moveRight(Plane avion) {
		float newSpeed = (float)avion.getSpeed()/DISTANCIAREAL;
		float newPosX = (float) (avion.getPosX() + newSpeed);
		avion.setPosX(newPosX);
		planeDao.updatePlane(avion);
		return avion;
	}

	private static Plane seeSpeed (Plane avion){
    	if(avion.getSpeed() > AIRPORTSPEED){
    		avion.setSpeed(avion.getSpeed() - SPEEDCHANGE);
    	}else {
    		avion.setSpeed(avion.getSpeed() + SPEEDCHANGE);
    	}
    	planeDao.updatePlane(avion);
    	return avion;
    }
   

	private static Boolean inTheWaitingArea(Lane currentLane, Plane avion){
    	Boolean answer = false;
    	if(currentLane.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE ||
    	   currentLane.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		answer = lookWaitingPlaceRight(currentLane, avion);
    		
    	}else if (currentLane.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL ||
    			  currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT ||
    			  currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT){
    		answer = lookWaitingPlaceDown(currentLane, avion);
    	} else {
    		answer = lookWaitingPlaceLeft(currentLane, avion);
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
