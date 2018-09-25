public int calculate(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int sign = 1;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
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
            }
        }
        return res;
    }
