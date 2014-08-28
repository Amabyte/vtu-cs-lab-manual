##Aim.
###Write two ALP modules stored in two different files; one module is to read a character from the keyboard and the other one is to display a character. Use the above two modules to read a string of characters from the keyboard terminated by the carriage return and print the string on the display in the next line.

##Program

### Macro for Read

	read macro
	mov ah,01h
	int 21h
	endm

### Macro to Display 

	disp macro
	mov ah,02h
	int 21h
	endm

###Main program
###Title String read and display using macros stored in different files

	include 2a1.asm
	include 2a2.asm

	.model small

	.data
	loc db 100 dup(0)
	st0 db 13,10,"Enter a string",13,10,'$'
	st1 db 13,10,"Entered string is $"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov cl,00h
			   lea bx,loc
			   lea dx,st0
			   mov ah,09h
			   int 21h
			   lea si,loc
	rd:      read
			   cmp al,08h
			   je new
			   cmp al,0dh
			   je print
			   mov [si],al
			   inc si
			   jmp rd
	new:    mov dl,' '
			   disp
			   mov dl,08h
			   disp
			   cmp si,bx
			   je rd
			   dec si
			   jmp rd
	print:  mov al,'$'
			   mov [si],al
			   lea dx,st1
			   mov ah,09h
			   int 21h
			   lea si,loc
	pri:      mov dl,[si]
			   cmp dl,'$'
			   je ter
			   disp
			   inc si
			   jmp pri
	ter:     mov ah,4ch
			   int 21h
	end start