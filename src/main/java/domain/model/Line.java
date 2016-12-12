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
    private int initialPosX;

    /**
     * Initial y position to put the airplane.
     */
    private int initialPosY;

    /**
     * The x way to move to the wait zone.
     */
    private int moveX;

    /**
     * The y way to move to the wait zone.
     */
    private int moveY;

    /**
     * The constructor of the class.
     * @param id is the id
     * @param tipoPista the type of the line
     * @param order the order of the line
     * @param airport the airport of the line
     * @param taken the estatus of the line
     * @param initialPosX is the initial x position
     * @param initialPosY is the initial y position
     * @param moveX is the way to move in x
     * @param moveY is the way to move in y
     */
    public Line(final int id, final int tipoPista,
            final int order, final String airport,
            final Boolean taken, final int initialPosX,
            final int initialPosY, final int moveX,
            final int moveY) {

        this.id = id;
        this.tipoPista = tipoPista;
        this.order = order;
        this.airport = airport;
        this.taken = taken;
        this.initialPosX = initialPosX;
        this.initialPosY = initialPosY;
        this.moveX = moveX;
        this.moveY = moveY;
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
    public int getInitialPosX() {
        return initialPosX;
    }

    /**
     * Setter of the initial x position.
     * @param initialPosX is the initial x position
     */
    public void setInitialPosX(final int initialPosX) {
        this.initialPosX = initialPosX;
    }

    /**
     * Getter of the initial y position.
     * @return the initial y position
     */
    public int getInitialPosY() {
        return initialPosY;
    }

    /**
     * Setter of the initial y position.
     * @param initialPosY is the initial y position.
     */
    public void setInitialPosY(final int initialPosY) {
        this.initialPosY = initialPosY;
    }

    /**
     * Getter of the way to move in x.
     * @return the way to move in x
     */
    public int getMoveX() {
        return moveX;
    }

    /**
     * Setter of the way to move in x.
     * @param moveX is the way in x
     */
    public void setMoveX(final int moveX) {
        this.moveX = moveX;
    }

    /**
     * Getter of the way to move in y.
     * @return the way to move in y
     */
    public int getMoveY() {
        return moveY;
    }

    /**
     * Setter of the way to move in y.
     * @param moveY is the way in y
     */
    public void setMoveY(final int moveY) {
        this.moveY = moveY;
    }

}
