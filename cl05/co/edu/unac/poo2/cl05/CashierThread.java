public class CashierThread extends Thread {

    private String cashierCode;
    private String name;
    private Customer customer;
    private long initialTime;

    public CashierThread(String cashierCode, String name, Customer customer, long initialTime) {
        this.cashierCode = cashierCode;
        this.name = name;
        this.customer = customer;
        this.initialTime = initialTime;
    }

    @Override
    public void run() {
        System.out.println("Cashier [" + this.name + "] starts checkout process for Client [" + this.customer.getName() + "] in " + (System.currentTimeMillis() - this.initialTime) / 1000 + " sec.");
        for (ShoppingItem item : this.customer.getShoppingCart()) {
            this.waitNSeconds(item.getSecondsForCheckout());
            System.out.println("Processing product: " + item.toString() + " --> " + (System.currentTimeMillis() - this.initialTime) / 1000 + " sec.");
        }
        System.out.println("Cashier [" + this.name + "] finished checkout process for Client [" + this.customer.getName() + "] in " + (System.currentTimeMillis() - this.initialTime) / 1000 + " sec.");
    }

    private void waitNSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
