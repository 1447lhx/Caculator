package cn.edu.bistu.cs.se.w;

public class ConvertPresenter {
    private ConversionView view;
//单位转换的二维数组
// mm： 0;
// cm : 1;
// dm : 2;
// m: 3;
//km: 4;
    private double[][] convert = {
            {1, 0.1, 0.01, 0.001, 0.000001,},
            {10, 1, 0.1, 0.01, 0.00001,},
            {100, 10, 1, 0.1, 0.0001,},
            {1000, 100, 10, 1, 0.001,},
            {1000000, 100000, 10000, 1000, 1,},
    };

    public ConvertPresenter(ConversionView view) {
        this.view = view;
    }

    public void convert() {
        double value1 = view.getValue1();
        double value2 = value1 * convert[view.getUnit1()][view.getUnit2()];
        view.setValue2(value2 + "");
    }
}
