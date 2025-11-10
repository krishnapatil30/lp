import java.util.*;

class Pass1 {
    public static void main(String args[]) {

        // Input Assembly Program
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

        // Mnemonic Table (MOT)
        Map<String, String> MOT = new HashMap<>();
        MOT.put("MULT", "IS,03");
        MOT.put("MOVER", "IS,04");
        MOT.put("ADD", "IS,01");
        MOT.put("MOVEM", "IS,05");
        MOT.put("START", "AD,01");
        MOT.put("END", "AD,02");
        MOT.put("DC", "DL,01");
        MOT.put("DS", "DL,02");

        // Register Table
        Map<String, String> REG = new HashMap<>();
        REG.put("AREG", "1");
        REG.put("BREG", "2");
        REG.put("CREG", "3");
        REG.put("DREG", "4");

        // Symbol Table
        Map<String, Integer> SYMTAB = new LinkedHashMap<>();

        // Literal Table (Empty because no literals exist)
        List<String> LITTAB = new ArrayList<>();

        // Intermediate Code list
        List<String> IC = new ArrayList<>();

        int LC = 0;

        // START directive handling
        if (program[0][0].equals("START")) {
            LC = Integer.parseInt(program[0][1]);
            IC.add("(AD,01) (C," + LC + ")");
        }

        // Process line by line
        for (int i = 1; i < program.length; i++) {

            if (program[i][0].equals("END")) {
                IC.add("(AD,02)");
                break;
            }

            String label = program[i][0];

            if (!label.equals(" ")) { // If label present
                SYMTAB.put(label, LC);
            }

            String opcode = program[i][1];

            if (opcode.equals("DS")) {
                IC.add("(DL,02) (C," + program[i][2] + ")");
                LC += Integer.parseInt(program[i][2]);
            }
            else if (opcode.equals("DC")) {
                IC.add("(DL,01) (C," + program[i][2] + ")");
                LC += 1;
            }
            else { // Normal Instruction
                String classOp = MOT.get(opcode);
                String reg = REG.getOrDefault(program[i][2], "-");
                String operand = program[i][3];
                IC.add("(" + classOp + ") (" + reg + ") (S," + operand + ")");
                LC++;
            }
        }

        // OUTPUT
        System.out.println("\n******** SYMBOL TABLE ********");
        System.out.println("Symbol\tAddress");
        for (String s : SYMTAB.keySet()) {
            System.out.println(s + "\t" + SYMTAB.get(s));
        }

        System.out.println("\n******** LITERAL TABLE ********");
        System.out.println("(No literals present in program)");

        System.out.println("\n******** INTERMEDIATE CODE ********");
        for (String line : IC) {
            System.out.println(line);
        }

        System.out.println("\nPASS 1 Completed Successfully.\n");
    }
}

