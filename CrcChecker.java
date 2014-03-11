/*	PROGRAM FOR CRC ERROR CORRECTION
	

	Default divisor used is 1011 and 0000

	reference forouzan 288


-------------------------------------SKELETON OF THE LOGIC--------------------------------------------

a,b,c are register for storing the bits...dividend is the codeword that is received(with or w/o errors)



	[a]<----divisor[0]XOR[b]<----divisor[1]XOR[c]<-----dividend[i]XORdivisor[2]

	if([a]=1)
	divisor=011
	else
	divisor=000


	
for details as why divisor is used as 000 or 011 refer forouzan CRC and pg 288

*/


import java.io.*;

class DataOfCrcChecker
{
	static InputStreamReader r;
	static BufferedReader input;
	
	static int dividend[];

	static int x;

	DataOfCrcChecker()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		dividend=new int[7];

	System.out.println("Enter the data word");	
	
	createDataWord();
	}

	void createDataWord()throws IOException
	{
	String s;
	s=input.readLine();


		for(int i=0;i<s.length();i++)
		dividend[i]=(int)s.charAt(i)%2;	//since modulo 2 dividend string is either 1 or 0 so ascii value will do
	
	crc_Checker();	
			
	}

	void crc_Checker()
	{
	int a,b,c;
	int divisor[]=new int[3];
	int time; //time clock---tick is one count and max value = size of codeword(in this case 7)
	a=b=c=0;

	resetDivisor(true,divisor);

		for(time=0;time<7;time++)
		{
		a=b^divisor[0];
		b=c^divisor[1];
		c=dividend[time]^divisor[2];

			if(a==0)		
			resetDivisor(true,divisor);
			else
			resetDivisor(false,divisor);	//if MSB=1....divisor becomes 011 since XOR can be applied
			
	
		}

			//System.out.print(a+""+b+""+c);	to check a,b,c registers while debugging

	dividend[4]=a;		//adding the remainder 
	dividend[5]=b;		//to form the codeword
	dividend[6]=c;		//with the crc check code


					//checkForErrors(a,b,c); ......alternate method to check for error

	checkForErrors();
		

	}

	void resetDivisor(boolean x,int divisor[])
	{
		if(x)			//order of divisor is acc to forouzan page 288
		{
		divisor[2]=0;
		divisor[1]=0;
		divisor[0]=0;
		}
		else
		{
		divisor[2]=1;
		divisor[1]=1;
		divisor[0]=0;
		}
	}

	void checkForErrors()
	{
		for(int i=4;i<7;i++)
			if(dividend[i]==1)	
			{
			System.out.println("ERROR");
			return;
			}

	System.out.println("NO ERRORS");
	}	

	/*---------------------------DUMMY METHOD--------------------------

		dummy void checkForErrors(int a,int b,int c)
		{
			if(a==0&&b==0&&c==0)
			System.out.println("NO ERRORS");
			else
			System.out.println("ERROR");
		}
	*/
	


}

class CrcChecker
{
	public static void main(String args[])throws IOException	
	{
		DataOfCrcChecker d=new DataOfCrcChecker();
	}
}