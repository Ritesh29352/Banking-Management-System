package model;

public class Account {

    private long accountNumber;
    private String holderName;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String accountType;
    private double balance;

    // Default Constructor
    public Account() {
    }

    // Parameterized Constructor
    public Account(long accountNumber, String holderName, int age,
                   String gender, String phone, String email,
                   String address, String accountType, double balance) {

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and Setters

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // toString() Method

    @Override
    public String toString() {
        return "Account {" +
                "\nAccount Number = " + accountNumber +
                "\nHolder Name    = " + holderName +
                "\nAge            = " + age +
                "\nGender         = " + gender +
                "\nPhone          = " + phone +
                "\nEmail          = " + email +
                "\nAddress        = " + address +
                "\nAccount Type   = " + accountType +
                "\nBalance        = " + balance +
                "\n}";
    }
}