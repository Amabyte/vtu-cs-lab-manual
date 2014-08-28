##AIM
###Design and develop the verilog/VHDL  code for mod 8 up counter simulate and verify its working.

![unavailable](9b1.png)


##VHDL code for Mod-8 Counter.

	library IEEE;
	use IEEE.STD_LOGIC_1164.ALL;
							
	entity mod_8 is
		Port ( rst, clk, en: in std_logic;
						   q : inout std_logic_vector(3 downto 0));
	end mod_8;

	architecture behavioral of mod_8 is
		begin
				process(clk,rst) is
		begin
			if rst='1' then q<="0000";
			elsif rising_edge(clk) then
				if en='1' then
					   Q<=Q+1;
				   end if;
			   if Q="0111" then
				   Q<= "0000";
				   end if;
				   end if;
			end process;
	end behavioral;

	
##Mod-8 Counter Simulation Results
![unavailable](9b2.png)