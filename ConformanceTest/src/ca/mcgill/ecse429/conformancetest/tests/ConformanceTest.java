package tests;

import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceStateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceXStream;

public class ConformanceTest {

	public static void main(String[] args) { 
		//take xml as an argument, eventually
		PersistenceStateMachine stateMach = new PersistenceStateMachine(); 
		stateMach.loadStateMachine("ccoinbox.xml");
		
		PersistenceXStream pers = new PersistenceXStream(); 
		StateMachine mach = (StateMachine) pers.loadFromXMLwithXStream();
		
		System.out.println(mach.toString());
		List<Transition> trans = mach.getTransitions();
		for (int i = 0; i < trans.size(); i++) {
			System.out.println("Action: " + trans.get(i).getAction()); 
			System.out.println("Condition: " + trans.get(i).getCondition()); 
			System.out.println("Event: " + trans.get(i).getEvent()); 
			System.out.println("To: " + trans.get(i).getTo()); 
			System.out.println("From: " + trans.get(i).getFrom()); 
			System.out.println(); 
		}
	}

}
