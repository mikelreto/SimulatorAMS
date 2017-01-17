package monitor;

import org.junit.Test;
import domain.monitor.*;

import domain.model.Lane;
import domain.model.LaneType;
import domain.model.Plane;

import static org.junit.Assert.*;

import org.junit.Before;

public class MonitorTest {
	
	
	private Plane p;
	private Lane lane;
	private LaneType laneTyp;
	@Before
	public void setUp(){
		p = new Plane();
		lane = new Lane();
		laneTyp = new LaneType();
	}
	
	
	@Test
    public void testSeeNextLaneRoute1OK(){
		laneTyp.setIdLaneType(GestorPistas.ATERRIZAJE);
		lane.setLaneOrder(1);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(2, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.CURVERIGHT);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(3, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.FORK);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(4, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.PISTATERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(11, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.POSTTERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(12, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.POSTTERMINAL);
		lane.setLaneOrder(2);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(13, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.POSTTERMINAL);
		lane.setLaneOrder(3);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(14, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(GestorPistas.CURVELEFT);
		lane.setLaneOrder(1);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		assertEquals(15, GestorPistas.seeNextLane(p));
		laneTyp.setIdLaneType(8);
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
		assertEquals(14, GestorPistas.seeNextLane(p));
		//Plane terminal case
		laneTyp.setIdLaneType(GestorPistas.FORK);
		lane.setLaneOrder(GestorPistas.LASTTERMINAL);
		lane.setLaneType(laneTyp);
		p.setLane(lane);
		p.setTerminal(4);
		assertEquals(10, GestorPistas.seeNextLane(p));
    }

}
