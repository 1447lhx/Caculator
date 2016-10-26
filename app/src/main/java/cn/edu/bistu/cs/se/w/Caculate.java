package cn.edu.bistu.cs.se.w;
import java.math.BigDecimal;
public class Caculate {
    public static final String errorInfo = "Error";//错误信息
    public static final String infinityInfo = "Infinity";//判断是否超出限定范围的信息
   public static final String naNInfo = "NaN";
    public static final double maxNum = 1.5E16;//限定最大数值
    // 阶乘的计算方法
    public static String factorial(int n) {
        double resDou = 1;
        if (n > 170) {
            return infinityInfo;
        } else {
            for (int i = 1; i <= n; i++) {
                resDou *= i;
                //判断结果是否大于最大数值
                if (resDou > Double.MAX_VALUE)
                    return infinityInfo;
            }
            return Double.toString(resDou);
        }
    }
    //log函数计算
    public static String log(double num) {
        double resDou = Math.log(num) / Math.log(10);
        //判断结果是否大于最大数值
        if( resDou > maxNum){
            return infinityInfo;
        }
        return String.valueOf(resDou);
    }

    // ln指数计算
    public static String ln(double num) {
        double resDou = Math.log(num);
        //判断结果是否大于最大数值
        if( resDou > maxNum){
            return infinityInfo;
        }
        return String.valueOf(resDou);
    }
    // sin函数计算
    public static String sin(double num) {

            double resDou = Math.sin(num);
        //判断结果是否大于最大数值
            if( resDou > maxNum){
                return infinityInfo;
            }
            return String.valueOf(resDou);
        }
    // cos函数计算
    public static String cos(double num) {

            double resDou = Math.cos(num);
            if( resDou > maxNum){
                return infinityInfo;
            }
            return String.valueOf(resDou);
        }
    // tan函数计算
    public static String tan(double num) {
            double resDou = Math.tan(num);
        //判断结果是否大于最大数值
            if( resDou > maxNum){
                return infinityInfo;
            }
            return String.valueOf(resDou);
        }
    //以E为底的指数函数
    public static String exp(double num) {
        double resDou = Math.exp(num);
        //判断结果是否大于最大数值
        if( resDou > maxNum){
            return infinityInfo;
        }
        return String.valueOf(resDou);
    }
    //判断某个字符是否为操作符号
    public boolean isOperator(char operator) {
        return ('+' == operator || '-' == operator || '*' == operator
                || '/' == operator || '^' == operator || '(' == operator || ')' == operator);
    }
    //判断某个字符是否为数字
    public boolean isnumber(char ch) {
        return (ch >= '0' && ch <= '9');
    }
    // 判断一个字符串是否为操作符号
    public boolean isOperatorStr(String _operatorStr) {
        return ("+".equals(_operatorStr) || "^".equals(_operatorStr)
                || "-".equals(_operatorStr) || "*".equals(_operatorStr) || "/"
                .equals(_operatorStr));
    }
    // 判断一个字符串是否为数字
    public boolean isNumStr(String _numberStr) {
        return !("+".equals(_numberStr) || "^".equals(_numberStr)
                || "-".equals(_numberStr) || "*".equals(_numberStr)
                || "/".equals(_numberStr) || ")".equals(_numberStr)
                || "(".equals(_numberStr) || "=".equals(_numberStr));
    }

    // 比较运算符的优先级
    // 1: 优先级高于
    // 0:优先级低于
    // -1:错误的运算符
    // 2:优先级相同
    public int compareOperator(String Operator1,
                                        String Operator2) {
        if ("^".equals(Operator1)) {//幂运算的优先级等于本身，高于加减乘除，低于其他运算符
            if ("^".equals(Operator2)) {
                return 2;
            } else if ("*".equals(Operator2)
                    || "/".equals(Operator2)
                    || "+".equals(Operator2)
                    || "-".equals(Operator2)) {
                return 1;
            } else {
                return -1;
            }
        } else if ("*".equals(Operator1)//乘除运算的优先级低于幂运算，高于加减运算
                || "/".equals(Operator1)) {
            if ("^".equals(Operator2)) {
                return 0;
            } else if ("*".equals(Operator2)
                    || "/".equals(Operator2)) {
                return 2;
            } else if ("+".equals(Operator2)
                    || "-".equals(Operator2)) {
                return 1;
            } else {
                return -1;
            }
        } else if ("+".equals(Operator1)//加减运算的优先级低于幂运算和乘除运算
                || "-".equals(Operator1)) {
            if ("^".equals(Operator2)
                    || "*".equals(Operator2)
                    || "/".equals(Operator2)) {
                return 0;
            } else if ("+".equals(Operator2)
                    || "-".equals(Operator2)) {
                return 2;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    // 将一个表达式转化为栈结构
    public Stack ChangeStrToStack(String str) {
        Stack myStack = new Stack();
        boolean getNumflag = false;//判断是否已经获得数字的标志，用于处理-为负号还是减号
        StringBuffer temStr = new StringBuffer();//临时表达式存储
        char temChar = ' ';
        // 如果表达式以'-('开头，自动插入0
        if (str.length() > 2 && '-' == str.charAt(0) && '(' == str.charAt(1)) {
            myStack.push("0");
        }
        for (int i = 0; i < str.length(); i++) {
            temChar =str.charAt(i);
            if ((!getNumflag && '-' == temChar) || !isOperator(temChar)) // 是负号
            {
                temStr.append(temChar);
                getNumflag = true;
            } else { // 是减号
                if (!"".equals(temStr.toString())) {
                    myStack.push(temStr.toString());// 压栈
                    temStr.delete(0, temStr.length()); // 清空temStr
                }
                myStack.push("" + temChar); // 压入运算符
                if (')' != temChar) {
                    getNumflag = false;
                } else { // ‘)’好后面的‘-’好应被看做运算符
                    getNumflag = true;
                }
            }
        }
        if (0 != temStr.length()) {
            myStack.push(temStr.toString());
        }
        return myStack;
    }
    //栈的遍历，处理表达式
    public Stack traverse(Stack stack) {
        String[] str = stack.getDataArray();
        Stack myStack = new Stack();
        Stack temStack = new Stack();
        boolean getNumflag = false;
        boolean getOperatorflag = false;
        for (int i = 0; i < stack.size(); i++)
        {
            if (isNumStr(str[i])) // 是数字，直接放入posfix序列中
            {
                getOperatorflag = false;
                if (getNumflag) // 如果之前处理的也是数字，在它后面插入一个*号
                {
                    myStack.push(str[i]);
                    myStack.push("*");
                } else {
                    myStack.push(str[i]);
                    getNumflag = true;
                }
            } else if ("(".equals(str[i])) // 遇到开括号，压栈
            {
                temStack.push(str[i]);
            } else if (")".equals(str[i])) // 遇到闭括号时
            {
                if (temStack.isEmpty()) {
                    return null;
                } else {
                    while (!"(".equals(temStack.top())) {
                        myStack.push(temStack.top());
                        temStack.pop();
                        if (temStack.isEmpty()) {
                            return null;
                        }
                    }
                    if ("(".equals(temStack.top())) {
                        temStack.pop(); // 弹出开括号
                    }
                }
            } else if (isOperatorStr(str[i])) // 为运算符
            {
                getNumflag = false;
                if (getOperatorflag) // 连续两个运算符
                {
                    return null;
                } else {
                    getOperatorflag = true;
                }
                while (!temStack.isEmpty()
                        && !"(".equals(temStack.top())
                        && compareOperator(temStack.top(), str[i]) > 0) {
                    myStack.push(temStack.top());
                    temStack.pop();
                }
                temStack.push(str[i]); // 将输入运算符放入栈内
            }
        }
        while (!temStack.isEmpty()) {
            if ("(".equals(temStack.top())) {
                return null;
            }
            myStack.push(temStack.top());
            temStack.pop();
        }
        return myStack;
    }
//表达式计算
    public String caculate(Stack stack) {
        if (null == stack) {
            return errorInfo;
        }
        String[] str = stack.getDataArray();
        Stack myStack = new Stack();
        double num1 = 0.;
        double num2 = 0.;
        for (int i = 0; i < stack.size(); i++) {
            if (isNumStr(str[i])) { // 如果是数字，进行压栈
                myStack.push(str[i]);
            } else if (isOperatorStr(str[i])) { // 如果是运算符，取出数字，进行计算
                if (myStack.size() < 2) // 如果栈中数字数目小于2，报错
                {
                    return errorInfo;
                }
                try {
                    if ("+".equals(str[i])) {
                        num1 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        num2 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        myStack.push(Double.toString(num2 + num1));
                    } else if ("-".equals(str[i])) {
                        num1 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        num2 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        myStack.push(Double.toString(num2 - num1));
                    } else if ("*".equals(str[i])) {
                        num1 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        num2 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        myStack.push(Double.toString(num2 * num1));
                    } else if ("/".equals(str[i])) {
                        num1 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        num2 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        if (Math.abs(num1) < 1e-10 && Math.abs(num2) < 1e-10) {
                            return naNInfo;
                        } else if (Math.abs(num1) < 1e-10) {
                            return infinityInfo;
                        }
                        myStack.push(Double.toString(num2 / num1));
                    } else if ("^".equals(str[i])) {
                        num1 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        num2 = Double.parseDouble(myStack.top());
                        myStack.pop();
                        myStack
                                .push(Double.toString(Math.pow(num2, num1)));
                    } else {
                        return errorInfo;
                    }

                } catch (NumberFormatException e) {
                    return errorInfo;
                }
            }
        }
        if (1 == myStack.size()) {
            return myStack.top();
        } else {

            return errorInfo;
        }
    }
    //其他字符过滤
    public String strFilter(String str) {
        char temChar = ' ';
        int j = 0;
        do {
            temChar = str.charAt(j);
            j++;
        } while (j < str.length() && !isnumber(temChar) && '.' != temChar
                && '(' != temChar && '-' != temChar);
        str = str.substring(j - 1);
        j = str.length() - 1;
        do {
            temChar = str.charAt(j);
            j--;
        } while (j > 0 && !isnumber(temChar) && ')' != temChar);
        str = str.substring(0, j + 2);
        StringBuffer exps = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            temChar = str.charAt(i);
            if (isnumber(temChar) || isOperator(temChar) || '.' == temChar
			 || 'E'==temChar || 'e'==temChar) {
                exps.append(temChar);
            }
        }
        return exps.toString();
    }
    //格式化结果四舍五入到第15位
    public static String formatNumber( double num, int scale){
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale(scale,BigDecimal.ROUND_HALF_EVEN);
        double d = bd.doubleValue();
        return String.valueOf(d);
    }

    public String caculateFromString(String expStr) {
        if (null == expStr || expStr.length() == 0) {
            return errorInfo;
        }
        Stack myStack_1 = ChangeStrToStack(expStr);//将表达式转为栈结构
        Stack myStack_2 = traverse(myStack_1);//遍历栈的结果放入另一个栈
        if (null == myStack_2) {
            return "Error";
        }
        String resultString = caculate(myStack_2);
        return resultString;
    }

}
