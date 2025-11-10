import java.util.*;

class Pass1 {

    public static void main(String args[]) {

        // Assembly Program Input
        String program[][] = {
            {"START", "101"},
            {"AGAIN", "MULT", "BREG", "TERM"},
            {" ", "MOVER", "CREG", "TERM"},
            {" ", "ADD", "CREG", "N"},
            {" ", "MOVEM", "CREG", "TERM"},
            {"N", "DS", "2"},
            {"RESULT", "DS", "2"},
            {"ONE", "DC", "1"},
            {"TERM", "DC", "1"},
            {"END"}
        };

        // Symbol Table
        Map<String, Integer> symbolTable = new LinkedHashMap<>();

        int LC = 0;  // Location Counter

        System.out.println("\nPASS 1 PROCESSING...\n");

        // START directive
        if(program[0][0].equals("START")) {
            LC = Integer.parseInt(program[0][1]); 
            System.out.println("START found. LC initialized to: " + LC);
        }

        // Process instructions
        for(int i = 1; i < program.length; i++) {

            // If line only contains END => stop
            if(program[i].length == 1 && program[i][0].equals("END")) {
                break;
            }

            String label = program[i][0];

            // If label exists
            if(!label.equals(" ")) {
                symbolTable.put(label, LC);
            }

            // Check for directives & normal instructions
            if(program[i][1].equals("DS")) {
                LC += Integer.parseInt(program[i][2]); // allocate DS
            }
            else if(program[i][1].equals("DC")) {
                LC += 1; // DC always takes 1 word
            }
            else {
                LC += 1; // Normal instruction size = 1 word
            }
        }

        // Print Symbol Table
        System.out.println("\n******** SYMBOL TABLE ********");
        System.out.println("Symbol\tAddress");
        System.out.println("----------------------");
        for(String key : symbolTable.keySet()) {
            System.out.println(key + "\t" + symbolTable.get(key));
        }

        System.out.println("\nPASS 1 Completed Successfully.\n");
    }
}
