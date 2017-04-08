import java.io.*;
public class Compress
{
	static int ref[]={0,0,0,0,33};
	public static void main(String args[])throws IOException
	{
		File f=new File("main.txt");
		File save=new File("compressed.txt");
		BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		PrintWriter pw=new PrintWriter(new FileOutputStream(save),true);
		String str=in.readLine(),sample,check,comp="",rep_str,rep_rev;
		int l=str.length(),sl,start_sample,end_sample,start_check,end_check,marker=2,i,k=0;
		for(sl=l/2;sl>1;sl--)
		{
			start_sample=0;end_sample=sl-1;
			for(;end_sample<l;start_sample++,end_sample++)
			{
				sample=str.substring(start_sample,end_sample);
				start_check=sl;end_check=sl+sl-1;
				if(end_check>l)
					continue;
				for(;end_check<l;start_check++,end_check++)
				{
					check=str.substring(start_check,end_check);
					k=0;rep_str=mark(sample,0);rep_rev=mark(sample,1);
					if(check.equals(sample)==true)
					{
						comp=comp.concat(rep_str);
						start_check=end_check;
						end_check+=sl;k=1;
					}
					else if(rev(sample).equals(check)==true)
					{
						comp=comp.concat(rep_rev);
						start_check=end_check;
						end_check+=sl;k=1;
					}
					else
						comp+=str.charAt(start_check);
					update(k);
				}
			}
			str=comp;comp="";
		}
		pw.println("\n"+str);
	}
	static String mark(String str,int x)throws IOException
	{
		String rep=" ";
		if(x==1)
			rep+='~';
		for(int i=4;i>=0;i--)
		{
			if(ref[i]==48 || ref[i]==49)
				ref[i]=50;
			if(ref[i]==126)
			{
				ref[i]=33;
				ref[i-1]++;
			}
			rep+=(char)ref[i];
		}
		ref[4]++;
		File save=new File("compressed.txt");
		PrintWriter pw=new PrintWriter(new FileOutputStream(save),true);
		pw.println(rep+" : "+str);
		return rep;
	}
	static String rev(String str)
	{
		String rev="";
		for(int i=str.length()-1;i>=0;i--)
			rev+=str.charAt(i);
		return(rev);
	}
	static void update(int k)
	{
		if(k==0)
			ref[4]--;
		else
			return;
		for(int i=4;i>=0;i--)
		{
			if(ref[i]<33 && i>0)
			{
				ref[i]=125;
				ref[i-1]--;
			}
		}
	}
}