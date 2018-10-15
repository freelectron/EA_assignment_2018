import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Tuning {


    public static void main(String[] args) throws FileNotFoundException {
        String filename = "hahaha.txt" ; 
        Evaluate evaluate = new Evaluate();
        // evaluate.setParamters(100,0.32,0.5);
        PrintWriter printer = new PrintWriter(filename);
        
        double evaluate1 = evaluate.evaluate(printer);

        printer.close();

//        int P = 10;
//        for (int i = 0; i<100; i++) {
//            evaluate.setParamters(P,0.17,0.6);
//            System.out.println(P);
//            System.out.println(evaluate.evaluate());
//            P += 1;
//        }
    }

}
