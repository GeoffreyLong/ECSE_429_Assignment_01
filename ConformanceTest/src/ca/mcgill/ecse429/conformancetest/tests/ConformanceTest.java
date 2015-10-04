package ca.mcgill.ecse429.conformancetest.tests;

import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceStateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceXStream;

public class ConformanceTest {

	public static void main(String[] args) { 
		if (args.length != 1){
			System.out.println("Please pass full path of XML file as argument");
		}
		else{
			TestGenerator tester = new TestGenerator(args[0]);
		}
	}
}
