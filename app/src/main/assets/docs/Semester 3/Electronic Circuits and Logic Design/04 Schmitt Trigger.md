##AIM : 
###Design and construct a Schmitt trigger circuit using op-amp for the given UTP 
and LTP values and demonstrate its working..

##COMPONENTS REQUIRED : 
IC microA 741, Resistor of  10Kohm, 90Kohm, DC regulated power supply, Signal generator, CRO

##DESIGN : 
From theory of Schmitt trigger circuit using op-amp, we have the trip points,
![Schmitt](sch1.png)
 PROCEDURE : 
1.	Before doing the connections, check all the components using multimeter.
2.	Make the connection as shown in circuit diagram.
3.	Using a signal generator apply the sinusoidal input waveform of peak-to-peak amplitude of 10V, frequency 1kHz.
4.	Keep the CRO in dual mode; apply input (Vin) signal to the channel 1 and observe the output (Vo) on channel 2 which is as shown in the waveform below. Note the amplitude levels from the waveforms.
5.	Now keep CRO in X-Y mode and observe the hysteresis curve.

 
##Circuit Diagram and actual connections of Schmitt Trigger Circuit
![Schmitt](sch2.png)
 
##Waveforms:
![Schmitt](sch3.png)
###CRO in DUAL mode

 
![Schmitt](sch4.png)
###CRO in X-Y mode showing the Hysteresis curve

##THEORY:
Schmitt Trigger converts an irregular shaped waveform to a square wave or pulse. Here, the input voltage triggers the output voltage every time it exceeds certain voltage levels called the upper threshold voltage VUTP and lower threshold voltage VLTP. The input voltage is applied to the inverting input. Because the feedback voltage is aiding the input voltage, the feedback is positive. A comparator using positive feedback is usually called a Schmitt Trigger. Schmitt Trigger is used as a squaring circuit, in digital circuitry, amplitude comparator, etc.
 
