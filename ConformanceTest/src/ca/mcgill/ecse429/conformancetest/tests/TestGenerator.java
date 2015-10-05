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

	}

	public void run(String xml){
		// Instantiate the state machine
		StateMachine mach = getStateMachine(xml);
		
		// Create new java file to write to
		String className = "GeneratedTest" + mach.getClassName();
		String filePath = System.getProperty("user.dir");
		String packageName = mach.getPackageName().replaceAll("\\.", "/");
		try {
			writer = new PrintWriter(filePath+ "/src/" + packageName + "/" + className, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		printFileHeader(mach, className);
		printSetUpMethod(mach);
		
		// Generate a test for each path through the RTP tree
		List<LinkedList<Transition>> paths = getPaths(mach);
		int i = 1;
		for(LinkedList<Transition> path : paths){
			generateTest(path, i);
			i ++;
		}
		
		printFileClose();
		writer.close();
	}
	
	private void printFileHeader(StateMachine mach, String className) {
		// Package name
		print(0,"package " + mach.getPackageName() + ";");

		// Imports
		print(0,"import " + mach.getPackageName() + ".*;");
		print(0,"import org.junit.*;");
		print(0,"import static org.junit.Assert.*;");
		print(0,"");
		
		// Name of testing class
		print(0,"public class " + className.split("\\.")[0] + " {");
	}
	
	private void printSetUpMethod(StateMachine mach) {
		// Instantiate a class object of the tested class's type
		String className = mach.getClassName().split("\\.")[0];
		print(1,"private " + className + " classObj;");
		print(1,"");
		print(1,"@Before");
		
		// Populate the class object
		print(1,"public void setUp() throws Exception {");
		print(2,"classObj = new " + className + "();");
		print(1,"}");
	}

	private void generateTest(LinkedList<Transition> path, int i) {
		// Print method header
		print(1,"");
		print(1,"@Test");
		print(1,"public void testConformance_" + i + "() {");

		List<String> declaredVars = new LinkedList<String>();
		for (Transition tran : path){
			// These are the variables that may change as a result of actions i.e. curQtrs = curQtrs + 1
			String[] actions = tran.getAction().split(";");

			// These are the variables used in the tested class
			List<String> vars = new LinkedList<String>();

			// Save the vars and their expected values to be checked after the state change
			int k = 0;
			for (String action: actions){
				action = action.trim();
				if (action != ""){	
					
					String[] tokens = action.split("=");
					vars.add(new String(tokens[0].substring(0, 1).toUpperCase() + tokens[0].substring(1)).trim());
					
					String newValue = "";
					String[] valueTokens = tokens[1].split(" ");
					for (int j = 0; j < valueTokens.length; j++){
						if (valueTokens[j].matches("[a-zA-Z]+") 
								&& !(valueTokens[j].equals("true") || valueTokens[j].equals("false"))){
							newValue += "classObj.get" + valueTokens[j].substring(0, 1).toUpperCase() + valueTokens[j].substring(1) + "()";
						}
						else{
							newValue += valueTokens[j];
						}
					}
					
					// Ensure there are no double declarations
					if (declaredVars.contains(vars.get(k))){
						print(2,"expected" + vars.get(k) + " = " + newValue + ";");						
					}
					else{
						print(2,"Object expected" + vars.get(k) + " = " + newValue + ";");	
						declaredVars.add(vars.get(k));
					}
					k++;
				}
			}
			

			// Generate the assertions for the conditions
			if (tran.getCondition() != ""){
				String newValue = "";
				String[] valueTokens = tran.getCondition().split(" ");
				for (int j = 0; j < valueTokens.length; j++){
					if (valueTokens[j].matches("[a-zA-Z]+") 
							&& !(valueTokens[j].equals("true") || valueTokens[j].equals("false"))){
						newValue += "classObj.get" + valueTokens[j].substring(0, 1).toUpperCase() + valueTokens[j].substring(1) + "()";
					}
					else if (valueTokens[j].contains("()")
							&& !(valueTokens[j].equals("true") || valueTokens[j].equals("false"))){
						if (valueTokens[j].substring(0, 1).equals("!")){
							newValue += "!classObj." + valueTokens[j].substring(1);
						}
						else{
							newValue += "classObj." + valueTokens[j];
						}
					}
					else{
						newValue += valueTokens[j];
					}
				}
				
				print(2,"assertTrue(" + newValue + ");");
			}
			
			// Initiate the transition
			// "start" isn't a state, don't use the transition
			if (!tran.getEvent().equals("@ctor")){
				String parameter = "(" + /* no params?  + */ ");";
				print(2,"classObj." + tran.getEvent() + parameter);			
			}
			
			// Check that the correct state was entered into
			print(2,"assertEquals(classObj.getStateFullName(),\"" + tran.getTo().getName() +"\");");
			
			// Check that the vars have the correct value after the state change
			for (String var : vars){
				print(2,"assertEquals(expected" + var + ", classObj.get" + var + "());");				
			}
		}
		
		print(1,"}");
	}
	
	private void printFileClose(){
		print(0,"}");
	}

	private StateMachine getStateMachine(String xml){
		PersistenceStateMachine stateMach = new PersistenceStateMachine(); 
		stateMach.loadStateMachine(xml);
		
		PersistenceXStream pers = new PersistenceXStream(); 
		StateMachine mach = (StateMachine) pers.loadFromXMLwithXStream();
		return mach;		
	}
	
	private List<LinkedList<Transition>> getPaths(StateMachine mach){
		Tree tree = new Tree(mach);
		return tree.getPaths();
	}
	
	// A small method to make formatting the prints easier
	private void print(int tabLevel, String toPrint){
		String printString = "";
		for(int i = 0; i < tabLevel; i++){
			printString += "\t";
		}
		printString += toPrint;
		writer.println(printString);
	}
}
