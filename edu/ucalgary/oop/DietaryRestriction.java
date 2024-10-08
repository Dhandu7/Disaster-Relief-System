package edu.ucalgary.oop;

public enum DietaryRestriction {
    AVML("Asian vegetarian meal"),
    DBML("Diabetic meal"),
    GFML("Gluten intolerant meal"),
    KSML("Kosher meal"),
    LSML("Low salt meal"),
    MOML("Muslim meal"),
    PFML("Peanut-free meal"),
    VGML("Vegan meal"),
    VJML("Vegetarian Jain meal");

    private final String description;

    DietaryRestriction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescriptions() {
        StringBuilder descriptions = new StringBuilder();
        for (DietaryRestriction restriction : DietaryRestriction.values()) {
            descriptions.append(restriction.name()).append(" - ").append(restriction.getDescription()).append("\n");
        }
        return descriptions.toString();
    }
}
