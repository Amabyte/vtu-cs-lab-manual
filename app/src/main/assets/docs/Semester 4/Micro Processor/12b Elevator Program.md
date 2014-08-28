##Aim
###Drive an elevator interface in the following way: i. Initially the elevator should be in the ground floor, with all requests in OFF state. ii. When a request is made from a floor, the elevator should move to that floor, wait there for a couple of seconds (approximately), and then come down to ground floor and stop. If some requests occur during going up or coming down they should be ignored.

##Program

	.model small

	.data
	clr db 0e0h,0d3h,0b6h,079h

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov al,82h
			   mov dx,303h
			   out dx,al
			   mov al,00h
			   mov dx,300h
			   out dx,al
			   mov al,0f0h
			   out dx,al
			   mov dx,301h
	lp:       in al,dx
			   and al,0fh
			   cmp al,0fh
			   je lp
			   mov cx,00h
	lp1:     ror al,01h
			   inc cx
			   jc lp1
			   dec cx
			   call ele
			   mov ah,4ch
			   int 21h

			   ele proc
			   push cx
			   mov al,cl
			   mov cl,03h
			   mov ah,00h
			   mul cl
			   mov cx,ax
			   mov dx,300h
			   mov al,0f0h
	lp2:     cmp cx,00h
			   je lp3
			   out dx,al
			   inc al
			   call delay1
			   dec cx
			   jmp lp2
	lp3:     pop bx
			   mov al,clr[bx]
			   push bx
			   out dx,al
			   or al,0f0h
			   out dx,al
			   mov al,bl
			   mov ah,00h
			   mov cl,03h
			   mul cl
			   or al,0f0h
			   mov cl,bl
	lp4:     cmp cl,00h
			   je rtn
			   dec al
			   out dx,al
			   call delay1
			   dec cl
			   jmp lp4
	rtn:     ret
			   ele endp

			   delay1 proc
			   push cx
			   push bx
			   mov bx,00aah
	lp5:     loop lp5
			   dec bx
			   jnz lp5
			   pop bx
			   pop cx
			   ret
			   delay1 endp
	end start