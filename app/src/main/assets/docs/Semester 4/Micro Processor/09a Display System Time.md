##Aim
###Read the current time from the system and display it in the standard format on the screen.

##program

	.model small

	.data
	msg db "system time is:","$"   

	.code
	start:  mov ax,@data          
			   mov ds,ax             
			   mov ah,09h            
			   lea dx,msg            
			   int 21h               
			   mov ah,2ch            
			   int 21h
			   mov bl,0ah
			   mov al,ch
			   call disp
			   mov al,cl
			   call disp
			   mov al,dh
			   call disp1
			   mov ah,4ch
			   int 21h

			   disp proc
			   call disp1
			   mov dl,':'
			   mov ah,02h
			   int 21h
			   ret
			   disp endp

			   disp1 proc
			   mov ah,00h
			   div bl
			   mov dl,'0'
			   xchg al,ah
			   add dl,ah
			   mov ah,02h
			   push ax
			   int 21h
			   pop ax
			   mov dl,al
			   add dl,'0'
			   int 21h
			   ret
			   disp1 endp
	end start
