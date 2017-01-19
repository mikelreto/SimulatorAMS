package domain.monitor;

import java.util.Date;

import domain.dao.DaoAirplane;
import domain.dao.DaoFlight;
import domain.dao.DaoLane;
import domain.model.Flight;
import domain.model.FlightStatus;
import domain.model.Lane;
import domain.model.Plane;
import domain.model.SimulatorLane;
import main.Main;

public class Monitor {
	
    private static final int ONESECOND = 300;
    private static final int AIRPORTSPEED = 40;
    private static final int SPEEDCHANGE = 5;
    private static final int DISTANCIAREAL= 100000;
    private static final int DESPEGUE = 0;
    private static final int LEFT = 180;
    private static final int RIGHT = 0;
    private static final int DOWN = 270;
    private static final String YES = "Y";
    private static final String NO = "N";
    private static DaoAirplane planeDao = new DaoAirplane();
    private static DaoLane laneDao = new DaoLane();
    private static Boolean tokenYaCogido = false;

    public static String enterPista(final int nextLineId, Plane avion){    	
    	if(nextLineId == DESPEGUE){
    		move(avion.getLane(), avion);
    		finalPista(avion);
    	} else {
    		moveInLanes(avion, nextLineId);
    	}	
    	return "update success";
   }
    
    private static void finalPista(Plane avion){
		if(avion.getLane() != null){
			if(avion.getLane().getLaneType().getIdLaneType() == GestorPistas.DESPEGUE){
				System.out.println("El final de la pista de aterrizaje es " + avion.getLane().getPosXFinal());
				if(avion.getPosX() >= avion.getLane().getPosXFinal()){
					setCurrentLine(avion.getLane(), avion);
				}
			}
		}
    }
    
    private static Plane terminarVuelo(Plane avion) {
    	Date ahora = new Date();
    	avion.getFlights().set(0, setFlightFinishData(avion.getFlights().get(0)));
    	DaoFlight.updateFlight(avion.getFlights().get(0));
    	avion.getFlights().set(0, (Flight) DaoFlight.getNewFlight(avion.getIdPlane()));
    	
    	while(avion.getFlights().get(0) == null || avion.getFlights().get(0).getTimeFrom().compareTo(ahora) == 1){
    		if(avion.getFlights().get(0) == null){
    			avion.getFlights().set(0, (Flight) DaoFlight.getNewFlight(avion.getIdPlane()));
        	}
    		ahora = new Date();
    	}
		return avion;
	}
    

	private static Flight setFlightFinishData(Flight flight) {
		FlightStatus nuevoStatus = new FlightStatus();
		/*SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date horaLlegada = null;
		Date horaLlegadaAprox = null;
		try {
			horaLlegada = format.parse(new Date().toString());
			horaLlegadaAprox = format.parse(flight.getTimeTo().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int delay = horaLlegadaAprox.compareTo(horaLlegada);
		int tiempoDelay = 0;
		long diffInMillies = horaLlegada.getTime() - horaLlegadaAprox.getTime();
		long diffMinutes = diffInMillies / (60 * 1000); 
		System.out.println("Kakafuti--------------------------------"+diffMinutes+" minutos");
	    //llegada aprox 12:15:00*/
	    nuevoStatus.setIdStatus(5);
		nuevoStatus.setDescription("arrive");
		flight.setFlightStatus(nuevoStatus);
		return flight;
	}

	private static Plane moveInLanes(Plane avion, final int nextLineId) {
    	Lane nextLane = getLaneFromId(nextLineId);
    	Lane currentLane = avion.getLane();
    	if(avion.getLane() != null){
			/*while(avion.getLane().getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL){
				avion = terminarVuelo(avion);
			}*/
		}
		while (nextLane.getTaken().equals(YES)){
    		avion = avanceMientrasLaPistaEstaOcupada(avion, currentLane, nextLane);
    	}
		comprobacionToken(nextLineId);
		avion = avanceSiNoEsAterrizaje(currentLane, avion);
		avion = setNuevosValoresAvionLane(avion, currentLane, nextLane);
	    return avion;
	}

	private static Plane setNuevosValoresAvionLane(Plane avion, Lane currentLane, Lane nextLane) {
    	setTaken(nextLane.getIdLane(), YES);
	    avion = setPlaneInNewLane(avion, nextLane);
	    if(currentLane != null){
	    	 setCurrentLine(currentLane, avion);
	    	 System.out.println("El avion "+ avion.getIdPlane() + " se ha movido de la pista "+ currentLane.getIdLane() +" a " + nextLane.getIdLane());
		}
	    return avion;
	}

	private static void setCurrentLine(Lane currentLane, Plane avion) {
		setTaken(currentLane.getIdLane(), NO);
		System.out.println("Devolviendo token con avion" + avion.getIdPlane() + "en pista "+ currentLane.getIdLane());
		giveBackToken(currentLane.getIdLane());
		  
	   
		
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
          	   avion = avanceEnWaitingArea(avion);
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
    	avion = avanceEnWaitingArea(avion);
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
    
    private static Plane avanceEnWaitingArea(Plane avion) {
		while(!inTheFinalPosOfLane(avion)){
			avion = move(avion.getLane(), avion);
			waitThread();
		}
		System.out.println("El avion" + avion.getIdPlane() + " esta en el final de la pista "+ avion.getLane().getIdLane());
		return avion;
	}

	private static boolean inTheFinalPosOfLane(Plane avion) {
		Boolean answer = false;
    	if(avion.getLane().getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE ||
    	   avion.getLane().getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		answer = lookFinalPlaceRight(avion.getLane(), avion);
    		
    	}else if (avion.getLane().getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL ||
    			  avion.getLane().getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT ||
    		      avion.getLane().getLaneType().getIdLaneType() == GestorPistas.CURVELEFT){
    		answer = lookFinalPlaceDown(avion.getLane(), avion);
    	} else {
    		answer = lookFinalPlaceLeft(avion.getLane(), avion);
    	}
    	return answer;
	}

	private static Boolean lookFinalPlaceLeft(Lane currentLane, Plane avion) {
		Boolean answer = false;
    	if(avion.getPosX() < currentLane.getPosXFinal()){
    		answer = true;
    	}
    	return answer;
	}

	private static Boolean lookFinalPlaceDown(Lane currentLane, Plane avion) {
		Boolean answer = false;
    	if(avion.getPosY() < currentLane.getPosYFinal()){
    		answer = true;
    	}
    	return answer;
	}

	private static Boolean lookFinalPlaceRight(Lane currentLane, Plane avion) {
		Boolean answer = false;
    	if(avion.getPosX() > currentLane.getPosXFinal()){
    		answer = true;
    	}
		return answer;
	}
}
