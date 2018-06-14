package com.eighteengray.datastructlib.stack;

import java.util.Stack;
import java.util.regex.Pattern;

public class ExpressionCaculate
{
	//��׺���ʽת��Ϊ��׺���ʽ��
	//��1����������ֱ�������������
	//��2�����������tʱ�����t�����ȼ�����ջ������������ջ��
	//���t�����ȼ�����ջ�����������ջ����ʼ�����ε����ȵ�ǰ�������������ȼ��ߵ��������ֱ��һ���������ȼ��͵Ļ���������һ�������ž�ֹͣ��
	//��3������ ( ʱ���ǽ���ѹ��ջ�С�
	//��4������ ) ʱ������ջ���ĵ�һ�� ( ����������ȫ�����ε�����������к��ٶ��������š�
	//��5����׺���ʽȫ���������ջ������������������͵���������С�
	public static String infixToSuffix(String infix)
	{
		Stack<Character> stack = new Stack<>();
		String suffix = "";
		int length = infix.length();
		for(int i=0; i<length; i++)
		{
			Character temp;//��������ջ�ĵ���Ԫ��
			char c = infix.charAt(i);
			switch (c)
			{
			case ' ':
				break;
				
			case '(':
				stack.push(c);
				break;
				
			//+-�����ȼ���ͣ�����ʱ�ѱ�+-���ȼ��ߵ�Ԫ�ض���������
			//�ٰѵ�ǰ����(+-)�ӵ�ջ�С�
			case '+':
			case '-':
				while(stack.size() != 0)
				{
					temp = stack.pop();
					if(temp == '(')
					{
						stack.push('(');
						break;
					}
					suffix += " " + temp;
				}
				stack.push(c);
				suffix += " ";
				break;
				
			//�����(+-�����*/��ջ�������*/���ջ�е�*/����ջ����������С�
			case '*':
			case '/':
				while(stack.size() != 0)
				{
					temp = stack.pop();
					if(temp == '(' || temp == '+' || temp == '-')
					{
						stack.push(temp);
						break;
					}
					else 
					{
						suffix += "" + temp;
					}
				}
				stack.push(c);
				suffix += "";
				break;
				
			case ')':
				while(stack.size() != 0)
				{
					temp = stack.pop();
					if(temp == '(')
					{
						break;
					}
					else 
					{
						suffix += "" + temp;
					}
				}
				break;

			default:
				suffix += c;
				break;
			}
		}
		
		while(stack.size() != 0)
		{
			suffix += "" + stack.pop();
		}
		return suffix;
	}
	
	
	
	//�����׺���ʽ��
	//��1������һ��ջS�������Ҷ����׺���ʽ���������־ͽ���ת��Ϊ��ֵѹ��S��
	//��2��������������ջ�����ε����������ֱ�Y��X�ϣ�Ȼ����X ����� Y ����ʽ������������ѹ��S�С�
	//��3�������׺���ʽδ���꣬���ظ�������̣�������ջ����ֵ������
	public static double suffixToArithmetic(String postfix)
	{
		Pattern pattern = Pattern.compile("\\d+||(\\d+\\.\\d+)"); //ʹ��������ʽ ƥ������
		String strings[] = postfix.split(" ");  //���ַ���ת��Ϊ�ַ�������
		for (int i = 0; i < strings.length; i++)
		{
			strings[i].trim();//ȥ���ַ�����β�Ŀո�
		}
		
		Stack<Double> stack = new Stack<Double>();
		for(int i=0; i<strings.length; i++)
		{
			if(strings[i].equals(""))
				continue;
			
			//��������֣����ջ
			if ((pattern.matcher(strings[i])).matches())
			{
				stack.push(Double.parseDouble(strings[i]));
			}
			//��������������������������������
			else 
			{
				double y = stack.pop();
				double x = stack.pop();
				stack.push(caculate(x, y, strings[i])); //������������ѹ��ջ��
			}
		}
		
		return stack.pop(); //����ջ��Ԫ�ؾ����������ս����
	}
	
	
	
	private static double caculate(double x, double y, String simble)
    {
        if (simble.trim().equals("+"))
            return x + y;
        if (simble.trim().equals("-"))
            return x - y;
        if (simble.trim().equals("*"))
            return x * y;
        if (simble.trim().equals("/"))
            return x / y;
        return 0;
    }
	
	
	
	public static void main(String[] args)
	{
		String str = "3+(2-5)*6/3";  //���׺���ʽΪ325-6*3/+
		
		//���÷�������׺���ʽת�ɺ�׺���ʽ
		System.out.println(ExpressionCaculate.infixToSuffix(str));
		
		//���÷���������һ���������ʽ����׺���ʽ�����õ�������
		System.out.println(ExpressionCaculate.suffixToArithmetic(str));
	}
	
	
	
	
	
	
	
	
	
}
