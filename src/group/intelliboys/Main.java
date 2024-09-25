package group.intelliboys;

import java.util.*;

public class Main {

    public static void generateMatrix(List<Process> processes) {
        try {
            Thread.sleep(1000);
            System.out.println("===========================================================================================");
            Thread.sleep(1000);
            System.out.println("== ID == ARRIVAL TIME == BURST TIME == COMPLETE TIME == TURN AROUND TIME == WAITING TIME ==");
            Thread.sleep(500);
            System.out.println("===========================================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }

        processes.forEach((p) -> {
            System.out.println("== " + p.getId() + "  ==\t " + p.getArrivalTime() + "\t\t  == \t " + p.getBurstTime() +
                    " \t\t==\t\t  ? \t == \t\t ? \t\t == \t ? \t\t ==");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Collections.sort(processes, Comparator.comparing(Process::getArrivalTime)
                .thenComparing(Process::getBurstTime));

        System.out.println("===========================================================================================");
    }

    public static List<Process> calculateMatrix(List<Process> processes) {
        int ct = 0, tat = 0, wt = 0;

        for (Process process : processes) {
            ct += process.getBurstTime();
            tat = ct - process.getArrivalTime();
            wt = tat - process.getBurstTime();

            process.setCompletedTime(Math.abs(ct));
            process.setTurnAroundTime(Math.abs(tat));
            process.setWaitingTime(Math.abs(wt));
        }

        return processes;
    }

    public static void generateCalculatedMatrix(List<Process> processes) {
        try {
            Thread.sleep(1000);
            System.out.println("===========================================================================================");
            Thread.sleep(1000);
            System.out.println("== ID == ARRIVAL TIME == BURST TIME == COMPLETE TIME == TURN AROUND TIME == WAITING TIME ==");
            Thread.sleep(500);
            System.out.println("===========================================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }

        processes.forEach((p) -> {
            System.out.println("== " + p.getId() + "  ==\t " + p.getArrivalTime() + "\t\t  == \t " + p.getBurstTime() +
                    " \t\t==\t\t " + p.getCompletedTime() + "\t\t == \t\t" + p.getTurnAroundTime() + " \t\t == \t" + p.getWaitingTime() + " \t\t ==");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("===========================================================================================");

    }

    public static void generateSummary(List<Process> processes) {

        int totalTat = 0, totalWT = 0;

        for (Process process : processes) {
            totalTat += process.getTurnAroundTime();
            totalWT += process.getWaitingTime();
        }

        float tatAverage = (float) totalTat / processes.size();
        float totalWtAverage = (float) totalWT / processes.size();

        System.out.println("============================== SUMMARY ============================\n" +
                "= Total TAT: " + totalTat + "\n" +
                "= Average: " + tatAverage + "ms \n\n" +
                "= Total WT: " + totalWT + "\n" +
                "= Average: " + totalWtAverage + "ms \n" +
                "===================================================================");

        processes.forEach(p -> {
            System.out.println("Process ID: " + p.getId() + " Complete Time: " + p.getCompletedTime());
        });
    }

    public static void run() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of Process: ");

        int numberOfProcess = in.nextInt();
        List<Process> processes = new ArrayList<>();
        Random randomNum = new Random();

        for (int i = 0; i < numberOfProcess; i++) {

            Process tmpProcess = new Process();
            tmpProcess.setId(i);
            tmpProcess.setArrivalTime(randomNum.nextInt(0, 10));
            tmpProcess.setBurstTime(randomNum.nextInt(0, 10));
            processes.add(tmpProcess);
        }

        generateMatrix(processes);

        System.out.println("==================================== SORTED TABLE =========================================");
        generateMatrix(processes);

        System.out.println("==================================== CALCULATED TABLE =====================================");
        generateCalculatedMatrix(calculateMatrix(processes));

        generateSummary(processes);
    }

    public static void main(String[] args) {
        run();
    }
}
