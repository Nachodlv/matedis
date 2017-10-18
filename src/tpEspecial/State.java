package tpEspecial;

import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public interface State {

    public State transition(char character);

    public List<State> transitions(char character);

    /**
     *
     * @param transition
     * @param character
     * @return false if the character is already in the transitions, true if the adding was successful
     */
    public boolean addTransition(State transition, char character);

    public boolean isAcceptance();

    public void setLabel(String label);

    public String getLabel();

}
