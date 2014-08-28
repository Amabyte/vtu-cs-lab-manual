## 1 Aim

###Design and develop a function matchany(s1,s2) which returns the first location in thestring s1 where any character from the string s2 occurs, or -1 if s1 contains nocharacter from s2. Do not use the standard library function which does a similar job!Invoke the function matchany (s1. s2) from the main for different strings and print boththe strings and the return value from the function matchany(s1,s2).

### 3 Design

#### 3.1 Algorithm

main() function:

1.  Start
2.  Read the First String
3.  Read the Second String
4.  Call the matchany() function and the value returned is stored in position variable
5.  Print Both i.e. First and Second Strings
6.  if position is equal to -1 output “No matching characters”
7.  Otherwise output position
8.  Stop

matchany(s1,s2) function:

1.  Start
2.  i=0;
3.  while s1[i]
4.  ch = s1[i];
5.  j=0;
6.  while s2[j]
7.  if ch = s2[j] return (i+1);
8.  j++;
9.  goto 6
10.  i++
11.  goto 3
12.  return -1;

#### 3.2 Flowchart


### 4 Code

	#include <stdio.h>
	int matchany(char s1[], char s2[]);

	void main()
	{
		char s1[20],s2[20];
		int position;

		printf("Enter the first String: ");
		gets(s1);

		printf("Enter the second String: ");
		gets(s2);

		position=matchany(s1,s2);
		printf("The First String is : %s\n",s1);
		printf("The Second String is : %s\n",s2);

		if (position == -1)
			printf("No character of %s is present in %s\n",s2,s1);
		else
			printf("Postion=%d\n",position);
	}

	int matchany(char s1[],char s2[])
	{
		int i,j;
		char symbol;

		for (i=0; s1[i]; i++)
		{
			symbol=s1[i];
			for(j=0; s2[j]; j++)
			{
				if(symbol==s2[j])return (i+1);
			}
		}
	return -1;
	}

### 5 Output

Output:
First Run:
Enter the first String
HANUMAN
Enter the second String
A
The First String is : HANUMAN
The Second String is : A
Postion=2

Second Run:
Enter the first String
SUDHA
Enter the second String
Z
The First String is : SUDHA
The Second String is : Z
No character of Z is present in SUDHA

Third Run:
Enter the first String
MANJUNATH
Enter the second String
a
The First String is : MANJUNATH
The Second String is : a
No character of a is present in MANJUNATH
