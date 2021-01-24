package lcts.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class MonitorPixelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDescribe() {
		MonitorPixel m = new MonitorPixel(34, 44);
		assertTrue(m.describe().compareTo("the monitor pixel (34, 44)") == 0);
	}

	@Test
	public void testNotate() {
		MonitorPixel m = new MonitorPixel();
		assertTrue(m.notate().compareTo("(0, 0)") == 0);
	}

	@Test
	public void testUnNotate() {
		fail("Not yet implemented");
	}

	@Test
	public void testMonitorPixel() {
		MonitorPixel m = new MonitorPixel();
		assertNotNull(m);
	}

	@Test
	public void testMonitorPixelIntInt() {
		MonitorPixel m = new MonitorPixel(34, 44);
		assertNotNull(m);
	}

	@Test
	public void testGetDistToMonitorPixel() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDistToMonitorPixelMonitorPixel() {
		fail("Not yet implemented");
	}

	@Test
	public void testClampIntIntInt() {
		assertTrue(MonitorPixel.clamp(1, 2, 0) == 1);
		assertTrue(MonitorPixel.clamp(0, 2, 1) == 1);
		assertTrue(MonitorPixel.clamp(2, 1, 0) == 1);
	}

}
