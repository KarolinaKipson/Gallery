package hr.kipson.karolina.gallery.state;

public class LowPriceState implements State {
    @Override
    public void changePrice(PriceContext context) {
        context.setState(new HighPriceState());
    }
}
