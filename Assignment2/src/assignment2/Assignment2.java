package assignment2;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Assignment2 {

    public static int getInteger(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextInt()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextInt();
        }
        
        public static double getDouble(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextDouble()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextDouble();
        }
        
    
            public static int getIntegerInRange(Scanner sc, String message, int lower, int upper) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            sc.nextLine(); //clear the invalid input ...
            System.out.print(message);
        }
        int choice = sc.nextInt();
        while (true) {
            if (lower <= choice && upper >= choice) {
                return choice;
            } else {
                System.out.println("Range value is out of range");
                return getIntegerInRange(sc, message, lower, upper);
            }
        }
    }


    public static void addWeapons(ArrayManager h, Scanner sc) {
        System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
        String weaponName;
        int weaponRange;
        int weaponDamage;
        double weaponWeight;
        double weaponCost;
        int quantity;
        System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
        weaponName = sc.next().toLowerCase();
        while (weaponName.compareTo("end") != 0) {
            if (!weaponName.matches("[0-9]")) {
                if (h.exists(weaponName) && h.get(weaponName).item.deleted == false) { // if weapon exists then only add quantity
                    System.out.println("That weapon already exists");
                    quantity = getInteger(sc, "Please enter amount of stock you would like to add:");
                    h.addQuantity(weaponName, quantity);
                } else {
                    weaponRange = getIntegerInRange(sc, "Please enter the Range of the Weapon (0-10):", 0, 10);
                    weaponDamage = getInteger(sc, "Please enter the Damage of the Weapon:");
                    weaponWeight = getDouble(sc, "Please enter the Weight of the Weapon (in pounds):");
                    weaponCost = getDouble(sc, "Please enter the Cost of the Weapon:");
                    Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                    quantity = getInteger(sc, "Please enter the quantity in stock:");
                    h.put(w, quantity);
                }
            }
            System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
            weaponName = sc.next().toLowerCase();
        }
    }


    public static void showRoomMenu(ArrayManager ht, Player p) {
        System.out.println("WELCOME TO THE SHOWROOM!!!!");
        if (ht.getNumItems() == 0) {
            System.out.println(" ** There are no weapons in the shop");
            System.out.println("Type 'end' to return to the main menu");
            return;
        }
        ht.printTable();
        System.out.println("You have " + p.money + " money.");
        System.out.println("Please select a weapon to buy('end' to quit):");
    }


    public static void showRoom(ArrayManager ht, Player p, Scanner sc) {
        String choice;
        showRoomMenu(ht, p);
        choice = sc.next().toLowerCase();
        while (choice.compareTo("end") != 0) {
            if (!choice.matches("[0-9]")) {
                ShopItem si = ht.get(choice);
                if (si != null) {
                    if (0 > (p.money - si.item.cost)) {
                        System.out.println("\n** You can not afford this item! (" + si.item.weaponName +  ")\n");
                    } else if (p.backpack.itemsMaxed(1)) {
                        System.out.println("\n** You don't have space for this item! (" + si.item.weaponName +  ")\n");
                    } else if (p.backpack.weightMaxed(si.item.weight)) {
                        System.out.println("\n** Your backpack is too heavy for this item! (" + si.item.weaponName +  ")\n");
                    } else {
                        if (si.numberInStock > 0) {
                            p.buy(si.item);
                            p.withdraw(si.item.cost);
                            si.numberInStock--;
                        } else {
                            System.out.println("\n** Item is out of stock!");
                        }
                    }
                } else {
                    System.out.println("\n ** " + choice + " not found!! **");
                }
            }
            showRoomMenu(ht, p);
            choice = sc.next();
        }
        System.out.println("");
    }


    public static void deleteWeaponsMenu(ArrayManager ht, Scanner sc) {
        System.out.println("***DELETE ITEMS FROM SHOP***");
        if (ht.getNumItems() == 0) {
            System.out.println("There are no weapons in the shop");
            System.out.println("Type 'end' to return to the main menu");
            return;
        }
        ht.printTable();
        System.out.println("Please select a weapon to delete('end' to quit)");
    }


    public static void deleteWeapons(ArrayManager ht, Player p, Scanner sc) {
        String choice;
        String confirmation;
        deleteWeaponsMenu(ht, sc);
        choice = sc.next();
        while (choice.compareTo("end") != 0) {
            if (!choice.matches("[0-9]")) {
                if (ht.exists(choice.toLowerCase())) {
                    System.out.println("Are you sure you would like to delete the following ('y' or 'n')");
                    System.out.println(ht.get(choice).toString());
                    confirmation = sc.next().toLowerCase();
                    if (confirmation.compareTo("y") == 0) {
                        System.out.println(ht.get(choice).toString() + " successfully deleted");
                        ht.delete(choice.toLowerCase());
                    }
                } else {
                    System.out.println("Item does not exist");
                }
            }
            deleteWeaponsMenu(ht, sc);
            choice = sc.next();
        }
        System.out.println("");
    }


    public static void viewPlayer(Player p, Scanner sc) {
        String choice = "";
        System.out.println("Here is " + p.name + "! ('back' to return to main menu)");
        System.out.println("----------------------------------------");
        while (choice.compareTo("back") != 0) {
            p.printCharacter();
            choice = sc.next();
        }
        System.out.println("");
    }


    public static void mainMenu(ArrayManager ht, Player p, Scanner sc) {
        System.out.println("** WELCOME TO WEAPON QUEST **");
        System.out.println("Please choose an option. ('end' to quit)");
        System.out.println("1. Add items to the shop");
        System.out.println("2. Delete items from the shop");
        System.out.println("3. Buy items from the shop");
        System.out.println("4. View backpack");
        System.out.println("5. View player");
        System.out.println("6. Exit");
    }


    public static void viewBackpack(Player p, Scanner sc) {
        String choice = "";
        System.out.println("Here is " + p.name + "'s backpack! ('back' to return to main menu)");
        System.out.println("----------------------------------------");
        while (choice.compareTo("back") != 0) {
            double weightPercent = Math.round(p.backpack.currWeight / p.backpack.maxWeight * 100);
            double itemPercent;
            if (p.backpack.itemsMaxed(1)) {
                itemPercent = 100.00;
            } else {
                itemPercent = Math.ceil(p.backpack.numItems / (p.backpack.maxItems * p.backpack.loadFactor) * 100);
            }
            System.out.println("Current Weight: " + p.backpack.currWeight + "(" + weightPercent + "% full)");
            System.out.println("Current Number of items: " + p.backpack.numItems + "(" + itemPercent + "% full)");
            p.backpack.printBackpack();
            choice = sc.next();
        }
        System.out.println("");
    }


    public static void mainMenuChoice(ArrayManager ht, Player p, Scanner sc) {
        String choice;
        mainMenu(ht, p, sc);
        choice = sc.next();
        while (choice.compareTo("end") != 0 && choice.compareTo("6") != 0) {


            switch (choice) {
                case "1":
                    addWeapons(ht, sc);
                    break;
                case "2":
                    deleteWeapons(ht, p, sc);
                    break;
                case "3":
                    showRoom(ht, p, sc);
                    break;
                case "4":
                    viewBackpack(p, sc);
                    break;
                case "5":
                    viewPlayer(p, sc);
                    break;
                default:
                    System.out.println("\n** Invalid choice **");
                    break;
            }
            mainMenu(ht, p, sc);
            choice = sc.next();
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pname;
        System.out.println("Please enter Player name:");
        pname = sc.next();
        Player pl = new Player(pname, 45, 37, 0.82);
        ArrayManager ht = new ArrayManager(101, 0.8);
        mainMenuChoice(ht, pl, sc);
    }


    
}
