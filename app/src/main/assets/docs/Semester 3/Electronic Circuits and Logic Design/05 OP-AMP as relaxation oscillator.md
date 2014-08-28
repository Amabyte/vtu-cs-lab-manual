##AIM :
### Design and construct a rectangular waveform generator (op-amp relaxation 
oscillator) for a given frequency and demonstrate its working...

##COMPONENTS REQUIRED: 
Op-amp uA 741, Resistor of 1Kohm, 10Kohm, 20 kohm Potentiometer, Capacitor of 0.1 microF, Regulated DC power supply, CRO 

##DESIGN : 
![Op-Amp](opamp1.png)

##PROCEDURE : 
1.	Before making the connections check all the components using multimeter.
2.	Make the connections as shown in figure and switch on the power supply.
3.	Observe the voltage waveform across the capacitor on CRO.
4.	Also observe the output waveform on CRO. Measure its amplitude and frequency.

 
 

##Circuit Diagram & actual connections
![Op-Amp](opamp2.png)
![Op-Amp](opamp3.png)
![Op-Amp](opamp4.png)

  
 
##WAVEFORMS
 ![Op-Amp](opamp5.png)
##RESULT:
The frequency of the oscillations = ___ Hz.

##THEORY:
Op-Amp Relaxation Oscillator is a simple Square wave generator which is also called as a Free running oscillator or Astable multivibrator or Relaxation oscillator.  In this figure the op-amp operates in the saturation region. Here, a fraction  (R2/(R1+R2)) of output is fed back to the noninverting input terminal. Thus reference voltage is (R2/(R1+R2)) Vo. And  may take values as +(R2/(R1+R2)) Vsat  or - (R2/(R1+R2)) Vsat. The output is also fed back to the inverting input terminal after integrating by means of a low-pass RC combination. Thus whenever the voltage at inverting input terminal just exceeds reference voltage, switching takes place resulting in a square wave output.
