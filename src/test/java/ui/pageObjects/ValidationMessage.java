package ui.pageObjects;

public enum ValidationMessage {
    FIELDISREQUIRED("Field is required"),
    INCORRECTEMAIL("Incorrect Email."),
    PASSWORDMINIMUM("Password must contain 6 characters"),
    WRONGINPUTE("Accepts alphabet, space and \"-.,\""),
    REPEATPASSWORD("Passwords must be identical");



    private String message;
    ValidationMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
