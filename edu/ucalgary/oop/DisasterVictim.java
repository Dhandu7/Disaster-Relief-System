package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumSet;

public class DisasterVictim extends Person {
    private static int counter = 0;
    private final int ASSIGNED_SOCIAL_ID;
    private ArrayList<FamilyRelation> familyConnections = new ArrayList<>();
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private ArrayList<Supply> personalBelongings;
    private final String ENTRY_DATE;
    private static final List<String> GENDER_OPTIONS = GenderOptionsReader.getGenderOptions();
    private String gender;
    private String comments;
    private EnumSet<DietaryRestriction> dietaryRestrictions;

    public DisasterVictim(String FIRST_NAME, String LAST_NAME, String dateOfBirth, String ENTRY_DATE) {
        super(FIRST_NAME, LAST_NAME, dateOfBirth);
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.dietaryRestrictions = EnumSet.noneOf(DietaryRestriction.class);
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    // constructor that uses gender
    public DisasterVictim(String FIRST_NAME, String LAST_NAME, String dateOfBirth, String ENTRY_DATE, String gender) {
        super(FIRST_NAME, LAST_NAME, dateOfBirth);
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.dietaryRestrictions = EnumSet.noneOf(DietaryRestriction.class);
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
        setGender(gender);
    }

    private static int generateSocialID() {
        counter++;
        return counter;
    }

    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

    // Getters and setters

    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    public ArrayList<FamilyRelation> getFamilyConnections() {
        return this.familyConnections;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return this.medicalRecords;
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return this.personalBelongings;
    }

    public void setFamilyConnections(ArrayList<FamilyRelation> connections) {
        this.familyConnections.clear();
        for (FamilyRelation newRecord : connections) {
            addFamilyConnection(newRecord);
        }
    }

    public void setMedicalRecords(ArrayList<MedicalRecord> records) {
        this.medicalRecords.clear();
        for (MedicalRecord newRecord : records) {
            addMedicalRecord(newRecord);
        }
    }

    public void setPersonalBelongings(ArrayList<Supply> belongings) {
        this.personalBelongings = belongings;
    }

    // Add a Supply to personalBelonging
    public void addPersonalBelonging(Supply supply) {
        if (personalBelongings == null) {
            personalBelongings = new ArrayList<>();
        }
        personalBelongings.add(supply);
    }

    // Remove a Supply from personalBelongings, we assume it only appears once
    public void removePersonalBelonging(Supply unwantedSupply) {
        personalBelongings.remove(unwantedSupply);
    }

    public void removeFamilyConnection(FamilyRelation exRelation) {
        if (familyConnections.contains(exRelation)) {
            familyConnections.remove(exRelation);
            exRelation.getPersonTwo().removeFamilyConnection(
                    new FamilyRelation(exRelation.getPersonTwo(), exRelation.getRelationshipTo(), this));
        }
    }

    public void addFamilyConnection(FamilyRelation relation) {
        if (!familyConnections.contains(relation)) {
            familyConnections.add(relation);
            DisasterVictim personTwo = relation.getPersonTwo();
            if (personTwo != null) {
                personTwo.addFamilyConnection(new FamilyRelation(personTwo, relation.getRelationshipTo(), this));
            }
        }
    }

    // Add a MedicalRecord to medicalRecords
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public String getEntryDate() {
        return ENTRY_DATE;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (GENDER_OPTIONS.contains(gender.toLowerCase())) {
            this.gender = gender.toLowerCase();
        } else {
            throw new IllegalArgumentException("Invalid gender option: " + gender);
        }
    }

    public void allocateSupply(Supply supply, Location location) {
        // Add the supply to the victim's personal belongings
        this.addPersonalBelonging(supply);
        // Remove the supply from the location's available supplies
        location.removeSupply(supply);
    }

    public EnumSet<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void addDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.add(restriction);
    }

    public void removeDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.remove(restriction);
    }

}
