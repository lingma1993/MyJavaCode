/*
抛硬币
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
假设有一枚硬币，抛出去正反的概率都是50%，那么抛n次硬币就会出现很多种情况。
比如抛3次硬币，就有正正正、反反反、正正反、正反正、反正正、反反正、反正反、正反反总共8种情况。
如果按正反次数分类，则有1个3正、1个3反、3个2正1反、3个2反1正，每类都包含奇数种情况。
请问抛n次硬币，按正反次数分类，有多少类包含奇数种情况，多少类包含偶数种情况？
输入
输入一行一个正整数n
对于40%的数据，1<=n<=100
对于80%的数据，1<=n<=10000
对于100%的数据，1<=n<=10000000
输出
输出两行
第一行一个整数表示有多少类包含奇数种情况
第二行一个整数表示有多少类包含偶数种情况

样例输入
3
样例输出
4
0
*/

/*
解题思路：
掷硬币按照正反次数来分类，每一类对应的种类数是奇数还是偶数

*/

import java.util.Scanner;

public class Ofo1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int odd = 1, even = 0;
            int i = 1;
            int num = 0;
            while (n > 0){
                int temp1 = n;
                while (temp1 % 2 == 0){
                    temp1 /= 2;
                    num++;
                }
                int temp2 = i;
                while (temp2 % 2 == 0){
                    temp2 /= 2;
                    num--;
                }
                if(num > 0) even++;
                else odd++;
                i++;
                n--;
            }
            System.out.println(odd);
            System.out.println(even);
        }
    }
}
