import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import co.edu.unac.poo2.cl03.items.*;

public class Main {

    static List<User> users = new ArrayList<User>();
    static Map<Integer, Account> accounts = new HashMap<Integer, Account>();

    static User findUser(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    static boolean findAccount(int userId) {
        if (accounts.get(userId) != null) return true;
        return false;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("Welcome to the library system. Please mark an option");
            System.out.println("1. Create User");
            System.out.println("2. Create Standard Account");
            System.out.println("3. Create Premium Account");
            System.out.println("4. Get List of Account");
            System.out.println("0. Exit");
            System.out.println("------------------------");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    System.out.print("User ID: ");
                    int userId = scan.nextInt();
                    if (findUser(userId) == null) {
                        System.out.print("Name: ");
                        String name = scan.next();
                        System.out.print("Address: ");
                        String address = scan.next();
                        System.out.print("Phone Number: ");
                        String phoneNumber = scan.next();
                        User user = new User(userId, name, address, phoneNumber);
                        System.out.println(user.toString());
                        users.add(user);
                    } else {
                        System.out.println("User with ID [" + userId + "] already exists");
                    }
                    break;
                case 2:
                    System.out.print("User ID: ");
                    int saUserId = scan.nextInt();
                    if (findUser(saUserId) != null) {
                        if (!findAccount(saUserId)) {
                            System.out.print("Account ID: ");
                            int accountId = scan.nextInt();
                            Account account = new StandardAccount(accountId, findUser(saUserId), new Date(), 10);
                            System.out.println(account.toString());
                            accounts.put(saUserId, account);
                        }
                    }
                    break;
                case 3:
                    System.out.print("User ID: ");
                    int paUserId = scan.nextInt();
                    if (findUser(paUserId) != null) {
                        if (!findAccount(paUserId)) {
                            System.out.print("Account ID: ");
                            int accountId = scan.nextInt();
                            PremiumAccount account = new PremiumAccount(accountId, findUser(paUserId), new Date(), 15);
                            System.out.print("Extend Loan? (Y/N): ");
                            String extendLoan = scan.next();
                            account.setExtendLoan(extendLoan.equals("Y") ? true : false);
                            System.out.println(account.toString());
                            accounts.put(paUserId, account);
                        }
                    }
                    break;
                case 4:
                    accounts.values().forEach(a -> {
                        System.out.println(a.toString());
                        System.out.println(a.showAccountType());
                        System.out.println(a.showDaysOfLoan());
                    });
                    break;
                default:
                    break;
            }
        }

        scan.close();
    }
}
