##Aim
###Design and develop the verilog/VHDL code for 8:1 MUX. Simulate and verify its working.

![8:1 mux simulation](mux5.png)
![8:1 mux simulation](mux6.png)

##VHDL code for  8 to 1 mux (Behavioral modeling).

	library IEEE;
	use IEEE.STD_LOGIC_1164.ALL;

	entity mux1 is
		Port ( I : in std_logic_vector(7 downto 0);
				  sel : in std_logic_vector(2 downto 0);
				  zout : out std_logic);
	end mux1;

	architecture behavioral of mux1 is
	begin
			  zout <=   I(0) when sel="000" else
			I(1) when sel="001" else
			I(2)  when sel="010" else
			I(3)  when sel="011" else
			I(4)  when sel="100" else
			I(5)  when sel="101" else
			I(6)  when sel="110" else
			I(7);
	end behavioral;
	
##8:1 Mux Simulation Results                                                        
![8:1 mux simulation](mux7.png)
