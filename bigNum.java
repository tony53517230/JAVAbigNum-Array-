import java.util.Scanner;

public class bigNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str_num_1 = sc.nextLine(), str_num_2 = sc.nextLine();
        bigNumMultiplication bn = new bigNumMultiplication();
        bn.multiply(str_num_1, str_num_2);
        sc.close();
    }
}

class bigNumMultiplication {
    private long[] num_1;
    private long[] num_2;
    private long[] answer;
    private int sign = 1;

    public boolean isZero(String num_1, String num_2) {//判斷是否為0
        if (num_1.equals("0") || num_2.equals("0")) {
            return true;
        }
        return false;
    }

    private void setNum(String num_1, String num_2) {//宣告數組陣列的大小為(字串切割個數 + 1個進位空間)，並判斷相乘後的正負號，將string轉為long array
        this.num_1 = new long[num_1.length() / 9 + 1];
        this.num_2 = new long[num_2.length() / 9 + 1];
        this.answer = new long[this.num_1.length + this.num_2.length];
        if (num_1.charAt(0) == '-') {
            num_1 = num_1.substring(1, num_1.length());
            this.sign *= -1;
        }
        if (num_2.charAt(0) == '-') {
            num_2 = num_2.substring(1, num_2.length());
            this.sign *= -1;
        }
        this.num_1 = StringToLong(num_1);
        this.num_2 = StringToLong(num_2);
    }

    private long[] StringToLong(String str) {//切割輸入字串，並限制位數為x(x=9)，轉存入long array
        long[] arr_num = new long[str.length() / 9 + 1];
        for (int i = str.length(), index = 0; i > 0; i -= i >= 9 ? 9 : i, index++) {
            arr_num[index] = Long.parseLong(str.substring(i >= 9 ? i - 9 : 0, i));
        }
        return arr_num;
    }

    public void multiply(String num_1, String num_2) {
        if (isZero(num_1, num_2)) {//判斷num_1、num_2其中1個為0則直接輸出答案為0
            System.out.println(0);
        } else {//將input轉為數組陣列，
            setNum(num_1, num_2);
            for (int i = 0; i < this.num_2.length; i++) {
                for (int j = 0; j < this.num_1.length; j++) {
                    this.answer[i + j + 1] += (this.answer[i + j] + (this.num_1[j] * this.num_2[i])) / 1000000000L;//對當前相乘結果取10^9餘數，可得進位後的值，而>=10^9的部分則進位到下一個結果
                    this.answer[i + j] = (this.answer[i + j] + (this.num_1[j] * this.num_2[i])) % 1000000000L;//num_1、num_2每個Array元素相乘後，以每個Array內容物長度不超過9為原則
                }
            }
            for (int i = answer.length - 1;; i--) {
                if (answer[i] == 0) {
                    continue;
                }
                printAnswer(i);
                break;
            }
        }
    }

    private void printAnswer(int first) {//判斷為負數輸出負號，接下來先print傳入index的Array值(因為後方要保留)
        if (this.sign == -1) {
            System.out.print('-');
        }
        System.out.print(this.answer[first]);
        for (int i = first - 1; i >= 0; i--) {
            System.out.printf("%09d", this.answer[i]);
        }
    }
}