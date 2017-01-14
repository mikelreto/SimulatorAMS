package domain.model;

import java.util.concurrent.Semaphore;

public class SimulatorLane {

	Lane lane;
	private Semaphore semaforo = new Semaphore (1, true);
	
	public SimulatorLane(Lane lane){
		this.lane = lane;		
	}
	
	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	public Lane getLane() {
		return lane;
	}

	public void setLane(Lane lane) {
		this.lane = lane;
	}
	
	public SimulatorLane getSimulatorLane (int idLane) {
		SimulatorLane answer = null;
		if(this.lane.getIdLane() == idLane){
			answer = this;
		}
		return answer;
	}

}
