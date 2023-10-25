package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddressInfo {
    private Map<Integer, Address> addressMap = new HashMap<>();
    private List<org.example.AddressHierarchy> hierarchyList = new ArrayList<>();

    public AddressInfo() {
        loadAddressData("AS_ADDR_OBJ.txt");
        loadHierarchyData("AS_ADM_HIERARCHY.txt");
    }

    public void loadAddressData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) !=null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    int objectId = Integer.parseInt(parts[0]);
                    String typeName = parts[2];
                    String name = parts[1];
                    addressMap.put(objectId, new Address(objectId, name, typeName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHierarchyData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine())!=null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int objectId = Integer.parseInt(parts[0]);
                    int parentId = Integer.parseInt(parts[1]);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date startDate = dateFormat.parse(parts[2]);
                    Date endDate = dateFormat.parse(parts[3]);
                    hierarchyList.add(new AddressHierarchy(objectId, parentId, startDate, endDate));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAddressDescriptions(String date, List<Integer> objectIds) {
        List<String> descriptions = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date targetDate;

        try {
            targetDate = dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd.");
            return descriptions;
        }

        for (org.example.AddressHierarchy hierarchy : hierarchyList) {
            if (targetDate.after(hierarchy.getStartDate()) && targetDate.before(hierarchy.getEndDate())
                    && objectIds.contains(hierarchy.getObjectId())) {
                descriptions.add(hierarchy.getObjectId() + ": " + addressMap.get(hierarchy.getObjectId()).getDescription());
            }
        }
        return descriptions;
    }
}
