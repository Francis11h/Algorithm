package amazonOA1;

public class GradingSystem {

    //成绩打分 ABCD，判断输入的数在什么范围, 它上面是 (x >= 70) || (x < 80), 两个|| 改 成 &&
    // 并且&&  or 或者 || 逻辑关系的错误使用
    public static char checkGrade (int marks){
        if (marks <= 60) {
            return 'D';
        } else {
            if ((61 <= marks) && (marks <= 75)) return 'C';
            else
                if ((76 <= marks) && (marks <= 90)) return 'B';
                else
                    return 'A';
        }
    }
    public static void main(String[] args) {
        System.out.println(checkGrade(67));
    }

}
