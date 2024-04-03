package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class InquirerManager {
    private List<Inquirer> inquirers;
    private List<List<String>> interactions;

    public InquirerManager() {
        inquirers = new ArrayList<>();
        interactions = new ArrayList<>();
    }

    public void addInteraction(Inquirer inquirer, String interaction) {
        int index = getInquirerIndex(inquirer);
        if (index == -1) {
            inquirers.add(inquirer);
            List<String> newInteractionList = new ArrayList<>();
            newInteractionList.add(interaction);
            interactions.add(newInteractionList);
        } else {
            List<String> inquirerInteractions = interactions.get(index);
            inquirerInteractions.add(interaction);
        }
    }

    public List<String> getInteractions(Inquirer inquirer) {
        int index = getInquirerIndex(inquirer);
        if (index != -1) {
            return interactions.get(index);
        }
        return new ArrayList<>();
    }

    private int getInquirerIndex(Inquirer inquirer) {
        for (int i = 0; i < inquirers.size(); i++) {
            if (inquirers.get(i).equals(inquirer)) {
                return i;
            }
        }
        return -1;
    }
}
