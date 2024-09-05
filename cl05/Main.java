import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {

        Customer c1 = new Customer();
        c1.setName("Jose");
        List<ShoppingItem> items1 = new ArrayList<ShoppingItem>();
        items1.add(new ShoppingItem("SI0001", "Air Fryer", 5));
        items1.add(new ShoppingItem("S10002", "Blender", 3));
        items1.add(new ShoppingItem("SI0003", "TV", 10));
        c1.setShoppingCart(items1);

        Customer c2 = new Customer();
        c2.setName("Gabriel");
        List<ShoppingItem> items2 = new ArrayList<ShoppingItem>();
        items2.add(new ShoppingItem("SI0001", "Sound Bar", 7));
        items2.add(new ShoppingItem("S10002", "TV", 10));
        items2.add(new ShoppingItem("SI0003", "Refrigerator", 12));
        c2.setShoppingCart(items2);

        long initialTime = System.currentTimeMillis();

        CashierThread t1 = new CashierThread("C0001", "Diana", c1, initialTime);
        CashierThread t2 = new CashierThread("C0002", "Pedro", c2, initialTime);

        t1.start();
        t2.start();

        /*Cashier k1 = new Cashier();
        k1.setCashierCode("C0001");
        k1.setName("Diana");

        Cashier k2 = new Cashier();
        k2.setCashierCode("C0002");
        k2.setName("Pedro");

        long startTime = System.currentTimeMillis();

        k1.checkout(c1, startTime);
        k2.checkout(c2, startTime);*/
    }
}
