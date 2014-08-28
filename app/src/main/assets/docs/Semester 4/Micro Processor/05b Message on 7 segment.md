##Aim
###Assume any suitable message of 12 characters length and display it in the rolling fashion on a 7-segment display interface for a suitable period of time. Ensure a flashing rate that makes it easy to read both the messages. (Examiner does not specify these delay values nor is it necessary for the student to compute these values).

##program

	.model small

	.data
	codes db 0c0h,0f9h,0a4h,0b0h,99h,92h,82h,0f8h,80h,98h,88h,80h,0c6h

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov dx,303h
			   mov al,80h
			   out dx,al
			   lea si,codes
			   mov ah,0ah
	lp:       mov bx,00h
	lp1:     mov cx,07h
	lp2:     mov dx,301h
			   mov al,si[bx]
			   ror al,cl
			   out dx,al
			   mov al,0ffh
			   mov dx,302h
			   out dx,al
			   mov al,00h
			   out dx,al
			   dec cx
			   jns lp2
			   call delay1
			   inc bx
			   cmp bx,0dh
			   jne lp1
			   dec ah
			   jnz lp
			   mov ah,4ch
			   int 21h

			   delay1 proc
			   push cx
			   push bx
			   mov bx,0aah
	lp3:     loop lp3
			   dec bx
			   jnz lp3
			   pop bx
			   pop cx
			   ret
			   delay1 endp
	end start

