/**
 * Created by pokerface_lx
 */
public class Position {

    private Integer row;
    private Integer col;

    public Position() {
        this.row = -1;
        this.col = -1;
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "(" + row +", " + col + ")";
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }
}