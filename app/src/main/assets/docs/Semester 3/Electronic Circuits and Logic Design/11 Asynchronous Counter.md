##AIM
###Design and implement asynchronous counter using decade counter IC to count up from 0 to n (n=9) and demonstrate its working.

##Components used:
IC 74LS90, Patch chords, Power chords and Trainer kit.

##Pin Diagram of 7490
![unavailable](111.png)
 
##Theory: 
Asynchronous counter is a counter in which the clock signal is connected to the clock input of only first stage flip flop. The clock input of the second stage flip flop is triggered by the output of the first stage flip flop and so on. This introduces an inherent propagation delay time through a flip flop. A transition of input clock pulse and a transition of the output of a flip flop can never occur exactly at the same time. Therefore, the two flip flops are never simultaneously triggered, which results in asynchronous counter operation.


##Circuit Diagram:
![unavailable](112.png)

![unavailable](113.png)
