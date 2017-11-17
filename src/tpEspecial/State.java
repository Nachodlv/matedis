package tpEspecial;

import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public interface State {

    State transition(char character);

    /**
     *
     * @param transition
     * @param character
     * @return false if the character is already in the transitions, true if the adding was successful
     */
    boolean addTransition(State transition, char character);

    boolean isAcceptance();

    void setLabel(String label);

    String getLabel();

}
