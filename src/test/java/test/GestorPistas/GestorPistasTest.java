package test.GestorPistas;

import org.junit.Test;
import domain.monitor.*;

import domain.model.Lane;
import domain.model.LaneType;
import domain.model.Plane;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

/**
 * The Class GestorPistasTest.
 */
public class GestorPistasTest {

	/** The Constant ULTIMAPISTAPOSTTERMINAL. */
	public static final int ULTIMAPISTAPOSTTERMINAL = 13;

	/** The Constant ANTEULTIMAPISTA. */
	public static final int ANTEULTIMAPISTA = 14;

	/** The Constant PISTADESPEGUE. */
	public static final int PISTADESPEGUE = 15;

	/** The Constant PISTADESPEGUE. */
	public static final int IDTIPOPISTADESPEGUE = 8;

	/** The Constant PRIMERAPISTAFORK. */
	public static final int PRIMERAPISTAFORK = 3;

	/** The Constant SEGUNDAPISTAFORK. */
	public static final int SEGUNDAPISTAFORK = 4;

	/** The Constant PRIMERAPISTAPOSTTERMINAL. */
	public static final int PRIMERAPISTAPOSTTERMINAL = 11;

	/** The Constant SEGUNDAPISTAPOSTTERMINAL. */
	public static final int SEGUNDAPISTAPOSTTERMINAL = 12;

	/** The Constant TERMINALCUATRO. */
	public static final int TERMINALCUATRO = 4;

	/** The Constant ULTIMAPISTATERMINAL. */
	public static final int ULTIMAPISTATERMINAL = 10;

	/** The Constant ORDENTRES. */
	public static final int ORDENTRES = 3;

	/** The p. */
	private Plane p;

	/** The lane. */
	private Lane lane;

	/** The lane typ. */
	private LaneType laneTyp;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		p = new Plane();
		lane = new Lane();
		laneTyp = new LaneType();
	}

	/**
	 * Test see next lane route 1 OK.
	 */
	@Test
    public void testSeeNextLaneRoute1OK() {
		laneTyp.setIdLaneType(GestorPistas.ATERRIZAJE);
		lane.setLaneOrder(1);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(2, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.CURVERIGHT);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(PRIMERAPISTAFORK, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.FORK);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(SEGUNDAPISTAFORK, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.PISTATERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(PRIMERAPISTAPOSTTERMINAL, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.POSTTERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(SEGUNDAPISTAPOSTTERMINAL, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.POSTTERMINAL);
		lane.setLaneOrder(2);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(ULTIMAPISTAPOSTTERMINAL, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.POSTTERMINAL);
		lane.setLaneOrder(ORDENTRES);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(ANTEULTIMAPISTA, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.CURVELEFT);
		lane.setLaneOrder(1);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(PISTADESPEGUE, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(IDTIPOPISTADESPEGUE);
		lane.setLaneOrder(1);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(0, GestorPistas.seeNextLane(p));
		p.setLane(null);
		assertEquals(1, GestorPistas.seeNextLane(p));
		//Last terminal case
		laneTyp.setIdLaneType(GestorPistas.PISTATERMINAL);
		lane.setLaneOrder(GestorPistas.LASTTERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(ANTEULTIMAPISTA, GestorPistas.seeNextLane(p));
		//Plane terminal case
		laneTyp.setIdLaneType(GestorPistas.FORK);
		lane.setLaneOrder(GestorPistas.LASTTERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		p.setTerminal(TERMINALCUATRO);
		assertEquals(ULTIMAPISTATERMINAL, GestorPistas.seeNextLane(p));
    }

}
