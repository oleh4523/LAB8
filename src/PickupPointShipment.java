public class PickupPointShipment extends ShipmentOrder {

    private String lockerSize;
    private boolean fragile;

    public PickupPointShipment(String orderNumber, String customerName,
                               double distanceKm, double baseFee, boolean insured,
                               String lockerSize, boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 0.75;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee;

        switch (lockerSize) {
            case "S":
                fee = 5;
                break;
            case "M":
                fee = 10;
                break;
            case "L":
                fee = 18;
                break;
            default:
                fee = 0;
        }

        if (fragile) {
            fee += 12;
        }

        return fee;
    }

    @Override
    protected void validateSpecificRules() {
        if (!lockerSize.equals("S") &&
                !lockerSize.equals("M") &&
                !lockerSize.equals("L")) {
            throw new IllegalArgumentException("Invalid locker size");
        }
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
    }
}