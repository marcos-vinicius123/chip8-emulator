public class Debugger {

    public static void log(String message) {
        if (Settings.debug) {
            System.out.println(message);
        }
    }

    public static void log(String format, Object... args) {
        if (Settings.debug) {
            System.out.println(String.format(format, args));
            System.out.println();
        }
    }
}
