import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //STORAGE
        HashMap<String,Country>countries=new HashMap<String, Country>(); //countryName , country
        //CREATE COUNTRIES FROM DATA FILE
        //ExcelPage excelPage = new ExcelPage("SDI2013.xlsx",0);
        ExcelPage excelPage = new ExcelPage("sdi_2013abbr withPOPnGDP.xlsx",0);
        String[] strings = excelPage.getRow(0);
        int countryNameCol=0;
        int countryCodeCol=0;
        int indicatorNameCol=0;
        int valueCol=0;
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].equals("Country Name")){
                countryNameCol=i;
                continue;
            }
            if(strings[i].equals("Country Code")){
                countryCodeCol = i;
                continue;
            }
            if(strings[i].equals("Indicator Name")){
                indicatorNameCol=i;
                continue;
            }
            if(strings[i].equals("Value")){
                valueCol=i;
                continue;
            }
            System.out.println(strings[i]);
        }
        String []row=excelPage.getRow(1);
        for (int i = 1;row!=null; i++) {
            if(!countries.containsKey(row[countryNameCol])){
                countries.put(row[countryNameCol],new Country(row[countryNameCol],row[countryCodeCol]));
                //System.out.println("<p><a href=\"http://wdc-app.herokuapp.com/app/tef_practice/"+row[countryCodeCol]+"\">"+row[countryNameCol]+"</a></p>");
            }
            try {
                countries.get(row[countryNameCol]).addValue(row[indicatorNameCol],row[valueCol]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            row=excelPage.getRow(i+1);
        }

        //READ REGIONS
        ExcelPage regions = new ExcelPage("Regions_amp_amp_Groups.xlsx",3);
        HashMap<String,String> regionUAEN = ExcelPage.getVocabulary(new ExcelPage("Regions_amp_amp_Groups.xlsx",1),1,1,3);
        HashMap<String,String> countryUAEN = ExcelPage.getVocabulary(new ExcelPage("Regions_amp_amp_Groups.xlsx",0),1,3,2);
        HashMap<String,String> regionUACO = ExcelPage.getVocabulary(new ExcelPage("Regions_amp_amp_Groups.xlsx",1),1,1,6);
        HashMap<String,Set<String>> regionCountries=new HashMap<String, Set<String>>();
        HashMap<String,String> countryENCO = ExcelPage.getVocabulary(new ExcelPage("Regions_amp_amp_Groups.xlsx",0),1,2,1);
        //SET region value to Country
        for (int j = 0; j < regionUAEN.size(); j++) {
            String[] s=regions.getCol(j, 1);
            for (int i = 1; i < s.length; i++) {
                if(countries.containsKey(countryUAEN.get(s[i]))) {
                    try {
                        countries.get(countryUAEN.get(s[i])).addGroup(regionUAEN.get(s[0]),regionUACO.get(s[0]));
                        if(!regionCountries.containsKey(regionUAEN.get(s[0]))){
                            regionCountries.put(regionUAEN.get(s[0]),new HashSet<String>());
                        }
                        regionCountries.get(regionUAEN.get(s[0])).add(countryUAEN.get(s[i]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
         }
        System.out.println(regionCountries);
        System.out.println(countryENCO);
        //SET REGIONS COUNTRIES TO COUNTRY
            Country.addAllGroups(regionCountries, countryENCO);
        //GET JSON from Countries
        FileInputStream fileInputStream = new FileInputStream("empty.json");
        Scanner scanner = new Scanner(fileInputStream);
        String empty = new String();
        while(scanner.hasNext()){
            empty+=scanner.nextLine();
        }
        FileInputStream fileInputStream2 = new FileInputStream("input.json");
        Scanner scanner2 = new Scanner(fileInputStream2);
        String template = new String();
        while(scanner2.hasNext()){
            template+=scanner2.nextLine();
        }
        Object[] objects=countries.values().toArray();
        String json = ((Country) objects[0]).toPage(template);
        for (int i = 1; i < objects.length; i++) {
            json+=",\n" + ((Country) objects[i]).toPage(template);
        }
        json=empty.replace("//TODO ALBANIA", json);
        PrintWriter pw = new PrintWriter(new File("output.json"));
        pw.println(json);
        pw.close();

        /*for(String key : countries.keySet()){
            System.out.println(countries.get(key));
        }*/
    }
}

