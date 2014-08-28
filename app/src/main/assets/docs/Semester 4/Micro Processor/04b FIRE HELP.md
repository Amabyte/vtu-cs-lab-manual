##Aim
### Display messages FIRE and HELP alternately with flickering effects on a 7-segment display interface for a suitable period of time. Ensure a flashing rate that makes it easy to read both the messages (Examiner does not specify these delay values nor is it necessary for the student to compute these values).

##Program

	.model small

	.data
	fir db 86h,88h,0f9h,8eh
	hel db 8ch,0c7h,86h,89h

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov dx,303h
			   mov al,80h
			   out dx,al
			   mov ah,0ah
	lp:       mov bx,00h
			   lea si,fir
	lp1:     mov cx,07h
	lp2:     mov dx,301h
			   mov al,si[bx]
			   ror al,cl
			   out dx,al
			   mov dx,302h
			   mov al,0ffh
			   out dx,al
			   mov al,00h
			   out dx,al
			   dec cx
			   jns lp2
			   inc bx
			   cmp bx,04h
			   jb lp1
			   call delay1
			   mov bx,00h
			   lea si,hel
	lp3:     mov cx,07h
	lp4:     mov dx,301h
			   mov al,si[bx]
			   ror al,cl
			   out dx,al
			   mov dx,302h
			   mov al,0ffh
			   out dx,al
			   mov al,00h
			   out dx,al
			   dec cx
			   jns lp4
			   inc bx
			   cmp bx,04h
			   jb lp3
			   call delay1
			   dec ah
			   jns lp
			  mov ah,4ch
			int 21h

			delay1 proc
			push cx
			push bx
			mov bx,0aaah
	lp5:    loop lp5
			dec bx
			jnz lp5
			pop bx
			pop cx
			ret
			delay1 endp
	end start