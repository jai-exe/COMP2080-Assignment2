package assignment2;

public class Player
    {
        public String name;
        public Backpack backpack;
        public int numItems;
        public double money;

        public Player(String n)
        {
            name = n;
            money = 45;
            numItems = 0;
            backpack = new Backpack();
        }

        public void buy(Weapon w)
        {
            System.out.println(w.weaponName+" bought...");
            backpack.addFront(w);
            numItems++;
            System.out.println(numItems);
        }
        public void withdraw(double amt)
        {
            money = money - amt;
        }

        public boolean inventoryFull()
        {
            return (numItems == 10) ;
        }

        public void printCharacter()
        {
            System.out.println(" Name:"+name+"\n Money:"+money);
            printBackpack();
        }

        public void printBackpack()
        {
            System.out.println(" "+name+", you own "+numItems+" Weapons:");
            backpack.printList();
        }
    }

