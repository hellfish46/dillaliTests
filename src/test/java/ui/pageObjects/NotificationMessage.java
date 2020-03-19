package ui.pageObjects;

public enum NotificationMessage {
    CUSTOMERUPDATED("Customer updated successfully"),
    CUSTOMERCREATED("Customer created successfully"),
    CUSTOMERDELETED("Customer deleted successfully"),

    FILLCOMPANYINFO("Please ensure all required company details added before you can create invoices"),
    SETTINGSUPDATED("Preferences updated successfully"),

    INVOICECREATED("Invoice created successfully"),
    INVOICEUPDATED("Invoice updated successfully"),
    INVOICESENT("Invoice sent successfully"),
    INVOICEDELETED("Invoice deleted successfully"),

    EMAILISTAKEN("The email has already been taken."),
    EMAILINUSE("Email already in use"),

    LOGINSUCCESS("Login Successful");



    private String message;
    NotificationMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
