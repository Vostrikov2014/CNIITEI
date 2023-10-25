package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AddressInfo addressInfo = new AddressInfo();
        String date = "2010-01-01";
        List<Integer> objectIds = Arrays.asList(1422396, 1450759, 1449192, 1451562);

        List<String> descriptions = addressInfo.getAddressDescriptions(date, objectIds);
        for (String description : descriptions) {
            System.out.println(description);
        }
    }
}
