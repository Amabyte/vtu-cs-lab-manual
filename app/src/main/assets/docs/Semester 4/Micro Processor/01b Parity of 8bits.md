## Aim.
###Read the status of eight input bits from the Logic Controller Interface and display ‘FF’ if it is the parity of the input read is even; otherwise display 00.

## Program
	.model small

	.code
	start: mov dx,303h
			  mov al,82h
			  out dx,al
			  mov dx,301h
			  in al,dx
			  mov cx,08h
			  mov bl,00h
	lp1:    ror al,01h
			  adc bl,00h
			  loop lp1
			  mov al,bl
			  mov ah,00h
			  mov bh,02h
			  div bh
			  cmp ah,00h
			  je ev
			  mov al,0h
			  jmp disp
	ev:     mov al,0ffh
	disp:  mov dx,300h
			  out dx,al
			  mov dl,bl
			  add dl,'0'
			  mov ah,02h
			  int 21h
			  mov ah,4ch
			  int 21h
	end start