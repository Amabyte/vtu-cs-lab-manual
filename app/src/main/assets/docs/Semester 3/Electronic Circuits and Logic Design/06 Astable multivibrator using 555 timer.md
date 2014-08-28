##AIM : 
###Design and implement an astable multivibrator using 555 Timer for a given frequency  and duty cycle.

##COMPONENTS REQUIRED:  
555 Timer IC, Resistors of 3.3Kohm, 6.8Kohm, Capacitors of 0.1 microF, 0.01 microF, Regulated power supply,	CRO

##DESIGN :
 Given frequency (f) = 1KHz and duty cycle = 60% (=0.6)
 The time period T =1/f = 1ms = tH +  tL  
Where tH is the time the output is high and  tL  is the time the output is low.
From the theory of astable multivibrator using 555 Timer(refer Malvino), we have

tH = 0.693 RB C 				------(1)
tL = 0.693 (RA + RB)C			------(2)
T = tH +  tL  = 0.693 (RA +2 RB) C

Duty cycle = tH / T = 0.6. Hence tH = 0.6T = 0.6ms and tL = T - tH = 0.4ms.
Let C=0.1microF and substituting in the above equations, 
RB = 5.8Kohm  (from equation 1) and  RA = 2.9Kohm (from equation 2 & RB values).

The Vcc determines the upper and lower threshold voltages (observed from the capacitor voltage waveform) as  . Vut = (2/3)Vcc and Vlt = (1/3)Vcc
Note: The duty cycle determined by RA & RB can vary only between 50 & 100%. If RA is much smaller than RB, the duty cycle approaches 50%.

Example 2: frequency = 1kHz and duty cycle =75%, RA = 7.2kohm & RB =3.6kohm, choose RA = 6.8kohm and RB = 3.3kohm.

 
##Circuit Diagram and actual connections
![Astable](astable1.png)


##PROCEDURE :  
1.	Before making the connections, check the components using multimeter.
2.	Make the connections as shown in figure and switch on the power supply.
3.	Observe the capacitor voltage waveform at 6th pin of  555 timer on CRO.
4.	Observe the output waveform at 3rd pin of  555 timer on CRO (shown below).
5.	Note down the amplitude levels, time period and hence calculate duty cycle.

##RESULT: 
The frequency of the oscillations = ___ Hz.

##WAVEFORMS
![Astable](astable2.png)
 

##THEORY:
Multivibrator is a form of oscillator, which has a non-sinusoidal output. The output waveform is rectangular. The multivibrators are classified as: Astable or free running multivibrator: It alternates automatically between two states (low and high for a rectangular output) and remains in each state for a time dependent upon the circuit constants. It is just an oscillator as it requires no external pulse for its operation. Monostable or one shot multivibrator: It has one stable state and one quasi stable. The application of an input pulse triggers the circuit time constants. After a period of time determined by the time constant, the circuit returns to its initial stable state. The process is repeated upon the application of each trigger pulse. Bistable Multivibrators: It has both stable states. It requires the application of an external triggering pulse to change the output from one state to other. After the output has changed its state, it remains in that state until the application of next trigger pulse. Flip flop is an example.   
