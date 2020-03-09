package ui.objectsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin {

    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String password;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String zip;
    private String addressFirst;
    private String addressSecond;
    private String currency;
    private String dateFormat;

    private String profilePicturePath;
    private String profilePictureName;

    private String companyLogoPath;
    private String companyLogoName;

    private List<Customer> customers = new ArrayList<>();




    public String getFullPathOfProfilePicture(){
        try {
            return profilePicturePath + profilePictureName;
        } catch (NullPointerException e){
            return "Profile Picture name or/and Profile Picture Path wasn't set !";
        }
    }

    public String getFullPathOfCompanyLogo(){
        try {
            return companyLogoPath + companyLogoName;
        } catch (NullPointerException e){
            return "Company Logo name or/and Company Logo Path wasn't set !";
        }
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddressFirst() {
        return addressFirst;
    }

    public void setAddressFirst(String addressFirst) {
        this.addressFirst = addressFirst;
    }

    public String getAddressSecond() {
        return addressSecond;
    }

    public void setAddressSecond(String addressSecond) {
        this.addressSecond = addressSecond;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getProfilePictureName() {
        return profilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) {
        this.profilePictureName = profilePictureName;
    }

    public String getCompanyLogoName() {
        return companyLogoName;
    }

    public void setCompanyLogoName(String companyLogoName) {
        this.companyLogoName = companyLogoName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", addressFirst='" + addressFirst + '\'' +
                ", addressSecond='" + addressSecond + '\'' +
                ", currency='" + currency + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", profilePicturePath='" + profilePicturePath + '\'' +
                ", profilePictureName='" + profilePictureName + '\'' +
                ", companyLogoPath='" + companyLogoPath + '\'' +
                ", companyLogoName='" + companyLogoName + '\'' +
                ", customers=" + customers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(firstName, admin.firstName) &&
                Objects.equals(lastName, admin.lastName) &&
                Objects.equals(company, admin.company) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(phone, admin.phone) &&
                Objects.equals(country, admin.country) &&
                Objects.equals(state, admin.state) &&
                Objects.equals(city, admin.city) &&
                Objects.equals(zip, admin.zip) &&
                Objects.equals(addressFirst, admin.addressFirst) &&
                Objects.equals(addressSecond, admin.addressSecond) &&
                Objects.equals(currency, admin.currency) &&
                Objects.equals(dateFormat, admin.dateFormat) &&
                Objects.equals(profilePictureName, admin.profilePictureName) &&
                Objects.equals(companyLogoName, admin.companyLogoName) &&
                Objects.equals(customers, admin.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, company, email, password, phone, country, state, city, zip, addressFirst, addressSecond, currency, dateFormat, profilePictureName, companyLogoName, customers);
    }
}
