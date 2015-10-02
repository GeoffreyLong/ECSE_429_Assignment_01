package ca.mcgill.ecse429.conformancetest.tests;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceStateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceXStream;

public class TestGenerator {
	PrintWriter writer;
	
	public TestGenerator() {
		try {
			writer = new PrintWriter("the-file-name.java", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StateMachine mach = getStateMachine();
		printFileHeader(mach);
		printSetUpMethod(mach);
		
		
		List<LinkedList<Transition>> paths = getPaths(mach);
		int i = 1;
		for(LinkedList<Transition> path : paths){
			generateTest(path, i);
			i ++;
		}

		/*
		System.out.println(mach.getPackageName());
		System.out.println(mach.getClassName());
		System.out.println(mach.getClass());
		*/
		
		/*
		List<Transition> trans = mach.getTransitions();
		
		for (int i = 0; i < trans.size(); i++) {
			System.out.println("Action: " + trans.get(i).getAction()); 
			System.out.println("Condition: " + trans.get(i).getCondition()); 
			System.out.println("Event: " + trans.get(i).getEvent()); 
			System.out.println("To: " + trans.get(i).getTo()); 
			System.out.println("From: " + trans.get(i).getFrom()); 
			System.out.println(); 
		}
		*/
		
		printFileClose();
		writer.close();
	}
	
	private void printFileHeader(StateMachine mach) {
		// Package name
		print(0,"package " + mach.getPackageName() + ".test;");

		// Imports
		print(0,"import " + mach.getPackageName() + ";");
		print(0,"import org.junit.*;");
		print(0,"");
		
		// Name of testing class
		print(0,"public class Test" + mach.getClassName().split("\\.")[0] + " {");
	}
	
	private void printSetUpMethod(StateMachine mach) {
		String className = mach.getClassName().split("\\.")[0];
		print(1,"private " + className + " classObj;");
		print(1,"");
		print(1,"@Before");
		
		print(1,"public void setUp() throws Exception {");
		
		//TODO Perhaps will require parameter to constructor
		print(2,"classObj = new " + className + "();");
		
		print(1,"}");
	}

	private void generateTest(LinkedList<Transition> path, int i) {
		// Print method header
		print(1,"");
		print(1,"@Test");
		print(1,"public void testConformance_" + i + "() {");
		
		for (Transition tran : path){
			// Could put in a setUp BeforeEach or whatever?
			// These are the variables that may change as a result of actions i.e. curQtrs = curQtrs + 1
			
			if (!tran.getEvent().equals("@ctor")){
				// "start" isn't a state
				String parameter = "(" + /* no params?  + */ ")";
				print(2,"classObj." + tran.getEvent() + parameter);			
			}
			print(2,"assertEquals(classObj.getStateFullName()," + tran.getTo().getName() +")");
		}
		
		print(1,"}");
	}
	
	private void printFileClose(){
		print(0,"}");
	}

	private StateMachine getStateMachine(){
		PersistenceStateMachine stateMach = new PersistenceStateMachine(); 
		stateMach.loadStateMachine("ccoinbox.xml");
		
		PersistenceXStream pers = new PersistenceXStream(); 
		StateMachine mach = (StateMachine) pers.loadFromXMLwithXStream();
		return mach;		
	}
	
	private void getMachineData(){
		
	}
	
	private List<LinkedList<Transition>> getPaths(StateMachine mach){
		Tree tree = new Tree(mach);
		return tree.getPaths();
	}
	
	private void print(int tabLevel, String toPrint){
		String printString = "";
		for(int i = 0; i < tabLevel; i++){
			printString += "\t";
		}
		printString += toPrint;
		writer.println(printString);
	}
}
