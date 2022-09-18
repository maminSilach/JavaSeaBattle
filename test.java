package JavaSeaBattle;



public class test {

    public static void main(String[] args) {
        // Указывается первый игрок
        GameField player1 = new GameField("Maksim");

        // Указывается второй игрок
        GameField player2 = new GameField("Ivan");

        // Начало игры
        BattleShip battleShip = new BattleShip(player1,player2);



        // Готовое поле
        // 1,7
        // 3,7
        //6,3
        // 10,3
        // 5,5;6,5
        // 6,8;7,8
        // 7,10;8,10
        // 2,1;2,2;2,3
        // 5,1;6,1;7,1
        // 9,5;9,6;9,7;9,8



    }


}










