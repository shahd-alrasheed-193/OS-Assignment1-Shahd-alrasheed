 import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

// ANSI Color Codes for enhanced terminal output
class Colors {
   
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

class Process implements Runnable {
    private String name; 
    private int burstTime; 
    private int timeQuantum; 
    private int remainingTime; 
    
    // FEATURE 1: Priority field
    private int priority; 
    
    // FEATURE 3: Waiting Time tracking fields
    private long creationTime;
    private long totalWaitingTime;
    private long lastReadyTime;

    public Process(String name, int burstTime, int timeQuantum, int priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime; 
        this.priority = priority; 
        
        // Initialize waiting time tracking
        this.creationTime = System.currentTimeMillis();
        this.totalWaitingTime = 0;
        this.lastReadyTime = this.creationTime;
    }

    @Override
    public void run() {
        int runTime = Math.min(timeQuantum, remainingTime); 
        
        System.out.println(Colors.BRIGHT_GREEN + "  ▶ " + Colors.BOLD + Colors.CYAN + name + 
                          Colors.RESET + Colors.GREEN + " executing quantum" + Colors.RESET + 
                          " [" + runTime + "ms] ");
        
        try {
            int steps = 5; 
            int stepTime = runTime / steps;
            for (int i = 1; i <= steps; i++) {
                Thread.sleep(stepTime);
                System.out.print("\r  " + Colors.YELLOW + "⚡" + Colors.RESET + 
                                " Quantum progress: " + createProgressBar((i * 100) / steps, 15));
            }
            System.out.println(); 
        } catch (InterruptedException e) {}
        
        remainingTime -= runTime; 
        int overallProgress = (int) (((double)(burstTime - remainingTime) / burstTime) * 100);
        System.out.println(Colors.YELLOW + "  ⏸ " + Colors.CYAN + name + Colors.RESET + 
                          " │ Overall progress: " + createProgressBar(overallProgress, 20));
        
        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " + Colors.CYAN + name + Colors.RESET + " yields CPU" + Colors.RESET);
        } else {
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name + " finished!" + Colors.RESET);
        }
    }
    
    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            bar.append(i < filled ? Colors.GREEN + "█" : Colors.WHITE + "░").append(Colors.RESET);
        }
        return bar.append("] ").append(progress).append("%").toString();
    }

    // FEATURE 3: Methods for waiting time
    public void updateWaitingTime() {
        long currentTime = System.currentTimeMillis();
        long waitTime = currentTime - lastReadyTime;
        totalWaitingTime += waitTime;
    }

    public void setLastReadyTime(long lastReadyTime) {
        this.lastReadyTime = lastReadyTime;
    }
     public void runToCompletion() {
        try {
            System.out.println(Colors.BRIGHT_CYAN + "  ⚡ " + Colors.BOLD + Colors.CYAN + name + " finishing remaining " + remainingTime + "ms" + Colors.RESET);
            Thread.sleep(remainingTime); 
            remainingTime = 0; 
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name + " finished!" + Colors.RESET);
        } catch (InterruptedException e) {}
    }

    public String getName() { return name; }
    public int getBurstTime() { return burstTime; }
    public int getPriority() { return priority; }
    public long getTotalWaitingTime() { return totalWaitingTime; }
    public boolean isFinished() { return remainingTime <= 0; }
}

public class SchedulerSimulation {
    
    // FEATURE 2: Context Switch Counter
    private static int contextSwitchCount = 0; 
    
    // FEATURE 3: List to store completed processes for final report
    private static List<Process> completedProcesses = new ArrayList<>();

    public static void main(String[] args) {
        int studentID = 445052193;  
        Random random = new Random(studentID);
        int timeQuantum = 2000 + random.nextInt(4) * 1000; 
        int numProcesses = 10 + random.nextInt(11); 
        
        Queue<Thread> processQueue = new LinkedList<>();
        Map<Thread, Process> processMap = new HashMap<>();
        
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_CYAN + "╔═══════════════════════════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET + Colors.BG_BLUE + "                       CPU SCHEDULER SIMULATION                        " + Colors.RESET + Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "╚═══════════════════════════════════════════════════════════════════════════╝" + Colors.RESET);
        
        for (int i = 1; i <= numProcesses; i++) {
            int burstTime = timeQuantum/2 + random.nextInt(2 * timeQuantum + 1);
            int priority = 1 + random.nextInt(5); 
            Process process = new Process("P" + i, burstTime, timeQuantum, priority);
            addProcessToQueue(process, processQueue, processMap);
        }
        
        while (!processQueue.isEmpty()) {
            Thread currentThread = processQueue.poll(); 
            Process process = processMap.get(currentThread);
            
            // FEATURE 2: Context Switch
            contextSwitchCount++; 

            // FEATURE 3: Update Waiting Time
            process.updateWaitingTime();
            
            System.out.println(Colors.BOLD + Colors.MAGENTA + "┌─ Executing: " + process.getName() + " (Priority: " + process.getPriority() + ") " + "─".repeat(30) + Colors.RESET);
            
            currentThread.start();
            try { currentThread.join(); } catch (InterruptedException e) {}
            
            if (!process.isFinished()) {
                process.setLastReadyTime(System.currentTimeMillis());
                addProcessToQueue(process, processQueue, processMap);
            } else {
                completedProcesses.add(process);
            }
        }
        
        // Final Statistics (Features 2 & 3)
        displayFinalReport();
    }
    
    public static void addProcessToQueue(Process process, Queue<Thread> processQueue, Map<Thread, Process> processMap) {
        Thread thread = new Thread(process);
        processQueue.add(thread);
        processMap.put(thread, process);
        System.out.println(Colors.BLUE + "  ➕ " + process.getName() + " (Priority: " + process.getPriority() + ") added to queue." + Colors.RESET);
    }

    public static void displayFinalReport() {
        System.out.println("\n" + Colors.BOLD + Colors.YELLOW + "╔═══════════════════════════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.YELLOW + "║" + Colors.
         RESET + "  📊 FINAL SCHEDULER STATISTICS                                            " + Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.YELLOW + "╠═══════════════════════════════════════════════════════════════════════════╣" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET + "  Total Context Switches: " + String.format("%-48d", contextSwitchCount) + Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET);
        
        long totalWait = 0;
        System.out.println(Colors.BOLD + Colors.YELLOW + "╠═══════════════════════════════════════════════════════════════════════════╣" + Colors.RESET);
        for (Process p : completedProcesses) {
            String line = String.format("  %s | Wait: %dms | Priority: %d", p.getName(), p.getTotalWaitingTime(), p.getPriority());
            System.out.println(Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET + String.format(" %-74s", line) + Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET);
            totalWait += p.getTotalWaitingTime();
        }
        
        double avgWait = (double) totalWait / completedProcesses.size();
        System.out.println(Colors.BOLD + Colors.YELLOW + "╠═══════════════════════════════════════════════════════════════════════════╣" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET + Colors.BRIGHT_GREEN + "  AVERAGE WAITING TIME: " + String.format("%-49.2f ms", avgWait) + Colors.BOLD + Colors.YELLOW + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.YELLOW + "╚═══════════════════════════════════════════════════════════════════════════╝" + Colors.RESET + "\n");
    }
}
