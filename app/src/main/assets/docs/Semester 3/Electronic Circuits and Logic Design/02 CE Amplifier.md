##AIM:
###Design and construct a suitable circuit and determine the frequency response, input  impedance, output impedance and  bandwidth of a CE amplifier..

##COMPONENTS REQUIRED:
 Transistor SL-100, Resistors -16 K ohm, 3.9 K ohm, 820 ohm, 220 ohm, Capacitors - 0.47 microF, 100 microF, DC regulated power supply, Signal generator, CRO

##PROCEDURE :
1.	Before making the connections check all components using multimeter.
2.	Make the connections as shown in circuit diagram.
3.	Using a signal generator apply a sinusoidal input waveform of peak-to-peak amplitude 20mV ( = Vin) to the circuit and observe the output signal on the CRO.
4.	Vary the frequency of input from 50Hz to 1MHz range and note down corresponding output voltage VO in the tabular column.
Note: When the input frequency is being changed the input amplitude (i.e., around 20 mV) should remain constant.
Adjust the amplitude of Vin (in mV) such that the output Vo does not get clipped (i.e., saturated) when the frequency is in the mid range say 1kHz.
5.	After the frequency has been changed from 50 Hz to 1MHz and the readings are tabulated in a tabular column, calculate gain of the amplifier (in dB) using the formula,  
Gain in dB = 20 log 10 (Vo/Vin)
6.	Plot the graph of gain versus frequency on a semilog sheet and hence determine the bandwidth as shown in Fig. 3. Bandwidth = B = f2-f1

To find input impedance, set the input DRBI to a minimum value and DRBO to a maximum value (say, 10k) as shown in figure 2. Now apply an input signal using signal generator, say a sine wave whose peak-to-peak amplitude is 50mV with a frequency of 10 KHz. Observe the output on CRO. Note this value of output with DRBI = 0 as Vx.

Now increase the input DRBI value till the output voltage Vo = (1/2) Vx. The corresponding DRBI value gives input impedance.


###To measure input impedence Ri
  ![CE AMPLIFIER](ce1.png)



To find output impedance, set DRBO which is connected across the output to a maximum value as shown in figure 2, with the corresponding DRBI at the minimum position. Apply the input signal using signal generator, say a sine wave whose peak-to-peak amplitude is 50mV with a frequency of 10 KHz. Observe the output on CRO. Note this value of output with DRBI = 0 as Vx. Now decrease the DRBO value till the output voltage Vo = (1/2) Vx. The corresponding DRBO value gives output impedance. 

###To measure output impedence R0
 ![CE AMPLIFIER](ce2.png)
 
Note: DRBI is connected between the signal generator and the input coupling capacitor. DRBO is connected across the output (across the CRO terminals).
The ground symbol in the circuit diagram implies a common point. In some of the power supplies, there will be three terminals - +(plus), -(minus) and GND (ground). Never connect this GND terminal to the circuit.
 
![CE AMPLIFIER](cetab.png)
![CE AMPLIFIER](ce3.png)
![CE AMPLIFIER](ce4.png)

###Fig. 1 : Transistor  as a CE  amplifier circuit diagram and actual connections (does not show RL) 
 
![CE AMPLIFIER](ce5.png)
###Fig. 2: CE Amplifier with DRBs connected at both input and output

WAVEFORMS:

![CE AMPLIFIER](ce6.png)


FREQUENCY RESPONSE:
![CE AMPLIFIER](ce7.png)

###Fig. 3 Frequency response plotted on semilog graph (X-axis is log scale)

RESULT:
1. BANDWIDTH  =    Hz
2. INPUT IMPEDANCE =  ?
3. OUTPUT IMPEDANCE = ?

Note: Maximum gain occurs in mid frequency region. This is also called mid band gain.
Gain-bandwidth product = Midband gain x Bandwidth

##THEORY:
The frequency response of an amplifier is the graph of its gain versus the frequency. Fig. 3 shows the frequency response of an ac amplifier. In the middle range of frequencies, the voltage gain is maximum. The amplifier is normally operated in this range of frequencies. At low frequencies, the voltage gain decreases because the coupling (CC in Fig.1) and bypass (CE) capacitors no longer act like short circuits; instead some of the ac signal voltage is attenuated. The result is a decrease of voltage gain as we approach zero hertz. At high frequencies, voltage gain decreases because the internal (parasitic) capacitances across the transistor junctions provide bypass paths for ac signal. So as frequency increases, the capacitive reactance becomes low enough to prevent normal transistor action. The result is a loss of voltage gain.
Cutoff frequencies (f1 & f2 in Fig. 3) are the frequencies at which the voltage gain equals 0.707 of its maximum value.   It is also referred to as the half power frequencies because the load power is half of its maximum value at these frequencies.

##DESIGN:
 ![CE AMPLIFIER](cedes.png)
 
