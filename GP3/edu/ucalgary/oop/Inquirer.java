package edu.ucalgary.oop;

public class Inquirer extends Person {
    private final String INFO;
    private final String SERVICES_PHONE;

    public Inquirer(String FIRST_NAME, String LAST_NAME, String dateOfBirth, String SERVICES_PHONE, String INFO) {
        super(FIRST_NAME, LAST_NAME, dateOfBirth);
        this.SERVICES_PHONE = SERVICES_PHONE;
        this.INFO = INFO;

    }

    public String getServicesPhoneNum() {
        return this.SERVICES_PHONE;
    }

    public String getInfo() {
        return this.INFO;
    }
}
