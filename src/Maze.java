import java.util.Scanner;
import java.util.Stack;

/**
 * Created by pokerface_lx
 */
public class Maze {

    private int maze[][];
    private int row = -1;
    private int col = -1;
    Stack<Position> stack = new Stack<>();
    boolean p[][] = null;

    public Maze() {
        maze = new int[15][15];
        p = new boolean[15][15];
    }

    public Maze(int row, int col) {
        this.row = row;
        this.col = col;
        maze = new int[row][col];
    }

    /***
     * 迷宫初始化
     */
    public void init() {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入迷宫的行数：");
        row = scan.nextInt();
        System.out.print("请输入迷宫的列数：");
        col = scan.nextInt();
        System.out.println("请输入" + row + "行" + col + "列的迷宫(0表示通路，1表示墙)：");
        int temp = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp = scan.nextInt();
                maze[i][j] = temp;
                p[i][j] = false;
            }
        }
    }

    /***
     * 对迷宫进行回溯算法
     */
    public void findPath() {
        //---先给原始迷宫的周围加上一圈围墙---
        int temp[][] = new int[row + 2][col + 2];
        for (int i = 0; i < row + 2; i++) {
            for (int j = 0; j < col + 2; j++) {
                temp[0][j] = 1;
                temp[row + 1][j] = 1;
                temp[i][0] = temp[i][col + 1] = 1;
            }
        }

        //---将原始迷宫复制到新的迷宫中---
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp[i + 1][j + 1] = maze[i][j];
            }
        }

        //---从左上角开始按照顺时针开始查询
        int i = 1;
        int j = 1;
        p[i][j] = true;
        stack.push(new Position(i, j));
        while (!stack.isEmpty() && (!(i == (row) && (j == col)))) {
            if (temp[i][j + 1] == 0 && p[i][j + 1] == false) {
                p[i][j + 1] = true;
                stack.push(new Position(i, j + 1));
                j++;
            } else if (temp[i + 1][j] == 0 && p[i + 1][j] == false) {
                p[i + 1][j] = true;
                stack.push(new Position(i + 1, j));
                i++;
            } else if (temp[i][j - 1] == 0 && p[i][j - 1] == false) {
                p[i][j - 1] = true;
                stack.push(new Position(i, j - 1));
                j--;
            } else if (temp[i - 1][j] == 0 && p[i - 1][j] == false) {
                p[i - 1][j] = true;
                stack.push(new Position(i - 1, j));
                i--;
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                i = stack.peek().getRow();
                j = stack.peek().getCol();
            }
        }


    }

    public void showResult() {
        Stack<Position> newPos = new Stack<>();
        if (stack.isEmpty()) {
            System.out.println("没有路径");
        } else {
            System.out.println("有路径，路径如下：");
            while (!stack.isEmpty()) {
                Position position = stack.pop();
                newPos.push(position);
            }
        }

        /***
         * 图形化输出
         */
        String result[][] = new String[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = maze[i][j] + "";
            }
        }
        while (!newPos.isEmpty()) {
            Position position = newPos.pop();
            result[position.getRow() - 1][position.getCol() - 1] = "#";
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(result[i][j] + '\t');
            }
            System.out.println();
        }
    }

}
