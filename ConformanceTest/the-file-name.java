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
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		classObj.returnQtrs()
		assertEquals(classObj.getStateFullName(),empty);
	}
	
	@Test
	public void testConformance_2() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedTotalQtrs = 0;
		classObj.reset()
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
	}
	
	@Test
	public void testConformance_3() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = 0;
		classObj.returnQtrs()
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
	}
	
	@Test
	public void testConformance_4() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		classObj.reset()
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
	}
	
	@Test
	public void testConformance_5() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = curQtrs+1;
		Object expectedAllowVend = true;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		classObj.returnQtrs()
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
	}
	
	@Test
	public void testConformance_6() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = curQtrs+1;
		Object expectedAllowVend = true;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		classObj.reset()
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
	}
	
	@Test
	public void testConformance_7() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = curQtrs+1;
		Object expectedAllowVend = true;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.add()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
	}
	
	@Test
	public void testConformance_8() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = curQtrs+1;
		Object expectedAllowVend = true;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedTotalQtrs = totalQtrs+2;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		classObj.vend()
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
	}
	
	@Test
	public void testConformance_9() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = curQtrs+1;
		Object expectedAllowVend = true;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedTotalQtrs = totalQtrs+2;
		Object expectedCurQtrs = 1;
		Object expectedAllowVend = false;
		classObj.vend()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
	}
	
	@Test
	public void testConformance_10() {
		Object expectedTotalQtrs = 0;
		Object expectedCurQtrs = 0;
		Object expectedAllowVend = false;
		assertEquals(classObj.getStateFullName(),empty);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedCurQtrs = curQtrs+1;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),notAllowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		Object expectedCurQtrs = curQtrs+1;
		Object expectedAllowVend = true;
		classObj.addQtr()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
		assertEquals(expectedAllowVend, classObj.getAllowVend());
		Object expectedTotalQtrs = totalQtrs+2;
		Object expectedCurQtrs = curQtrs-2;
		classObj.vend()
		assertEquals(classObj.getStateFullName(),allowed);
		assertEquals(expectedTotalQtrs, classObj.getTotalQtrs());
		assertEquals(expectedCurQtrs, classObj.getCurQtrs());
	}
}
