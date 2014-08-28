## Aim
###Search a key element in a list of ‘n’ 16-bit numbers using the Binary search algorithm.

## Program
	.model small

	.data
	arr dw 1234h,2345h,3456h,4567h,5678h,6789h,789ah
	len db ($-arr-1)/2
	key dw 789h
	suc db 13,10,"Element found at position = "
	pos db ?,13,10,'$'
	fai db 13,10,"Element not found!!$"

	.code
	start: mov ax,@data
			  mov ds,ax
			  mov ax,00h
			  mov cx,len 
			  mov dx,key
	lp1:   cmp cx,ax
			  jb fail
			  mov bx,cx
			  add bx,ax
			  shr bx,01h
			  mov si,bx
			  shl si,01h
			  cmp arr[si],dx
			  jb gtr
			  je succ
			  cmp bx,00h
			  je fail
			  dec bx
			  mov cx,bx
			  jmp lp1
	gtr:    inc bx
			  mov ax,bx
			  jmp lp1
	succ:  add bl,'1'
			  mov pos,bl
			  lea dx,suc
			  jmp print
	fail:    lea dx,fai
	print: mov ah,09h
			  int 21h
			  mov ah,4ch
			  int 21h
end start