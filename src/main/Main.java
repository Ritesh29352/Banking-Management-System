package main;

import dao.AccountDAO;
import model.Account;

import java.util.Scanner;

public class Main {

    // Login Method
    public static boolean login(Scanner sc) {

        String username = "admin";
        String password = "admin123";

        System.out.println("========================================");
        System.out.println("      BANKING MANAGEMENT SYSTEM");
        System.out.println("========================================");

        System.out.print("Username : ");
        String user = sc.nextLine();

        System.out.print("Password : ");
        String pass = sc.nextLine();

        if (user.equals(username) && pass.equals(password)) {

            System.out.println("\nLogin Successful!\n");
            return true;

        } else {

            System.out.println("\nInvalid Username or Password!");
            return false;

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AccountDAO dao = new AccountDAO();

        // Login
        if (!login(sc)) {
            sc.close();
            return;
        }

        while (true) {

            System.out.println("\n========================================");
            System.out.println("      BANKING MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Search Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Transfer Money");
            System.out.println("7. Update Account");
            System.out.println("8. Delete Account");
            System.out.println("9. Check Balance");
            System.out.println("10. Exit");
            System.out.println("========================================");

            System.out.print("Enter Your Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n===== CREATE ACCOUNT =====");

                    System.out.print("Enter Account Number: ");
                    long accountNumber = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter Holder Name: ");
                    String holderName = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter Account Type (Savings/Current): ");
                    String accountType = sc.nextLine();

                    System.out.print("Enter Opening Balance: ");
                    double balance = sc.nextDouble();

                    Account account = new Account(
                            accountNumber,
                            holderName,
                            age,
                            gender,
                            phone,
                            email,
                            address,
                            accountType,
                            balance
                    );

                    dao.createAccount(account);

                    break;

                case 2:

                    System.out.println("\n===== VIEW ACCOUNTS =====");

                    dao.viewAccounts();

                    break;

                case 3:

                    System.out.print("Enter Account Number: ");

                    long searchAccount = sc.nextLong();

                    dao.searchAccount(searchAccount);

                    break;

                case 4:

                    System.out.println("\n===== DEPOSIT MONEY =====");

                    System.out.print("Enter Account Number: ");
                    long depositAccount = sc.nextLong();

                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = sc.nextDouble();

                    if (depositAmount <= 0) {

                        System.out.println("Invalid Amount!");

                    } else {

                        dao.deposit(depositAccount, depositAmount);

                    }

                    break;

                case 5:

                    System.out.println("\n===== WITHDRAW MONEY =====");

                    System.out.print("Enter Account Number: ");
                    long withdrawAccount = sc.nextLong();

                    System.out.print("Enter Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    if (withdrawAmount <= 0) {

                        System.out.println("Invalid Amount!");

                    } else {

                        dao.withdraw(withdrawAccount, withdrawAmount);

                    }

                    break;
                case 6:

                    System.out.println("\n===== TRANSFER MONEY =====");

                    System.out.print("Enter Sender Account Number: ");
                    long fromAccount = sc.nextLong();

                    System.out.print("Enter Receiver Account Number: ");
                    long toAccount = sc.nextLong();

                    System.out.print("Enter Amount: ");
                    double transferAmount = sc.nextDouble();

                    if (transferAmount <= 0) {

                        System.out.println("Invalid Amount!");

                    } else {

                        dao.transferMoney(fromAccount, toAccount, transferAmount);

                    }

                    break;
                case 7:

                    System.out.println("\n===== UPDATE ACCOUNT =====");

                    System.out.print("Enter Account Number: ");
                    long updateAccount = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter New Holder Name: ");
                    String newHolder = sc.nextLine();

                    System.out.print("Enter New Phone: ");
                    String newPhone = sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter New Address: ");
                    String newAddress = sc.nextLine();

                    System.out.print("Enter New Account Type (Savings/Current): ");
                    String newType = sc.nextLine();

                    dao.updateAccount(updateAccount, newHolder, newPhone,
                            newEmail, newAddress, newType);

                    break;

                case 8:

                    System.out.println("\n===== DELETE ACCOUNT =====");

                    System.out.print("Enter Account Number: ");
                    long deleteAccount = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Are you sure you want to delete this account? (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {

                        dao.deleteAccount(deleteAccount);

                    } else {

                        System.out.println("Deletion Cancelled.");

                    }

                    break;

                case 9:

                    System.out.println("\n===== CHECK BALANCE =====");

                    System.out.print("Enter Account Number: ");
                    long balanceAccount = sc.nextLong();

                    dao.checkBalance(balanceAccount);

                    break;

                case 10:

                    System.out.println("\n================================");
                    System.out.println("Thank You for Using");
                    System.out.println("Banking Management System");
                    System.out.println("================================");

                    sc.close();

                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Choice!");

            }

        }

    }

}