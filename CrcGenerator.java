/*	PROGRAM FOR CRC ERROR CHECK
	

	Default divisor used is 1011 and 0000

	reference forouzan 288

*/


import java.io.*;

class DataOfCrcGenerator
{
	static InputStreamReader r;
	static BufferedReader input;
	
	static int dividend[];

	static int x;

	DataOfCrcGenerator()throws IOException
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

		display();
		

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

	void display()
	{
		System.out.println("--------The final codeword is-----------");
	
		for(int i=0;i<7;i++)
		System.out.print(dividend[i]+ " ");
	}

	


}

class CrcGenerator
{
	public static void main(String args[])throws IOException	
	{
		DataOfCrcGenerator d=new DataOfCrcGenerator();
	}
}