package hr.kipson.karolina.gallery.pricing;

@FunctionalInterface
public interface BillingStrategy {

    int getBillingPrice(int price);


    static BillingStrategy smallStrategy() {
        return price -> price / 2;
    }

    static BillingStrategy largeStrategy() {
        return price -> price * 2;
    }
}
