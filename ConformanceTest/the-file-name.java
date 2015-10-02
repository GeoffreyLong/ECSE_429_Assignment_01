package ca.mcgill.ecse429.conformancetest.ccoinbox.test;
import ca.mcgill.ecse429.conformancetest.ccoinbox;
import org.junit.*;

public class TestCCoinBox {
	private CCoinBox classObj;
	
	@Before
	public void setUp() throws Exception {
		classObj = new CCoinBox();
	}
	
	@Test
	public void testConformance_1() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.returnQtrs()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_2() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.reset()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_3() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.returnQtrs()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_4() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.reset()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_5() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed)
		classObj.returnQtrs()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_6() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed)
		classObj.reset()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_7() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed)
		classObj.add()
		assertEquals(classObj.getStateFullName(),allowed)
	}
	
	@Test
	public void testConformance_8() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed)
		classObj.vend()
		assertEquals(classObj.getStateFullName(),empty)
	}
	
	@Test
	public void testConformance_9() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed)
		classObj.vend()
		assertEquals(classObj.getStateFullName(),notAllowed)
	}
	
	@Test
	public void testConformance_10() {
		assertEquals(classObj.getStateFullName(),empty)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed)
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed)
		classObj.vend()
		assertEquals(classObj.getStateFullName(),allowed)
	}
}
