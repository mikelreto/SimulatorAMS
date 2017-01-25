package domain.monitor;

import domain.dao.DaoAirplane;
import domain.dao.DaoFlight;
import domain.dao.DaoLane;
import domain.model.Flight;
import domain.model.FlightStatus;
import domain.model.Lane;
import domain.model.Plane;
import domain.model.SimulatorLane;
import main.Main;

/**
 * The Class Monitor.
 */
public class Monitor {

	/** The Constant INITPOS. */
    public static final int INITPOS = 80;

    /** The Constant ONESECOND. */
    private static final int ONESECOND = 300;

    /** The Constant AIRPORTSPEED. */
    private static final int AIRPORTSPEED = 40;

    /** The Constant SPEEDCHANGE. */
    private static final int SPEEDCHANGE = 5;

    /** The Constant DISTANCIAREAL. */
    private static final int DISTANCIAREAL = 100000;

    /** The Constant DESPEGUE. */
    private static final int DESPEGUE = 0;

    /** The Constant LEFT. */
    private static final int LEFT = 180;

    /** The Constant RIGHT. */
    private static final int RIGHT = 0;

    /** The Constant DOWN. */
    private static final int DOWN = 270;

    /** The Constant YES. */
    private static final String YES = "Y";

    /** The Constant NO. */
    private static final String NO = "N";

    /** The plane dao. */
    private static DaoAirplane planeDao = new DaoAirplane();

    /** The lane dao. */
    private static DaoLane laneDao = new DaoLane();

    /** The token ya cogido. */
    private static Boolean tokenYaCogido = false;

    /**
     * Enter pista.
     *
     * @param nextLineId the next line id
     * @param avion the avion
     */
    public static void enterPista(final int nextLineId, Plane avion) {
    	if (nextLineId == DESPEGUE) {
    		moverEnPistaDeDespegue(avion);
    	} else {
    		moveInLanes(avion, nextLineId);
    	}
   }

    /**
     * Mover en pista de despegue.
     *
     * @param avion the avion
     */
    private static void moverEnPistaDeDespegue(Plane avion) {
    	move(avion.getLane(), avion);
		waitThread();
		finalPista(avion);
    }

    /**
     * Final pista.
     *
     * @param avion the avion
     */
    private static void finalPista(Plane avion) {
		if (avion.getLane() != null) {
			if (avion.getLane().getLaneType().getIdLaneType()
					                == GestorPistas.DESPEGUE) {
				avion.setSpeed(avion.getSpeed() + SPEEDCHANGE);
				if (avion.getPosX() >= avion.getLane().getPosXFinal()) {
					avion.setPosX(INITPOS);
					avion.setPosY(INITPOS);
			//avion.getFlights().get(0).getFlightStatus().setIdStatus(6);
					avion.getFlights().get(0).getFlightStatus().
					                          setDescription("Flying");
					DaoFlight.updateFlight(avion.getFlights().get(0));
					planeDao.updatePlane(avion);
					setCurrentLine(avion.getLane(), avion);
				} else {
		//avion.getFlights().get(0).getFlightStatus().setIdStatus(5);
					avion.getFlights().get(0).getFlightStatus().
					                          setDescription("Takeoff");
				}
			}
		}
    }

    /**
     * Terminar vuelo.
     *
     * @param avion the avion
     * @return the plane
     */
    private static Plane terminarVuelo(Plane avion) {
    	//Date ahora = new Date();
    	//avion.getFlights().set(0, setFlightFinishData(avion.getFlights().get(0)));
    	//DaoFlight.updateFlight(avion.getFlights().get(0));
    	//avion.getFlights().set(0, (Flight) DaoFlight.getNewFlight(avion.getIdPlane()));
    	/*
    	while(avion.getFlights().get(0) == null
    	      || avion.getFlights().get(0).getTimeFrom().compareTo(ahora) == 1){
    		if(avion.getFlights().get(0) == null){
    			avion.getFlights().set(0,
    			      (Flight) DaoFlight.getNewFlight(avion.getIdPlane()));
        	}
    		ahora = new Date();
    	}*/
		return avion;
	}


	/**
	 * Sets the flight finish data.
	 *
	 * @param flight the flight
	 * @return the flight
	 */
	private static Flight setFlightFinishData(Flight flight) {
		FlightStatus nuevoStatus = new FlightStatus();
//		try {
			//nuevoStatus.setIdStatus(4);
			nuevoStatus.setDescription("Embark");
			//Thread.sleep(5000);
			//nuevoStatus.setIdStatus(7);
			nuevoStatus.setDescription("Done");
			//Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		/*SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date horaLlegada = null;
		Date horaLlegadaAprox = null;
		try {
			horaLlegada = format.parse(new Date().toString());
			horaLlegadaAprox = format.parse(flight.getTimeTo().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int delay = horaLlegadaAprox.compareTo(horaLlegada);
		int tiempoDelay = 0;
		long diffInMillies = horaLlegada.getTime() - horaLlegadaAprox.getTime();
		long diffMinutes = diffInMillies / (60 * 1000);
	    //llegada aprox 12:15:00*/
		flight.setFlightStatus(nuevoStatus);
		return flight;
	}

	/**
	 * Move in lanes.
	 *
	 * @param avion the avion
	 * @param nextLineId the next line id
	 * @return the plane
	 */
	private static Plane moveInLanes(Plane avion, final int nextLineId) {
    	Lane nextLane = getLaneFromId(nextLineId);
    	Lane currentLane = avion.getLane();
    	if (avion.getLane() != null) {
    		avion.getFlights().get(0).getFlightStatus().setIdStatus(2);
			avion.getFlights().get(0).getFlightStatus().setDescription("Landed");
			//while(avion.getLane().getLaneType().getIdLaneType()
			                    //== GestorPistas.PISTATERMINAL){
				//avion = terminarVuelo(avion);
			//}
		} else {
			avion.getFlights().get(0).getFlightStatus().setIdStatus(1);
			avion.getFlights().get(0).getFlightStatus().setDescription("Landing");
		}
    	planeDao.updatePlane(avion);
		while (nextLane.getTaken().equals(YES)) {
    		avion = avanceMientrasLaPistaEstaOcupada(avion, currentLane, nextLane);
    	}
		comprobacionToken(nextLineId);
		avion = avanceSiNoEsAterrizaje(currentLane, avion);
		avion = setNuevosValoresAvionLane(avion, currentLane, nextLane);
	    return avion;
	}

	/**
	 * Sets the nuevos valores avion lane.
	 *
	 * @param avion the avion
	 * @param currentLane the current lane
	 * @param nextLane the next lane
	 * @return the plane
	 */
	private static Plane setNuevosValoresAvionLane(Plane avion,
			                                       Lane currentLane,
			                                       Lane nextLane) {
    	setTaken(nextLane.getIdLane(), YES);
	    avion = setPlaneInNewLane(avion, nextLane);
	    if (currentLane != null) {
	    	 setCurrentLine(currentLane, avion);
	    	 System.out.println("El avion " + avion.getIdPlane()
	                            + " se ha movido de la pista "
	    			            + currentLane.getIdLane() + " a "
	                            + nextLane.getIdLane());
		}
	    return avion;
	}

	/**
	 * Sets the current line.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 */
	private static void setCurrentLine(final Lane currentLane, final Plane avion) {
		setTaken(currentLane.getIdLane(), NO);
		giveBackToken(currentLane.getIdLane());
	}

	/**
	 * Avance si no es aterrizaje.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane avanceSiNoEsAterrizaje(Lane currentLane, Plane avion) {
    	if (currentLane != null) {
    		avion = avanceEnPista(avion);
    	}
    	return avion;
	}

	/**
	 * Comprobacion token.
	 *
	 * @param nextLineId the next line id
	 */
	private static void comprobacionToken(final int nextLineId) {
    	if (!tokenYaCogido) {
			takeToken(nextLineId);
		}
	}

	/**
	 * Avance mientras la pista esta ocupada.
	 *
	 * @param avion the avion
	 * @param currentLane the current lane
	 * @param nextLane the next lane
	 * @return the plane
	 */
	private static Plane avanceMientrasLaPistaEstaOcupada(Plane avion,
			                                              Lane currentLane,
			                                              Lane nextLane) {
		if (currentLane != null) {
    		if (inTheWaitingArea(currentLane, avion)) {
    		   //avion.getFlights().get(0).getFlightStatus().setIdStatus(8);
    		   //avion.getFlights().get(0).getFlightStatus().setDescription("Waiting");
          	   avion = avanceEnWaitingArea(avion);
          	   planeDao.updatePlane(avion);
          	   takeToken(nextLane.getIdLane());
          	   tokenYaCogido = true;
            } else {
          	   avion = seeSpeed(avion);
          	   avion = move(avion.getLane(), avion);
            }
		} else {
			System.out.println("Deteniedo avion " + avion.getIdPlane()
			                   +  "mientras intenta aterrizar");
			//avion.getFlights().get(0).getFlightStatus().setIdStatus(8);
 		    //avion.getFlights().get(0).getFlightStatus().setDescription("Waiting");
 		    planeDao.updatePlane(avion);
			takeToken(nextLane.getIdLane());
			tokenYaCogido = true;
		}
		waitThread();
		nextLane = getLaneFromId(nextLane.getIdLane());
		return avion;
    }

	/**
	 * Avance en pista.
	 *
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane avanceEnPista(Plane avion) {
    	while (!inTheWaitingArea(avion.getLane(), avion)) {
    		System.out.println("Avion " + avion.getIdPlane()
    		                   + " en pista " + avion.getLane().getIdLane());
    		avion = seeSpeed(avion);
            move(avion.getLane(), avion);
            waitThread();
    	}
    	avion = avanceEnWaitingArea(avion);
    	return avion;
	}

	/**
	 * Wait thread.
	 */
	public static void waitThread() {
    	 try {
      	   Thread.sleep(ONESECOND);
         } catch (InterruptedException ex) {
	           Thread.currentThread().interrupt();
         }
    }

    /**
     * Give back token.
     *
     * @param id the id
     */
    private static void giveBackToken(final Integer id) {
    	for (SimulatorLane i:Main.getSimulatorList()) {
    		if (i.getLane().getIdLane() == id) {
    			i.getSemaforo().release();
    		}
    	}
	}

	/**
	 * Take token.
	 *
	 * @param id the id
	 */
	private static void takeToken(final int id) {
    	for (SimulatorLane i:Main.getSimulatorList()) {
    		if (i.getLane().getIdLane() == id) {
    			try {
					i.getSemaforo().acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
	}

	/**
	 * Sets the taken.
	 *
	 * @param id the id
	 * @param taken the taken
	 */
	private static void setTaken(final int id, final String taken) {
    	for (SimulatorLane i:Main.getSimulatorList()) {
    		if (i.getLane().getIdLane() == id) {
    			i.getLane().setTaken(taken);
    			laneDao.updateLane(i.getLane());
    		}
    	}
    }

    /**
     * Gets the lane from id.
     *
     * @param id the id
     * @return the lane from id
     */
    public static Lane getLaneFromId(final int id) {
    	Lane erantzuna = null;
    	for (SimulatorLane i:Main.getSimulatorList()) {
    		if (i.getLane().getIdLane() == id) {
    			erantzuna = i.getLane();
    		}
    	}
    	return erantzuna;
    }

    /**
     * Sets the plane in new lane.
     *
     * @param avion the avion
     * @param nextLine the next line
     * @return the plane
     */
    private static Plane setPlaneInNewLane(Plane avion, Lane nextLine) {
    	   avion.setAngle(getAngle(nextLine));
    	   avion.setPosX(nextLine.getPosXInitLane());
    	   avion.setPosY(nextLine.getPosYInitLane());
    	   avion.setLane(nextLine);
    	   planeDao.updatePlane(avion);
    	   return avion;
	}

	/**
	 * Gets the angle.
	 *
	 * @param nextLine the next line
	 * @return the angle
	 */
	private static Integer getAngle(final Lane nextLine) {
		Integer newAngle = 0;
		if (nextLine.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE
		   || nextLine.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
		        newAngle = RIGHT;
		} else if (nextLine.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT
		    	  || nextLine.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT
		    	  || nextLine.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL) {
		    	newAngle = DOWN;
	    } else  {
		    	newAngle = LEFT;
		    	}
		return newAngle;
	}

	/**
	 * Move.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane move(Lane currentLane, Plane avion) {
    	if (currentLane.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE
    	   || currentLane.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		avion = moveRight(avion);
    	} else if (currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT
    	   || currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT
    	   || currentLane.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL) {
    		avion = moveDown(avion);
    	} else  {
    		avion = moveLeft(avion);
    	}
    	return avion;
	}

	/**
	 * Move down.
	 *
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane moveDown(Plane avion) {
		float newSpeed = (float) avion.getSpeed() / DISTANCIAREAL;
		float newPosY = (float) (avion.getPosY() - newSpeed);
		avion.setPosY(newPosY);
		planeDao.updatePlane(avion);
		return avion;
	}

	/**
	 * Move left.
	 *
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane moveLeft(Plane avion) {
		float newSpeed = (float) avion.getSpeed() / DISTANCIAREAL;
		float newPosX = (float) (avion.getPosX() - newSpeed);
		avion.setPosX(newPosX);
		planeDao.updatePlane(avion);
		return avion;
	}

	/**
	 * Move right.
	 *
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane moveRight(Plane avion) {
		float newSpeed = (float) avion.getSpeed() / DISTANCIAREAL;
		float newPosX = (float) (avion.getPosX() + newSpeed);
		avion.setPosX(newPosX);
		planeDao.updatePlane(avion);
		return avion;
	}

	/**
	 * See speed.
	 *
	 * @param avion the avion
	 * @return the plane
	 */
	private static Plane seeSpeed(Plane avion) {
    	if (avion.getSpeed() > AIRPORTSPEED) {
    		avion.setSpeed(avion.getSpeed() - SPEEDCHANGE);
    	} else {
    		avion.setSpeed(avion.getSpeed() + SPEEDCHANGE);
    	}
    	planeDao.updatePlane(avion);
    	return avion;
    }

	/**
	 * In the waiting area.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 * @return the boolean
	 */
	private static Boolean inTheWaitingArea(final Lane currentLane, final Plane avion) {
    	Boolean answer = false;
    	if (currentLane.getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE
    	   || currentLane.getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		answer = lookWaitingPlaceRight(currentLane, avion);
    	} else if (currentLane.getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL
    			  || currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT
    			  || currentLane.getLaneType().getIdLaneType() == GestorPistas.CURVELEFT) {
    		answer = lookWaitingPlaceDown(currentLane, avion);
    	} else {
    		answer = lookWaitingPlaceLeft(currentLane, avion);
    	}
    	return answer;
    }

    /**
     * Look waiting place right.
     *
     * @param currentLane the current lane
     * @param avion the avion
     * @return the boolean
     */
    private static Boolean lookWaitingPlaceRight(final Lane currentLane, final Plane avion) {
    	Boolean answer = false;
    	if (avion.getPosX() > currentLane.getPosXInitWait()) {
    		answer = true;
    	}
		return answer;
	}

    /**
     * Look waiting place left.
     *
     * @param currentLane the current lane
     * @param avion the avion
     * @return the boolean
     */
    private static Boolean lookWaitingPlaceLeft(final Lane currentLane, final Plane avion) {
    	Boolean answer = false;
    	if (avion.getPosX() < currentLane.getPosXInitWait()) {
    		answer = true;
    	}
    	return answer;
    }

    /**
     * Look waiting place down.
     *
     * @param currentLane the current lane
     * @param avion the avion
     * @return the boolean
     */
    private static Boolean lookWaitingPlaceDown(final Lane currentLane, final Plane avion) {
    	Boolean answer = false;
    	if (avion.getPosY() < currentLane.getPosYInitWait()) {
    		answer = true;
    	}
    	return answer;
    }

    /**
     * Avance en waiting area.
     *
     * @param avion the avion
     * @return the plane
     */
    private static Plane avanceEnWaitingArea(Plane avion) {
		while (!inTheFinalPosOfLane(avion)) {
			avion = move(avion.getLane(), avion);
			waitThread();
		}
		System.out.println("El avion" + avion.getIdPlane()
		        + " esta en el final de la pista " + avion.getLane().getIdLane());
		return avion;
	}

	/**
	 * In the final pos of lane.
	 *
	 * @param avion the avion
	 * @return true, if successful
	 */
	private static boolean inTheFinalPosOfLane(final Plane avion) {
		Boolean answer = false;
    	if (avion.getLane().getLaneType().getIdLaneType() == GestorPistas.ATERRIZAJE
    	   || avion.getLane().getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
    		answer = lookFinalPlaceRight(avion.getLane(), avion);

    	} else if (avion.getLane().getLaneType().getIdLaneType() == GestorPistas.PISTATERMINAL
    			 || avion.getLane().getLaneType().getIdLaneType() == GestorPistas.CURVERIGHT
    		     || avion.getLane().getLaneType().getIdLaneType() == GestorPistas.CURVELEFT) {
    		answer = lookFinalPlaceDown(avion.getLane(), avion);
    	} else {
    		answer = lookFinalPlaceLeft(avion.getLane(), avion);
    	}
    	return answer;
	}

	/**
	 * Look final place left.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 * @return the boolean
	 */
	private static Boolean lookFinalPlaceLeft(final Lane currentLane, final Plane avion) {
		Boolean answer = false;
    	if (avion.getPosX() < currentLane.getPosXFinal()) {
    		answer = true;
    	}
    	return answer;
	}

	/**
	 * Look final place down.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 * @return the boolean
	 */
	private static Boolean lookFinalPlaceDown(final Lane currentLane, final Plane avion) {
		Boolean answer = false;
    	if (avion.getPosY() < currentLane.getPosYFinal()) {
    		answer = true;
    	}
    	return answer;
	}

	/**
	 * Look final place right.
	 *
	 * @param currentLane the current lane
	 * @param avion the avion
	 * @return the boolean
	 */
	private static Boolean lookFinalPlaceRight(final Lane currentLane, final Plane avion) {
		Boolean answer = false;
    	if (avion.getPosX() > currentLane.getPosXFinal()) {
    		answer = true;
    	}
		return answer;
	}
}