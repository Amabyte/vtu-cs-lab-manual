##Aim.
### Read the status of two 8-bit inputs (X & Y) from the Logic Controller Interface and display X*Y.

##Program.

	.model small

	.code
	start: mov dx,303h
			  mov al,8bh
			  out dx,al
			  mov dx,301h
			  in al,dx
			  mov cl,al
			  mov dx,302h
			  in al,dx
			  mov ah,00h
			  mul cl
			  mov dx,300h
			  out dx,al
			  mov bx,0aaah
	lp1:    loop lp1
			  dec bx
			  jnz lp1
			  mov al,ah
			  out dx,al
			  mov ah,4ch
			  int 21h
	end start