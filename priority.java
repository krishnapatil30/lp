import java.util.Scanner;

class priority {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];      // Process IDs
        int bt[] = new int[n];       // Burst Time
        int pr[] = new int[n];       // Priority (Lower number = Higher priority)
        int wt[] = new int[n];       // Waiting Time
        int tat[] = new int[n];      // Turnaround Time

        // Input
        for(int i = 0; i < n; i++) {
            System.out.print("Enter Process ID: ");
            pid[i] = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            bt[i] = sc.nextInt();
            System.out.print("Enter Priority: ");
            pr[i] = sc.nextInt();
            System.out.println();
        }

        // Sorting based on Priority
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                if(pr[i] > pr[j]) {   // Lower priority value = higher priority
                    int temp;

                    temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        // Waiting Time calculation
        wt[0] = 0;
        for(int i = 1; i < n; i++) {
            wt[i] = wt[i-1] + bt[i-1];
        }

        // Turnaround Time calculation
        for(int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        System.out.println("PID\tBT\tPriority\tWT\tTAT");
        for(int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + bt[i] + "\t" + pr[i] + "\t\t" + wt[i] + "\t" + tat[i]);
        }

        sc.close();
    }
}
