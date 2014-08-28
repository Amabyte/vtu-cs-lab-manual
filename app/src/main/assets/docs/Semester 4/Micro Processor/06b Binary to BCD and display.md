##Aim
### Convert a 16-bit binary value (assumed to be an unsigned integer)to BCD and display it from left to right and right to left for specified number of times on a 7-segment display interface.

##Program

	.model small

	.data
	bin dw 0ffffh
	bcd db 5 dup(0)
	cod db 0c0h,0f9h,0a4h,0b0h,99h,92h,82h,0f8h,80h,90h

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov al,80h
			   mov dx,303h
			   out dx,al
			   mov ax,bin
			   mov dx,00h
			   mov bx,04h
			   mov cx,0ah
	lp:       div cx
			   mov bcd[bx],dl
			   mov dl,00h
			   dec bx
			   cmp ax,09h
			   jnb lp
			   mov bcd[bx],al
			   mov ah,03h
	lp1:     push ax
			   mov bx,00h
	lp2:     lea si,bcd
			   mov al,si[bx]
			   push bx
			   mov bl,al
			   lea si,cod
			   mov cx,07h
			   call disp
			   pop bx
			   cmp bx,03h
			   jne incr
			   call delay1
	incr:     inc bx
			   cmp bx,05h
			   jne lp2
			   call delay1
			   mov bx,03h
	lp4:     lea si,bcd
			   mov al,si[bx]
			   push bx
			   mov bl,al
			   lea si,cod
			   mov cx,07h
			   call disp
			   pop bx
			   dec bx
			   jns lp4
			   call delay1
			   mov bx,04h
	lp8:     lea si,bcd
			   mov al,si[bx]
			   push bx
			   mov bl,al
			   lea si,cod
			   mov cx,07h
			   call disp
			   pop bx
			   dec bx
			   jnz lp8
			   call delay1
			   pop ax
			   dec ah
			   cmp ah,00h
			   jnz lp1
			   mov ah,4ch
			   int 21h

			   disp proc
	lp3:     mov al,si[bx]
			   mov dx,301h
			   ror al,cl
			   out dx,al
			   mov al,0ffh
			   mov dx,302h
			   out dx,al
			   mov al,00h
			   out dx,al
			   dec cx
			   jns lp3
			   ret
			   disp endp

			   delay1 proc
			   push cx
			   push bx
			   mov bx,05aah
	lp5:     loop lp5
			   dec bx
			   jnz lp5
			   pop bx
			   pop cx
			   ret
			   delay1 endp
	end start

