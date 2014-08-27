## Name of the Program:
Write a C++ program to create a class called DATE. Accept two valid dates of the
form dd/mm/yy. Implement the following operations by overloading the operators + and -.
After each operation ,display the result by overloading the << operator.  
1. no_of_days=d1-d2, where d1 and d2 are date objects,
d1>=d2 and no_of_days is an integer  
2. d2=d1+no_of_days, where d1 is a DATE object and
no_of_dys is an integer. 

### Theory:


In operator overloading, we can give special meanings to operators, when they are used with user-defined
classes. For example, to overload the + operator for your class, you would provide a member-function
named operator+ on your class.
The following set of operators is commonly overloaded for user-defined classes: 

*  = (assignment operator) 
*  .+ - *  (binary arithmetic operators) 
*  += -= *= (compound assignment operators) 
*  == != (comparison operators) 




### Algorithm:



### Code: 
*date.cpp*


    #include<iostream>
    using namespace std;
    class date
    {
         int dd,mm,yy;
         int a[13];
         long double dateno;
         void getno();
         public:
         date()
         {
                 a[1]=a[3]=a[5]=a[7]=a[8]=a[10]=a[12]=31;
                 a[4]=a[6]=a[9]=a[11]=30;
                 a[2]=28;
                 dd=mm=yy=1;
         }
         void getdate();
         friend ostream& operator<<(ostream&,date&);
         long double operator-(date&);
         date operator+(long double);
    };
    void date::getdate()
    {
         cout<<"\n Enter date";
         cout<<"\n\t day(dd):";
         cin>>dd;
         cout<<"\n\t\ month(mm):";
         cin>>mm;
         cout<<"\n\t year(yy):";
         cin>>yy;
         while((yy%4!=0&&dd>a[mm])||(yy%4==0 && mm==2 && dd>29) ||dd<=0 || mm>12 || mm<=0 )
         {
                 cout<<"\n Invlid entry";
                 getdate();
         } 
         getno();
    }
    void date::getno()
    {
         int m=1;
         dateno=(long double)yy*365+yy/4;
         if(yy%4>0)
         dateno++;
         while(m!=mm)
         {
                 dateno+=a[m];
                 if(yy%4==0 && m==2)
                 dateno++;
                 m++;
         }
         dateno+=dd;
    }
    ostream& operator<<(ostream &out,date &d1)
     {
          out<<d1.dd<<"/"<<d1.mm<<"/"<<d1.yy;
          return out;
     } 
     long double date::operator-(date &b)
     {
         return(dateno-b.dateno);
     }

    date date::operator+(long double b)
    {
         for(int i=1;i<=b;i++)
         {
                 dd++;
                 if(dd>a[mm])
                 {
                         mm++;
                         dd=1;
                 }
                 if(mm>12)
                 {
                         yy++;
                         mm=1;
                 }
                 if(yy%4==0)
                         a[2]=29;
         }
         return *this;
    }
    int main()
    {
         date d1,d2,d3;
         d1.getdate();
         cout<<"\n\td1="<<d1;
         d2.getdate();
         cout<<"\n\td2="<<d2;
         long double diff, days;
         diff=d1-d2;
         cout<<"\n\n The difference between the two dates = ";
         cout<<diff<<endl;
         cout<<"\n\n Enter the no. of days to be added to the date "<<d1<<" :";
         cin>>days;
         d3=d1+days;
         cout<<"\n New date is..."<<d3<<endl;
         return 0;
    }


### Steps for obtaining output:
*Steps for checking output-* 

* Locate the folder in which the file is present in the terminal.
* Execute the command "g++ the <filename.cpp>"
* Execute ethe command "./a.out"

### Output:

 Enter date
	 day(dd):29

	 month(mm):02

	 year(yy):2012

	 d1=29/2/2012
 Enter date
	 day(dd):31

	 month(mm):01

	 year(yy):2012

	 d2=31/1/2012

 The difference between the two dates = 29


 Enter the no. of days to be added to the date 29/2/2012 :29

 New date is...29/3/2012

		

### Screenshots:


![output](date.png)



