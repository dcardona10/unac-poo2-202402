public class ShoppingItem {
    
    private String code;
    private String description;
    private int secondsForCheckout;

    public ShoppingItem(String code, String description, int secondsForCheckout) {
        this.code = code;
        this.description = description;
        this.secondsForCheckout = secondsForCheckout;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ShoppingItem [code=" + code + ", description=" + description + "]";
    }

    public int getSecondsForCheckout() {
        return secondsForCheckout;
    }

    public void setSecondsForCheckout(int secondsForCheckout) {
        this.secondsForCheckout = secondsForCheckout;
    }
}
