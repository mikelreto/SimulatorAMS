package main;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import domain.dao.DaoAirplane;
import domain.dao.DaoLane;
import domain.model.Plane;
import domain.model.SimulatorAirport;
import domain.model.SimulatorLane;
import domain.monitor.Monitor;
import domain.model.Lane;

/**
 * The Class Main.
 */
public class Main {
	
	/** The plane list. */
	private static List<Plane> planeList;
	
	/** The new plane list. */
	private static List<Plane> newPlaneList;
    
    /** The plane dao. */
    private static DaoAirplane planeDao;
    
    /** The lane dao. */
    private static DaoLane laneDao;
    
    /** The lane list. */
    private static List<Lane> laneList;
    
    /** The simulator list. */
    private static List<SimulatorLane> simulatorList;
    
    /** The aiport con semaforo. */

    private static SimulatorAirport aiportConSemaforo;
    
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		aiportConSemaforo = new SimulatorAirport();
		laneDao = new DaoLane();
		laneList = laneDao.loadLane();
		simulatorList = new ArrayList<SimulatorLane> ();
		addPlanesToSimulator();
		planeDao = new DaoAirplane();
		planeList = planeDao.loadPlane();
		initialize();
		laneList = laneDao.loadLane();
		planeList = planeDao.loadPlane();
		crearteThreads();
		//seguirMirando();
	}
	

	/**
	 * Seguir mirando.
	 */
	private static void seguirMirando() {
		Boolean on = true;
		while(on){
			newPlaneList = planeDao.loadPlane();
			comparador();
			Monitor.waitThread();
		}
	}
	


	/**
	 * Comparador.
	 */
	private static void comparador(){
		int kont = 0;
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for(int i = 0; i < planeList.size(); i++){
			for(int j = 0; j < newPlaneList.size(); j++){
				if(planeList.get(i).getIdPlane() != newPlaneList.get(j).getIdPlane()){
					kont++;
					if(kont == planeList.size()){
						executor.submit(newPlaneList.get(j));
					}
				}
			}
		}
		planeList = newPlaneList;
	}

    public static void startSimulatorList(){
    	simulatorList = new ArrayList<SimulatorLane> ();
    }

	/**
	 * Initialize.
	 */
	private static void initialize() {
		for(Plane i:Main.getPlaneList()){
			i.setPosX(80);
			i.setPosY(80);
			i.setLane(null);
			planeDao.updatePlane(i);
		}
		
		for(SimulatorLane i:Main.getSimulatorList()){
			i.getLane().setTaken("N");
			laneDao.updateLane(i.getLane());
		}
	}


	/**
	 * Gets the plane list.
	 *
	 * @return the plane list
	 */
	public static List<Plane> getPlaneList() {
		return planeList;
	}

	/**
	 * Sets the plane list.
	 *
	 * @param planeList the new plane list
	 */
	public static void setPlaneList(List<Plane> planeList) {
		Main.planeList = planeList;
	}
	
	/**
	 * Gets the aiport con semaforo.
	 *
	 * @return the aiport con semaforo
	 */
	public static SimulatorAirport getAiportConSemaforo() {
		return aiportConSemaforo;
	}

	/**
	 * Sets the aiport con semaforo.
	 *
	 * @param aiportConSemaforo the new aiport con semaforo
	 */
	public static void setAiportConSemaforo(SimulatorAirport aiportConSemaforo) {
		Main.aiportConSemaforo = aiportConSemaforo;
	}

	/**
	 * Gets the lane list.
	 *
	 * @return the lane list
	 */
	public static List<Lane> getLaneList() {
		return laneList;
	}

	/**
	 * Sets the lane list.
	 *
	 * @param laneList the new lane list
	 */
	public static void setLaneList(List<Lane> laneList) {
		Main.laneList = laneList;
	}

	/**
	 * Gets the simulator list.
	 *
	 * @return the simulator list
	 */
	public static List<SimulatorLane> getSimulatorList() {
		return simulatorList;
	}

	/**
	 * Sets the simulator list.
	 *
	 * @param simulatorList the new simulator list
	 */
	public static void setSimulatorList(List<SimulatorLane> simulatorList) {
		Main.simulatorList = simulatorList;
	}

	private static void crearteThreads() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for (int i = 0; i < planeList.size(); i++) {
			Plane avion = planeList.get(i);
			executor.submit(avion);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Adds the planes to simulator.
	 */
	private static void addPlanesToSimulator() {
		for(int i = 0; i < laneList.size(); i++){
			SimulatorLane slane = new SimulatorLane(laneList.get(i));
			simulatorList.add(slane);
		}
	}
	
	/**
	 * Gets the lane from id.
	 *
	 * @return the lane from id
	 */
	public static Lane getLaneFromId(){
		Lane lane = null;
		for(SimulatorLane i:Main.getSimulatorList()){
			if(i.getLane().getIdLane() == 2){
				return i.getLane();
			}
		}
		return lane;
	}
}
