package cn.edu.bistu.cs.se.w;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
public class ConversionActivity extends AppCompatActivity implements ConversionView {

    private Button btn_back,btn_swap,btn_convert;
    private TextView text_value1,text_value2;
    private Spinner spinner_1,spinner_2;
    private ConvertPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        initViews();//初始化组件
        presenter = new ConvertPresenter(this);
    }
    public void initViews() {
        spinner_1 = (Spinner) findViewById(R.id.spinner_1);
        spinner_2 = (Spinner) findViewById(R.id.spinner_2);
        text_value1 = (TextView) findViewById(R.id.text_value1);
        text_value1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text_value1.setText("");
            }
        });
        text_value2 = (TextView) findViewById(R.id.text_value2);
        text_value2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text_value2.setText("");
            }
        });
        btn_back = (Button) findViewById(R.id.btn_tocalculator);
        //返回科学计算器界面
        btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        btn_swap = (Button) findViewById(R.id.btn_swap);
        //交换text_1和text_2的值
        btn_swap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String t = text_value1.getText() + "";
                text_value1.setText(text_value2.getText());
                text_value2.setText(t);
            }
        });

        //单位转换
        btn_convert = (Button) findViewById(R.id.btn_convert);
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert();
            }
        });
    }
//获取第一个数值
    public double getValue1() {
        double ret = 0;
        try {
            ret = Double.parseDouble(text_value1.getText() + "");
        } catch (Exception e) {
        }
        return ret;
    }
    //获取第一个单位
    public int getUnit1() {

        return unitToInt(spinner_1.getSelectedItem().toString());
    }
    //获取第二个数值
    public double getValue2() {
        double ret = 0;
        try {
            ret = Double.parseDouble(text_value2.getText() + "");
        } catch (Exception e) {
        }
        return ret;
    }
//获取第二个单位
    public int getUnit2() {
        return unitToInt(spinner_2.getSelectedItem().toString());
    }

   //将第二个数值改为点击转换按钮后的数值
    public void setValue2(String value2) {
        text_value2.setText(value2);
    }
//用于建立二维矩阵
    public int unitToInt(String unit) {
        if (unit.equals("mm")) return 0;
        if (unit.equals("cm")) return 1;
        if (unit.equals("dm")) return 2;
        if (unit.equals("m")) return 3;
        if (unit.equals("km")) return 4;
        return -1;
    }
}
