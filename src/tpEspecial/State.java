package tpEspecial;

/**
 * Created by Dwape on 10/6/17.
 */
public interface State {

    public State transition(char character);

    public void addTransition(State transition, char character);

    public boolean isAcceptance();
}
