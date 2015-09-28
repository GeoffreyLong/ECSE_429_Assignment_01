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
				LinkedList<Transition> newTemp = (LinkedList<Transition>) temp.clone();
				if (!checkExistence(newTemp, tran)){
					
					newTemp.add(tran);
					breadthFirst(newTemp);
				}
				else{
					if(!paths.contains(newTemp)) paths.add(newTemp);
				}
			}
		}
		
		return temp;
	}

	private boolean checkExistence(LinkedList<Transition> list, Transition checked){
		for (Transition tran : list){			
			if (tran.getFrom().equals(tran.getTo())){
				return true;
			}
			
			if (checked.getFrom().equals(checked.getTo())){
				list.add(checked);
				return true;
			}
			
			if (tran.getFrom().equals(checked.getTo()) && !tran.getFrom().equals(tran.getTo())){
				list.add(checked);
				return true;
			}
		}
		
		return false;
	}
	
	public List<LinkedList<Transition>> getPaths(){
		return this.paths;
	}
}