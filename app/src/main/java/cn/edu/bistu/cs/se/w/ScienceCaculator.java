package cn.edu.bistu.cs.se.w;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class ScienceCaculator extends Activity {
    private EditText edittext = null;//显示区域
    private Button one_button = null;
    private Button two_button = null;
    private Button three_button = null;
    private Button four_button = null;
    private Button five_button = null;
    private Button six_button = null;
    private Button seven_button = null;
    private Button eight_button = null;
    private Button nine_button = null;
    private Button zero_button = null;
    private Button point = null;
    private Button r_brackets = null;//右括号
    private Button l_brackets = null;//左括号
    private Button pi_button = null;//pi
    private Button plus_button = null;//加
    private Button minus_button = null;//减
    private Button mutiply_button = null;//乘
    private Button divide_button = null;//除
    private Button power_button = null;//幂
    private Button factoric_button = null;//阶乘
    private Button sin_button = null;
    private Button cos_button = null;
    private Button tan_button = null;
    private Button log_button = null;
    private Button ln_button = null;
    private Button backplace_button = null;//回退
    private Button equal_button = null;
    private Button clear_button = null;//清空
    private Button exp_button = null;//常数e
    private Button dec2=null;//2进制
    private Button dec16=null;//16进制
    private Caculate myCaculator = new Caculate();
    private boolean caculated = false;//记录计算是否完成的标志
    private boolean superCaculated = false;//记录是否调用高级方法计算的标志
    private String caculateStr = "";//计算表达式
    private int MAX_CACULATE_LEN = 70;//表达式长度
    private static int SCALE = 15;//结过格式化到小数点后15位
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         init();//初始化
        setButtonsOnClickEvent();
        // 标准计算器按钮实现跳转
        Button standar = (Button) findViewById(R.id.btn_standar);
        Button btn_toConvertion = (Button) findViewById(R.id.btn_calculator_to_unit_convert);
        standar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ScienceCaculator.this, StandardCaculator.class);
                startActivity(intent);
            }
        });
        //跳转到单位换算界面
       btn_toConvertion.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ScienceCaculator.this, ConversionActivity.class);
                startActivity(intent);
            }
        });
    }
//获取表达式最后一个数字
    private String getNumEnd() {
        if (caculated) {
            if (Caculate.infinityInfo.equals(edittext.getText()
                    .toString())
                    || Caculate.naNInfo.equals(edittext.getText()
                    .toString())
                    || Caculate.errorInfo.equals(edittext.getText()
                    .toString())) {
                return null;
            } else {
                String numStr = edittext.getText().toString();
                clearEditText();
                numStr = myCaculator.strFilter(numStr);
                if (null != numStr && !"".equals(numStr)) {
                    return numStr;
                } else {
                    return null;
                }
            }
        } else {
            String temStr = edittext.getText().toString();
            if ("".equals(temStr)) {
                return null;
            } else {
                //其他字符过滤
                temStr = myCaculator.strFilter(temStr);
                if (null == temStr || "".equals(temStr)) {
                    return null;
                }
            }

            int i = 0;
            for (i = temStr.length() - 1; i >= 0; i--) {
                if (!myCaculator.isnumber(temStr.charAt(i))
                        && '.' != temStr.charAt(i)) {
                    break;
                }
            }
            // 针对编辑框中仅仅一个负数的情况
            if (0 == i && temStr.charAt(i) == '-') {
                edittext.setText("");
                // 返回整个temStr
            } else {
                edittext.setText(temStr.substring(0, i + 1));
                temStr = temStr.substring(i + 1);
            }
            return temStr;
        }
    }
    //组件初始化
   private void init() {
      edittext = (EditText) super.findViewById(R.id.result);
        edittext.setText("");
        one_button = (Button) super.findViewById(R.id.num1);
        two_button = (Button) super.findViewById(R.id.num2);
        three_button = (Button) super.findViewById(R.id.num3);
        four_button = (Button) super.findViewById(R.id.num4);
        five_button = (Button) super.findViewById(R.id.num5);
        six_button = (Button) super.findViewById(R.id.num6);
        seven_button = (Button) super.findViewById(R.id.num7);
        eight_button = (Button) super.findViewById(R.id.num8);
        nine_button = (Button) super.findViewById(R.id.num9);
        zero_button = (Button) super.findViewById(R.id.num0);
       point = (Button) super.findViewById(R.id.point);
        r_brackets = (Button) super.findViewById(R.id.right_bracket);
        l_brackets = (Button) super.findViewById(R.id.left_bracket);

        plus_button = (Button) super.findViewById(R.id.add);
        minus_button = (Button) super.findViewById(R.id.subtract);
        mutiply_button = (Button) super.findViewById(R.id.multiply);
        divide_button = (Button) super.findViewById(R.id.divide);
        power_button = (Button) super.findViewById(R.id.ci);

        factoric_button = (Button) super.findViewById(R.id.factorial);
        sin_button = (Button) super.findViewById(R.id.sin);
        cos_button = (Button) super.findViewById(R.id.cos);
        tan_button = (Button) super.findViewById(R.id.tan);
        log_button = (Button) super.findViewById(R.id.log);
        ln_button = (Button) super.findViewById(R.id.ln);
        exp_button = (Button) super.findViewById(R.id.exp);
        pi_button = (Button) super.findViewById(R.id.pi);
        backplace_button = (Button) super.findViewById(R.id.del);
        equal_button = (Button) super.findViewById(R.id.equal);
        clear_button = (Button) super.findViewById(R.id.clear);
        dec2=(Button)super.findViewById(R.id.de2);
        dec16=(Button)super.findViewById(R.id.de2);
    }

    // 设置所有按钮的点击动作
    private void setButtonsOnClickEvent() {
        setOnNumButtonClickEvent(one_button, "1");
        setOnNumButtonClickEvent(two_button, "2");
        setOnNumButtonClickEvent(three_button, "3");
        setOnNumButtonClickEvent(four_button, "4");
        setOnNumButtonClickEvent(five_button, "5");
        setOnNumButtonClickEvent(six_button, "6");
        setOnNumButtonClickEvent(seven_button, "7");
        setOnNumButtonClickEvent(eight_button, "8");
        setOnNumButtonClickEvent(nine_button, "9");
        setOnNumButtonClickEvent(zero_button, "0");

        point.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //避免superCaculated被置true
                superCaculated = false;
                if (shouldCalculate()) {
                    caculate();
                    return;
                }

                if (caculated) {
                    clearEditText();
                    caculated = false;
                    edittext.append(".");
                } else {
                    char temChar = ' ';
                    String temStr = edittext.getText().toString();
                    if (temStr.length() > 0) {
                        temChar = edittext
                                .getText()
                                .toString()
                                .charAt(edittext.getText().toString()
                                        .length() - 1);
                        // 如果是以小数点或者‘）’号结尾，不作处理
                        if ('.' == temChar || ')' == temChar) {
                            // do nothing
                            return;
                        }
                    }
                    // 从后往前查询
                    for (int i = temStr.length() - 1; i >= 0; i--) {
                        temChar = temStr.charAt(i);
                        if (myCaculator.isOperator(temChar) || ')' == temChar
                                || '(' == temChar) {
                            break;
                        } else if ('.' == temChar)// 如果在最末端的一个数字中已经有了小数点，不作处理
                        {
                            // do nothing
                            return;
                        }
                    }
                    edittext.append(".");
                }
            }
        });

        setOnBracketsButtonClickEvent(r_brackets, ")");
        setOnBracketsButtonClickEvent(l_brackets, "(");

        setOnOperatorButtonClickEvent(plus_button, "+");
        setOnOperatorButtonClickEvent(mutiply_button, "*");
        setOnOperatorButtonClickEvent(divide_button, "/");
        setOnOperatorButtonClickEvent(power_button, "^");

        setSpecialCalculateClickEvent(factoric_button);
        setSpecialCalculateClickEvent(sin_button);
        setSpecialCalculateClickEvent(cos_button);
        setSpecialCalculateClickEvent(tan_button);
        setSpecialCalculateClickEvent(ln_button);
        setSpecialCalculateClickEvent(log_button);
        setSpecialCalculateClickEvent(exp_button);
        minus_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //避免superCaculated被置true
                superCaculated = false;
                if (shouldCalculate()) {
                    caculate();
                    return;
                }
                dec2.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        //避免superCaculated被置true
                        superCaculated = false;
                        if (edittext.getText().toString().length() > 0) {
                            char lastChar = edittext
                                    .getText()
                                    .toString()
                                    .charAt(edittext.getText().toString().length() - 1);
                            if (myCaculator.isnumber(lastChar) || ')' == lastChar
                                    || '.' == lastChar) {
                                return;
                            }
                        }
                                         String a=edittext.getText().toString();
                                         String b = Integer.toBinaryString(Integer.valueOf(a));
                                         edittext.append(b);
                                     }
                             });
                dec16.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        //避免superCaculated被置true
                        superCaculated = false;
                        if (edittext.getText().toString().length() > 0) {
                            char lastChar = edittext
                                    .getText()
                                    .toString()
                                    .charAt(edittext.getText().toString().length() - 1);
                            if (myCaculator.isnumber(lastChar) || ')' == lastChar
                                    || '.' == lastChar) {
                                return;
                            }
                        }
                        String a=edittext.getText().toString();
                        String b = Integer.toHexString(Integer.valueOf(a));
                        edittext.append(b);
                    }
                });
            //如果计算过
                if (caculated) {
                    // 如果计算结果为Error或者Infinity，清空EditText
                    if ("Error".equals(edittext.getText().toString())
                            || "Infinity".equals(edittext.getText()
                            .toString())
                            || Caculate.naNInfo.equals(edittext.getText()
                            .toString())) {
                        clearEditText();
                        caculated = false;
                    } else {// 否则直接在EditText后面添加，而不清空
                        edittext.append("-");
                        caculated = false;
                    }
                }
                //如果没有计算过
                else {
                    if (edittext.getText().toString().length() == 0) {
                        edittext.append("-");
                    } else {
                        char temChar = edittext
                                .getText()
                                .toString()
                                .charAt(edittext.getText().toString()
                                        .length() - 1);
                        //如果最后一个字符不是左右括号或操作符，回退一个字符并添加-
                        if ('(' != temChar && ')' != temChar
                                && myCaculator.isOperator(temChar)) {
                            backplace();
                            edittext.append("-");
                        }
                        //如果最后一个字符是左右括号或操作符，并添加-
                        else {
                            edittext.append("-");
                        }
                    }
                }
            }
        });

        backplace_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Caculate.infinityInfo.equals(edittext.getText()
                        .toString())
                        || Caculate.errorInfo.equals(edittext.getText()
                        .toString())
                        || Caculate.naNInfo.equals(edittext.getText()
                        .toString())) {
                    clearEditText();
                }
                if (caculated) {
                    clearEditText();
                    caculated = false;
                } else {
                    backplace();
                }
                shouldCalculate();
            }
        });
        clear_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                clearEditText();

            }
        });
        equal_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edittext.getText().toString().length() == 0) {
                }
                //如果表达式框中，仅仅一个数字，而且调用了高级运算，再按下等号，则清空编辑框
                else if (superCaculated) {
                    clearEditText();
                    superCaculated = false;
                }
                //如果表达式框中没有调用了高级运算，按下等号，则在上方显示表达式
                else {
                    caculate();
                    caculateStr += '=';

                }
            }
        });


        pi_button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //避免superCaculated被置true
               superCaculated = false;

                if (edittext.getText().toString().length() > 0) {
                    char lastChar = edittext
                            .getText()
                            .toString()
                            .charAt(edittext.getText().toString().length() - 1);
                    if (myCaculator.isnumber(lastChar) || ')' == lastChar
                            || '.' == lastChar) {
                        return;
                    }
                }
               edittext.append("π=" + String.valueOf(Math.PI));

            }
        });


    }
    // 检查表达式字符串长度是否超过最大长度，如果超过，返回true，反之返回false
    private boolean shouldCalculate() {
        int text_len = edittext.getText().toString().length();
        if (text_len > MAX_CACULATE_LEN) // caculte
        {
            return true;
        } else {
            return false;
        }
    }
    private void setSpecialCalculateClickEvent(Button button) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //在表达式末端获取一个数字
                String numString = getNumEnd();
                if (null == numString || "".equals(numString)) {
                }
                //如果表达式末端数字不为空
                else {
                    //如果将编辑框中所有的数字都拿走，则将superCaculated标识置为true
                    if (edittext.getText().toString().length() == 0) {
                        superCaculated = true;
                    } else {
                        superCaculated = false;
                    }
                    double temDou = 0.;
                    String resultStr = null;
                    switch (v.getId()) {
                        // 阶乘
                        case R.id.factorial:
                            if (numString.indexOf('.') == -1)// 是整数，计算阶乘
                            {
                                int n = 0;
                                try {
                                    n = Integer.parseInt(numString);
                                } catch (NumberFormatException e) {

                                    edittext.append(numString);
                                    return;
                                }
                                resultStr = Caculate.factorial(n);
                                resultStr = formatResult(resultStr);// 格式化结果
                                edittext.append(numString + "!=" + resultStr);
                            } else {// 是小数，不计算阶乘
                                edittext.append(numString);
                            }
                            break;
                        // 正弦函数
                        case R.id.sin:
                            try {
                                edittext.append("sin" + numString + "=");
                                temDou = Double.parseDouble(numString);
                            } catch (NumberFormatException e) {
                                edittext.append(numString);
                                return;
                            }
                            resultStr = Caculate.sin(temDou);
                            resultStr = formatResult(resultStr);// 格式化结果
                            edittext.append(resultStr);
                            break;
                        // 余弦函数
                        case R.id.cos:
                            try {
                                edittext.append("cos" + numString + "=");
                                temDou = Double.parseDouble(numString);
                            } catch (NumberFormatException e) {

                                edittext.append(numString);
                                return;
                            }
                            resultStr = Caculate.cos(temDou);
                            resultStr = formatResult(resultStr);// 格式化结果
                            edittext.append(resultStr);

                            break;
                        case R.id.tan:
                            try {
                                edittext.append("tan" + numString + "=");
                                temDou = Double.parseDouble(numString);
                            } catch (NumberFormatException e) {
                                edittext.append(numString);
                                return;
                            }
                            resultStr = Caculate.tan(temDou);
                            resultStr = formatResult(resultStr);// 格式化结果
                            edittext.append(resultStr);
                            break;
                        case R.id.ln:
                            try {
                                edittext.append("ln" + numString + "=");
                                temDou = Double.parseDouble(numString);
                            } catch (NumberFormatException e) {
                                edittext.append(numString);
                                return;
                            }
                            resultStr = Caculate.ln(temDou);
                            resultStr = formatResult(resultStr);// 格式化结果
                            edittext.append(resultStr);
                            break;
                        case R.id.log:
                            try {
                                edittext.append("log" + numString + "=");
                                temDou = Double.parseDouble(numString);
                            } catch (NumberFormatException e) {
                                edittext.append(numString);
                                return;
                            }
                            resultStr = Caculate.log(temDou);
                            resultStr = formatResult(resultStr);// 格式化结果
                            edittext.append(resultStr);

                            break;
                        case R.id.exp:
                            try {
                                temDou = Double.parseDouble(numString);
                            } catch (NumberFormatException e) {
                                edittext.append(numString);
                                return;
                            }
                            resultStr = Caculate.exp(temDou);
                            resultStr = formatResult(resultStr);// 格式化结果
                            edittext.append(numString + "e=" + resultStr);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    // 设置数字键的点击动作
    private void setOnNumButtonClickEvent(Button button, final String str) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //避免superCaculated被置true
                superCaculated = false;
                if (shouldCalculate()) {
                    caculate();
                    return;
                }
                //已经计算过，清空编辑框，显示该数字
                if (caculated) {
                    clearEditText();
                    edittext.append(str);
                    caculated = false;
                } else {
                    // 如果是空字符串，可以在其后添加数字
                    if (edittext.getText().toString().length() == 0) {
                        edittext.append(str);
                    }
                    // 如果字符串以‘)’结尾，则不允许添加数字
                    else if (')' == edittext
                            .getText()
                            .toString()
                            .charAt(edittext.getText().toString().length() - 1)) {

                    }
                    // 其他情况允许添加数字
                    else {
                        edittext.append(str);
                    }
                }
            }
        });
    }
//括号匹配
    private boolean bracketsMachTest(String brackets) {
        Stack myStack = new Stack();
        String temStr = edittext.getText().toString();
        for (int i = 0; i < temStr.length(); i++) {
            //遇到左括号，入栈
            if ('(' == temStr.charAt(i)) {
                myStack.push("(");
            } else if (')' == temStr.charAt(i)) {
                //遇到右括号，如果此时栈空或栈顶元素不为左括号，则匹配失败
                if (myStack.isEmpty() || (!"(".equals(myStack.top()))) {
                    return false;
                } else {
                    myStack.pop();
                }
            }
        }
        if ("(".equals(brackets)) {
            return myStack.isEmpty();
        } else if (")".equals(brackets)) {
            return (!myStack.isEmpty()) && "(".equals(myStack.top());
        } else if ("".equals(brackets)) {
            return myStack.isEmpty();
        } else {

            return false;
        }
    }

    // 设置括号键的点击动作
    private void setOnBracketsButtonClickEvent(Button button, final String str) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //避免superCaculated被置true
                superCaculated = false;
                if (shouldCalculate()) {
                    caculate();
                    return;
                }

                if (caculated) {
                    clearEditText();
                    edittext.append(str);
                    caculated = false;
                } else {
                    // 当编辑框为空的时候，不允许输入右括号
                    if (edittext.getText().toString().length() == 0) {
                      //输入右括号，不进行操作
                        if (v.getId() == R.id.right_bracket) {

                        }
                        //输入左括号，添加到编辑框
                        else
                        {
                            {
                                edittext.append(str);
                            }
                        }
                    } else {
                        char temChar = edittext
                                .getText()
                                .toString()
                                .charAt(edittext.getText().toString()
                                        .length() - 1);
                        // 当以数字或者'.'号结尾的时候
                        if (myCaculator.isnumber(temChar) || '.' == temChar) {
                            //输入左括号,不执行操作
                            if (v.getId() == R.id.left_bracket) {

                            }
                            //输入右括号
                            else
                            {
                                if (bracketsMachTest(")")) {// 如果括号匹配成功，则输入（括号
                                    edittext.append(str);
                                }
                            }
                        } else if (myCaculator.isOperator(temChar)) {
                            // 在+-*/以及（后面，不应该输入‘)’括号
                            if (')' != temChar
                                    && v.getId() == R.id.right_bracket) {
                            }
                            // 在‘）’号后面不应输入'('号
                            else if (')' == temChar
                                    && v.getId() == R.id.left_bracket) {
                            } else {
                                if (")".equals(str)) {// 如果括号匹配成功，则输入（括号
                                    if (bracketsMachTest(str)) {
                                        edittext.append(str);
                                    }
                                } else {
                                    edittext.append(str);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    // 设置运算符键的点击动作
    private void setOnOperatorButtonClickEvent(Button button, final String str) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //避免superCaculated被置true
                superCaculated = false;
                if (shouldCalculate()) {
                    caculate();
                    return;
                }

                if (caculated) {
                    // 如果计算结果为Error或者Infinity，清空EditText
                    if ("Error".equals(edittext.getText().toString())
                            || "Infinity".equals(edittext.getText()
                            .toString())
                            || Caculate.naNInfo.equals(edittext.getText()
                            .toString())) {
                        clearEditText();
                        caculated = false;
                    } else {// 否则直接在EditText后面添加，而不清空
                        edittext.append(str);
                        caculated = false;
                    }
                } else { // 在运算过程中
                    if (edittext.getText().toString().length() == 0
                            || "-".equals(edittext.getText().toString())) {

                    } else {
                        char temChar = edittext
                                .getText()
                                .toString()
                                .charAt(edittext.getText().toString()
                                        .length() - 1);
                        // 如果以运算符结尾
                        if ('(' != temChar && ')' != temChar
                                && myCaculator.isOperator(temChar)) {
                            backplace();
                            edittext.append(str);
                        }
                        // 如果以‘(’结尾
                        else if ('(' == temChar) {
                            // do nothing
                        } else {
                            edittext.append(str);
                        }
                    }
                }
            }
        });
    }

    // 返回括号匹配情况
    // 0：括号匹配，
    // n：栈中剩余n个(号需要匹配
    // -1:有无法匹配的')'号
    private int bracketsMachCount(String temStr) {
        Stack myStack = new Stack();
        for (int i = 0; i < temStr.length(); i++) {
            if ('(' == temStr.charAt(i)) {
                myStack.push("(");
            } else if (')' == temStr.charAt(i)) {
                if (myStack.isEmpty() || (!"(".equals(myStack.top()))) {
                    return -1;
                } else {
                    myStack.pop();
                }
            }
        }
        return myStack.size();
    }

    private void caculate() {
        // 记录表达式字符串
        caculateStr = edittext.getText().toString();
        if (!"".equals(caculateStr)) {
            caculateStr = myCaculator.strFilter(caculateStr);
            int bracketsCount = bracketsMachCount(caculateStr);
            if (-1 == bracketsCount) {// 括号不匹配
                edittext.setText("Error");
                return;
            } else if (bracketsCount > 0) {// 右括号不够，添加右括号括号
                for (int i = 0; i < bracketsCount; i++) {
                    caculateStr += ')';
                }
            }
            String resultStr = myCaculator.caculateFromString(caculateStr);
            edittext.setText(formatResult(resultStr));
            caculated = true;
        }
    }

    private String formatResult(String resultStr) {
        try {
            double temDou = Double.parseDouble(resultStr);
            resultStr = Caculate.formatNumber(temDou, SCALE); // 格式化(四舍五入)
            if (resultStr.indexOf('E') == -1) // 不是科学计数法
            {
                int i = 0;
                for (i = resultStr.length() - 1; i >= 0; i--) {
                    if ('0' != resultStr.charAt(i)) {
                        break;
                    }
                }
                if (resultStr.charAt(i) == '.') {
                    resultStr = resultStr.substring(0, i); // 去掉小数点和后面的0
                } else {
                    resultStr = resultStr.substring(0, i + 1); // 去掉后面的0
                }
            }
        } catch (NumberFormatException e) {

        }
        return resultStr;
    }

    private void clearEditText() {
        edittext.setText("");
    }
//回退
    private void backplace() {
        if (edittext.getText().length() > 0) {
            edittext.setText(edittext.getText().subSequence(0,
                    edittext.length() - 1));
        }
        shouldCalculate();
    }
}
