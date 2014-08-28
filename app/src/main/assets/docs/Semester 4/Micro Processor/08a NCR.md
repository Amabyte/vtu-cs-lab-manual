##Aim
###Compute nCr using recursive procedure. Assume that ‘n’ and ‘r’ are non-negative integers. 

##Program

	.model small

	.data
	n db 05h
	r db 02h
	ncr dw ?

	.code
	start:  mov ax,@data
			   mov ds,ax
			   mov ax,00h
			   mov al,n
			   mov bl,r
			   mov ncr,00h
			   call ncrp
			   mov ah,4ch
			   int 21h
			
			   ncrp proc
			   cmp ax,bx
			   je pls1
			   cmp bx,00h
			   je pls1
			   cmp bx,01h
			   je plsn
			   dec ax
			   cmp ax,bx
			   je pls
			   push ax
			   push bx
			   call ncrp
			   pop bx
			   pop ax
			   dec bx
			   push ax
			   push bx
			   call ncrp
			   pop bx
			   pop ax
			   ret
	pls1:   inc ncr
			   ret
	plsn:   add ncr,ax
			   ret
	pls:     add ncr,ax
			   inc ncr
			   ret
			   ncrp endp
	end start

