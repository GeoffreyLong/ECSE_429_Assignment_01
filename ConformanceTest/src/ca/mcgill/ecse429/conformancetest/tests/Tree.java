package ca.mcgill.ecse429.conformancetest.tests;

import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;

public class Tree {
	private List<Transition> transitions;
	private List<LinkedList<Transition>> paths;
	private List<State> endStates = new LinkedList<State>();
	
	public Tree(StateMachine stateMachine){
		List<State> states = stateMachine.getStates();
		transitions = stateMachine.getTransitions();
		paths = new LinkedList<LinkedList<Transition>>();
			
		endStates.addAll(stateMachine.getStates());
		
		for (Transition tran : transitions){
			if (endStates.contains(tran.getFrom())){
				endStates.remove(tran.getFrom());
			}
		}
		
		for (Transition tran : transitions){
			if (tran.getEvent().equals("@ctor")){
				List<Transition> temp = new LinkedList<Transition>();
				temp.add(tran);
				breadthFirst((LinkedList<Transition>) temp);				
			}
		}
	}
	
	private List breadthFirst(LinkedList<Transition> temp){
		Transition curTransition = temp.get(temp.size()-1);
		
		for(Transition tran : transitions){

			if (curTransition.getTo().getName().equals(tran.getFrom().getName())){
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
			
			if (endStates.contains(checked.getTo())){
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