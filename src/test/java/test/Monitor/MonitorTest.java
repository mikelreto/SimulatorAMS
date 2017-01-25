package test.Monitor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.dao.DaoAirplane;
import domain.dao.DaoLane;
import domain.model.Lane;
import domain.model.LaneType;
import domain.model.Plane;
import domain.model.SimulatorLane;
import domain.monitor.GestorPistas;
import domain.monitor.Monitor;
import main.Main;

public class MonitorTest {

	private Plane p, destPlane;
	private Lane lane;
	private LaneType laneTyp;
	private static DaoAirplane planeDao;
    private static DaoLane laneDao;
	@Before
	public void setUp(){
		p = new Plane();
		destPlane = new Plane();
		lane = new Lane();
		laneTyp = new LaneType();
		planeDao = new DaoAirplane();
		laneDao = new DaoLane();
		Main.startSimulatorList();
		Main.planeList = planeDao.loadPlane();
		Main.laneList = laneDao.loadLane();
		Main.addPlanesToSimulator();
		for(Plane i:Main.getPlaneList()){
			if(i.getIdPlane() == 1){
				i.setPosX(80);
				i.setPosY(80);
				i.setTerminal(i.getIdPlane());
				i.setLane(null);
				p = i;
			}
			planeDao.updatePlane(i);
	}
	
	for(SimulatorLane i:Main.getSimulatorList()){
			i.getLane().setTaken("N");
			laneDao.updateLane(i.getLane());
	}
	}
	
	@Test
	public void testMonitorsOK(){
		assertEquals("update success", Monitor.enterPista(1, p));
		laneTyp.setIdLaneType(GestorPistas.CURVELEFT);
		lane.setLaneType(laneTyp);
		lane = Monitor.getLaneFromId(15);
		p.setPosX(lane.getPosXInitLane());
		p.setPosY(lane.getPosYInitLane());
		p.setLane(lane);
		assertEquals("update success", Monitor.enterPista(0, p));
	}
}
