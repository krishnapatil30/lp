import java.util.*;

class Pass1 {
    public static void main(String args[]) {

        // Input Assembly Code
        String program[][] = {
            {"START", "100"},
            {"AGAIN", "MOVER", "AREG", "=5"},
            {" ", "ADD", "BREG", "N"},
            {" ", "MOVEM", "AREG", "RESULT"},
            {"N", "DS", "1"},
            {"RESULT", "DS", "1"},
            {" ", "END"}
        };

        // Symbol Table
        Map<String, Integer> SYMTAB = new LinkedHashMap<>();
        // Literal Table
        List<String> LITTAB = new ArrayList<>();
        // Literal Address Table
        List<Integer> LITADDR = new ArrayList<>();
        // Pool Table
        List<Integer> POOLTAB = new ArrayList<>();
        POOLTAB.add(0); // First pool always starts at index 0

        int LC = 0;

        System.out.println("\nPASS 1 PROCESSING...\n");

        // START
        if (program[0][0].equals("START")) {
            LC = Integer.parseInt(program[0][1]);
            System.out.println("LC initialized to: " + LC);
        }

        // Process instructions
        for (int i = 1; i < program.length; i++) {

            // END => Assign literal addresses
            if (program[i][0].equals("END")) {
                for (int k = POOLTAB.get(POOLTAB.size() - 1); k < LITTAB.size(); k++) {
                    LITADDR.add(LC);
                    LC++;
                }
                POOLTAB.add(LITTAB.size());
                break;
            }

            String label = program[i][0];
            if (!label.equals(" ")) {
                SYMTAB.put(label, LC);
            }

            String opcode = program[i][1];

            if (opcode.equals("DS")) {
                LC += Integer.parseInt(program[i][2]);
            }
            else if (opcode.equals("DC")) {
                LC += 1;
            }
            else {
                // Check if operand is literal
                String operand = program[i][3];
                if (operand.startsWith("=")) {
                    if (!LITTAB.contains(operand)) {
                        LITTAB.add(operand);
                    }
                }
                LC++;
            }
        }

        // Print SYMTAB
        System.out.println("\n******** SYMBOL TABLE ********");
        System.out.println("Symbol\tAddress");
        for (String s : SYMTAB.keySet()) {
            System.out.println(s + "\t" + SYMTAB.get(s));
        }

        // Print LITTAB
        System.out.println("\n******** LITERAL TABLE ********");
        System.out.println("Literal\tAddress");
        for (int i = 0; i < LITTAB.size(); i++) {
            System.out.println(LITTAB.get(i) + "\t" + LITADDR.get(i));
        }

        // Print POOLTAB
        System.out.println("\n******** POOL TABLE ********");
        System.out.println("Pool#\tStart Index");
        for (int i = 0; i < POOLTAB.size(); i++) {
            System.out.println((i+1) + "\t" + POOLTAB.get(i));
        }

        System.out.println("\nPASS 1 Completed Successfully.\n");
    }
}
