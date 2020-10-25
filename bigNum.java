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

    public boolean isZero(String num_1, String num_2) {//�P�_�O�_��0
        if (num_1.equals("0") || num_2.equals("0")) {
            return true;
        }
        return false;
    }

    private void setNum(String num_1, String num_2) {//�ŧi�Ʋհ}�C���j�p��(�r����έӼ� + 1�Ӷi��Ŷ�)�A�çP�_�ۭ��᪺���t���A�Nstring�ରlong array
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

    private long[] StringToLong(String str) {//���ο�J�r��A�í����Ƭ�x(x=9)�A��s�Jlong array
        long[] arr_num = new long[str.length() / 9 + 1];
        for (int i = str.length(), index = 0; i > 0; i -= i >= 9 ? 9 : i, index++) {
            arr_num[index] = Long.parseLong(str.substring(i >= 9 ? i - 9 : 0, i));
        }
        return arr_num;
    }

    public void multiply(String num_1, String num_2) {
        if (isZero(num_1, num_2)) {//�P�_num_1�Bnum_2�䤤1�Ӭ�0�h������X���׬�0
            System.out.println(0);
        } else {//�Ninput�ର�Ʋհ}�C�A
            setNum(num_1, num_2);
            for (int i = 0; i < this.num_2.length; i++) {
                for (int j = 0; j < this.num_1.length; j++) {
                    this.answer[i + j + 1] += (this.answer[i + j] + (this.num_1[j] * this.num_2[i])) / 1000000000L;//���e�ۭ����G��10^9�l�ơA�i�o�i��᪺�ȡA��>=10^9�������h�i���U�@�ӵ��G
                    this.answer[i + j] = (this.answer[i + j] + (this.num_1[j] * this.num_2[i])) % 1000000000L;//num_1�Bnum_2�C��Array�����ۭ���A�H�C��Array���e�����פ��W�L9����h
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

    private void printAnswer(int first) {//�P�_���t�ƿ�X�t���A���U�ӥ�print�ǤJindex��Array��(�]�����n�O�d)
        if (this.sign == -1) {
            System.out.print('-');
        }
        System.out.print(this.answer[first]);
        for (int i = first - 1; i >= 0; i--) {
            System.out.printf("%09d", this.answer[i]);
        }
    }
}