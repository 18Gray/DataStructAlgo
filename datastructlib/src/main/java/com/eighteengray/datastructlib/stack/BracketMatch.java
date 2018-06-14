package com.eighteengray.datastructlib.stack;

import java.util.Stack;


public class BracketMatch
{
    public static void main(String[] args)
    {
        String str = "{{{a+b}}}(())";
        char[] c = str.toCharArray();
        if (isMatch(c))
        {
            System.out.println("ƥ��");
        } else
            System.out.println("��ƥ��");
    }

    
    public static boolean isMatch(char[] c)
    {
        Stack<Character> stack = new Stack<Character>();
        int length = c.length;
        char temp;
        boolean b = true;
        
        for (int i = 0; i < length; i++)
        {
            temp = c[i];
            if (temp == '{' || temp == '[' || temp == '(')
                //������������ʱ���ͽ�ջ��  
                stack.push(temp);
            
            if (temp == '}' || temp == ']' || temp == ')')
            {
                //�����պ�����ʱ���ͷ�����ж�  
                //���ջ��Ϊ�գ���ô�϶��ǲ�ƥ����ˡ�  
                if (stack.isEmpty())
                {
                    b = false;
                    break;
                } 
                else
                {
                    //�����Ϊ�գ����ж�ջ���������ǲ��������е�����ƥ��  
                    //����ջ��Ԫ������ջ����,����϶����ǲ�ƥ�䡣  
                    if ((temp == '}' && stack.peek() == '{') ||
                    	(temp == ']' && stack.peek() == '[') ||
                        (temp == ')' && stack.peek() == '('))
                    {
                        stack.pop();
                    } 
                    else
                    {
                        b = false;
                        break;
                    }
                }
            }
        }

        //�����бպ����Ŷ�ƥ����ʱ�����ջ��Ϊ�գ���ô�ͱ�ʾ���п�������û��ƥ�䡣  
        if (!stack.isEmpty())
        {
            return b;
        }
        return b;
    }

}
