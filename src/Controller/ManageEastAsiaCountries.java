package Controller;

import Model.EastAsiaCountries;
import Model.Library;
import View.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ManageEastAsiaCountries extends Menu {
    static String[] mchoice = {
            "Input the information of 11 countries in East Asia",
            "Display the information of country you've just input",
            "Search the information of country by user-entered name",
            "Display the information of countries sorted name in ascending order",
            "Exit"};
    protected Library week4;
    ArrayList<EastAsiaCountries> countriesList = new ArrayList<>();

    public ManageEastAsiaCountries() {
        super("\n\t\t\t\t\t\t\t\t\tMENU", mchoice);
        week4 = new Library();
        countriesList = new ArrayList<>();
    }

    @Override
    public void execute(int n) {
        if (n == mchoice.length) System.exit(0);

        switch (n) {
            case 1: //input 11 countries
                try {
                    addCountryInformation();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 2: //display country have enter
                try {
                    displayRecentlyEnteredInformation();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 3: //search by name
                try {
                    searchInformationByName();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 4: //display by sort
                try {
                    displayInformationByAscendingOrder();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                System.out.println("Bye~~~~");
                System.exit(0);
        }
    }

    Scanner scanner = new Scanner(System.in);
    public void addCountryInformation() throws Exception {
        System.out.println("\nEnter information for a country~~~~");

        System.out.print("Country Code: ");
        String code = scanner.nextLine();

        System.out.print("Country Name: ");
        String name = scanner.nextLine();

        System.out.print("Total Area: ");
        float area = scanner.nextFloat();
        if (area <= 0) {
            throw new Exception("Total area must be greater than 0.");
        }

        scanner.nextLine();
        System.out.print("Country Terrain: ");
        String terrain = scanner.nextLine();

        EastAsiaCountries country = new EastAsiaCountries(code, name, area, terrain);
        countriesList.add(country);

        System.out.println("Country information added successfully.");
    }

    public void displayRecentlyEnteredInformation() throws Exception {
        if (countriesList.isEmpty()) {
            throw new Exception("No country information available.");
        }
        //EastAsiaCountries recentlyEntered = countriesList.get(countriesList.size() - 1);
        System.out.println("\nRecently Entered Country Information~~~~");
        System.out.println("ID\t\tName\t\t\tTotal Area\t\tTerrain");
        /*recentlyEntered.display();*/
        for (EastAsiaCountries country : countriesList) {
            country.display();
            System.out.println();
        }
    }

    public void searchInformationByName() throws Exception {
        System.out.print("Enter Country Name to search: ");
        String searchName = scanner.nextLine();

        ArrayList<EastAsiaCountries> result = new ArrayList<>();
        for (EastAsiaCountries country : countriesList) {
            if (country.getCountryName().equalsIgnoreCase(searchName)) {
                result.add(country);
            }
        }

        if (result.isEmpty()) {
            throw new Exception("Country not found.");
        }

        System.out.println("Search Result:");
        for (EastAsiaCountries country : result) {
            country.display();
        }
    }

    public void displayInformationByAscendingOrder() throws Exception {
        if (countriesList.isEmpty()) {
            throw new Exception("No country information available.");
        }

        ArrayList<EastAsiaCountries> sortedList = new ArrayList<>(countriesList);
        Collections.sort(sortedList, Comparator.comparing(EastAsiaCountries::getCountryName));

        System.out.println("Country Information Sorted by Name (Ascending Order):");
        for (EastAsiaCountries country : sortedList) {
            country.display();
        }
    }

}
