package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class Inquirer extends Person {
    private final String INFO;
    private final String SERVICES_PHONE;
    private final InquirerManager inquirerManager;
    private List<String> interactions; // Maintain a list of interactions with this inquirer

    public Inquirer(String FIRST_NAME, String LAST_NAME, String dateOfBirth, String SERVICES_PHONE, String INFO) {
        super(FIRST_NAME, LAST_NAME, dateOfBirth);
        this.SERVICES_PHONE = SERVICES_PHONE;
        this.INFO = INFO;
        this.inquirerManager = new InquirerManager();
        this.interactions = new ArrayList<>();
    }

    public String getServicesPhoneNum() {
        return this.SERVICES_PHONE;
    }

    public String getInfo() {
        return this.INFO;
    }

    // Method to add an interaction to this inquirer's log
    public void addInteraction(String interaction) {
        interactions.add(interaction);
        inquirerManager.addInteraction(this, interaction); // Update the InquirerManager
    }

    // Method to get all interactions of this inquirer
    public List<String> getInteractions() {
        return interactions;
    }
}
