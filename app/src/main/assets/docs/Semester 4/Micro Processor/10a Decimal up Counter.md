##Aim
###Write a program to simulate a Decimal Up-counter to display 00-99.

##Program

	.model small

	.data
	cnt db 64h
	msg db "BCD upcounter"
	cr db 13,10,'$'

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,msg
			   mov ah,09h
			   int 21h
			   mov cl,cnt
			   mov al,00h
	lp1:     call disp
			   loop lp1
			   mov ah,4ch
			   int 21h

			   disp proc
			   mov al,64h
			   sub al,cl
			   mov bl,0ah
			   mov ah,00h
			   div bl
			   xchg al,ah
			   mov dl,ah
			   add dl,'0'
			   mov ah,02h
			   push ax
			   int 21h
			   pop ax
			   mov dl,al
			   add dl,'0'
			   int 21h
			   mov dl,0dh
			   int 21h
			   push cx
			   mov bx,01aah
	lp:       loop lp
			   dec bx
			   jnz lp
			   pop cx
			   ret
			   disp endp
	end start