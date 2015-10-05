package ca.mcgill.ecse429.conformancetest.tests;
public class ConformanceTest {

	public static void main(String[] args) { 
		if (args.length != 1){
			System.out.println("Please pass full path of XML file as argument");
		}
		else{
			TestGenerator tester = new TestGenerator();
			tester.run(args[0]);
		}
	}
}
