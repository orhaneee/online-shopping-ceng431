package domain;

public class Shopping {

    private State state;

    public Shopping() {
        this.state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
