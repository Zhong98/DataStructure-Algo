package stack_linkedList;

public class CheckBrackets {
    public int check(String checkStr) {
        char[] chars = checkStr.toCharArray();
        Stack stack = new Stack();
        for (int i = 0; i < chars.length; i++) {
            //If an opening parenthesis is detected, insert
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.insert(chars[i]);
            }

            //If the closing bracket is detected, it will pop up in turn until the corresponding opening bracket is found.
            if (chars[i] == ')' && stack.length > 0) {
                Unit pop = stack.pop();
                while ((char) pop.data != '(') { 
                    if (stack.length == 0) { //After traversing the current stack, the corresponding parentheses are still not found, and the length=-1
                        stack.length--;
                        break;
                    }
                    pop = stack.pop(); 
                }
            } else if (chars[i] == ']' && stack.length > 0) {
                Unit pop = stack.pop();
                while ((char) pop.data != '[') {
                    if (stack.length == 0) {
                        stack.length--;
                        break;
                    }
                    pop = stack.pop();
                }
            } else if (chars[i] == '}' && stack.length > 0) {
                Unit pop = stack.pop();
                while ((char) pop.data != '{') {
                    if (stack.length == 0) {
                        stack.length--;
                        break;
                    }
                    pop = stack.pop();
                }
            }
        }
        return stack.length;
    }
}
