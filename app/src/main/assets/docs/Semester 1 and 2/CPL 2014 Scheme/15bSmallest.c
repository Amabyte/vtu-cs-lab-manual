#include <stdio.h>
#include <stdlib.h>

//int main()
//{
//    int iNum, i, j;
//    float faArr1[50], fTemp;
//    float *fPtr;
//
//    float fMedian;
//
//    fPtr = faArr1;
//
//    printf("\nEnter the no of elements in the array\n");
//    scanf("%d",&iNum);
//
//    printf("\nEnter elements of Array\n");
//    for(i = 0; i < iNum; i++)
//        scanf("%f",fPtr+i);
//
//    printf("\nThe elements of Array\n");
//    for(i = 0; i < iNum; i++)
//        printf("%0.2f \t",*(fPtr+i));
//
//    for(i = 0; i < iNum-1; i++)
//    {
//            for(j = i+1; j < iNum; j++)
//            {
//                if(faArr1[i] > faArr1[j])
//                {
//                    fTemp = faArr1[i];
//                    faArr1[i] = faArr1[j];
//                    faArr1[j] = fTemp;
//                }
//            }
//
//    }
//    printf("\nThe elements of Array\n");
//    for(i = 0; i < iNum; i++)
//        printf("%0.2f \t",*(fPtr+i));
//
//    if(iNum%2)
//        fMedian = *(fPtr+(iNum/2));
//    else
//        fMedian = (*(fPtr+(iNum/2)) + *(fPtr+(iNum/2)-1))/2.0f;
//
//
//    printf("\nMedian = %f\n", fMedian);
//
//    return 0;
//}
//

int main()
{
    int iNum, i, j, iPos=1;
    float faArr1[50], fTemp;
    float *fPtr;

    float fSmall;

    fPtr = faArr1;

    printf("\nEnter the no of elements in the array\n");
    scanf("%d",&iNum);

    printf("\nEnter elements of Array\n");
    for(i = 0; i < iNum; i++)
        scanf("%f",fPtr+i);

    fSmall = *fPtr;
    for(i = 1; i < iNum; i++)
    {
        if(*(fPtr+i) < fSmall)
        {
            fSmall = *(fPtr+i);
            iPos = i+1;
        }
    }
    printf("\nThe elements of Array\n");
    for(i = 0; i < iNum; i++)
        printf("%0.2f \t",*(fPtr+i));

    printf("\nSmallest element is %0.2f and present at %d position\n", fSmall, iPos);
    return 0;
}

