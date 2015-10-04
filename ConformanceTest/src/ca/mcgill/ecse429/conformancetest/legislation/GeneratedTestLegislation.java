package ca.mcgill.ecse429.conformancetest.legislation;
import ca.mcgill.ecse429.conformancetest.legislation.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GeneratedTestLegislation {
	private Legislation classObj;
	
	@Before
	public void setUp() throws Exception {
		classObj = new Legislation();
	}
	
	@Test
	public void testConformance_1() {
		Object expectedIsCommonsBill = true;
		assertEquals(classObj.getStateFullName(),"inPreparation");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		expectedIsCommonsBill = false;
		classObj.introduceInSenate();
		assertEquals(classObj.getStateFullName(),"inSenate");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		classObj.voteFails();
		assertEquals(classObj.getStateFullName(),"inPreparation");
	}
	
	@Test
	public void testConformance_2() {
		Object expectedIsCommonsBill = true;
		assertEquals(classObj.getStateFullName(),"inPreparation");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		expectedIsCommonsBill = false;
		classObj.introduceInSenate();
		assertEquals(classObj.getStateFullName(),"inSenate");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		assertTrue(!classObj.getIsCommonsBill());
		classObj.votePasses();
		assertEquals(classObj.getStateFullName(),"inHouseOfCommons");
		classObj.voteFails();
		assertEquals(classObj.getStateFullName(),"inPreparation");
	}
	
	@Test
	public void testConformance_3() {
		Object expectedIsCommonsBill = true;
		assertEquals(classObj.getStateFullName(),"inPreparation");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		expectedIsCommonsBill = false;
		classObj.introduceInSenate();
		assertEquals(classObj.getStateFullName(),"inSenate");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		assertTrue(!classObj.getIsCommonsBill());
		classObj.votePasses();
		assertEquals(classObj.getStateFullName(),"inHouseOfCommons");
		assertTrue(classObj.getIsCommonsBill());
		classObj.votePasses();
		assertEquals(classObj.getStateFullName(),"inSenate");
	}
	
	@Test
	public void testConformance_4() {
		Object expectedIsCommonsBill = true;
		assertEquals(classObj.getStateFullName(),"inPreparation");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		expectedIsCommonsBill = false;
		classObj.introduceInSenate();
		assertEquals(classObj.getStateFullName(),"inSenate");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		assertTrue(!classObj.getIsCommonsBill());
		classObj.votePasses();
		assertEquals(classObj.getStateFullName(),"inHouseOfCommons");
		assertTrue(!classObj.getIsCommonsBill());
		classObj.votePasses();
		assertEquals(classObj.getStateFullName(),"finalized");
	}
	
	@Test
	public void testConformance_5() {
		Object expectedIsCommonsBill = true;
		assertEquals(classObj.getStateFullName(),"inPreparation");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		expectedIsCommonsBill = false;
		classObj.introduceInSenate();
		assertEquals(classObj.getStateFullName(),"inSenate");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		assertTrue(classObj.getIsCommonsBill());
		classObj.votePasses();
		assertEquals(classObj.getStateFullName(),"finalized");
	}
	
	@Test
	public void testConformance_6() {
		Object expectedIsCommonsBill = true;
		assertEquals(classObj.getStateFullName(),"inPreparation");
		assertEquals(expectedIsCommonsBill, classObj.getIsCommonsBill());
		classObj.introduceInHouse();
		assertEquals(classObj.getStateFullName(),"inHouseOfCommons");
	}
}
