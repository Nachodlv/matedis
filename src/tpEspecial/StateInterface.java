package tpEspecial;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dwape on 10/6/17.
 */
public interface StateInterface {

    public List<StateInterface> transitions(char character);

    public StateInterface AddTransition(char character);
}
