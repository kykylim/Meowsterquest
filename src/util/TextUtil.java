package util;

public class TextUtil {

    public static final int DEFAULT_WIDTH = 160;

    //CENTERED PRINTING

    public static void printCentered(String text) {
        printCentered(text, DEFAULT_WIDTH);
    }

    public static void printCentered(String text, int width) {
        System.out.println(getCenteredText(text, width));
    }

    private static String getCenteredText(String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        return " ".repeat(padding) + text;
    }

    /* ---------------------- TYPEWRITER PRINTING ---------------------- */

    public static void typewriterPrint(String text) {
        typewriterPrint(text, 40);
    }

    public static void typewriterPrint(String text, int delayMs) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }
        System.out.println();
    }

    public static void typewriterPrintCentered(String text, int delayMs) {
        typewriterPrintCentered(text, delayMs, DEFAULT_WIDTH);
    }

    public static void typewriterPrintCentered(String text, int delayMs, int width) {
        System.out.print(getCenteredText(text, width));
        typewriterPrint("", delayMs); // already handles printing characters + newline
    }

    /* ---------------------- TITLE PRINTING ---------------------- */

    public static void printTitle(String title) {
        printTitle(title, DEFAULT_WIDTH);
    }

    public static void printTitle(String title, int width) {
        String line = "=".repeat(width);
        System.out.println(line);
        System.out.println(getCenteredText(title, width));
        System.out.println(line);
    }

    /* ---------------------- UTILITY ---------------------- */

    public static void pause(int milliseconds) {
        sleep(milliseconds);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clearScreen() {
        System.out.println("\n".repeat(40));
    }
}
