public class Cashier {
    
    private String cashierCode;
    private String name;

    public String getCashierCode() {
        return cashierCode;
    }

    public void setCashierCode(String cashierCode) {
        this.cashierCode = cashierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void checkout(Customer customer, long timestamp) {
        
        System.out.println("Cashier [" + this.name + "] starts checkout process for Client [" + customer.getName() + "] in " + (System.currentTimeMillis() - timestamp) / 1000 + " sec.");
        for (ShoppingItem item : customer.getShoppingCart()) {
            this.waitNSeconds(item.getSecondsForCheckout());
            System.out.println("Processing product: " + item.toString() + " --> " + (System.currentTimeMillis() - timestamp) / 1000 + " sec.");
        }
        System.out.println("Cashier [" + this.name + "] finished checkout process for Client [" + customer.getName() + "] in " + (System.currentTimeMillis() - timestamp) / 1000 + " sec.");
    }

    private void waitNSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
