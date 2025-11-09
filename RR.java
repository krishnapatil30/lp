import java.util.Scanner;

public class RR {
    public static void main(String args[]) {
        int n, i, qt, count = 0, temp, sq = 0;
        int bt[], wt[], tat[], rem_bt[];
        float awt = 0, atat = 0;

        bt = new int[10];
        wt = new int[10];
        tat = new int[10];
        rem_bt = new int[10];

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of processes (maximum 10): ");
        n = s.nextInt();

        System.out.println("Enter the burst time of each process:");
        for (i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + " = ");
            bt[i] = s.nextInt();
            rem_bt[i] = bt[i];
        }

        System.out.print("Enter the Quantum Time: ");
        qt = s.nextInt();

        // Round Robin Logic
        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = qt;

                if (rem_bt[i] == 0) {
                    count++;
                    continue;
                }

                if (rem_bt[i] > qt) {
                    rem_bt[i] -= qt;
                } else {
                    temp = rem_bt[i];
                    rem_bt[i] = 0;
                }

                sq += temp;
                tat[i] = sq;
            }
            if (count == n)
                break;
        }

        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-20s %-15s\n", "Process", "Burst Time", "Turnaround Time", "Waiting Time");
        System.out.println("-------------------------------------------------------------");

        for (i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];
            awt += wt[i];
            atat += tat[i];
            System.out.printf("%-10s %-15d %-20d %-15d\n", "P" + (i + 1), bt[i], tat[i], wt[i]);
        }

        awt /= n;
        atat /= n;

        System.out.println("-------------------------------------------------------------");
        System.out.printf("Average Waiting Time = %.2f\n", awt);
        System.out.printf("Average Turnaround Time = %.2f\n", atat);

        s.close();
    }
}
