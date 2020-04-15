import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PasswordCracking {

    //Using JUNG 2.0.1
    //Used for solving Project Euler Problem 79 https://projecteuler.net/problem=79

    public static void main(String[] args) {
        DirectedSparseGraph<Integer, String> g = new DirectedSparseGraph<>();
        String result = "";

        String fileName = "keylog.txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int firstDigit = Integer.parseInt(String.valueOf(line.charAt(0)));
                int secondDigit = Integer.parseInt(String.valueOf(line.charAt(1)));
                int thirdDigit = Integer.parseInt(String.valueOf(line.charAt(2)));
                g.addEdge(firstDigit+""+secondDigit, firstDigit, secondDigit);
                g.addEdge(secondDigit+""+thirdDigit, secondDigit, thirdDigit);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (g.getEdgeCount() != 1) {
            for (int v: g.getVertices()) {
                if (g.inDegree(v) == 0) {
                    result += v;
                    break;
                }
            }
            g.removeVertex(Integer.parseInt(String.valueOf(result.charAt(result.length()-1))));
        }
        result += g.getEdges().toArray()[0];
        System.out.println(result); //73162890
    }
}
