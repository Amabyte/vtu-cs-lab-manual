##Aim
###Write a program to create a file (input file) and to delete an existing file.


##Program

	.model small

	.data
	ent db 13,10,"Enter a file name",13,10,'$'
	crt db 50 dup(0)
	del db 50 dup(0)
	cr db 13,10,"File creation successful$"
	crf db 13,10,"File creation unsuccessful$"
	dl1 db 13,10,"File deletion successful$"
	dlf db 13,10,"File deletion unsuccessful$"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,ent
			   mov ah,09h
			   int 21h
			   lea si,crt
			   call read
			   lea dx,ent
			   mov ah,09h
			   int 21h
			   lea si,del
			   call read
			   mov cx,00h
			   clc
			   lea dx,crt
			   mov ah,3ch
			   int 21h
			   jc er
			   lea dx,cr
			   jmp disp
	er:       lea dx,crf
	disp:   mov ah,09h
			   int 21h
			   clc
			   mov cx,00h
			   mov ah,41h
			   lea dx,del
			   int 21h
			   jc err1
			   lea dx,dl1
			   jmp disp1
	err1:    lea dx,dlf
	disp1:  mov ah,09h
			   int 21h
			   mov ah,4ch
			   int 21h

			   read proc
			   mov bx,00h
	lp1:     mov ah,01h
			   int 21h
			   cmp al,0dh
			   je rtn
			   cmp al,08h
			   je bck
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
			   mov ah,00h
			   mov si[bx],ah
			   jmp lp1
	rtn:     ret
			   read endp
	end start