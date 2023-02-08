package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.JelaManager;
import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.domain.Restorani;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Amar Grizovic
 * CLI (Command Line Interface) implementation in following class
 * Even though this type of presentation layer (called CLI) is becoming past tense for GUI apps
 * it's good to see how you can manipulate data through command line and database also
 *
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addRestaurant = new Option("r","add-restaurant",false, "Adding new restaurant to ordering-system database");
    private static final Option addMeal = new Option("m","add-meal",false, "Adding new meal to ordering-system database");
    private static final Option getRestaurants = new Option("getR", "get-restaurants",false, "Printing all restaurants from ordering-system database");
    private static final Option getMeals = new Option("getM", "get-meals",false, "Printing all meals from ordering-system database");
    private static final Option restaurantDefinition = new Option(null, "restaurant",false, "Defining restaurant for next added meal");
    private static final Option priceDefinition = new Option(null, "price", false, "Defining price for next added meal");
    private static final Option descriptionDefinition = new Option(null, "description", false, "Defining description for next added meal");
    private static final Option managerDefinition = new Option(null, "manager", false, "Defining manager for next added restaurant");
    private static final Option locationDefinition = new Option(null,"location",false,"Defining location for next added restaurant");

    /**
     * Printing on console screen how to properly use commands.
     * @param options available options that can be used
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekatrpr.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * Adding options that can be used on console screen.
     * @return all options
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addRestaurant);
        options.addOption(addMeal);
        options.addOption(getRestaurants);
        options.addOption(getMeals);
        options.addOption(restaurantDefinition);
        options.addOption(priceDefinition);
        options.addOption(descriptionDefinition);
        options.addOption(managerDefinition);
        options.addOption(locationDefinition);
        return options;
    }

    /**
     * Searching the specific restaurant in list of all restaurants.
     * @param listOfRestaurants list of all restaurants in database
     * @param restaurantName name of specific restaraurant which is being searched
     * @return all information about restaurant if it has been found
     */
    public static Restorani searchThroughRestaurants(List<Restorani> listOfRestaurants, String restaurantName) {
        Restorani restorani = null;
        restorani = listOfRestaurants.stream().filter(rest -> rest.getNaziv().toLowerCase().equals(restaurantName.toLowerCase())).findAny().get();
        return restorani;
    }


    /**
     * Main program for manipulation with console screen commands.
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if((cl.hasOption(addMeal.getOpt()) || cl.hasOption(addMeal.getLongOpt())) && cl.hasOption((restaurantDefinition.getLongOpt())) && cl.hasOption((priceDefinition.getLongOpt())) && cl.hasOption((descriptionDefinition.getLongOpt()))){
            JelaManager jelaManager = new JelaManager();
            RestoraniManager restoraniManager = new RestoraniManager();
            Restorani restorani = null;
            try {
                restorani = searchThroughRestaurants(restoraniManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no restaurant in the list! Try again.");
                System.exit(1);
            }
            Jela j = new Jela();
            j.setIdRestorana(restorani.getId());
            j.setJelo(cl.getArgList().get(0));
            j.setCijena(Double.parseDouble(cl.getArgList().get(2)));
            j.setOpis(cl.getArgList().get(3));
            jelaManager.add(j);
            System.out.println("You successfully added meal to database!");
        } else if(cl.hasOption(getMeals.getOpt()) || cl.hasOption(getMeals.getLongOpt())){
            JelaManager jelaManager = new JelaManager();
            jelaManager.getAll().forEach(j -> System.out.println(j.getJelo()));
        } else if(cl.hasOption(addRestaurant.getOpt()) || cl.hasOption(addRestaurant.getLongOpt()) && cl.hasOption(managerDefinition.getLongOpt()) && cl.hasOption(locationDefinition.getLongOpt())){
            try {
                RestoraniManager restoraniManager = new RestoraniManager();
                Restorani rest = new Restorani();
                rest.setNaziv(cl.getArgList().get(0));
                rest.setVlasnik(cl.getArgList().get(1));
                rest.setLokacija(cl.getArgList().get(2));
                restoraniManager.add(rest);
                System.out.println("Restaurant has been added successfully");
            }catch(Exception e) {
                System.out.println("There is already restaurant with same name in database! Try again");
                System.exit(1);
            }

        } else if(cl.hasOption(getRestaurants.getOpt()) || cl.hasOption(getRestaurants.getLongOpt())){
            RestoraniManager restoraniManager = new RestoraniManager();
            restoraniManager.getAll().forEach(r -> System.out.println(r.getNaziv()));
        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
