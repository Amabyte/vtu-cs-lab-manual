##Aim
###Scan a 8 x 3 keypad for key closure and to store the code of the key pressed in a memory location or display on screen. Also display row and column numbers of the key pressed.

##Program

	.model small

	.data
	msg db "0123456789ABCDEFGHIJ"
	rd db 13,10,"Read character is = $"
	rw db 13,10,"Row number is = "
	row db ?
	cl1 db 13,10,"Column number is = "
	col db ?,'$'
	en db 13,10,"Enter 20 characters from keypad.$"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov dx,303h
			   mov al,90h
			   out dx,al
			   lea dx,en
			   mov ah,09h
			   int 21h
			   mov cx,14h
	lp:       mov dx,302h
			   mov al,07h
			   out dx,al
			   mov dx,300h
	lp1:     in al,dx
			   cmp al,00h
			   je lp1
			   call cvt
			   mov bx,0403h
	lp2:     mov al,bh
			   mov dx,302h
			   out dx,al
			   mov dx,300h
			   in al,dx
			   ror bh,01h
			   dec bl
			   cmp al,00h
			   je lp2
			   add bl,'1'
			   mov col,bl
			   call disp
			   loop lp
			   mov ah,4ch
			   int 21h

			   cvt proc
			   push cx
			   mov cx,08h
	lp3:     rol al,01h
			   jc lp4
			   loop lp3
	lp4:     add cl,'0'
			   mov row,cl
			   pop cx
			   ret
			   cvt endp

			   disp proc
			   mov al,col
			   sub al,'1'
			   mov bl,08h
			   mov ah,00h
			   mul bl
			   mov bl,row
			   sub bl,'1'
			   add al,bl
			   mov bx,ax
			   lea dx,rd
			   mov ah,09h
			   int 21h
			   lea si,msg
			   mov dl,si[bx]
			   mov ah,02h
			   int 21h
			   lea dx,rw
			   mov ah,09h
			   int 21h
			   push cx
			   push bx
			   mov bx,011h
	lp5:     loop lp5
			   dec bx
			   jnz lp5
			   pop bx
			   pop cx
			   ret
			   disp endp
	end start