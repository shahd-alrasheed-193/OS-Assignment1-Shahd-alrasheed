# Development Log

## Instructions
Document your development process as you work on the assignment. Add entries showing:
- What you worked on
- Problems you encountered
- How you solved them
- Time spent

**Requirements**: Minimum 5 entries showing progression over time.

---

## Example Entry Format:

### Entry 1 - [April 1, 2026, 2:30 PM]
**What I did**: Forked the repository and set up my student ID

**Details**: 
- Created GitHub account with university email
- Forked the starter repository
- Changed student ID on line 92 to my actual ID (441234567)
- Compiled and ran the program successfully

**Challenges**: Had to install JDK first because javac wasn't recognized

**Solution**: Downloaded JDK 17 from Oracle website and set PATH variable

**Time spent**: 30 minutes

---

## Your Development Log:

### Entry 1 - [March 16, 2026 | 5:00 PM – 6:30 PM]
**What I did**: Customizing the Simulation Core.    

**Details**:I modified the studentID variable to my actual ID, which automatically changed the random seed for the entire simulation, including the number of processes and the time quantum.  

**Challenges**: Ensuring that the custom seed produced a stable simulation without the processes finishing too quickly.

**Solution**:I tested the execution multiple times to verify that the generated burst times were compatible with the time quantum.

**Time spent**:  1 Hours.

---

### Entry 2 - [March 18, 2026 | 8:00 PM – 10:00 PM]
**What I did**: Feature 1: Process Priority System.

**Details**: I updated the Process class by adding a priority field (1-5) and modified the constructor to assign these values randomly during process creation.

**Challenges**:The priority was set correctly in the object but was invisible in the logs during the Round-Robin cycles.

**Solution**:  I injected the priority variable into the addProcessToQueue print statements to track it visually.  

**Time spent**:  2 Hours

---

### Entry 3 - [March 21, 2026 | 4:30 PM – 5:45 PM]
**What I did**: Feature 2: CPU Context Switch Tracking.

**Details**:I implemented a static counter to track the total number of context switches that occur when the CPU stops one thread and starts another.

**Challenges**: The counter was incrementing incorrectly within the internal run() loop of the process.

**Solution**: : I repositioned the counter to the main scheduler loop in SchedulerSimulation.java so it only triggers when a new process is dispatched from the queue.  

**Time spent**:  1.25 Hours

---

### Entry 4 - [March 24, 2026 | 9:00 PM – 11:30 PM]
**What I did**: Feature 3: Performance Metrics Logic.

**Details**:: I added logic to capture the exact creation time and completion time for each process using System.currentTimeMillis().

**Challenges**: :Calculating the waiting time for processes that get re-queued multiple times in the Round-Robin queue.  

**Solution**:  I used the formula (Finish Time - Creation Time - Burst Time) to isolate the time the process spent strictly waiting in the ready queue

**Time spent**: 2.5 Hours.

---

### Entry 5 - [March 27, 2026 | 2:00 PM – 4:00 PM]
**What I did**:  Data Visualization & Summary Table.  

**Details**: I built a formatted summary table that prints at the end of the simulation, displaying each process's name, burst time, and calculated waiting time.  

**Challenges**: Aligning the table columns and ensuring the ANSI colors didn't break the table structure in the terminal. 

**Solution**:  I used System.out.printf() to ensure fixed-width columns for a professional look.

**Time spent**:  2 Hours

---

### Entry 6 - [Optional - Date and Time]
**What I did**: 

**Details**: 

**Challenges**: 

**Solution**: 

**Time spent**: 

---

## Summary

**Total time spent on assignment**: [9.25 hours]

**Most challenging part**: Calculating the accurate waiting time for each process was definitely the toughest part. Since we are using a Round-Robin algorithm, processes are constantly being paused and re-queued, so I had to figure out how to subtract the burst time from the total time in the system to get it right.

**Most interesting learning**: It was really cool to see the Java Thread lifecycle in action. Actually coding the "yield" mechanism and seeing how threads move from Runnable to Running and back to the queue made the theory I learned in class finally click. 

**What I would do differently next time**: Next time, I would try to implement a more complex GUI or a live chart to visualize the waiting time as it happens, rather than just printing a summary table at the end.
