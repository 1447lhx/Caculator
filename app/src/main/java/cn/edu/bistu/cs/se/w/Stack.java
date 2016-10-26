package cn.edu.bistu.cs.se.w;

class Stack {
    private final static int STACK_LEN = 100;//栈的长度
    private String[] data = null;
    private int top = -1;//栈顶

    public Stack() {
        if (null == data) {
            data = new String[STACK_LEN];
        }
        top = -1;
    }
//判断栈是否为空
    public boolean isEmpty() {
        return -1 == top;
    }
//入栈
    public void push(String _data) {
        if (top >= STACK_LEN) {

        }
        top++;
        data[top] = _data;
    }
    //出栈
    public void pop() {
        if (top <= -1) {

        } else {
            top--;
        }
    }
//取栈顶元素
    public String top() {
        if (-1 >= top) {

            return null;
        }
        return data[top];
    }
//计算栈中元素的个数
    public int size() {
        return top + 1;
    }
    //清空栈
    public void clear() {
        top = -1;
    }
    //获取字符串数组
    public String[] getDataArray() {
        return data;
    }

}
