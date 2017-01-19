package domain.model;

import java.util.concurrent.Semaphore;

public class SimulatorAirport {
	
	Airport aeropuerto;
	Semaphore semaforoAeropuerto = new Semaphore (6, true);
	
	public SimulatorAirport(){
		
	}
	
	public Airport getAeropuerto() {
		return aeropuerto;
	}
	public void setAeropuerto(Airport aeropuerto) {
		this.aeropuerto = aeropuerto;
	}
	public Semaphore getSemaforoAeropuerto() {
		return semaforoAeropuerto;
	}
	public void setSemaforoAeropuerto(Semaphore semaforoAeropuerto) {
		this.semaforoAeropuerto = semaforoAeropuerto;
	}

}
