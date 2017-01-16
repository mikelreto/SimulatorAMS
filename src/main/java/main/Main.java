package main;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import domain.dao.DaoAirplane;
import domain.dao.DaoLane;
import domain.model.Plane;
import domain.model.SimulatorLane;
import domain.model.Lane;

/**
 * The Class Main.
 */
public class Main {
	
	/** The user list. */
	private static List<Plane> planeList;
    
    /** The user dao. */
    private static DaoAirplane planeDao;
    private static DaoLane laneDao;
    private static List<Lane> laneList;
    private static List<SimulatorLane> simulatorList;
    


	public static List<Lane> getLaneList() {
		return laneList;
	}

	public static void setLaneList(List<Lane> laneList) {
		Main.laneList = laneList;
	}

	public static List<SimulatorLane> getSimulatorList() {
		return simulatorList;
	}

	public static void setSimulatorList(List<SimulatorLane> simulatorList) {
		Main.simulatorList = simulatorList;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		laneDao = new DaoLane();
		laneList = laneDao.loadLane();
		simulatorList = new ArrayList<SimulatorLane> ();
		addPlanesToSimulator();
		
		System.out.println("Hay " + laneList.size() + " lanes");
		planeDao = new DaoAirplane();
		System.out.println("LLEGA 1");

		planeList = planeDao.loadPlane();
		
		for(Plane i:Main.getPlaneList()){
    			i.setPosX(80);
    			i.setPosY(80);
    			i.setTerminal(i.getIdPlane());
    			i.setLane(null);
    			planeDao.updatePlane(i);
    	}
		
		for(SimulatorLane i:Main.getSimulatorList()){
    			i.getLane().setTaken("N");
    			laneDao.updateLane(i.getLane());
    	}
		
		laneList = laneDao.loadLane();
		planeList = planeDao.loadPlane();
		
		System.out.println("LLEGA 2");
		System.out.println("Hay " + planeList.size() + " planes");
		crearteThreads();
	}
	

	public static List<Plane> getPlaneList() {
		return planeList;
	}

	public static void setPlaneList(List<Plane> planeList) {
		Main.planeList = planeList;
	}

	private static void crearteThreads() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for (int i = 0; i < planeList.size(); i++) {
			Plane avion = planeList.get(i);
			executor.submit(avion);
		}
		executor.shutdown();
		System.out.println("Fin");
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private static void addPlanesToSimulator() {
		for(int i = 0; i < laneList.size(); i++){
			SimulatorLane slane = new SimulatorLane(laneList.get(i));
			simulatorList.add(slane);
		}
	}
	
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
