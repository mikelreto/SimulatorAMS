package domain.model;

import java.sql.Timestamp;

/**
 * The class of the flight.
 * @author PBL5
 *
 */
public class Flight {

    /**
     * Time to start the flight.
     */
    private Timestamp timeIn;

    /**
     * Time to finish the flight.
     */
    private Timestamp timeOut;

    /**
     * The airport of the beggining of the flight.
     */
    private String airportIn;

    /**
     * The airport of the final of the flight.
     */
    private String airportOut;

    /**
     * The delay of the flight.
     */
    private int delay;

    /**
     * The estate of the flight.
     */
    private String estate;

    /**
     * The number of the port that is going to be the airplane of the flight.
     */
    private int numberPort;

    /**
     * The number of the luggage that is going to be the airplane of the flight.
     */
    private int numberLuggage;

    /**
     * The id of the flight.
     */
    private int id;

    /**
     * The airplane of the flight.
     */
    private Airplane airplane;

    /**
     * The constructor of the flight.
     * @param id is the id
     * @param airplane the airplane of the flight.
     * @param estate the estate
     * @param timeIn the time to start
     * @param timeOut the time to finish.
     * @param airportIn the airport of the beggining
     * @param airportOut the airport of the final
     * @param delay the delay of the flight
     * @param numberPort the port of the airplane of the flight
     * @param numberLuggage the luggage in witch is the airplane
     */
    public Flight(final int id, final Airplane airplane, final String estate,
                final Timestamp timeIn, final Timestamp timeOut,
                final String airportIn, final String airportOut,
                final int delay, final int numberPort,
                final int numberLuggage) {
        this.id = id;
        this.airplane = airplane;
        this.estate = estate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.airportIn = airportIn;
        this.airportOut = airportOut;
        this.delay = delay;
        this.numberPort = numberPort;
        this.numberLuggage = numberLuggage;
    }

    /**
     * Getter of the time to enter to the airport.
     * @return the enter time
     */
    public Timestamp getTimeIn() {
        return timeIn;
    }

    /**
     * Setter of the time to enter to the airport.
     * @param timeIn the time to enter to the airport
     */
    public void setTimeIn(final Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    /**
     * Getter of the time to exit from the airport.
     * @return the exit time.
     */
    public Timestamp getTimeOut() {
         return timeOut;
    }

    /**
     * Setter of the time to exit the airport.
     * @param timeOut the time to exit the airport.
     */
    public void setTimeOut(final Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Getter of the enter airport.
     * @return the name of the airport.
     */
    public String getAirportIn() {
        return airportIn;
    }

    /**
     * Setter fo the enter airport.
     * @param airportIn is the airport to arrive.
     */
    public void setAirportIn(final String airportIn) {
        this.airportIn = airportIn;
    }

    /**
     * Getter of the exit airport.
     * @return the name of the exit airport.
     */
    public String getAirportOut() {
        return airportOut;
    }

    /**
     * Setter of the exit airport.
     * @param airportOut is the airport of the beggining.
     */
    public void setAirportOut(final String airportOut) {
        this.airportOut = airportOut;
    }

    /**
     * Getter of the delay of the flight.
     * @return the delay in a number
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Setter of the delay of the flight.
     * @param delay is the time that is delayed the flight.
     */
    public void setDelay(final int delay) {
        this.delay = delay;
    }

    /**
     * Getter of the estate of the flight.
     * @return the estate of the flight.
     */
    public String getEstate() {
        return estate;
    }

    /**
     * Setter of the estate of the flight.
     * @param estate is the actual estate of the flight.
     */
    public void setEstate(final String estate) {
        this.estate = estate;
    }

    /**
     * Getter of the number of the port.
     * @return the number of the port.
     */
    public int getNumberPort() {
        return numberPort;
    }

    /**
     * Setter of the number of the port.
     * @param numberPort is the number of the port
     */
    public void setNumberPort(final int numberPort) {
        this.numberPort = numberPort;
    }

    /**
     * Getter of the number of the luggage.
     * @return the number of the luggage
     */
    public int getNumberLuggage() {
        return numberLuggage;
    }

    /**
     * Setter of the number of the luggage.
     * @param numberLuggage is the number of the luggage.
     */
    public void setNumberLuggage(final int numberLuggage) {
         this.numberLuggage = numberLuggage;
    }

    /**
     * Getter of the id of the flight.
     * @return the id of the flight.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of the id of the flight.
     * @param id is the identificator of the flight.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter of the airplane of the flight.
     * @return the airplane
     */
    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * Setter of the airplane of the flight.
     * @param airplane is the airplane of the flight.
     */
    public void setAirplane(final Airplane airplane) {
        this.airplane = airplane;
    }
}
