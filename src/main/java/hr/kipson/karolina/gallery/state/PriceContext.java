package hr.kipson.karolina.gallery.state;

public class PriceContext {
    private State state;

    public PriceContext(State state) {
        this.state = state;
    }

    public void change() {

        state.changePrice(this);

    }
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
