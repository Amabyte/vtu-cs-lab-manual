##AIM
### Design and construct a suitable circuit and demonstrate  the working of positive clipper, double ended clipper and positive clamper using diodes.

##COMPONENTS REQUIRED 
Diode (BY-127 / IN4007), Resistors- 10Kohm & 3.3kohm, DC regulated power supply (for Vref), Signal generator (for Vi) and CRO.

##CIRCUIT DIAGRAM OF POSITIVE CLIPPER
![Clipper](clipper1.png)
 
###Fig.1 a Positive clipper Circuit                b. Transfer Characteristics

Clippers clip off a portion of the input signal without distorting the remaining part of the waveform. In the positive clipper shown above the input waveform above Vref is clipped off. If Vref = 0V, the entire positive half of the input waveform is clipped off.

Plot of input Vi (along X-axis) versus output Vo (along Y-axis) called transfer characteristics of the circuit can also be used to study the working of the clippers.

Choose R value such that   where Rf = 100? and Rr = 100k? are the resistances of the forward and reverse diode respectively. Hence R = 3.3k?.

Let the output resistance RL = 10k?. The simulations can be done even without RL.
	
##PROCEDURE:
1.	Before making the connections check all components using multimeter.
2.	Make the connections as shown in circuit diagram.
3.	Using a signal generator (Vi) apply a sine wave of 1KHz frequency and a peak-to-peak amplitude of 10V to the circuit. (Square wave can also be applied.)
4.	Keep the CRO in dual mode, connect the input (Vi) signal to channel 1 and output waveform (Vo) to channel 2. Observe the clipped output waveform which is as shown in fig. 2. Also record the amplitude and time data from the waveforms.
5.	Now keep the CRO in X-Y mode and observe the transfer characteristic waveform.

##Note: 
1.	Vary Vref and observe the variation in clipping level. For this use variable DC power supply for Vref.
2.	Change the direction of diode and Vref to realize a negative clipper.
3.	For double-ended clipping circuit, make the circuit connections as shown in fig.3 and the output waveform observed is as shown in figure 5.
4.	Adjust the ground level of the CRO on both channels properly and view the ouput in DC mode (not in AC mode) for both clippers and clampers.

##WAVEFORMS
 ![Clipper](clipper2.png)

Fig. 2. Input and output waveform for positive Clipper

##DOUBLE ENDED CLIPPER
 ![Clipper](clipper3.png)
###Fig.3 Double ended clipper Circuit                b. Transfer Characteristics

 
![Clipper](clipper4.png)
###Fig. 4. Input and output waveform for double-ended clipping circuit

##Note: 
###The above clipper circuits are realized using the diodes in parallel with the load (at the output), hence they are called shunt clippers. The positive (and negative) clippers can also be realized in the series configuration wherein the diode is in series with the load. These circuits are called series clippers.

##POSITIVE CLAMPER

##COMPONENTS REQUIRED: 
Diode (BY-127), Resistor of 200 K?, Capacitor - 0.1 ?F, DC regulated power supply, Signal generator, CRO
 
 ![Clipper](clipper5.png)
###Fig. 5 Positive Clamper

The clamping network is one that will "clamp" a signal to a different DC level. The network must have a capacitor, a diode and a resistive element, but it can also employ an independent DC supply (Vref) to introduce an additional shift. The magnitude of R and C must be chosen such that time constant ?=RC is large enough to ensure the voltage across capacitor does not discharge significantly during the interval of the diode is non-conducting.  

##DESIGN:
Say for t = 20msec (corresponding to a frequency of 50 Hz), then for RC >> t, let C=0.1µF, then R = 200K?. 

##PROCEDURE :
1.	Before making the connections check all components using multimeter.
2.	Make the connections as shown in circuit diagram (fig. 5).
3.	Using a signal generator apply a square wave input (Vi) of peak-to-peak amplitude of 10V (and frequency greater than 50Hz) to the circuit. (Sine wave can also be applied)
4.	Observe the clamped output waveform on CRO which is as shown in Fig. 6.

##Note:  
1.	For clamping circuit with reference voltage Vref, the output waveform is observed as shown in Fig. 7. For without reference voltage, Keep Vref = 0V.
2.	CRO in DUAL mode and DC mode. Also the grounds of both the channels can be made to have the same level so that the shift in DC level of the output can be observed.
3.	For negative clampers reverse the directions of both diode and reference voltage.

 
![Clipper](clipper6.png)
###Fig. 6 Input and output waveform for positive clamper without reference voltage.
 
![Clipper](clipper7.png)
###Fig. 7 Input and output waveform for positive clamper circuit with reference voltage = 2V

