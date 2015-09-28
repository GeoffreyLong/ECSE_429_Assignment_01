package ca.mcgill.ecse429.conformancetest.tests;

import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceStateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceXStream;

public class ConformanceTest {

	public static void main(String[] args) { 
		//take xml as an argument, eventually
		TestGenerator tester = new TestGenerator(/*Pass XML into Generator*/);
	}
}
