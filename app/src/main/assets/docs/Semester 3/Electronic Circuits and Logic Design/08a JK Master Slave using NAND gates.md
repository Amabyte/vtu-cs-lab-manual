##AIM
###Realize a J-K Master/Slave FF using NAND gates and verify its truth table.

##Components used:
IC 74LS00, IC 74LS10, IC 74LS20, Power chords, Patch chords, Trainer kit.

###Pin Details of the ICs:  7400
![unavailable](jk1.png)


###IC-7410
![unavailable](jk2.png)


###IC-7420
![unavailable](jk3.png)
 

##Theory:
The circuit below shows the solution. To the RS flip-flop we have added two new connections from the Q and Q' outputs back to the original input gates. Remember that a NAND gate may have any number of inputs, so this causes no trouble. To show that we have done this, we change the designations of the logic inputs and of the flip-flop itself. The inputs are now designated J (instead of S) and K (instead of R). The entire circuit is known as a JK flip-flop.
 
![unavailable](jk4.png)

In most ways, the JK flip-flop behaves just like the RS flip-flop. The Q and Q' outputs will only change state on the falling edge of the CLK signal, and the J and K inputs will control the future output state pretty much as before. However, there are some important differences. 
Since one of the two logic inputs is always disabled according to the output state of the overall flip-flop, the master latch cannot change state back and forth while the CLK input is at logic 1. Instead, the enabled input can change the state of the master latch once, after which this latch will not change again. This was not true of the RS flip-flop.

If both the J and K inputs are held at logic 1 and the CLK signal continues to change, the Q and Q' outputs will simply change state with each falling edge of the CLK signal. (The master latch circuit will change state with each rising edge of CLK.) We can use this characteristic to advantage in a number of ways. A flip-flop built specifically to operate this way is typically designated as a T (for Toggle) flip-flop. The lone T input is in fact the CLK input for other types of flip-flops.

The JK flip-flop must be edge triggered in this manner. Any level-triggered JK latch circuit will oscillate rapidly if all three inputs are held at logic 1. This is not very useful. For the same reason, the T flip-flop must also be edge triggered. For both types, this is the only way to ensure that the flip-flop will change state only once on any given clock pulse.

Because the behavior of the JK flip-flop is completely predictable under all conditions, this is the preferred type of flip-flop for most logic circuit designs. The RS flip-flop is only used in applications where it can be guaranteed that both R and S cannot be logic 1 at the same time.

At the same time, there are some additional useful configurations of both latches and flip-flops. In the next pages, we will look first at the major configurations and note their properties. Then we will see how multiple flip-flops or latches can be combined to perform useful functions and operations.

##Master Slave Flip Flop:
The control inputs to a clocked flip flop will be making a transition at approximately the same times as triggering edge of the clock input occurs. This can lead to unpredictable triggering.
      
A JK master flip flop is positive edge triggered, where as slave is negative edge triggered. Therefore master first responds to J and K inputs and then slave. If J=0 and K=1, master resets on arrival of positive clock edge. High output of the master drives the K input of the slave. For the trailing edge of the clock pulse the slave is forced to reset. If both the inputs are high, it changes the state or toggles on the arrival of the positive clock edge and the slave toggles on the negative clock edge. The slave does exactly what the master does.

##Function Table:
![unavailable](jk5.png)
![unavailable](jk6.png)

##Procedure
1. Verify all components and patch cords whether they are in good condition or not.
2. Make connections as shown in the circuit diagram.
3. Give supply to trainer kit.
4. Provide input data to circuit via switched.
5. Verify truth table sequence and observe outputs.

