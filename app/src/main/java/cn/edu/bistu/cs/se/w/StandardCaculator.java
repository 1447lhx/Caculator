package cn.edu.bistu.cs.se.w;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class StandardCaculator extends Activity {
    private Button[] btnNum = new Button[11];
    private Button[] btnCommand = new Button[5];//运算操作按钮数组
    private EditText editText = null;//显示计算区域
    private Button btnClear = null;
    private String lastCommand; // 用于保存运算符
    private boolean clearFlag;
    private boolean firstFlag;
    private double result;
    public StandardCaculator(){
        result = 0;
        firstFlag = true;
        clearFlag = false;
        lastCommand = "=";
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btnCommand[0] = (Button) findViewById(R.id.add);
        btnCommand[1] = (Button) findViewById(R.id.subtract);
        btnCommand[2] = (Button) findViewById(R.id.multiply);
        btnCommand[3] = (Button) findViewById(R.id.divide);
        btnCommand[4] = (Button) findViewById(R.id.equal);
        btnNum[0] = (Button) findViewById(R.id.num0);
        btnNum[1] = (Button) findViewById(R.id.num1);
        btnNum[2] = (Button) findViewById(R.id.num2);
        btnNum[3] = (Button) findViewById(R.id.num3);
        btnNum[4] = (Button) findViewById(R.id.num4);
        btnNum[5] = (Button) findViewById(R.id.num5);
        btnNum[6] = (Button) findViewById(R.id.num6);
        btnNum[7] = (Button) findViewById(R.id.num7);
        btnNum[8] = (Button) findViewById(R.id.num8);
        btnNum[9] = (Button) findViewById(R.id.num9);
        btnNum[10] = (Button) findViewById(R.id.point);
        editText = (EditText) findViewById(R.id.result);
        editText.setText("0.0");
        NumberAction na = new NumberAction();//数字按钮监听器
        CommandAction ca = new CommandAction();//运算操作按钮监听器
        for (Button bc : btnCommand) {
            bc.setOnClickListener(ca);
         }
        for (Button bc : btnNum) {
            bc.setOnClickListener(na);
        }
        btnClear = (Button) findViewById(R.id.clear);
        btnClear.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                editText.setText("0.0");
                result = 0;
                firstFlag = true; // 清空以后将首次运算标志置为true
                clearFlag = false; // 清空以后将清空标志置为false
                lastCommand = "="; // 运算符
            }
        });
        Button btnScience = (Button) findViewById(R.id.btn_science);//科学计算器按钮
        btnScience.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StandardCaculator.this, ScienceCaculator.class);
                startActivity(intent);
            }
        });
    }
    // 数字按钮监听器
    private class NumberAction implements OnClickListener {
        public void onClick(View view) {
            Button btn = (Button) view;
            String input = btn.getText().toString();
            //输入事件判断
            if (firstFlag) {
                if (input.equals(".")) {
                    return;//首次输入的是小数点不进行操作
                }
                if (editText.getText().toString().equals("0.0")) {
                    editText.setText("");
                }
                firstFlag = false;
            } else {
                String editTextStr = editText.getText().toString();
                if (editTextStr.indexOf(".") != -1 && input.equals(".")) {
                    return;//多次输入小数点不进行操作，editTextStr.indexOf(".") != -1用来判断显示区域是否已经有小数点
                }
                if (editTextStr.equals("-") && input.equals(".")) {
                    return;//在-后面输入小数点不进行操作
                }
                if (editTextStr.equals("0") && !input.equals(".")) {
                    return;//显示区域为0只有输入小数点才要进行操作
                }
            }
            if (clearFlag) {
                editText.setText("");
                clearFlag = false;
            }
            editText.setText(editText.getText().toString() + input);// 设置显示区域的值为输入内容
        }
    }

    private class CommandAction implements OnClickListener {
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            String inputCommand = (String) btn.getText();
            if (firstFlag) {
                if (inputCommand.equals("-")) {//负号的处理
                    editText.setText("-");
                    firstFlag = false;
                }
            } else {
                if (!clearFlag) {
                    calculate(Double.parseDouble(editText.getText().toString()));// 不是首次输入，清空标志为false,保存显示区域的值,并计算
                }
                lastCommand = inputCommand;//记录输入的符号
                clearFlag = true;
            }
        }
    }
    // 基础计算
    private void calculate(double x) {
        if (lastCommand.equals("+")) {
            result += x;
        } else if (lastCommand.equals("-")) {
            result -= x;
        } else if (lastCommand.equals("*")) {
            result *= x;
        } else if (lastCommand.equals("/")) {
            result /= x;
        } else if (lastCommand.equals("=")) {
            result = x;
        }
        editText.setText("" + result);
    }
}
