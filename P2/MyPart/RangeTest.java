package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

import org.junit.Test;

public class RangeTest {
	
	private Range exampleRange;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception{}
	
	@Before
	public void setUp() throws Exception{
		exampleRange = new Range(-5, 5);
	}

	// testing function getCentralValue()
	@Test
	public void centralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0",
				0, exampleRange.getCentralValue(), .000000001d);
	}
	
	//Testing function getLength()
	@Test
	public void lengthShouldBeTwo() {
		assertEquals("The length of the range should be 10",
				10, exampleRange.getLength(), .000000001d);
	}
	
	//Testing function intersects()
	
	//Nominal tests intersects
	@Test
	public void fullyWithinExampleRange() {
		assertTrue("The lower is greater than the upperbound of the range,"
				+ " and the upper bound is less than the lower bound of the range", exampleRange.intersects(-0.5, 0.5));
	}
	
	@Test
	public void upperOverlap() {
		assertTrue("lower bound is within range, upper bound above range, there should be overlap",
				exampleRange.intersects(2, 7));
		
		//fails
	}
	
	@Test
	public void lowerOverlap() {
		assertTrue("upper bound is within range, lower bound below range, there should be overlap",
				exampleRange.intersects(-8, 3));
	}
	
	@Test
	public void exampleRangeWithinInputRange() {
		assertTrue("lower bound is lower than range, upper bound above range, there should be overlap",
				exampleRange.intersects(-8, 8));
	}
	
	@Test
	public void inputRangeAboveExampleRange() {
		assertFalse("lower bound and upper bound above range, there should be no overlap",
				exampleRange.intersects(7, 10));
	}
	
	@Test
	public void inputRangeBelowExampleRange() {
		assertFalse("lower bound and upper bound below range, there should be no overlap",
				exampleRange.intersects(-10, -7));
		//fails  
	}
	
	//Robustness Tests intersects
	@Test
	public void lowerGreaterUpperBelowExampleRange() {
		assertFalse("lower is greater than upper, lower is less than examplerange.lowerbound"
				+ " and upper is less than examplerange.lowerbound should be false",
				exampleRange.intersects(-6, -10));
		//fails
	}
	
	@Test
	public void lowerGreaterUpperLowerOverlap() {
		assertFalse("lower is greater than upper, lower is within examplerange"
				+ " and upper is less than examplerange.lowerbound should be false",
				exampleRange.intersects(-4, -6));
	}
	
	@Test
	public void lowerGreaterUpperWithinExampleRange() {
		assertFalse("lower is greater than upper, lower and upper within exampleRange",
				exampleRange.intersects(4, 3));
	}
	
	@Test
	public void lowerGreaterUpperAboveExampleRange() {
		assertFalse("lower is greater than upper, lower is greater than examplerange.upperbound"
				+ " and upper is greater than examplerange.upperbound should be false",
				exampleRange.intersects(7, 6));
	}
	
	@Test
	public void lowerGreaterUpperHigherOverlap() {
		assertFalse("lower is greater than upper, lower is greater than examplerange.upperbound"
				+ " and upper within exampleRange should be false",
				exampleRange.intersects(7, 3));
	}
	
	@Test
	public void lowerGreaterUpperEngulfExampleRange() {
		assertFalse("lower is greater than upper, lower is greater than examplerange.upperbound"
				+ " and upper is less than examplerange.lowerbound should be false",
				exampleRange.intersects(7, -7));
	}
	
	//Boundary Value tests intersects
	@Test
	public void lowerAndUpperOnLB() {
		assertTrue("both inputs are equal and on the range's lower bound, should overlap",
				exampleRange.intersects(-5, -5));
	}
	
	@Test
	public void lowerAndUpperOnUB() {
		assertTrue("both inputs are equal and on the range's upper bound, should overlap",
				exampleRange.intersects(5, 5));
		//fails
	}
	
	@Test
	public void lowerOnLBUpperALB() {
		assertTrue("lower on the range's lower bound, upper is just above, should overlap",
				exampleRange.intersects(-5, -4.9));
	}
	
	@Test
	public void lowerOnALBUpperOnALB() {
		assertTrue("both inputs are equal and just above the range's lower bound, should overlap",
				exampleRange.intersects(-4.9, -4.9));
	}
	
	@Test
	public void lowerOnBUBUpperOnBUB() {
		assertTrue("both inputs are equal and just below the range's upper bound, should overlap",
				exampleRange.intersects(4.9, 4.9));
	}
	
	@Test
	public void lowerBUBUpperOnUB() {
		assertTrue("lower just below the range's upper bound, upper on range's upper bound, should overlap",
				exampleRange.intersects(4.9, 5));
	}
	
	@Test
	public void lowerOnLBUpperOnUB() {
		assertTrue("lower on the range's lower bound, upper on range's upper bound, should overlap",
				exampleRange.intersects(-5, 5));
	}
	
	@Test
	public void lowerOnALBUpperOnBUB() {
		assertTrue("lower just above the range's lower bound, upper just below the range's upper bound, should overlap",
				exampleRange.intersects(-4.9, 4.9));
	}
	
	@Test
	public void lowerOnALBUpperOnUB() {
		assertTrue("lower just above the range's lower bound, upper on the range's upper bound, should overlap",
				exampleRange.intersects(-4.9, 5));
		
		//fails
	}
	
	@Test
	public void lowerOnLBUpperOnBUB() {
		assertTrue("lower on the range's lower bound, upper just below the range's upper bound, should overlap",
				exampleRange.intersects(-5, 4.9));
	}
	
	@Test
	public void lowerOnBLBUpperOnBLB() {
		assertFalse("lower just below the range's lower bound, upper just below the range's lower bound, should overlap",
				exampleRange.intersects(-5.1, -5.1));
		//fails
	}
	
	@Test
	public void lowerOnAUBUpperOnAUB() {
		assertFalse("lower just above the range's upper bound, upper just above the range's upper bound, should not overlap",
				exampleRange.intersects(5.1, 5.1));
	}
	
	@Test
	public void lowerOnBLBUpperOnAUB() {
		assertTrue("lower just below the range's lower bound, upper just above the range's upper bound, should overlap",
				exampleRange.intersects(-5.1, 5.1));
	}
	
	
	
	
	@After
	public void tearDown() throws Exception{}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{}
	
}
