package ui.objectsUI;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    String invoiceNumber;
    String dueDate;
    String invoiceDate;
    String poNumber;
    String paymentMethod;

    double discount;
    String tax;

    Customer customer;

    List<Item> items = new ArrayList<>();



}
