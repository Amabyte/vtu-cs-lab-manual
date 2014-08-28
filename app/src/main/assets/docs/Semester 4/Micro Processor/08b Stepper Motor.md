##Aim
###Drive a Stepper Motor interface to rotate the motor in specified direction (clockwise or counter-clockwise) by N steps (Direction and N are specified by the examiner). Introduce suitable delay between successive steps. (Any arbitrary value for the delay may be assumed by the student).

##NOTE
###This File contains 3 Programs ,To rotate Clockwise,Anti Clockwise and Both Directions.
###Any program can be asked by the examiner.

##Program to rotate in Clockwise Direction


	.model small

	.data
	msg db "Motor is rotating in clockwise direction$"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,msg
			   mov ah,09h
			   int 21h
			   mov al,80h
			   mov dx,303h
			   out dx,al
			   mov cx,0c8h
			   mov al,077h
			   mov dx,302h
	lp1:     out dx,al
			   call delay1
			   ror al,01h
			   loop lp1
			   mov ah,4ch
			   int 21h

			   delay1 proc
			   push cx
			   push bx
			   mov bx,00aah
	lp2:     loop lp2
			   dec bx
			   jnz lp2
			   pop bx
			   pop cx
			   ret
			   delay1 endp
	end start



##Program to rotate in Anti Clockwise Direction


	.model small

	.data
	msg db "Motor is rotating in anti clockwise direction$"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,msg
			   mov ah,09h
			   int 21h
			   mov al,80h
			   mov dx,303h
			   out dx,al
			   mov cx,0c8h
			   mov al,0eeh
			   mov dx,302h
	lp1:     out dx,al
			   call delay1
			   rol al,01h
			   loop lp1
			   mov ah,4ch
			   int 21h

			   delay1 proc
			   push cx
			   push bx
			   mov bx,00aah
	lp2:     loop lp2
			   dec bx
			   jnz lp2
			   pop bx
			   pop cx
			   ret
			   delay1 endp
	end start



##Program to rotate in both direction

	.model small

	.data
	msg db "Motor is rotating in clockwise direction$"
	ms1 db "Motor is rotating in anti-clockwise direction$"

	.code
	start:  mov ax,@data
			   mov ds,ax
			   lea dx,msg
			   mov ah,09h
			   int 21h
			   mov al,80h
			   mov dx,303h
			   out dx,al
			   mov cx,064h
			   mov dx,302h
			   mov al,077h
	lp:       out dx,al
			   ror al,01h
			   call delay1
			   loop lp
			   lea dx,ms1
			   mov ah,09h
			   int 21h
			   mov cx,064h
			   mov al,0eeh
	lp1:     out dx,al
			   call delay1
			   rol al,01h
			   loop lp1
			   mov ah,4ch
			   int 21h

			   delay1 proc
			   push cx
			   push bx
			   mov bx,00aah
	lp2:     loop lp2
			   dec bx
			   jnz lp2
			   pop bx
			   pop cx
			   ret
			   delay1 endp
	end start

