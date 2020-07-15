package ui.pageObjects;

public enum Month {
    JAN("January"),
    FEB("February"),
    MAR("March"),
    APR("April"),
    MAY("May"),
    JUN("June"),
    JUL("July"),
    AUG("August"),
    SEP("September"),
    OCT("October"),
    NOV("November"),
    DEC("December");

    private String fullNameOfMonth;

    Month(String fullNameOfMonth){
        this.fullNameOfMonth = fullNameOfMonth;
    }

}
