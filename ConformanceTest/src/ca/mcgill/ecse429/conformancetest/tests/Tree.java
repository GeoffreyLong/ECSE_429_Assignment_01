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
	private List<State> visited = new LinkedList<State>();
	
	public Tree(StateMachine stateMachine){
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
		visited.add(checked.getFrom());
		for (Transition tran : list){		

			if (visited.contains(checked.getTo())){
				list.add(checked);
				return true;
			}
			
			if (endStates.contains(checked.getTo())){
				list.add(checked);
				return true;
			}
		}
		visited.add(checked.getTo());	
		return false;
	}
	
	public List<LinkedList<Transition>> getPaths(){
		return this.paths;
	}
}