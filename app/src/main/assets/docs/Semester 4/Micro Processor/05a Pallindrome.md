##Aim
###Reverse a given string and check whether it is a palindrome or not. 

##Program

	.model small

	.data       
	act db 99 dup(0)
	rev db 99 dup(0)
	sl db 13,10,"String length is="
	len db ?,?,'$'
	pal db 13,10,"Entered string is a palindrome$"
	npal db 13,10,"Entered string is not a palindrome$"
	stg db "Enter a string",13,10,'$'

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,stg
			   mov ah,09h
			   lea si,act
			   mov bx,00h
			   int 21h
	lp1:    mov ah,01h
			   int 21h
			   cmp al,08h
			   je bck
			   cmp al,0dh
			   je lp2
			   mov si[bx],al
			   inc bx
			   jmp lp1
	bck:    cmp bx,00h
			   je lp1
			   dec bx
			   mov dl,' '
			   mov ah,02h
			   int 21h
			   mov dl,08h
			   int 21h
			   jmp lp1
	lp2:     mov al,'$'
			   mov si[bx],al
			   mov ax,bx
			   mov cx,bx
			   mov bl,0ah
			   mov ah,00h
			   div bl
			   add ah,'0'
			   mov [len+1],ah
			   add al,'0'
			   mov [len],al
			   lea di,rev
			   mov bx,cx
			   dec bx
	lp3:     mov al,si[bx]
			   mov [di],al
			   inc di
			   dec bx
			   jns lp3
			   mov al,'$'
			   mov [di],al
			   lea di,rev
	lp4:     mov al,[di]
			   cmp al,[si]
			   jne fail
			   inc si
			   inc di
			   loop lp4
			   lea dx,pal
			   jmp dsp
	fail:     lea dx,npal
	dsp:    mov ah,09h
			   int 21h
			   lea dx,sl
			   int 21h
			   mov ah,4ch
			   int 21h
	end start