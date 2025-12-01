package system;

import core.Main;
import entity.NPC;
import entity.player.CatBreed;
import entity.player.CatColor;
import entity.player.Player;
import util.TextUtil;

public class Characters {
    public Player createCharacter() {
        TextUtil.printTitle("CHARACTER CREATION");

        System.out.print("Enter your cat's name: ");
        String name = Main.scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter a name: ");
            name = Main.scanner.nextLine().trim();
        }

        System.out.println("\n=== Choose Your Cat Breed (Class) ===\n");
        System.out.println("1. Persian (Mage) - Balanced magic user");
        System.out.println(CatBreed.PERSIAN.getAsciiArtIdle());

        System.out.println("2. Ragdoll (Ranged) - Quick and agile");
        System.out.println(CatBreed.RAGDOLL.getAsciiArtIdle());

        System.out.println("3. Puskal (Warrior) - Strong and tough");
        System.out.println(CatBreed.PUSKAL.getAsciiArtIdle());

        System.out.print("\nSelect breed (1-3): ");
        int breedChoice = Main.getIntInput(1, 3);

        CatBreed breed = CatBreed.values()[breedChoice - 1];

        System.out.println("\n=== Choose Your Cat Color (Elemental Trait) ===");
        System.out.println("1. Orange - Radiant Burst (300 dmg every 3 turns)");
        System.out.println("2. Black - Shadow Speed (Extra turn every 3 turns)");
        System.out.println("3. White - Healing Aura (Restore 200 HP every 3 turns)");
        System.out.println("4. Tilapia - Reflect Shield (Deflect damage every 3 turns)");
        System.out.print("\nSelect color (1-4): ");
        int colorChoice = Main.getIntInput(1, 4);

        CatColor color = CatColor.values()[colorChoice - 1];

        Player newPlayer = new Player(name, breed, color);

        //FIX delay and print center
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(157));
        TextUtil.typewriterPrintCentered("The " + color.ColoredName() + " " + breed.name() +  " named " + name + 
        " is ready for battle! It leaps into action, claws flashing and eyes burning with courage.", 30);
        TextUtil.typewriterPrintCentered("The fate of Pawshire rests on its paws!", 30);
        System.out.println("=".repeat(157));
        newPlayer.displayStats();

        Main.pause(2000);
        return newPlayer;
    }

    public void showNPCEncounter(String playerName) {
        Main.clearScreen();
        TextUtil.printTitle("PAWSHIRE VILLAGE");

        NPC maximo = new NPC("Maximo", "Wise old cat mentor");
        maximo.speak("Ah, " + playerName + "... you've returned, the Asonians took our kin to the Tower of Tails.");
        Main.pause(1500);

        NPC wankie = new NPC("Wankie", "Maximo's curious son");
        wankie.speak("They took Rowma, Necko, and Cleo! Please, help us!");
        Main.pause(1500);

        System.out.println("\n=== Your Response ===");
        System.out.println("1. Absolutely! Asonia won't stop me!");
        System.out.println("2. Surely I will save them all!");
        System.out.println("3. I'll save them, Maximo. You have my word, Pawshire will be free again.");
        System.out.print("\nChoose (1-3): ");
        Main.getIntInput(1, 3);

        //FIX TextUtil
        System.out.println("\n" + "=".repeat(157));
        TextUtil.typewriterPrintCentered("With courage burning bright, " + playerName + " journeys to Asonia", 35);
        TextUtil.typewriterPrintCentered("to reclaim Pawshire's freedom.", 35);
        System.out.println("=".repeat(157));
        Main.pause(2000);

        TextUtil.printCentered("""
            
                                  |>>>
                                  |
                    |>>>      _  _|_  _         |>>>
                    |        |;| |;| |;|        |
                _  _|_  _    \\.    .  /    _  _|_  _
               |;|_|;|_|;|    \\:. ,  /    |;|_|;|_|;|
               \\..      /    ||;   . |    \\.    .  /
                \\.  ,  /     ||:  .  |     \\:  .  /
                 ||:   |_   _ ||_ . _ | _   _||:   |
                 ||:  .|||_|;|_|;|_|;|_|;|_|;||:.  |
                 ||:   ||.    .     .      . ||:  .|
                 ||: . || .     . .   .  ,   ||:   |       \\,/
                 ||:   ||:  ,  _______   .   ||: , |            /`\
                 ||:   || .   /+++++++\\   . ||:   |
                 ||:   ||.    |+++++++| .    ||: . |
              __ ||: . ||: ,  |+++++++|.  . _||_   |
     ____--`~    '--~~__|.    |+++++__|----~    ~`---,              ___
-~--~                   ~---__|,--~'                  ~~----_____-~'   `~----~~


                """,160);
    }
}
