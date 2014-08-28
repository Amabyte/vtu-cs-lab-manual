##AIM
###Design and develop the verilog/VHDL code for D Flip Flop with positive edge triggering.Simulate and verify its working.

![unavailable](dff1.png)

##VHDL code for D Flip Flop Counter.

	library IEEE;
	use IEEE.STD_LOGIC_1164.ALL;

	entity d_ff is
		Port ( D,Clk : in std_logic;
				  Q : inout std_logic;
				  Qbar : out std_logic);
	end d_ff;

	architecture behavioral of d_ff is
	begin
		process(clk)
		begin
		   if rising_edge(clk) then
		   Q<= D;
		   end if;
		end process;
	  Qbar<= not Q;
	 
	end behavioral;
	
##D-flip flop Simulation Results
![unavailable](dff2.png)
