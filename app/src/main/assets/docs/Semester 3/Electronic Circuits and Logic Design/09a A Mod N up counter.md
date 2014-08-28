##AIM
###Design and implement  a mod n (a<8)synchronous up counter using JK FF IC's and demonstrate its working. 

##Components used:
 IC 74LS76, IC 74LS08, Patch chords, power chords, and Trainer kit.

##Pin diagram of 7476
![unavailable](9a1.png)
![unavailable](9a2.png)

##IC:7408
![unavailable](9a3.png)
![unavailable](9a4.png)
 
##Theory:

The ripple counter requires a finite amount of time for each flip flop to change state. This problem can be solved by using a synchronous parallel counter where every flip flop is triggered in synchronism with the clock, and all the output which are scheduled to change do so simultaneously.

The counter progresses counting upwards in a natural binary sequence from count 000 to count 100 advancing count with every negative clock transition and get back to 000 after this cycle.

![unavailable](9a5.png)
![unavailable](9a6.png)

##Transition Table:
![unavailable](9a7.png)
![unavailable](9a8.png)
![unavailable](9a9.png)

##Procedure
1. Verify all components and patch cords whether they are in good condition or not.
2. Make connections as shown in the circuit diagram.
3. Give supply to trainer kit.
4. Provide input data to circuit via switched.
5. Verify truth table sequence and observe outputs.