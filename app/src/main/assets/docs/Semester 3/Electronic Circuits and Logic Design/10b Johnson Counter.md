##AIM
###Design and develop the verilog /VHDL code for switched tail counter.Simulate and verify its working.
![unavailable](10b1.png)


##VHDL code for Johnson counter.

	library IEEE;
	use IEEE.STD_LOGIC_1164.ALL;
	use IEEE.STD_LOGIC_ARITH.ALL;
	use IEEE.STD_LOGIC_UNSIGNED.ALL;

	entity jc is
		Port ( clk, en, rst : in std_logic;
				  q : inout std_logic_vector(3 downto 0));
	end jc;

	architecture behavioral of jc is
	begin
		Process(clk,rst)
		begin
			if rst='1' then q<="0001";
			elsif rising_edge (clk) then
				 if en='1' then
				 q<=(not q(0)) & q(3 downto 1);             
				 end if;
			end if;
		end process;

	end behavioral;



##Johnson Counter Simulation Results
![unavailable](10b2.png)