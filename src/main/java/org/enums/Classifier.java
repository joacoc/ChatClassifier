package org.enums;

public enum Classifier {

    J48(1, "J48"),
    NAIVE_BAYES(2, "Naive Bayes"),
    SMO(3, "SMO");

    private int code;
    private String description;

    Classifier(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Classifier getClassifier(String description) {
        for (Classifier status : values()) {
            if (status.description.equals(description)) {
                return status;
            }
        }
        return null;
    }

}
