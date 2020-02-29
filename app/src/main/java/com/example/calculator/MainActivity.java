package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,
            btn_point,btn_clear,btn_del,btn_plus,btn_sub,btn_multiply,btn_divide,
            btn_equal,btn_left,btn_right;
    private TextView tv_input;
    private StringBuilder pending=new StringBuilder();

    private void initView(){
        btn_0= (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_4= (Button) findViewById(R.id.btn_4);
        btn_5= (Button) findViewById(R.id.btn_5);
        btn_6= (Button) findViewById(R.id.btn_6);
        btn_7= (Button) findViewById(R.id.btn_7);
        btn_8= (Button) findViewById(R.id.btn_8);
        btn_9= (Button) findViewById(R.id.btn_9);
        btn_point= (Button) findViewById(R.id.btn_point);
        btn_clear= (Button) findViewById(R.id.btn_clear);
        btn_del= (Button) findViewById(R.id.btn_del);
        btn_plus= (Button) findViewById(R.id.btn_plus);
        btn_sub= (Button) findViewById(R.id.btn_sub);
        btn_multiply= (Button) findViewById(R.id.btn_multiply);
        btn_divide= (Button) findViewById(R.id.btn_divide);
        btn_equal= (Button) findViewById(R.id.btn_equal);
        btn_left= (Button) findViewById(R.id.btn_left);
        btn_right= (Button) findViewById(R.id.btn_right);
        tv_input= (TextView) findViewById(R.id.tv_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View v) {
        int last=0;
        if (pending.length()!=0){
            //返回指定索引处的字符（Unicode 代码点）。索引引用 char 值（Unicode 代码单元），其范围从 0 到 length() - 1
            last=pending.codePointAt(pending.length()-1);
        }
        switch (v.getId()){
            case R.id.btn_0:
                pending=pending.append("0");
                tv_input.setText(pending);
                break;
            case R.id.btn_1:
                pending=pending.append("1");
                tv_input.setText(pending);
                break;
            case R.id.btn_2:
                pending=pending.append("2");
                tv_input.setText(pending);
                break;
            case R.id.btn_3:
                pending=pending.append("3");
                tv_input.setText(pending);
                break;
            case R.id.btn_4:
                pending=pending.append("4");
                tv_input.setText(pending);
                break;
            case R.id.btn_5:
                pending=pending.append("5");
                tv_input.setText(pending);
                break;
            case R.id.btn_6:
                pending=pending.append("6");
                tv_input.setText(pending);
                break;
            case R.id.btn_7:
                pending=pending.append("7");
                tv_input.setText(pending);
                break;
            case R.id.btn_8:
                pending=pending.append("8");
                tv_input.setText(pending);
                break;
            case R.id.btn_9:
                pending=pending.append("9");
                tv_input.setText(pending);
                break;
            case R.id.btn_plus:
               if (last>='0'&&last<='9'){
                   pending=pending.append("+");
                   tv_input.setText(pending);
               }
                break;
            case R.id.btn_sub:
                if (last>='0'&&last<='9'){
                    pending=pending.append("-");
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_multiply:
                if (last>='0'&&last<='9'){
                    pending=pending.append("×");
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_divide:
                if (last>='0'&&last<='9'){
                    pending=pending.append("÷");
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_point://小数点
                if (judje1()){
                    pending=pending.append(".");
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_left://左括号
                if((last!='(')||(last>='0' &&last<='9')){
                    pending = pending.append("(");
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_right://右括号
                if (last>='0'&&last<='9'||last!=')'&&judje2()==1){
                    pending = pending.append(")");
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_del://删除
                if (pending.length()!=0){
                    pending=pending.delete(pending.length()-1,pending.length());
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_clear://清空
                if (pending.length()!=0){
                    pending=pending.delete(0,pending.length());
                    tv_input.setText(pending);
                }
                break;
            case R.id.btn_equal://等于
                if(pending.length()>1){
                    InfixInToDuffix inf=new InfixInToDuffix();
                    String result;
                    try {
                        String a=inf.toSuffix(pending);
                        result=inf.dealEquation(a);
                    }catch (Exception e){
                        result="出错";
                    }
                    tv_input.setText(pending+"="+result);
                    pending=pending.delete(0,pending.length());
                    if (Character.isDigit(result.charAt(0))){
                        pending=pending.append(result);
                    }
                }
                break;
            default:
                break;
        }

    }

    private boolean judje1() {
        String a="+-×÷.";
        int[] b=new int[a.length()];
        int max;
        for (int i=0;i<a.length();i++){
            String c=""+a.charAt(i);
            b[i]=pending.lastIndexOf(c);
        }
        Arrays.sort(b);
        if (b[a.length()-1]==-1){
            max=0;
        }else {
            max=b[a.length()-1];
        }
        if (pending.indexOf(".",max)==-1){
            return true;
        }else {
            return false;
        }
    }


    private int judje2() {
        int a=0,b=0;
        for (int i=0;i<pending.length();i++){
            if (pending.charAt(i)=='('){
                a++;
            }
            if (pending.charAt(i)==')'){
                b++;
            }
        }
        if (a==b){
            return 0;
        }
        if (a>b){
            return 1;
        }
        return 2;
    }
}
