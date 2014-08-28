/***************************************************************************
*File		: 07BubbleSort.c
*Description	: Program to implement Bubble Sort Algorithm
*Author		: Prabodh C P
*Compiler	: gcc compiler, Ubuntu 8.10
*Date		: 7 September 2010
***************************************************************************/

#include<stdio.h>
#include<stdlib.h>

/***************************************************************************
*Function	: main
*Input parameters: none
*RETURNS	:	0 on success
***************************************************************************/

int main(void)
{
	int iNum, i, j, k, iaArr[10], iTemp, iKey, iPos, iLow, iHigh, iMid, iFound;

	printf("\nEnter no of elements\n");
	scanf("%d",&iNum);

	printf("\nEnter the elements\n");
	for(i=0;i<iNum;i++)
		scanf("%d",&iaArr[i]);

	for(i=0;i<iNum-1;i++)
	{
		for(j=i+1;j<iNum;j++)
		{
			if(iaArr[i] > iaArr[j])
			{
				iTemp = iaArr[i];
				iaArr[i] = iaArr[j];
				iaArr[j] = iTemp;
			}
		
		}
	}

	printf("\nThe Sorted array is \n");

	for(i=0;i<iNum;i++)
		printf("%d\t",iaArr[i]);

	printf("\n");
	printf("\nEnter the Key element\n");
	scanf("%d",&iKey);

	iFound = 0;
	iLow = 0;
	iHigh = iNum-1;

	while(iLow <= iHigh)
	{
		iMid = (iLow + iHigh)/2;
		
		if(iKey == iaArr[iMid])	/*KEY ELEMENT FOUND*/
		{
			iPos = iMid;
			iFound = 1;
			break;
		}
		else if(iKey < iaArr[iMid])	/*KEY ELEMENT IS IN 1ST HALF*/
			iHigh = iMid - 1;
		else	/*KEY ELEMENT IS IN 2ND HALF*/
			iLow = iMid +1;
	}


	if(iFound)
		printf("\nKey element %d found at position %d\n",iKey,iPos+1);
	else
		printf("\nKey element not found\n");

	return 0;
}


