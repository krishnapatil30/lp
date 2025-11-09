class FCFS1 {
    public static void main(String a[]) {
        String p[] = {"P1", "P2", "P3"};
        int bt[] = {24, 3, 4};
        int wt[] = new int[3];
        int tat[] = new int[3];
        int i;

        wt[0] = 0;

        for (i = 1; i < 3; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        for (i = 0; i < 3; i++) {
            tat[i] = wt[i] + bt[i];
        }

        System.out.println("\n\tFCFS SCHEDULING OUTPUT");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s\n", "Process", "Burst Time", "Waiting Time", "Turnaround Time");
        System.out.println("---------------------------------------------------------");

        int totalwt = 0, totaltat = 0;

        for (i = 0; i < 3; i++) {
            System.out.printf("%-10s %-15d %-15d %-15d\n", p[i], bt[i], wt[i], tat[i]);
            totalwt += wt[i];
            totaltat += tat[i];
        }

        float avgwt = (float) totalwt / 3;
        float avgtat = (float) totaltat / 3;

        System.out.println("---------------------------------------------------------");
        System.out.println("Total Waiting Time = " + totalwt);
        System.out.println("Total Turnaround Time = " + totaltat);
        System.out.println("Average Waiting Time = " + avgwt);
        System.out.println("Average Turnaround Time = " + avgtat);
    }
}
