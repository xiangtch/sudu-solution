import java.util.Objects;

/**
 * @author xiangtch - xiangtiancheng@youxin.cloud
 * @date 2021/7/26 10:25
 */
public class ShuduMatrix {

    public static final int NINE = 9;
    public static final int TWO = 2;
    private int[][] matrix = {
            {0, 0, 9,   3, 0, 0,    0, 6, 0},
            {6, 0, 0,   4, 9, 5,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 1},

            {0, 5, 0,   0, 0, 0,    0, 7, 6},
            {0, 2, 0,   0, 0, 0,    0, 1, 0},
            {9, 8, 0,   0, 0, 0,    0, 3, 0},

            {2, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   2, 1, 8,    0, 0, 3},
            {0, 6, 0,   0, 0, 7,    2, 0, 0}
    };

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ShuduMatrix matrix = new ShuduMatrix();
        matrix.calc();
        long endTime = System.currentTimeMillis();
        System.out.printf("本次计算用时：%sms%n", endTime - startTime);

    }

    private void calc() {
        int[] coordinate = getNextData();
        int row = coordinate[0];
        int col = coordinate[1];

        for (int i = 1; i <= NINE; i++) {
            if (checkNumber(row, col, i)) {
                matrix[row][col] = i;

                if (Objects.isNull(getNextData())) {
                    print(matrix);
                }else {
                    calc();
                }
            }
        }

        matrix[row][col] = 0;
    }

    private void print(int[][] grid) {
        for (int i = 0; i < NINE; i++) {
            for (int j = 0; j < NINE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean checkNumber(int row, int col, int n) {
        for (int i = 0; i < NINE; i++) {
            if (matrix[row][i] == n || matrix[i][col] == n) {
                return false;
            }
        }

        int[] leftTop = {row - (row % 3), col - (col % 3)};

        for (int r = leftTop[0]; r <= leftTop[0] + TWO; r++) {
            for (int c = leftTop[1]; c <= leftTop[1] + TWO; c++) {
                if (matrix[r][c] == n) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] getNextData() {
        for (int row = 0; row < NINE; row++) {
            for (int col = 0; col < NINE; col++) {
                if (matrix[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }
}
