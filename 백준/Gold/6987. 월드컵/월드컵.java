import java.util.*;

/**
*    팀 6개 > 15경기
*    1. 승수 = 패수, 2. 각 팀의 전적합 = 5, 3. 무승부는 항상 2개씩 발생
*/
class Main {
    static int[][] data;
    static boolean flag;
    static int tc;
    static int[] ta = new int[15], tb = new int[15];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        data = new int[4][18];
        for (int i = 0; i < 4; i++) for (int j = 0; j < 18; j++) data[i][j] = sc.nextInt();
        sc.close();
        
        match_setting();
        
        for (tc = 0; tc < 4; tc++) {
            flag = false;
            if (chk1()) chk2(0);
            System.out.print(flag ? "1 " : "0 ");
        }
        
    }
    
    static void match_setting() {
        int idx = 0;
        for (int i = 0; i < 6; i++) for (int j = i + 1; j < 6; j++) {
            ta[idx] = i;
            tb[idx] = j;
            idx++;
        }
    }
    
    // 기본조건 만족 확인
    static boolean chk1() {        
        int w = 0, d = 0, l = 0;
        for (int i = 0; i < 6; i++) {
            int a = data[tc][i * 3];
            int b = data[tc][i * 3 + 1];
            int c = data[tc][i * 3 + 2];
            
            if (a + b + c != 5) return false;
            
            w += a;
            d += b;
            l += c;            
        }
        if (w != l) return false;
        if (d % 2 != 0) return false;
        return true;
    }
    
    // 재귀
    static void chk2(int depth) {
        if (flag) return;    // 다른 세계선은 알빠노, 단 하나의 성공케이스만 있으면 됨
        
        if (depth == 15) {
            flag = true;
            return;
        }
        
        int cur_a = ta[depth];
        int cur_b = tb[depth];
        
        // a승 가정
        if (data[tc][cur_a * 3] > 0 && data[tc][cur_b * 3 + 2] > 0) {
            data[tc][cur_a * 3]--;    // a승수 까주고
            data[tc][cur_b * 3 + 2]--;// b패수 까주고
            chk2(depth + 1);
            data[tc][cur_a * 3]++;    // 원복
            data[tc][cur_b * 3 + 2]++;// 원복
        }
        
        // 무승부
        if (data[tc][cur_a * 3 + 1] > 0 && data[tc][cur_b * 3 + 1] > 0) {
            data[tc][cur_a * 3 + 1]--;
            data[tc][cur_b * 3 + 1]--;
            chk2(depth + 1);
            data[tc][cur_a * 3 + 1]++;
            data[tc][cur_b * 3 + 1]++;
        }

        // a패, b승
        if (data[tc][cur_a * 3 + 2] > 0 && data[tc][cur_b * 3] > 0) {
            data[tc][cur_a * 3 + 2]--;
            data[tc][cur_b * 3]--;
            chk2(depth + 1);
            data[tc][cur_a * 3 + 2]++;
            data[tc][cur_b * 3]++;
        }
        
    }
    
}