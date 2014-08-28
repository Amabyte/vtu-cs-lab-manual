##Aim
###Generate a Half Rectified Sine wave form using the DAC interface. (The output of the DAC is to be displayed on the CRO).

##Program

	.model small

	.data
	sin db 00h,16h,2bh,40h,51h,61h,6dh,77h,7dh,7fh

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov al,80h
			   mov dx,303h
			   out dx,al
			   mov dx,300h
			   mov bx,00h
	lp1:     mov al,sin [bx]
			   add al,80h
			   out dx,al
			   inc bx
			   cmp bx,09h
			   jb lp1
	lp2:     mov al,sin[bx]
			   add al,80h
			   out dx,al
			   dec bx
			   cmp bx,00h
			   jne lp2
	lp3:     mov al,80h
			   sub al,00h
			   out dx,al
			   inc bx
			   cmp bx,09h
			   jb lp3
	lp4:     mov al,80h
			   sub al,00h
			   out dx,al
			   dec bx
			   cmp bx,00h
			   jne lp4
			   loop lp1
			   mov ah,4ch
			   int 21h
	end start

