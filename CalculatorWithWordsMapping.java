import java.util.*;
public class FoundationsOfJava{
    String calculate(String s, Map<String, Integer> map) {
        if (null == s || s.length() == 0) {
            return "";
        }
        int sign = 1;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        Map<String, Integer> signMap = new HashMap<>();
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
                StringBuilder sb = new StringBuilder();
                while (i < len && Character.isLetter(s.charAt(i))) {
                    sb.append(s.charAt(i++));
                }
                i--;
                if (map.containsKey(sb.toString())) {
                    res += sign * map.get(sb.toString());
                    continue;
                }
                int count = signMap.getOrDefault(sb.toString(), 0);
                int curSign = sign;
                Stack<Integer> copiedStack = (Stack<Integer>)stack.clone();
                while (!copiedStack.isEmpty()) {
                    curSign *= copiedStack.pop();
                    copiedStack.pop();
                }
                count += curSign;
                if (count == 0) {
                    signMap.remove(sb.toString());
                } else {
                    signMap.put(sb.toString(), count);
                }
            }
        }
        String ans = String.valueOf(res);
        for (Map.Entry<String, Integer> entry : signMap.entrySet()) {
            if (entry.getValue() == 1) {
                ans += (" + " + entry.getKey());
            } else if (entry.getValue() == -1) {
                ans += (" - " + entry.getKey());
            } else {
                ans += (entry.getValue() + "*" +entry.getKey());
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        FoundationsOfJava f = new FoundationsOfJava();
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        System.out.println(f.calculate("(1+(4+5+2)-3)+(6+8)", map));
        System.out.println(f.calculate(" 2-1 + 2 ", map));
        System.out.println(f.calculate(" 1-(1-(2-(3-(4-apple)+5)+b-6)+b)", map));
    }
}
