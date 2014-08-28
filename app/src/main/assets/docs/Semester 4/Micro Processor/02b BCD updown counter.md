## Aim.
### Implement a BCD Up-Down Counter on the Logic Controller Interface.

##Program

	.model small

	.code
	start: mov al,80h
			  mov dx,303h
			  out dx,al
			  mov dx,300h
			  mov al,80h
			  mov cx,30h
	lp1:    out dx,al
			  ror al,01h
			  call delay1
			  loop lp1
			  mov ah,4ch
			  int 21h

			  delay1 proc
			  push cx
			  push ax
			  mov ax,0aah
	lp3:    loop lp3
			  dec ax
			  jnz lp3
			  pop ax
			  pop cx
			  ret
			  delay1 endp
	end start

