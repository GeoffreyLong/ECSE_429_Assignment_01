package ca.mcgill.ecse429.conformancetest.tests;

import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;

public class Tree {
	private List<Transition> transitions;
	private List<LinkedList<Transition>> paths;
	
	public Tree(StateMachine stateMachine){
		List<State> states = stateMachine.getStates();
		transitions = stateMachine.getTransitions();
		// State startState = stateMachine.getStartState();
		
		// states.get(0).getName();
		// transitions.get(0).getTo();
		// transitions.get(0).getFrom();
		paths = new LinkedList<LinkedList<Transition>>();
		
		for (Transition tran : transitions){
			if (tran.getEvent().equals("@ctor")){
				List temp = new LinkedList<Transition>();
				temp.add(tran);
				breadthFirst((LinkedList<Transition>) temp);				
			}
		}

		for(LinkedList<Transition> path : paths){
			for (Transition tran : path){
				System.out.println(tran.getFrom().getName() + " " + tran.getTo().getName() + " " + tran.getEvent());
			}
			System.out.println();
		}
		
	}
	
	private List breadthFirst(LinkedList<Transition> temp){
		Transition curTransition = temp.get(temp.size()-1);
		
		for(Transition tran : transitions){
			if (curTransition.getTo().getName().equals(tran.getFrom().getName())){
				// This one iterates through ALL of the transitions
				//if (!temp.contains(tran)){
					//temp.add(tran);
				if (!checkExistence(temp, tran)){
					LinkedList<Transition> newTemp = (LinkedList<Transition>) temp.clone();
					newTemp.add(tran);
					breadthFirst(newTemp);
				}
				else{
					if(!paths.contains(temp)) paths.add(temp);
				}
			}
		}
		
		return temp;
	}

	private boolean checkExistence(LinkedList<Transition> list, Transition checked){
		// System.out.println("1: " + checked.getFrom().getName() + " " + checked.getTo().getName() + " " + checked.getEvent());

		for (Transition tran : list){
			// System.out.println("2: " + tran.getFrom().getName() + " " + tran.getTo().getName() + " " + tran.getEvent());
			
			if (tran.getFrom().equals(checked.getTo())){
				// System.out.println("3: " + tran.getFrom().getName() + " " + checked.getTo().getName());
				return true;
			}
		}
		
		return false;
	}
	
	public List<LinkedList<Transition>> getPaths(){
		return this.paths;
	}
}