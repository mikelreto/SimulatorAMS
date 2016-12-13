package domain.model;

/**
 * The class of the line.
 * @author PBL5
 *
 */
public class Line {

    /**
     * The id of the line.
     */
    private int id;

    /**
     * The type of the line.
     */
    private int tipoPista;

    /**
     * The order that follows the line.
     */
    private int order;

    /**
     * The name of the airport of the line.
     */
    private String airport;

    /**
     * To know if the line is taken.
     */
    private Boolean taken;

    /**
     * Initial x position to put the airplane.
     */
    private float initialPosX;

    /**
     * Initial y position to put the airplane.
     */
    private float initialPosY;

    /**
     * The x way to move to the wait zone.
     */
    private float initialWaitPosX;

    /**
     * The y way to move to the wait zone.
     */
    private float initialWaitPosY;

    /**
     * The final position of the line in x.
     */
    private float finalPistaPosX;

    /**
     * The final position of the line in y.
     */
    private float finalPistaPosY;

    /**
     * The way that is going to move the airplane in the line.
     */
    private String way;

    /**
     * The constructor of the class.
     * @param id is the id
     * @param tipoPista the type of the line
     * @param order the order of the line
     * @param airport the airport of the line
     * @param taken the estatus of the line
     * @param initialPosX is the initial x position
     * @param initialPosY is the initial y position
     * @param initialWaitPosX is the initial x position to wait.
     * @param initialWaitPosY is the initial y position to wait
     * @param finalPistaPosX is the final x position of the line.
     * @param finalPistaPosY is the final y position of the line.
     * @param way the way that is going to move the airplane in the line.
     */
    public Line(final int id, final int tipoPista,
            final int order, final String airport,
            final Boolean taken, final float initialPosX,
            final float initialPosY, final float initialWaitPosX,
            final float initialWaitPosY, final float finalPistaPosX,
            final float finalPistaPosY, final String way) {

        this.id = id;
        this.tipoPista = tipoPista;
        this.order = order;
        this.airport = airport;
        this.taken = taken;
        this.initialPosX = initialPosX;
        this.initialPosY = initialPosY;
        this.initialWaitPosX = initialWaitPosX;
        this.initialWaitPosY = initialWaitPosY;
        this.finalPistaPosX = finalPistaPosX;
        this.finalPistaPosY = finalPistaPosY;
        this.way = way;
    }

    /**
     * Getter of the type of the line.
     * @return the type of the line
     */
    public int getTipoPista() {
        return tipoPista;
    }

    /**
     * Setter of the type of the line.
     * @param tipoPista is the type of the line
     */
    public void setTipoPista(final int tipoPista) {
        this.tipoPista = tipoPista;
    }

    /**
     * Getter of the id of the line.
     * @return the id of the line.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of the id of the line.
     * @param id is the identificator of the line.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter of the order of the line.
     * @return the order of the line.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Setter of the order of the line.
     * @param order is the order of the line.
     */
    public void setOrder(final int order) {
        this.order = order;
    }

    /**
     * Getter of the airport.
     * @return the name of the airport.
     */
    public String getAirport() {
        return airport;
    }

    /**
     * Setter of the airport.
     * @param airport is the name of the airport.
     */
    public void setAirport(final String airport) {
        this.airport = airport;
    }

    /**
     * Getter of the state of the line.
     * @return true or false
     */
    public Boolean getTaken() {
        return taken;
    }

    /**
     * Setter of the state of the line.
     * @param taken is true or false.
     */
    public void setTaken(final Boolean taken) {
        this.taken = taken;
    }

    /**
     * Getter of the initial position in x.
     * @return the position x
     */
    public float getInitialPosX() {
        return initialPosX;
    }

    /**
     * Setter of the initial x position.
     * @param initialPosX is the initial x position
     */
    public void setInitialPosX(final float initialPosX) {
        this.initialPosX = initialPosX;
    }

    /**
     * Getter of the initial y position.
     * @return the initial y position
     */
    public float getInitialPosY() {
        return initialPosY;
    }

    /**
     * Setter of the initial y position.
     * @param initialPosY is the initial y position.
     */
    public void setInitialPosY(final float initialPosY) {
        this.initialPosY = initialPosY;
    }

    /**
     * Getter of the initial x position to wait.
     * @return the position.
     */
    public float getInitialWaitPosX() {
        return initialWaitPosX;
    }

    /**
     * Setter of the initial y position to wait.
     * @param initialWaitPosX float.
     */
    public void setInitialWaitPosX(final float initialWaitPosX) {
        this.initialWaitPosX = initialWaitPosX;
    }

    /**
     * Getter of the initial y position to wait.
     * @return the position
     */
    public float getInitialWaitPosY() {
        return initialWaitPosY;
    }

    /**
     * Setter of the initial y position to wait.
     * @param initialWaitPosY float.
     */
    public void setInitialWaitPosY(final float initialWaitPosY) {
        this.initialWaitPosY = initialWaitPosY;
    }

    /**
     * Getter of the final x position of the line.
     * @return float
     */
    public float getFinalPistaPosX() {
        return finalPistaPosX;
    }

    /**
     * Setter of the final x position of the line.
     * @param finalPistaPosX float
     */
    public void setFinalPistaPosX(final float finalPistaPosX) {
        this.finalPistaPosX = finalPistaPosX;
    }

    /**
     * Getter of the final y position of the line.
     * @return float.
     */
    public float getFinalPistaPosY() {
        return finalPistaPosY;
    }

    /**
     * Setter of the final y position of the line.
     * @param finalPistaPosY float.
     */
    public void setFinalPistaPosY(final float finalPistaPosY) {
        this.finalPistaPosY = finalPistaPosY;
    }

    /**
     * Getter of the way.
     * @return String
     */
    public String getWay() {
         return way;
    }

    /**
     * Setter of the way.
     * @param way String
     */
    public void setWay(final String way) {
         this.way = way;
    }
}
