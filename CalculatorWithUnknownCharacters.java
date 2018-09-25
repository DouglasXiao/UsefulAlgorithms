String calculate(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        int sign = 1;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int[] ascii = new int[256];
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i);
            if (Character.isDigit(x)) {
                int cur = 0;
                cur = cur * 10 + (x - '0');
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    cur = cur * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                res += sign * cur;
            } else if (x == '+') {
                sign = 1;
            } else if (x == '-') {
                sign = -1;
            } else if (x == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (x == ')') {
                res = res * stack.pop() + stack.pop();
            } else if (Character.isLetter(x)) {
                ascii[x] = sign;
                Stack<Integer> copiedStack = (Stack<Integer>)stack.clone();
                while (!copiedStack.isEmpty()) {
                    ascii[x] *= copiedStack.pop();
                    copiedStack.pop();
                }
            }
        }
        String ans = String.valueOf(res);
        for (int i = 0; i < 256; ++i) {
            if (ascii[i] == 1) {
                ans = ans + '+' + (char)i;
            } else if (ascii[i] == -1) {
                ans = ans + '-' + (char)i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        FoundationsOfJava f = new FoundationsOfJava();

        System.out.println(f.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(f.calculate(" 2-1 + 2 "));
        System.out.println(f.calculate(" 1-(1-(2-(3-(4-a)+5)+b-6))"));
    }
