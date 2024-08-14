package co.edu.unac.poo2.cl02.items;

public class User {
    
    public int userId;
    public String name;
    public String address;
    public String phoneNumber;

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
                + "]";
    }    
}
