import java.util.Random;
import java.util.Scanner;

public class XOGame {
    static final int SIZE = 3;
    //    static final int DOTS_TO_WIN = 3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
/*
            if (checkWin(DOT_X)) {
                System.out.println("Ты победил!");
                break;
            }*/
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {

                    if (checkRight(map, i, j, 4, DOT_X)) {
                        System.out.println("Ты победил!");
                        break;
                    }
                }
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();

          /*  if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил!");
                break;
            }*/
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }


    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x, y;

        do {
            System.out.println("Введите координаты вашего хода X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x, y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


   /* public static boolean checkWin(char c) {

        for (int i = 0; i < 3; i++)
            if ((map[i][0] == c && map[i][1] == c &&
                    map[i][2] == c) ||
                    (map[0][i] == c && map[1][i] == c &&
                            map[2][i] == c))
                return true;
        if ((map[0][0] == c && map[1][1] == c &&
                map[2][2] == c) ||
                (map[2][0] == c && map[1][1] == c &&
                        map[0][2] == c))
            return true;
        return false;
       } }*/

    public static boolean checkRight(char[][] map, int x, int y, int winLength, char charToCheck) {
        int endIndex = x + winLength;
        if (endIndex > map[y].length) {
            return false;
        }
        boolean win = true;
        for (int i = x; i < endIndex; i++) {
            win &= map[y][i] == charToCheck;
            if (win == false) {
                return false;
            }
        }
        return true;
    }


}
