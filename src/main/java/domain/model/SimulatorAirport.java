package domain.model;

import java.util.concurrent.Semaphore;

/**
 * The Class SimulatorAirport.
 */
public class SimulatorAirport {

	/** The Constant TOKENS. */
    private static final int TOKENS = 6;

	/** The aeropuerto. */
	private Airport aeropuerto;

	/** The semaforo aeropuerto. */
	private Semaphore semaforoAeropuerto = new Semaphore(TOKENS, true);

	/**
	 * Instantiates a new simulator airport.
	 */
	public SimulatorAirport() {

	}

	/**
	 * Gets the aeropuerto.
	 *
	 * @return the aeropuerto
	 */
	public Airport getAeropuerto() {
		return aeropuerto;
	}

	/**
	 * Sets the aeropuerto.
	 *
	 * @param aeropuerto the new aeropuerto
	 */
	public void setAeropuerto(Airport aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	/**
	 * Gets the semaforo aeropuerto.
	 *
	 * @return the semaforo aeropuerto
	 */
	public Semaphore getSemaforoAeropuerto() {
		return semaforoAeropuerto;
	}

	/**
	 * Sets the semaforo aeropuerto.
	 *
	 * @param semaforoAeropuerto the new semaforo aeropuerto
	 */
	public void setSemaforoAeropuerto(Semaphore semaforoAeropuerto) {
		this.semaforoAeropuerto = semaforoAeropuerto;
	}

}
