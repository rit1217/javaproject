package com.local;

public class UserData {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String firstName;
    private String lastName;
    private String zip_code;
    private String province;
    private String accType;
    private String cardNum = "";
    private String cardName = "";
    private String cardCvv = "";

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getProvince() {
        return province;
    }

    public String getAccType() {
        return accType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address, String province, String zip_code) {
        this.address = address;
        this.province = province;
        this.zip_code = zip_code;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public UserData( String username, String password, String email, String phoneNumber, String firstName, String lastName ) {
        this.username = username ;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accType = "Customer";
        this.cardNum = UserDatabaseModel.cardNum(username);
        this.cardName = UserDatabaseModel.cardName(username);
        this.cardCvv = UserDatabaseModel.cardCvv(username);
    }

    public void setCard( String cardNum, String fullName, String cvv) {
        this.cardNum = cardNum;
        this.cardName = fullName;
        this.cardCvv = cvv;
        UserDatabaseModel.insertCard(this);
    }



    public String getCardNum() {
        return cardNum;
    }

    public String getCardName() { return cardName; }

    public String getCardCvv() { return cardCvv; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAdmin() {
        this.accType = "Admin";
    }
    @Override
    public String toString() {
        return "UserData{" +
                "username = " + username + '\n' +
                ", password = " + password + '\n' +
                ", email = " + email + '\n' +
                ", phoneNumber = " + phoneNumber + '\n' +
                ", address = " + address + '\n' +
                ", firstName = " + firstName + '\n' +
                ", lastName = " + lastName + '\n' +
                '}';
    }
}
