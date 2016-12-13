package domain.model;

/**
 * Is the class of the airplanes.
 * @author PBL5
 *
 */
public class Airplane {

    /**
     * The type of the plane.
     */
    private int idTipoAvion;

    /**
     * The identificator of the plane.
     */
    private int id;

    /**
     * The identificator of the airline of the plane.
     */
    private int idAirline;

    /**
     * The estatus of the plane.
     */
    private int idEstatus;

    /**
     * The actual terminal of the plane.
     */
    private int terminal;

    /**
     * The actual x position of the plane in the map.
     */
    private float posX;

    /**
     * The actual y position of the plane in the map.
     */
    private float posY;

    /**
     * The speed of the airplane.
     */
    private float speed;

    /**
     * The angle of the airplane to know the direction.
     */
    private float angle;

    /**
     * To know when the airplane is speeding up.
     */
    private Boolean speedUp;

    /**
     * To know when the airplane is speeding down.
     */
    private Boolean speedDown;

    /**
     * The complete constructor of the plane.
     * @param id the identificator
     * @param idTipoAvion the type of the plane
     * @param idAirline the id of the airline
     * @param idEstatus the estatus of the plane
     * @param posX the x position
     * @param posY the y position
     * @param terminal the actual terminal
     * @param speed the speed of the airplane.
     * @param angle the direction of the airplane.
     * @param speedUp to know when the airplane is speeding up.
     * @param speedDown to know when the airplane is speeding down.
     */
    public Airplane(final int id, final int idTipoAvion, final int idAirline,
            final int idEstatus, final float posX, final float posY,
            final int terminal, final float speed, final float angle,
            final Boolean speedUp, final Boolean speedDown) {
        this.id = id;
        this.idTipoAvion = idTipoAvion;
        this.idAirline = idAirline;
        this.idEstatus = idEstatus;
        this.posX = posX;
        this.posY = posY;
        this.terminal = terminal;
        this.speed = speed;
        this.angle = angle;
        this.speedUp = speedUp;
        this.speedDown = speedDown;
    }

    /**
     * Getter of the speed.
     * @return the speed.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Setter of the speed.
     * @param speed is the speed.
     */
    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    /**
     * Getter of the angle.
     * @return the angle.
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Setter of the angle.
     * @param angle is the angle
     */
    public void setAngle(final float angle) {
        this.angle = angle;
    }

    /**
     * Getter of the speed up.
     * @return true or false.
     */
    public Boolean getSpeedUp() {
        return speedUp;
    }

    /**
     * Setter of the speed up.
     * @param speedUp is true or false.
     */
    public void setSpeedUp(final Boolean speedUp) {
        this.speedUp = speedUp;
    }

    /**
     * Getter of the speed down.
     * @return true or false.
     */
    public Boolean getSpeedDown() {
        return speedDown;
    }

    /**
     * Setter of the speed down.
     * @param speedDown is true or false.
     */
    public void setSpeedDown(final Boolean speedDown) {
        this.speedDown = speedDown;
    }

    /**
     * Getter of id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of id.
     * @param id is the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter of the position x.
     * @return position x
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Setter of the position x.
     * @param posX is the x position.
     */
    public void setPosX(final float posX) {
        this.posX = posX;
    }

    /**
     * Getter of the position y.
     * @return the position y
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Setter of the position y.
     * @param posY is the y position
     */
    public void setPosY(final float posY) {
        this.posY = posY;
    }

    /**
     * Getter of the terminal.
     * @return the actual terminal
     */
    public int getTerminal() {
        return terminal;
    }

    /**
     * Setter of the terminal.
     * @param terminal is the actual terminal
     */
    public void setTerminal(final int terminal) {
        this.terminal = terminal;
    }

    /**
     * Getter of idTipoAvion.
     * @return idTipoAvion
     */
    public int getIdTipoAvion() {
        return idTipoAvion;
    }

    /**
     * Setter of idTipoAvion.
     * @param idTipoAvion the idTipoAvion
     */
    public void setIdTipoAvion(final int idTipoAvion) {
        this.idTipoAvion = idTipoAvion;
    }

    /**
     * Getter of idAirline.
     * @return idAirline
     */
    public int getIdAirline() {
        return idAirline;
    }

    /**
     * Setter of idAiline.
     * @param idAirline is the idAirline
     */
    public void setIdAirline(final int idAirline) {
        this.idAirline = idAirline;
    }

    /**
     * Getter of idEstatus.
     * @return idEstatus
     */
    public int getIdEstatus() {
        return idEstatus;
    }

    /**
     * Setter of idEstatus.
     * @param idEstatus the id of the estatus
     */
    public void setIdEstatus(final int idEstatus) {
        this.idEstatus = idEstatus;
    }
}
