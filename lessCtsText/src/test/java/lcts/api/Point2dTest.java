package lcts.api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Point2dTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testPoint2d() {
		assertNotNull(new Point2d());
	}

	@Test
	public void testPoint2dFloatFloat() {
		assertNotNull(new Point2d(1f, 1f));
	}

	@Test
	public void testGetDistToPoint2d() {
		assertEquals(5.0, new Point2d(0, 0).getDistTo(new Point2d(3, 4)), 0.1);
	}

	@Test
	public void testGetDistToPoint2dPoint2d() {
		assertEquals(5.0, Point2d.getDistTo(new Point2d(0, 0), new Point2d(3, 4)), 0.1);
	}

	@Test
	public void testGetDistToPoint2dPoint2dBoolean() {
		Point2d vector = Point2d.getDistTo(new Point2d(0, 0), new Point2d(3, 4), true);
		assertEquals(3.0, vector.getX(), 0.1);
		
		Point2d vector2 = Point2d.getDistTo(new Point2d(-1, 4), new Point2d(12, -10), true);
		assertEquals(-14.0, vector2.getY(), 0.1);
	}

	@Test
	public void testGetPartialDist() {
		Point2d vector = Point2d.getPartialDist(new Point2d(0, 0), new Point2d(3, 4), 3);
		assertEquals(1.0, vector.getX(), 0.1);
	}

	@Test
	public void testClamp() {
		fail("Not yet implemented");
	}

	@Test
	public void testDescribe() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnNotate() {
		fail("Not yet implemented");
	}

}
