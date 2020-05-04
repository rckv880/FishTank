//package com.fishtank.collection;// Java program for checking
//// balanced Parenthesis
//  import java.util.Scanner;
//
//public class Bracketsorter
//{
//    static class stack
//    {
//        int top=-1;
//        char items[] = new char[100];
//
//        void push(char x)
//        {
//            if (top == 99)
//            {
//                System.out.println("Stack full");
//            }
//            else
//            {
//                items[++top] = x;
//            }
//        }
//
//        char pop()
//        {
//            if (top == -1)
//            {
//                System.out.println("Underflow error");
//                return '\0';
//            }
//            else
//            {
//                char element = items[top];
//                top--;
//                return element;
//            }
//        }
//
//        boolean isEmpty()
//        {
//            return (top == -1) ? true : false;
//        }
//    }
//
//
//
//
//
//
//
//
//    /* Returns true if character1 and character2
//       are matching left and right Parenthesis */
//    static boolean isMatchingPair(char character1, char character2)
//    {
//       if (character1 == '(' && character2 == ')')
//         return true;
//       else if (character1 == '{' && character2 == '}')
//         return true;
//       else if (character1 == '[' && character2 == ']')
//         return true;
//       else
//         return false;
//    }
//
//
//
//
//
//    /* Return true if expression has balanced
//       Parenthesis */
//    static boolean areParenthesisBalanced(char exp[])
//    {
//       /* Declare an empty character stack */
//       stack st=new stack();
//
//       /* Traverse the given expression to
//          check matching parenthesis */
//       for(int i=0;i<exp.length;i++)
//       {
//
//          /*If the exp[i] is a starting
//            parenthesis then push it*/
//          if (exp[i] == '{' || exp[i] == '(' || exp[i] == '[')
//            st.push(exp[i]);
//
//          /* If exp[i] is an ending parenthesis
//             then pop from stack and check if the
//             popped parenthesis is a matching pair*/
//          if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']')
//          {
//
//              /* If we see an ending parenthesis without
//                 a pair then return false*/
//             if (st.isEmpty())
//               {
//                   return false;
//               }
//
//             /* Pop the top element from stack, if
//                it is not a pair parenthesis of character
//                then there is a mismatch. This happens for
//                expressions like {(}) */
//             else if ( !isMatchingPair(st.pop(), exp[i]) )
//               {
//                   return false;
//               }
//          }
//
//       }
//
//       /* If there is something left in expression
//          then there is a starting parenthesis without
//          a closing parenthesis */
//
//       if (st.isEmpty())
//         return true; /*balanced*/
//       else
//         {   /*not balanced*/
//             return false;
//         }
//    }
//
//
//
//
//    /* UTILITY FUNCTIONS */
//    /*driver program to test above functions*/
//    public static void main(String[] args)
//    {
//
//
//
//        //{'{','(',')'}; // not balanced.
//
//
//    	// Taking input from the user and putting it into the
//    	// array to then compare if the string inputted is
//    	// balanced or not.
//
//        Scanner sc = new Scanner(System.in);
//        String input;
//        char[] exp = new char[10];
//        System.out.println("Enter character array: ");
//        input = sc.next();
//
//        //int inputsize=input.length();
//        //char[] exp = new char[inputsize+1];
//
//        for (int i=0; i<= 10; i++)
//        	exp[i] = input.charAt(i);
//
//
//          if (areParenthesisBalanced(exp))
//
//            System.out.println("Balanced");
//
//          else
//            System.out.println("Not Balanced ");
//    }
//
//}