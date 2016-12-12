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
     * The constructor of the class.
     * @param id is the id
     * @param tipoPista the type of the line
     * @param order the order of the line
     * @param airport the airport of the line
     * @param taken the estatus of the line
     */
    public Line(final int id, final int tipoPista,
            final int order, final String airport,
            final Boolean taken) {
        this.id = id;
        this.tipoPista = tipoPista;
        this.order = order;
        this.airport = airport;
        this.taken = taken;
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

}
