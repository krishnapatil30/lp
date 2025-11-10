import java.util.*;

class Pass1 {
    public static void main(String args[]) {

        // Assembly Input
        String program[][] = {
            {"START", "100"},
            {"AGAIN", "MOVER", "AREG", "=5"},
            {" ", "ADD", "BREG", "N"},
            {" ", "MOVEM", "AREG", "RESULT"},
            {"N", "DS", "1"},
            {"RESULT", "DS", "1"},
            {"END"}
        };

        // MOT (Mnemonic Opcode Table)
        Map<String, String> MOT = new HashMap<>();
        MOT.put("MOVER", "IS,04");
        MOT.put("ADD", "IS,01");
        MOT.put("MOVEM", "IS,05");
        MOT.put("START", "AD,01");
        MOT.put("END", "AD,02");
        MOT.put("DS", "DL,02");
        MOT.put("DC", "DL,01");

        // Register Table
        Map<String, String> REG = new HashMap<>();
        REG.put("AREG", "1");
        REG.put("BREG", "2");
        REG.put("CREG", "3");
        REG.put("DREG", "4");

        Map<String, Integer> SYMTAB = new LinkedHashMap<>();
        List<String> LITTAB = new ArrayList<>();
        List<Integer> LITADDR = new ArrayList<>();
        List<Integer> POOLTAB = new ArrayList<>();
        POOLTAB.add(0);

        List<String> IC = new ArrayList<>();
        int LC = 0;

        System.out.println("\nPASS 1 PROCESSING...\n");

        // START
        if (program[0][0].equals("START")) {
            LC = Integer.parseInt(program[0][1]);
            IC.add("(AD,01) (C," + LC + ")");
        }

        for (int i = 1; i < program.length; i++) {

            if (program[i][0].equals("END")) {

                // Assign literal addresses
                for (int k = POOLTAB.get(POOLTAB.size()-1); k < LITTAB.size(); k++) {
                    LITADDR.add(LC);
                    LC++;
                }
                POOLTAB.add(LITTAB.size());

                IC.add("(AD,02)");
                break;
            }

            String label = program[i][0];
            if (!label.equals(" ")) {
                SYMTAB.put(label, LC);
            }

            String opcode = program[i][1];

            // DS / DC
            if (opcode.equals("DS")) {
                IC.add("(DL,02) (C," + program[i][2] + ")");
                LC += Integer.parseInt(program[i][2]);
            }
            else if (opcode.equals("DC")) {
                IC.add("(DL,01) (C," + program[i][2] + ")");
                LC++;
            }
            else {
                // Instruction
                String opClass = MOT.get(opcode);
                String reg = REG.get(program[i][2]);
                String operand = program[i][3];

                if (operand.startsWith("=")) {
                    if (!LITTAB.contains(operand)) {
                        LITTAB.add(operand);
                    }
                    IC.add("(" + opClass + ") (" + reg + ") (L," + (LITTAB.indexOf(operand)) + ")");
                }
                else {
                    IC.add("(" + opClass + ") (" + reg + ") (S," + operand + ")");
                }

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
        System.out.println("Literal\tAddress");
        for (int i = 0; i < LITTAB.size(); i++) {
            System.out.println(LITTAB.get(i) + "\t" + LITADDR.get(i));
        }

        System.out.println("\n******** POOL TABLE ********");
        System.out.println("Pool#\tStart Index");
        for (int i = 0; i < POOLTAB.size(); i++) {
            System.out.println((i+1) + "\t" + POOLTAB.get(i));
        }

        System.out.println("\n******** INTERMEDIATE CODE ********");
        for (String s : IC) {
            System.out.println(s);
        }

        System.out.println("\nPASS 1 Completed Successfully.\n");
    }
}

         
           
