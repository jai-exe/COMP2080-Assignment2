package assignment2;

import java.util.*;

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
        
    
        public static void addWeapons(ArrayManager h,Scanner sc)
        {
            System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
            String weaponName; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
            int quantity;
            System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
            weaponName=sc.next();
            while (weaponName.compareTo("end") != 0)
            {
                weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
                weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:"); 
                weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
                weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
                Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                quantity=getInteger(sc,"Please enter the quantity in stock:"); 
                h.put(w,quantity);
                System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
                weaponName = sc.next();
            }
        }



        public static void showRoomMenu(ArrayManager ht,Player p){
            System.out.println("WELCOME TO THE SHOWROOM!!!!");
            ht.printTable();
            System.out.println("You have "+p.money+" money.");
            System.out.println("Please select a weapon to buy('end' to quit):");
        }
        
        public static void showRoom(ArrayManager ht, Player p,Scanner sc)
        {
            String choice;
            showRoomMenu(ht,p);
            choice=sc.next();
            while (choice.compareTo("end") != 0 && !p.inventoryFull())
            {
                ShopItem si = ht.get(choice);
                if (si != null)
                {

                        p.buy(si.item);
                        p.withdraw(si.item.cost);
                        si.numberInStock--;
 
                }
                else
                {
                    System.out.println(" ** "+choice+" not found!! **" );
                }
                showRoomMenu(ht,p);
                choice = sc.next();
            }
            System.out.println("");
        }
        
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            String pname;
            System.out.println("Please enter Player name:");
            pname=sc.next();
            Player pl= new Player(pname);
            ArrayManager ht= new ArrayManager();
            addWeapons(ht,sc);
            showRoom(ht, pl,sc);
            pl.printCharacter();

        }

    
}
