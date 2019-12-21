package com.petrichor.java.language.emunstudy;

public enum QueryType {
    BUSINESS, SOURCE, SERVICE;

    public static QueryType getTypeByValue(int value) {
        for (QueryType enums : QueryType.values()) {
            if (enums.ordinal() == value) {
                return enums;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        QueryType typeByValue = QueryType.getTypeByValue(1);
        if (typeByValue == null) {
            return;

        }
        switch (typeByValue) {
            case BUSINESS:
                System.out.println("a");
                break;
            case SOURCE:
                System.out.println("b");
                break;
            case SERVICE:
                System.out.println("c");
                break;
        }

    }
}
