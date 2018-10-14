import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandToString {

    public String executeCommand(String[] command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            // System.out.println(command);
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            // System.out.println(p.getInputStream());
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(output.toString());

        return output.toString();
    }
}