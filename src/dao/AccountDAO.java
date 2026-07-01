package dao;

import model.Account;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    // Create Account
    public void createAccount(Account account) {

        String sql = "INSERT INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, account.getAccountNumber());
            ps.setString(2, account.getHolderName());
            ps.setInt(3, account.getAge());
            ps.setString(4, account.getGender());
            ps.setString(5, account.getPhone());
            ps.setString(6, account.getEmail());
            ps.setString(7, account.getAddress());
            ps.setString(8, account.getAccountType());
            ps.setDouble(9, account.getBalance());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("\nAccount Created Successfully!");
            }

            connection.close();

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                System.out.println("Account Number Already Exists!");
            } else {
                e.printStackTrace();
            }

        }

    }

    // View All Accounts
    public void viewAccounts() {

        String sql = "SELECT * FROM accounts";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===============================================================================================");
            System.out.printf("%-15s %-20s %-12s %-15s %-12s%n",
                    "ACC NO", "NAME", "TYPE", "PHONE", "BALANCE");
            System.out.println("===============================================================================================");

            while (rs.next()) {

                System.out.printf("%-15d %-20s %-12s %-15s %-12.2f%n",
                        rs.getLong("account_number"),
                        rs.getString("holder_name"),
                        rs.getString("account_type"),
                        rs.getString("phone"),
                        rs.getDouble("balance"));

            }

            System.out.println("===============================================================================================");

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Search Account
    public void searchAccount(long accountNumber) {

        String sql = "SELECT * FROM accounts WHERE account_number = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n========== ACCOUNT DETAILS ==========");

                System.out.println("Account Number : " + rs.getLong("account_number"));
                System.out.println("Holder Name    : " + rs.getString("holder_name"));
                System.out.println("Age            : " + rs.getInt("age"));
                System.out.println("Gender         : " + rs.getString("gender"));
                System.out.println("Phone          : " + rs.getString("phone"));
                System.out.println("Email          : " + rs.getString("email"));
                System.out.println("Address        : " + rs.getString("address"));
                System.out.println("Account Type   : " + rs.getString("account_type"));
                System.out.println("Balance        : ₹" + rs.getDouble("balance"));

            } else {

                System.out.println("Account Not Found!");

            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Deposit Money
    public void deposit(long accountNumber, double amount) {

        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setLong(2, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("\n₹" + amount + " Deposited Successfully!");

                checkBalance(accountNumber);

            } else {

                System.out.println("Account Not Found!");

            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Check Balance
    public void checkBalance(long accountNumber) {

        String sql = "SELECT balance FROM accounts WHERE account_number = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Current Balance : ₹" + rs.getDouble("balance"));

            } else {

                System.out.println("Account Not Found!");

            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }



    }
    // Withdraw Money
    public void withdraw(long accountNumber, double amount) {

        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        String updateSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";

        try {

            Connection connection = DBConnection.getConnection();

            // Check account balance
            PreparedStatement checkPs = connection.prepareStatement(checkSql);
            checkPs.setLong(1, accountNumber);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                double currentBalance = rs.getDouble("balance");

                if (amount > currentBalance) {

                    System.out.println("Insufficient Balance!");

                } else {

                    PreparedStatement updatePs = connection.prepareStatement(updateSql);

                    updatePs.setDouble(1, amount);
                    updatePs.setLong(2, accountNumber);

                    updatePs.executeUpdate();

                    System.out.println("\n₹" + amount + " Withdrawn Successfully!");

                    checkBalance(accountNumber);

                }

            } else {

                System.out.println("Account Not Found!");

            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    // Transfer Money
    public void transferMoney(long fromAccount, long toAccount, double amount) {

        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        String withdrawSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String depositSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            // Check Sender Balance
            PreparedStatement checkPs = connection.prepareStatement(checkSql);
            checkPs.setLong(1, fromAccount);

            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {

                System.out.println("Sender Account Not Found!");
                return;

            }

            double balance = rs.getDouble("balance");

            if (balance < amount) {

                System.out.println("Insufficient Balance!");
                return;

            }

            // Withdraw from sender
            PreparedStatement withdrawPs = connection.prepareStatement(withdrawSql);
            withdrawPs.setDouble(1, amount);
            withdrawPs.setLong(2, fromAccount);

            int row1 = withdrawPs.executeUpdate();

            // Deposit to receiver
            PreparedStatement depositPs = connection.prepareStatement(depositSql);
            depositPs.setDouble(1, amount);
            depositPs.setLong(2, toAccount);

            int row2 = depositPs.executeUpdate();

            if (row1 > 0 && row2 > 0) {

                connection.commit();

                System.out.println("\nTransfer Successful!");

            } else {

                connection.rollback();

                System.out.println("Transfer Failed!");

            }

            connection.close();

        } catch (SQLException e) {

            try {

                if (connection != null) {
                    connection.rollback();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        }

    }
    // Update Account Details
    public void updateAccount(long accountNumber, String holderName,
                              String phone, String email,
                              String address, String accountType) {

        String sql = "UPDATE accounts SET holder_name=?, phone=?, email=?, address=?, account_type=? WHERE account_number=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, holderName);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, accountType);
            ps.setLong(6, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("\nAccount Updated Successfully!");

            } else {

                System.out.println("Account Not Found!");

            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    // Delete Account
    public void deleteAccount(long accountNumber) {

        String sql = "DELETE FROM accounts WHERE account_number=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Account Deleted Successfully!");

            } else {

                System.out.println("Account Not Found!");

            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
}