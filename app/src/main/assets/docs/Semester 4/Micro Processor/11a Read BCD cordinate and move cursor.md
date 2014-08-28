##Aim
###Read a pair of input co-ordinates in BCD and move the cursor to the specified location on the screen. 

##program

	.model small

	.data
	col db 13,10,"Enter column no(BCD) $"
	cl1 db ?,?
	row db 13,10,"Enter row no(BCD) $"
	rw db ?,?
	msg db 01h,"You are here$"
	bin db ?,?

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,row
			   mov ah,09h
			   int 21h
			   call read
			   mov rw,cl
			   mov rw+1,al
			   lea dx,col
			   mov ah,09h
			   int 21h
			   call read
			   mov cl1,cl
			   mov cl1+1,al
			   call cvt
			   mov ah,06h
			   mov al,00h
			   mov bh,07h
			   mov cx,00h
			   mov dx,1850h
			   int 10h
			   mov ah,02h
			   mov bh,00h
			   mov dh,bin
			   mov dl,bin+1
			   int 10h
			   lea dx,msg
			   mov ah,09h
			   int 21h
			   mov bx,0h
	lp:       loop lp
			   dec bx
			   jnz lp
			   mov ah,4ch
			   int 21h

			   cvt proc
			   mov al,rw
			   mov ah,00h
			   mov bl,0ah
			   mul bl
			   mov ah,rw+1
			   add al,ah
			   mov bin,al
			   mov al,cl1
			   mov ah,00h
			   mov bl,0ah
			   mul bl
			   mov ah,cl1+1
			   add al,ah
			   mov bin+1,al
			   ret
			   cvt endp

			   read proc
			   mov ah,01h
			   int 21h
			   sub al,'0'
			   mov cl,al
			   int 21h
			   sub al,'0'
			   ret
			   read endp
	end start