package co.edu.unac.poo2.cl04;

import java.util.*;

import co.edu.unac.poo2.cl04.items.*;
import co.edu.unac.poo2.cl04.util.*;

public class Main {

    public static List<User> users = new ArrayList<User>();
    public static List<Account> accounts = new ArrayList<Account>();

    public static final User findUser(String userId) throws UserException {
        User user = null;
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                user = u;
            } else {
                throw new UserException("User already exists");
            }
        }
        return user;
    }

    public static final User newUser(String userId) throws UserException {
        User user = null;
        for (User u : users) {
            if (!u.getUserId().equals(userId)) {
                user = u;
            } else {
                throw new UserException("User already exists");
            }
        }
        return user;
    }

    public static final Account findAccount(String accountId) throws AccountException {
        Account account = null;
        for (Account a : accounts) {
            if (!a.getAccountId().equals(accountId)) {
                account = a;
            } else {
                throw new AccountException("Account already exists");
            }
        }
        return account;
    }
    
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int option = -1;
        
        do {
            System.out.println("Welcome to the library system. Please mark an option");
            System.out.println("1. Create User");
            System.out.println("2. Create Account");
            System.out.println("0. Exit");
            System.out.println("------------------------");
            option = scan.nextInt();

            switch(option) {
                case 1:
                    try {
                        System.out.print("User ID: ");
                        String userId = scan.next();
                        if (newUser(userId) == null) { 
                            System.out.print("Name: ");
                            String userName = scan.next();
                            System.out.print("Address: ");
                            String address = scan.next();
                            System.out.print("Phone Number: ");
                            String phoneNumber = scan.next();
                            User newUser = new User();
                            newUser.setUserId(userId);
                            newUser.setName(userName);
                            newUser.setAddress(address);
                            newUser.setPhoneNumber(phoneNumber);
                            users.add(newUser);
                        }
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                        e.printStackTrace();
                    } finally {
                        System.out.println("User transaction finished");
                    }
                break;
                case 2:
                    try {
                        System.out.print("Account ID: ");
                        String accountId = scan.next();
                        if (findAccount(accountId) == null) {
                            System.out.print("User ID: ");
                            String accountUserId = scan.next();
                            if (findUser(accountUserId) != null) {
                                Account newAccount = new Account();
                                newAccount.setAccountId(accountId);
                                newAccount.setUser(findUser(accountUserId));
                                newAccount.setStatus('1');
                                accounts.add(newAccount);
                            } else {
                                throw new AccountException("Cannot link Account", new UserException("User not found"));
                            }
                        }
                    } catch (AccountException e) {
                        System.out.println("Exception: " + e.getMessage());
                        e.printStackTrace();
                    } catch (UserException e) {
                        System.out.println("Exception: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        } while(option != 0);

        scan.close();
    }
}
