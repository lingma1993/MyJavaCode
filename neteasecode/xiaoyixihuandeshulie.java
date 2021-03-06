/**
 * Created by ml on 2017/8/12.
 */

import java.util.Scanner;

/**
 * 小易非常喜欢拥有以下性质的数列:
 * 1、数列的长度为n
 * 2、数列中的每个数都在1到k之间(包括1和k)
 * 3、对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
 * 例如,当n = 4, k = 7
 * 那么{1,7,7,2},它的长度是4,所有数字也在1到7范围内,并且满足第三条性质,所以小易是喜欢这个数列的
 * 但是小易不喜欢{4,4,4,2}这个数列。小易给出n和k,希望你能帮他求出有多少个是他会喜欢的数列。
 * 输入描述:
 * 输入包括两个整数n和k(1 ≤ n ≤ 10, 1 ≤ k ≤ 10^5)
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数,即满足要求的数列个数,因为答案可能很大,输出对1,000,000,007取模的结果。
 * <p>
 * 输入例子1:
 * 2 2
 * <p>
 * 输出例子1:
 * 3
 */
public class xiaoyixihuandeduilie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int res = calNum2(n, k);
        System.out.println(res);
    }
    //明显是动态回归解法
    //如果我们确定这个数列的第一个数是i,那么第二个数可以是1到k中除了是i的约数的任何数。
    //问题是要找符合条件的数列的种类

    //对比于凑硬币的问题
    //队列的长度相当于凑硬币中的总金额
    //每个元素的范围（1到k之间以及其他限制条件）相当于凑硬币中的可用硬币数组

    //那么问题来了
    //本道题的状态：d(i)=j 表示队列长度为i时喜欢数列的个数？
    //但是这种想法并没有把问题分解原子化，就是这样的状态中每一个数列(k的不同范围的每一个数都可以对应下一个状态)都是可以对应下一个状态的，而不应该把它们都整合在一起

    //k的范围是1到k，这个是固定不变的
    //n是数组长度，这个是可变的，按照长度的变化划分子状态，但是每一个子状态可选的数组元素为k个，这里就出现了两层状态的变化
    //如果一维描述不清楚，那就上二维
    //dp[i][j]表示长度为i最后一个数是j的小易喜欢的数列的数量
    //最后的结果是dp[n][j]对j从1到k的遍历累加求和即可

    public static int calNum2(int n, int k) {

        int MOD = (int) (1e9 + 7);
        int MAX = (int) (1e5 + 5);
        int res = 0;
        int[][] dp = new int[n + 1][MAX];

        //初始化 n从1开始，k从1开始
        dp[0][1] = 1;

        //
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            //计算i-1的个数
            for (int j = 1; j <= k; j++) {
                sum += dp[i - 1][j];
                sum %= MOD;
            }
            //计算不符合条件的种类数
            //第i位遍历
            //在i-1位，找1到k的约数，这里巧妙地利用循环累加约数计算所有不符合的条件
            for (int j = 1; j <= k; j++) {
                int sum2 = 0;
                for (int z = j + j; z <= k; z += j) {
                    sum2 += dp[i - 1][z];
                    sum2 %= MOD;
                }
                // dp[i][j]表示长度为i最后一个数是j的小易喜欢的数列的数量 = i-1的结果数 - 不符合条件的为约数的结果数
                dp[i][j] = (sum - sum2 + MOD) % MOD;
            }
        }

        //计算结果
        for (int j = 1; j <= k; j++) {
            res += dp[n][j];
            res %= MOD;
        }

        return res;
    }
}
