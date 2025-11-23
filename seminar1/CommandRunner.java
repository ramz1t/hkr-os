
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CommandRunner implements Runnable {

    BufferedReader bufferReader = null;
    ProcessBuilder processBuilder = null;
    String bufferLine;

    public CommandRunner(String command) {
        List<String> commands = Arrays.asList(command.split(" "));
        processBuilder = new ProcessBuilder(commands);
    }

    @Override
    public void run() {
        try {
            Process proc = processBuilder.start();
            InputStream inputStream = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(isr);
            while ((bufferLine = bufferReader.readLine()) != null) {
                System.out.println(bufferLine);
            }
            bufferReader.close();
        } catch (java.io.IOException ioe) {
            System.err.println(ioe);
            Shell.logs.add(String.join(" ", processBuilder.command()));
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException ioe) {
                    System.err.println("Couldn't close bufferReader");
                    System.err.println(ioe);
                }
            }
        }
    }
}
