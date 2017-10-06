package tpEspecial;

/**
 * Created by Dwape on 10/6/17.
 */
public interface State {

    public State transitions(char character);

    public void addTransition(State transition, char character);
}
