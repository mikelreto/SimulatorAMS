package domain.model;

import java.util.concurrent.Semaphore;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulatorLane.
 */
public class SimulatorLane {

	/** The lane. */
	Lane lane;
	
	/** The semaforo. */
	private Semaphore semaforo = new Semaphore (1, true);
	
	/**
	 * Instantiates a new simulator lane.
	 *
	 * @param lane the lane
	 */
	public SimulatorLane(Lane lane){
		this.lane = lane;		
	}
	
	/**
	 * Gets the semaforo.
	 *
	 * @return the semaforo
	 */
	public Semaphore getSemaforo() {
		return semaforo;
	}

	/**
	 * Sets the semaforo.
	 *
	 * @param semaforo the new semaforo
	 */
	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	/**
	 * Gets the lane.
	 *
	 * @return the lane
	 */
	public Lane getLane() {
		return lane;
	}

	/**
	 * Sets the lane.
	 *
	 * @param lane the new lane
	 */
	public void setLane(Lane lane) {
		this.lane = lane;
	}
	
	/**
	 * Gets the simulator lane.
	 *
	 * @param idLane the id lane
	 * @return the simulator lane
	 */
	public SimulatorLane getSimulatorLane (int idLane) {
		SimulatorLane answer = null;
		if(this.lane.getIdLane() == idLane){
			answer = this;
		}
		return answer;
	}

}
