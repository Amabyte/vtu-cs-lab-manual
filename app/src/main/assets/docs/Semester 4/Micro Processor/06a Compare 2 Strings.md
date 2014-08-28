##Aim
###Read two strings, store them in locations STR1 and STR2. Check whether they are equal or not and display appropriate messages. Also display the length of the stored strings. 

##Program

	.model small

	.data       
	st1 db 99 dup(0)
	st2 db 99 dup(0)
	sl1 db 13,10,"String length of string 1 is = "
	ln1 db ?,?,'$'
	sl2 db 13,10,"String length of string 2 is = "
	ln2 db ?,?,'$'
	ln db ?
	m1 db 13,10,"Enter string 1",13,10,'$'
	m2 db 13,10,"Enter string 2",13,10,'$'
	suc db 13,10,"Entered strings are equal$"
	fai db 13,10,"Entered strings are not equal$"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,m1
			   mov ah,09h
			   int 21h
			   lea si,st1
			   call read
			   mov ln,bl
			   call cvt
			   mov [ln1+1],ah
			   mov [ln1],al
			   lea dx,m2
			   mov ah,09h
			   int 21h
			   lea si,st2
			   call read
			   call cvt
			   mov [ln2+1],ah
			   mov [ln2],al
			   mov ah,ln1
			   mov al,ln1+1
			   mov bh,ln2
			   mov bl,ln2+1
			   cmp bx,ax
			   jne fail
			   mov cl,ln
			   mov ch,00h
			   lea si,st1
			   lea di,st2
	lp3:     mov al,[si]
			   cmp al,[di]
			   jne fail
			   inc si
			   inc di
			   loop lp3
			   lea dx,suc
			   jmp disp
	fail:     lea dx,fai
	disp:   mov ah,09h
			   int 21h
			   lea dx,sl1
			   int 21h
			   lea dx,sl2
			   int 21h
			   mov ah,4ch
			   int 21h

			   read proc
			   mov bx,00h
	lp1:     mov ah,01h
			   int 21h
			   cmp al,08h
			   je bck
			   cmp al,0dh
			   je lp2
			   mov si[bx],al
			   inc bx
			   jmp lp1
	bck:    mov dl,' '
			   mov ah,02h
			   int 21h
			   mov dl,08h
			   int 21h
			   cmp bx,00h
			   je lp1
			   dec bx
			   jmp lp1
	lp2:     mov al,'$'
			   mov si[bx],al
			   ret
			   read endp

			   cvt proc
			   mov ax,bx
			   mov bl,0ah
			   mov ah,00h
			   div bl
			   add ah,'0'
			   add al,'0'
			   ret
			   cvt endp
	end start