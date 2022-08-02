package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jmock.*;
import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jfree.data.Values2D;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;

public class DataUtilitiesTest {
	
	private Values2D values;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception{}
	
	@Before
	public void setUp() throws Exception{
		Mockery mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 1);
				will(returnValue(8.5));
				one(values).getValue(1, 1);
				will(returnValue(2.5));
				
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 2);
				will(returnValue(3.5));
				one(values).getValue(1, 2);
				will(returnValue(1.5));
				
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 3);
				will(returnValue(5.5));
				one(values).getValue(1, 3);
				will(returnValue(5.5));
			}
		});
		
	}

	//Nominal Tests
	@Test
	public void calculateColumnTotalForTwoValues() {
		
		final Values2D valuesMock = values;

		//exercise
		double result = DataUtilities.calculateColumnTotal(values, 2);
		
		//verify
		assertEquals(result, 5.0, .000000001d);
		//no tear-down here
	}
	
	//Robustness Tests
	@Test(expected = org.jmock.api.ExpectationError.class)
	public void calculateColumnTotalWithOutOfBoundsIndexBelow() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(values, -1);
		
	}
	
	@Test(expected = org.jmock.api.ExpectationError.class)
	public void calculateColumnTotalWithOutOfBoundsIndexAbove() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(values, 4);
		
	}
	
	
	@Test(expected = java.lang.NullPointerException.class)
	public void calculateColumnTotalWithInvalidInput() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(null, 5);
		
	}
	
	//Boundary Value Tests
	@Test
	public void indexOnLB() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		assertEquals(result, 10.0, .000000001d);
		
	}
	
	@Test
	public void indexOnUB() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(values, 3);
		
		assertEquals(result, 11.0, .000000001d);
	}
	
	@Test
	public void indexBUB() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(values, 2);
		
		assertEquals(result, 5.0, .000000001d);
	}

	@Test
	public void IndexALB() {
		final Values2D valuesMock = values;
		
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		assertEquals(result, 11.0, .000000001d);
	}
	
	
	@After
	public void tearDown() throws Exception{}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{}

}
