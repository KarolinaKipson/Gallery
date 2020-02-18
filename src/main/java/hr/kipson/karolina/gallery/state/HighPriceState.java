package hr.kipson.karolina.gallery.state;

public class HighPriceState implements State {

    @Override
    public void changePrice(PriceContext context) {
         context.setState(new LowPriceState());
    }
}
