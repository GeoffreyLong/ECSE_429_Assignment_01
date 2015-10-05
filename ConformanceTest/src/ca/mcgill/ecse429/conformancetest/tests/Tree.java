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
		
		// This is to find all the leaf states (those that have no transitions out)
		endStates.addAll(stateMachine.getStates());
		for (Transition tran : transitions){
			if (endStates.contains(tran.getFrom())){
				endStates.remove(tran.getFrom());
			}
		}
		
		// Begin generating paths from the start node
		for (Transition tran : transitions){
			if (tran.getEvent().equals("@ctor")){
				List<Transition> temp = new LinkedList<Transition>();
				temp.add(tran);
				breadthFirst((LinkedList<Transition>) temp);				
			}
		}
	}
	
	// Recursive function to generate all the paths of the tree
	private List breadthFirst(LinkedList<Transition> temp){
		// Get the last transition on the current path
		Transition curTransition = temp.get(temp.size()-1);
		
		for(Transition tran : transitions){
			// Want every transition that has "curTransition" as a jumping off point
			if (curTransition.getTo().getName().equals(tran.getFrom().getName())){
				// Clone the path so that the original does not get muddled
				LinkedList<Transition> newTemp = (LinkedList<Transition>) temp.clone();
				
				// Method to see if the state is a leaf state
				// If it isn't then continue recurring
				// If it is then the path is completed, so add to set of paths
				if (!isLeaf(newTemp, tran)){
					newTemp.add(tran);
					breadthFirst(newTemp);
				}
				else{
					// If the new path isn't already a path, then add it to the set of paths
					if(!paths.contains(newTemp)) paths.add(newTemp);
				}
			}
		}
		
		return temp;
	}

	// Check if the current node is a leaf state
	private boolean isLeaf(LinkedList<Transition> list, Transition checked){
		// Add the state the path is coming from in case it hasn't already been marked as visited
		visited.add(checked.getFrom());
		
		for (Transition tran : list){		
			// If the final state on the path has been visited then add the transition and the state is a leaf
			if (visited.contains(checked.getTo())){
				list.add(checked);
				return true;
			}
			
			// If the final state on the path is a leaf then add the transition and the state is a leaf
			if (endStates.contains(checked.getTo())){
				list.add(checked);
				return true;
			}
		}
		
		// The state is now on the path, so must be marked as visited
		visited.add(checked.getTo());	
		return false;
	}
	
	public List<LinkedList<Transition>> getPaths(){
		return this.paths;
	}
}