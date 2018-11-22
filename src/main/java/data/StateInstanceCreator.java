package data;

import com.google.gson.InstanceCreator;
import domain.DeliveredState;
import domain.State;

import java.lang.reflect.Type;

public class StateInstanceCreator implements InstanceCreator<State> {

    @Override
    public State createInstance(Type type) {
        DeliveredState deliveredState = new DeliveredState();
        return deliveredState;
    }

}
