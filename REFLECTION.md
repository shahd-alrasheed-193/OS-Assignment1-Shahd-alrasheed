# Reflection Questions

## Instructions
Answer the following questions about your learning experience. Each answer should be **at least 5-7 sentences** and show your understanding.

---

## Question 1: What did you learn about multithreading?

**Your Answer:**
Through this assignment, I learned that multithreading is a core concept that allows a program to execute multiple tasks concurrently using the Runnable interface. I gained a clear understanding of the thread lifecycle, specifically how processes transition between states like New, Runnable, and Terminated. It was fascinating to see how the CPU switches between threads so quickly that they appear to run at the same time. I also learned how to use Thread.start() to begin execution and Thread.sleep() to simulate processing time within each thread. What surprised me most was how much coordination is needed to manage shared resources without causing conflicts. Overall, this project made abstract operating system concepts feel much more practical and easy to understand.
---

## Question 2: What was the most challenging part of this assignment?

**Your Answer:**
The most challenging part was definitely implementing the Waiting Time calculation logic within a Round-Robin scheduling environment. This was difficult because processes are not executed all at once; they are frequently interrupted by the time quantum and moved back to the ready queue. I had to figure out how to accurately capture the creation time and the final completion time to determine the total time spent in the system. Relate to course concepts, this required a deep understanding of how the scheduler dispatches threads and how to track their progress across multiple cycles. Debugging the logic to ensure the math was correct for every process took a significant amount of effort. Finally, formatting the summary table to display these metrics clearly in the console was another technical hurdle I had to overcome.
---

## Question 3: How did you overcome the challenges you faced?

**Your Answer:**
To overcome these challenges, I followed a systematic problem-solving approach by first breaking down the requirements into smaller, manageable tasks. I spent a lot of time reading the provided documentation and the starter code to understand the existing relationship between the Process and SchedulerSimulation classes. When I got stuck on the waiting time logic, I used print statements as a debugging tool to log the status of each variable in real-time. I also searched for Java Threading documentation to clarify how the yield() method affects thread scheduling. Using Git for version control was very helpful, as it allowed me to save my progress and experiment with different code solutions safely. Lastly, drawing a manual timeline of the process execution helped me visualize the logic before I started coding.

## Question 4: How can you apply multithreading concepts in real-world applications?

**Your Answer:**
Multithreading is essential for modern software that we use every day, such as web browsers where each tab can run on its own thread to stay responsive. In video games, threads are used to handle heavy tasks like graphics rendering and physics calculations simultaneously without causing the game to freeze. Mobile applications also rely on background threads to fetch data from the internet while the user continues to interact with the interface. This assignment showed me that without an efficient scheduling algorithm like Round-Robin, these applications would not be able to share CPU resources fairly. I can now see how the "yield" and "time quantum" concepts I practiced are used by operating systems to keep multiple apps running smoothly. Mastering these threading concepts is clearly the key to developing high-performance and reliable real-world systems.

## Additional Reflections (Optional)

### What would you like to learn more about?

[Any topics related to threading, concurrency, or operating systems that you're curious about?]

---

### How confident do you feel about multithreading concepts now?

[Rate yourself and explain: Beginner / Intermediate / Confident]

[Explain your rating - what do you understand well? What needs more practice?]

---

### Feedback on the assignment

[Any comments about the assignment? Was it helpful? Too easy/hard? Suggestions for improvement?]
