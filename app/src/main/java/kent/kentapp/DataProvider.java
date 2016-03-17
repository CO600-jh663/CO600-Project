package kent.kentapp;

import java.util.*;

public class DataProvider {

    public static HashMap<String, List<String>> getDataHashMap() {
        HashMap<String, List<String>> optionsHashMap = new HashMap<String, List<String>>();

        List<String> medicalCentreList = new ArrayList<String>();
        List<String> securityList = new ArrayList<String>();
        List<String> financeList = new ArrayList<String>();

        for (int i = 0; i < DataArrays.medicalCentre.length; i++) {
            medicalCentreList.add(DataArrays.medicalCentre[i]);
        }

        for (int i = 0; i < DataArrays.security.length; i++) {
            securityList.add(DataArrays.security[i]);
        }

        for (int i = 0; i < DataArrays.finances.length; i++) {
            financeList.add(DataArrays.finances[i]);
        }

        optionsHashMap.put("Medical Centre", medicalCentreList);
        optionsHashMap.put("Campus Security", securityList);
        optionsHashMap.put("Finance", financeList);

        return optionsHashMap;

    }
}
