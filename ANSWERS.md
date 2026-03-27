# Assignment Questions

## Instructions
Answer all 4 questions with detailed explanations. Each answer should be **3-5 sentences minimum** and demonstrate your understanding of the concepts.

---

## Question 1: Thread vs Process

**Question**: Explain the difference between a **thread** and a **process**. Why did we use threads in this assignment instead of creating separate processes?

**Your Answer:**
A process is like a complete program running with its own dedicated memory and resources, which makes it "heavy" to create. On the other hand, a thread is a lightweight unit of execution that lives inside a process and shares memory with other threads. In this assignment, we used threads because they are much faster to create and switch between, which is perfect for simulating multiple tasks like P1 to P10 running simultaneously without crashing the system resources.

---

## Question 2: Ready Queue Behavior

**Question**: In Round-Robin scheduling, what happens when a process doesn't finish within its time quantum? Explain using an example from your program output.

**Your Answer:**

In Round-Robin scheduling, if a process exceeds its assigned Time Quantum before finishing its task, it is "preempted." The scheduler stops the process, saves its state, and moves it from the Running state back to the end of the Ready Queue. It has to wait for its next turn while other processes get a chance to use the CPU.
Example from my output:
```
[P3] executing quantum [4000ms]
P3 yields CPU for next process.
P3 added to ready queue: [P4 P5 P6 P7 P8 P9 P10 P1 P2 P3]
```

**Explanation of example:**
This snippet from my output shows that P3 was running but could not finish within the 4000ms limit. The scheduler forced it to "yield" the CPU and moved it to the very end of the Ready Queue (after P2). This proves the Round-Robin logic is working by giving P4 the next turn.

---

## Question 3: Thread States

**Question**: A thread can be in different states: **New**, **Runnable**, **Running**, **Waiting**, **Terminated**. Walk through these states for one process (P1) from your simulation.

**Your Answer:**

[Write your answer here. For each state, explain when P1 enters that state during the simulation. Use your understanding of the code to trace through the lifecycle.]

1. **New**: [ When the P1 thread object is first created in the code using new Thread(p1). At this point, it hasn't started running yet.]

2. **Runnable**: [When the code calls p1.start() and we see the message "P1 added to ready queue" in the terminal. It is now waiting for the scheduler to give it CPU time.]

3. **Running**: [When the terminal shows "[P1] executing quantum [4000ms]". This means P1 is currently using the CPU to perform its work.]

4. **Waiting**: [When P1's time quantum ends and it "yields" the CPU. It goes back to the queue and "waits" while other processes (like P2, P3) take their turn, or when it uses Thread.sleep() during the simulation.]

5. **Terminated**: [ When P1 completes all its burst time and the terminal shows "P1 finished execution!". The thread is now closed and cannot be run again.]

---

## Question 4: Real-World Applications

**Question**: Give **TWO** real-world examples where Round-Robin scheduling with threads would be useful. Explain why this scheduling algorithm works well for those scenarios.

**Your Answer:**

### Example 1: [Web Servers handling HTTP requests]

**Description**: 
[When many users try to access a website at the same time, the server creates a thread for each request.]

**Why Round-Robin works well here**: 
[Explain why Round-Robin scheduling is suitable. Consider fairness, responsiveness, predictability, etc.It ensures fairness so that no single user has to wait too long. Every request gets a small slice of CPU time, keeping the website responsive for everyone instead of finishing one person's request while others see a loading screen.]

### Example 2: [Multitasking in Operating Systems]

**Description**: 
[ Running multiple applications like a music player, a web browser, and a code editor (VS Code) simultaneously on a laptop.]

**Why Round-Robin works well here**: 
[It provides predictability and smoothness. By switching between these apps very quickly using a time quantum, it gives the user the illusion that all apps are running at the exact same time without any lag or freezing.]

---

## Summary

**Key concepts I understood through these questions:**
1. 
2. 
3. 

**Concepts I need to study more:**
1. 
2. 
