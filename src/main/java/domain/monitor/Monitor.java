package domain.monitor;

import domain.dao.DaoAirplane;
import domain.dao.DaoLane;
import domain.model.Lane;
import domain.model.Plane;
import domain.model.SimulatorLane;
import main.Main;


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
    private static final int SPEEDCHANGE = 5;
    private static final int DISTANCIAREAL= 100000;
    private static DaoAirplane planeDao = new DaoAirplane();
    private static DaoLane laneDao = new DaoLane();

    /**
     * Function to enter to a new Line.
     * @param currentLine is the actual line.
     * @param nextLine is the next line.
     * @param vuelo is the flight that needs to see the lines.
     * @throws InterruptedException if there is any problem.
     */
    public static void enterPista(final int nextLineId,
                                        Plane avion)
                                        throws InterruptedException {
    	
    	Lane nextLane = getLaneFromId(nextLineId);
    	Lane currentLane = avion.getLane();
    	System.out.println("Dentro de monitor");
    	System.out.println("Siguiente pista esta " + nextLane.getTaken());
    	while (nextLane.getTaken().equals("N")){
    		System.out.println("Dentro del do while");
    		if(currentLane != null){
	    		if (inTheWaitingArea(currentLane, avion)) {
	          	   System.out.println("Deteniedo avion" + avion.getIdPlane() + "en pista "+ avion.getLane().getIdLane());
	          	   takeToken(nextLineId);
	            } else {
	          	   avion = seeSpeed(avion);
	          	   System.out.println(avion.getPosX()+"--"+ avion.getPosY());
	          	   avion = move(avion.getLane(), avion);
	          	   System.out.println("Moviendo avion" + avion.getIdPlane() + "en pista" + avion.getLane().getIdLane());
	          	   System.out.println(avion.getPosX()+"--"+ avion.getPosY());
	            }
    		} else {
    			takeToken(nextLineId);
    		}
    		//waitThread();
    		nextLane = getLaneFromId(nextLineId);
    	}
    	System.out.println("La siguiente pista esta libre");
    	if(currentLane != null){
    		avion = avanceEnPista(avion);
    	}
	   setTaken(nextLineId, "Y");
	   avion = setPlaneInNewLane(avion, nextLane);
	   if(currentLane != null){
		   setTaken(currentLane.getIdLane(), "N");
		   System.out.println("Devolviendo token con avion" + avion.getIdPlane() + "en pista "+ currentLane.getIdLane());
		   giveBackToken(currentLane.getIdLane());
		   System.out.println("El avion "+ avion.getIdPlane() + "se ha movido de "+ currentLane.getIdLane() +" a " + nextLane.getIdLane());
	   }
   }
    
    private static Plane avanceEnPista(Plane avion) {
    	System.out.println("Dentro de avancePista");
    	while (!inTheWaitingArea(avion.getLane(), avion)) {
    		avion = seeSpeed(avion);
            move(avion.getLane(), avion);
            System.out.println("Moviendo avion" + avion.getIdPlane() + "en pista" + avion.getLane().getIdLane());
            //waitThread();
    	}
    	return avion;
	}

	private static void waitThread(){
    	 try {
    	   System.out.println("Parando thread un segundo");
      	   Thread.sleep(ONESECOND);
         } catch (InterruptedException ex) {
	           Thread.currentThread().interrupt();
         }
    }
    
    private static void giveBackToken(Integer id) {
		System.out.println("En funcion giveBackToken");
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			i.getSemaforo().release();
    		}
    	}	
	}

	private static void takeToken(int id) {
		System.out.println("En funcion takeToken");
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
		System.out.println("En funcion setTaken");
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			i.getLane().setTaken(taken);
    			laneDao.updateLane(i.getLane());
    		}
    	}
    }
    
    private static Lane getLaneFromId(int id){
    	Lane erantzuna = null;
    	System.out.println("En funcion geLaneFromId");
    	for(SimulatorLane i:Main.getSimulatorList()){
    		if(i.getLane().getIdLane() == id){
    			erantzuna = i.getLane();
    		}
    	}
    	return erantzuna;
    }
    
    private static Plane setPlaneInNewLane(Plane avion, Lane nextLine) {
    	System.out.println("En funcion setPlaneInNewLane");
    	   avion.setPosX(nextLine.getPosXInitLane());
    	   avion.setPosY(nextLine.getPosYInitLane());
    	   avion.setLane(nextLine);
    	   planeDao.updatePlane(avion);
    	   return avion;
	}

	private static Plane move(Lane currentLane, Plane avion) {
		System.out.println("En funcion move");
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
		System.out.println("En funcion moveDown");
		System.out.println("La velocidad actual es de " + avion.getSpeed());
		System.out.println("La nueva posicion Y es de " + newPosY);
		System.out.println(avion.getPosY() +"-" + newSpeed);
		avion.setPosY(newPosY);
		System.out.println("Nuevas posiciones "+ avion.getPosX() +"-"+ avion.getPosY());
		planeDao.updatePlane(avion);
		return avion;
		
	}

	private static Plane moveLeft(Plane avion) {
		float newSpeed = (float)avion.getSpeed()/DISTANCIAREAL;
		float newPosX = (float) (avion.getPosX() + newSpeed);
		System.out.println("En funcion moveLeft");
		System.out.println("La velocidad actual es de " + avion.getSpeed());
		System.out.println("La nueva posicion X es de " + newPosX);
		System.out.println(avion.getPosX() +"-" + newSpeed);
		avion.setPosX(newPosX);
		planeDao.updatePlane(avion);
		System.out.println("Nuevas posiciones "+ avion.getPosX() +"-"+ avion.getPosY());
		return avion;
	}

	private static Plane moveRight(Plane avion) {
		float newSpeed = (float)avion.getSpeed()/DISTANCIAREAL;
		float newPosX = (float) (avion.getPosX() + newSpeed);
		System.out.println("En funcion moveRight");
		System.out.println("La velocidad actual es de " + avion.getSpeed());
		System.out.println("La nueva posicion X es de " + newPosX);
		System.out.println(avion.getPosX() +"-" + newSpeed);
		avion.setPosX(newPosX);
		planeDao.updatePlane(avion);
		System.out.println("Nuevas posiciones "+ avion.getPosX() +"-"+ avion.getPosY());
		return avion;
	}

	private static Plane seeSpeed (Plane avion){
		System.out.println("En funcion seeSpeed");
    	if(avion.getSpeed() > AIRPORTSPEED){
    		avion.setSpeed(avion.getSpeed() - SPEEDCHANGE);
    	}else {
    		avion.setSpeed(avion.getSpeed() + SPEEDCHANGE);
    	}
    	planeDao.updatePlane(avion);
    	return avion;
    }
   

	private static Boolean inTheWaitingArea(Lane currentLane, Plane avion){
		System.out.println("En funcion inTheWaitingArea en lane " + currentLane.getIdLane() + "En posicion: "+avion.getPosX()+ "-" + avion.getPosY());
    	Boolean answer = false;
    	if(currentLane.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE ||
    	   currentLane.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		System.out.println("Viendo si esta en area de espera"+ currentLane.getIdLane());
    		answer = lookWaitingPlaceRight(currentLane, avion);
    		
    	}else if (currentLane.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL ||
    			  currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT ||
    			  currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT){
    		System.out.println("Viendo si esta en area de espera"+ currentLane.getIdLane());
    		answer = lookWaitingPlaceDown(currentLane, avion);
    	} else {
    		System.out.println("Viendo si esta en area de espera"+ currentLane.getIdLane());
    		answer = lookWaitingPlaceLeft(currentLane, avion);
    	}
    	System.out.println("final de inthewaitingarea ////RESPUESTA = " + answer);
    	return answer;
    }

    private static Boolean lookWaitingPlaceRight(Lane currentLane, Plane avion) {
    	System.out.println("En funcion lookWaitingPlaceRight");
    	Boolean answer = false;
    	if(avion.getPosX() > currentLane.getPosXInitWait()){
    		System.out.println("En zona de espera derecha" + currentLane.getIdLane());
    		answer = true;
    	}
		return answer;
	}
    
    private static Boolean lookWaitingPlaceLeft(Lane currentLane, Plane avion){
    	System.out.println("En funcion lookWaitingPlaceLeft");
    	Boolean answer = false;
    	if(avion.getPosX() < currentLane.getPosXInitWait()){
    		System.out.println("En zona de espera izquierda" + currentLane.getIdLane());
    		answer = true;
    	}
    	return answer;
    }
    
    private static Boolean lookWaitingPlaceDown(Lane currentLane, Plane avion){
    	System.out.println("En funcion lookWaitingPlaceDown");
    	Boolean answer = false;
    	if(avion.getPosY() < currentLane.getPosYInitWait()){
    		System.out.println("En zona de espera abajo" + currentLane.getIdLane());
    		answer = true;
    	}
    	return answer;
    }

}
