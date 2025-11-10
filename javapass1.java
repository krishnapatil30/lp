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

        // Symbol Table to fill dynamically
        Map<String, Integer> symbolTable = new LinkedHashMap<>();

        int LC = 0;  // Location Counter

        System.out.println("\nPASS 1 PROCESSING...\n");

        // Step 1: Check START directive
        if(program[0][0].equals("START")) {
            LC = Integer.parseInt(program[0][1]); // Set LC to start address
            System.out.println("START found. LC initialized to: " + LC);
        }

        // Process each instruction
        for(int i = 1; i < program.length; i++) {

            String label = program[i][0];

            // If label exists, add it to symbol table
            if(!label.equals(" ")) {
                symbolTable.put(label, LC);
            }

            // Check for directives
            if(program[i][1].equals("DS")) {
                LC += Integer.parseInt(program[i][2]); // allocate storage
            }
            else if(program[i][1].equals("DC")) {
                LC += 1; // constant occupies one word
            }
            else if(program[i][0].equals("END") || program[i][1].equals("END")) {
                break; // stop processing
            }
            else {
                LC += 1; // normal instruction => 1 word size
            }
        }

        System.out.println("\n\n******** SYMBOL TABLE ********");
        System.out.println("Symbol\tAddress");
        System.out.println("----------------------");
        for(String key : symbolTable.keySet()) {
            System.out.println(key + "\t" + symbolTable.get(key));
        }

        System.out.println("\nPASS 1 Completed Successfully.\n");
    }
}
