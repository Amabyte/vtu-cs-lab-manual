##Aim
###Read your name from the keyboard and display it at a specified location on the screen after the message “What is your name?” You must clear the entire screen before display.

##Program

	.model small

	.data
	ms1 db "What is your name?$"
	ms2 db "My name is: "
	nam db 99 dup(0)

	.code
	start:  mov ax,@data
			   mov ds,ax
			   call clr
			   mov dx,0c23h
			   call pos
			   lea dx,ms1
			   mov ah,09h
			   int 21h
			   lea si,nam
			   call read
			   mov dx,0d23h
			   call pos
			   lea dx,ms2
			   mov ah,09h
			   int 21h
			   mov ah,4ch
			   int 21h

			   clr proc
			   mov ah,06h
			   mov al,00h
			   mov bh,07h
			   mov cx,00h
			   mov dx,1850h
			   int 10h
			   ret
			   clr endp

			   pos proc
			   mov ah,02h
			   mov bh,00h
			   int 10h
			   ret
			   pos endp

			   read proc
			   lea di,nam
	lp1:     mov ah,01h
			   int 21h
			   cmp al,08h
			   je bck
			   cmp al,0dh
			   je dol
			   mov [si],al
			   inc si        
			   jmp lp1
	bck:    mov dl,' '
			   mov ah,02h
			   int 21h
			   mov dl,08h
			   int 21h
			   cmp si,di
			   je lp1
			   dec si
			   jmp lp1
	dol:     mov al,'$'
			   mov [si],al
			   ret
			   read endp
	end start

