class StringToBinary
{
	public static void main(String args[])
	{
		String str="dghfkjdhgvhbrjfhdrghgfdsgf",bin="";
		int i,l=str.length(),ch,x,d;
		for(i=0;i<l;i++)
		{
			ch=str.charAt(i);x=0;
			while(ch>0)
			{
				d=ch%2;
				x=x*10+d;
				ch/=2;
			}
			bin=bin.concat(Integer.toString(x));
		}
	}
}