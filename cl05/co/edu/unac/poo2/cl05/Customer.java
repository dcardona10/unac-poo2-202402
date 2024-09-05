import java.util.List;

public class Customer {
    
    private String customerCode;
    private String name;
    private List<ShoppingItem> shoppingCart;
    
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ShoppingItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
