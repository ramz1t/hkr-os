
import java.util.*;

public class Shell {

    public static List<String> logs = new ArrayList();

    static void createProcess(String command) {
        CommandRunner runner = new CommandRunner(command);
        new Thread(runner).start();
    }

    public static void main(String[] args) {
        String commandLine;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n***** Welcome to the Java Command Shell *****");
        System.err.println("Type any console command to run");
        System.out.println("Available JCS commands: stopwatch, showerrlog, end\n");
        while (true) {
            commandLine = scanner.nextLine().trim();
            if (commandLine.equals("")) {
                // skip iteration 
            } else if (commandLine.toLowerCase().equals("showerrlog")) {
                if (!logs.isEmpty()) {
                    logs.forEach(System.out::println);
                } else {
                    System.out.println("Logs are empty");
                }
            } else if (commandLine.toLowerCase().equals("stopwatch")) {
                System.out.println("Main thread. Waiting for stopwatch thread...");
                Stopwatch stopwatch = new Stopwatch();
                stopwatch.start(5);
                System.out.println("Main thread. Finished stopwatch thread");
            } else if (commandLine.toLowerCase().equals("end")) {
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                scanner.close();
                System.exit(0);
            } else {
                createProcess(commandLine);
            }
        }
    }
}
