##Aim
###Read an alphanumeric character and display its equivalent ASCII code at the center of the screen. 

##Program

	.model small

	.data
	msg db "Enter the charecter to check the ASCII value$"
	no db ?
	ms1 db ' ','-',' '
	bcd db 4 dup(0)

	.code
	start:   mov ax,@data
			 mov ds,ax
			 lea dx,msg
			 mov ah,09h
			 int 21h
	lp1:   mov ah,01h
			 int 21h
			 cmp al,1ah
			 je ter
			 mov no,al
			 mov al,00h
			 mov cx,00h
			 mov dx,1850h
			 mov ah,06h
			 mov bh,07h
			 int 10h
			 mov dx,0c23h
			 mov ah,02h
			 mov bh,00h
			 int 10h
			 call cvt
			 lea dx,no
			 mov ah,09h
			 int 21h
			 jmp lp1
	ter:   mov ah,4ch
			 int 21h

			 cvt proc
			 push bx
			 mov [bcd+3],'$'
			 mov al,no
			 mov cl,0ah
			 mov bx,02h
	lp:      mov ah,00h
			 div cl
			 add ah,'0'
			 mov bcd[bx],ah
			 dec bx
			 jns lp
			 pop bx
			 ret
			 cvt endp
	end start
