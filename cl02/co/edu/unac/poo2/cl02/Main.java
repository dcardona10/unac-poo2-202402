package co.edu.unac.poo2.cl02;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import co.edu.unac.poo2.cl02.items.*;

public class Main {

    static int userId;

    static List<User> users = new ArrayList<User>();
    static Map<Integer, Account> accounts = new HashMap<Integer, Account>();
    
    static User findUser(int userId) {
        for (User u : users) {
            if (u.userId == userId) {
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
            System.out.println("2. Create Account");
            System.out.println("3. View Users");
            System.out.println("4. View Accounts");
            System.out.println("5. Search User by Name");
            System.out.println("0. Exit");
            System.out.println("------------------------");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    User user = new User();
                    System.out.print("User ID: ");
                    user.userId = scan.nextInt();
                    if (findUser(user.userId) == null) {
                        System.out.print("Name: ");
                        user.name = scan.next();
                        System.out.print("Address: ");
                        user.address = scan.next();
                        System.out.print("Phone Number: ");
                        user.phoneNumber = scan.next();
                        System.out.println("New User: " + user.toString());
                        users.add(user);
                    } else {
                        System.out.println("User with ID [" + userId + "] already exists");
                    }
                    break;
                case 2: 
                    Account account = new Account();
                    System.out.print("User ID: ");
                    userId = scan.nextInt();
                    if(!findAccount(userId)) {
                        System.out.print("Account ID: ");
                        account.accountId = scan.nextInt();
                        System.out.print("User ID: ");
                        if (findUser(userId) != null) {
                            account.user = findUser(userId);
                            account.openedDate = new Date();
                            accounts.put(userId, account);
                            System.out.println("New Account: " + account.toString());
                        } else {
                            System.out.println("User not found");
                        }                        

                    } else {
                        System.out.println("Account with ID [" + account.accountId + "] already exists");
                    }
                    break;
                case 3:
                    users.forEach(u -> System.out.println(u.toString()));
                    break;
                case 4:
                    accounts.values().forEach(a -> System.out.println(a.toString()));
                    break;
                case 5:
                    System.out.print("Insert keyword to start search: ");
                    String keyword = scan.next();
                    List<User> usersByName = users.stream()
                                                .filter(u -> u.name.contains(keyword))
                                                .map(u -> u)
                                                .collect(Collectors.toList());
                    usersByName.forEach(u -> System.out.println(u.toString()));
                default:
                    break;
            }
        }

        scan.close();
    }
}
