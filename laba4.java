import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class laba4 {

    static String fileName = "Выражение.txt";

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            try {
                String expression;
                while ((expression = in.readLine()) != null) {
                    MatchParser pm = new MatchParser();
                    try {
                        System.out.println(expression + " = " + pm.Parse(expression));
                    } catch (Exception e) {
                        System.err.println("ОШИБКА при поиске '" + expression + "' имеет следующее: " + e.getMessage());
                    }
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Result {
        public double acc;
        public String rest;

        public Result(double v, String r)
        {
            this.acc = v;
            this.rest = r;
        }
    }
}
