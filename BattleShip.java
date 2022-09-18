package JavaSeaBattle;


import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

// Наша игра
public class BattleShip {
    // Рандом нужен для того, чтобы определить кто будет ходить первым
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    // Первый флаг используется для проверки правильности введеных координат игроком, если все окей возвращается true
    private boolean flag = false;
    // Второй флаг проверяет, идет ли еще игра
    private boolean isGame = false;
    GameField player1;
    GameField player2;

    /*
    Счетчик чтобы определить кто сейчас ходит, 0 - первый игрок, 1 - второй
    В самой первой инициализации кто ходит первым определяется случайно
     */
    private int count = random.nextInt(2);

    // Конструктор класса где все и происходит
    public BattleShip(GameField player1, GameField player2) {
        this.player1 = player1;
        this.player2 = player2;

        // Информация для игроков
        System.out.println("                     INFO");
        System.out.println("------------------------------------------------------");
        System.out.println("\uD83D\uDFE6 - Зона игрового поля");
        System.out.println("\uD83D\uDFE9 - Ореол корабля (его водное пространство) ");
        System.out.println("🛥️ - Корабль" );
        System.out.println("\uD83D\uDFE5 - Корабль был поврежден");
        System.out.println("\uD83D\uDFE8 - Выстрел соперника мимо");
        System.out.println("Игра завершается автоматически, когда на поле одного из двух игроков не остается целых кораблей");
        System.out.println("Соблюдайте правила ввода в консоль, указанные в описании");
        System.out.println("------------------------------------------------------" + "\n");

        // Расстановка первого игрока, после указывается 13 пробелов, чтобы второй игрок не увидел поле
        player1.rasstanovka();
        for (int i = 0; i < 13; i++) {
            System.out.println();
        }
        // Расстановка второго игрока
        player2.rasstanovka();
        for (int i = 0; i < 13; i++) {
            System.out.println();
        }

        // Пока флаг isGame != true, игра продолжается
        while (!isGame) {
            String line = "";

            // Ход первого игрока
            if (count == 0) {
                System.out.println("Ходит игрок " + player1.getPlayerName());

                //Пока flag != true , будет просится ввод в консоль ПРАВИЛЬНЫХ координат
                while (!flag) {
                    System.out.println("Введите координаты, по которым желаете нанести удар( формат: y,x)");
                    System.out.println("1.Посмотреть свое поле");
                    System.out.println("2.Посмотреть координаты, по которым вы уже наносили удар");
                    try {
                        line = scanner.nextLine().trim();
                        // метод checkOfFormat возвращает true только если все было указано верно
                        flag = checkOfFormat(line);
                    } catch (Exception e) {
                        // В случае ошибки вылезает сообщение, в котором просится ввести верные координаты
                        e.printStackTrace();
                        System.err.println("Введите координаты как указано в формате!");
                    }
                }
                // Вызов метода где и происходит сам ход игрока
                stepOfPlayer(line, player1);
                flag = false;
                count = 1;
            } else {
                // Ход второго игрока, все тоже самое
                System.out.println("Ходит игрок " + player2.getPlayerName());
                while (!flag) {
                    System.out.println("Введите координаты, по которым желаете нанести удар( формат: y,x)");
                    System.out.println("1.Посмотреть свое поле");
                    System.out.println("2.Посмотреть координаты, по которым вы уже наносили удар");
                    try {
                        line = scanner.nextLine().trim();
                        flag = checkOfFormat(line);
                    } catch (Exception e) {
                        System.err.println("Введите координаты как указано в формате!");
                    }
                }
                stepOfPlayer(line, player2);
                flag = false;
                count = 0;
            }
        }
        // Эти сообщения выводятся только когда главный цикл с переменной isGame останавливается
        System.out.println("Поле игрока: " + player2.getPlayerName());
        player1.printField();
        System.out.println("Поле игрока: " + player1.getPlayerName());
        player2.printField();
    }

// Проверка на правильность координат
    boolean checkOfFormat(String line) {
        String[] lineSplitSize = line.split(",");
        // Если было введено число 1, будет вызываться метод с выводом поля игрока на экран
        if(lineSplitSize.length==1 && Integer.parseInt(lineSplitSize[0]) == 1) {
          printOfField(count);
            System.out.println();
          return false;
        }
        // Если было введено число 2, будет вызываться метод с выводом координат, которые уже были введены ранее
        if(lineSplitSize.length==1 && Integer.parseInt(lineSplitSize[0]) == 2) {
            printOfCoordinates(count);
            return false;
        }
        // Проверка что было указано только одна координата для удара
        if (lineSplitSize.length != 2) {
            System.err.println("Введите только две координаты через запятую!");
            return false;
        }
        if(line.equals("")) {
            return false;
        }
        int y = Integer.parseInt(lineSplitSize[0]);
        int x = Integer.parseInt(lineSplitSize[1]);
        // Валидация
        if ((x > 10 || x <= 0) || (y > 10 || y <= 0)) {
            System.err.println("Координаты не могут быть меньше 0 и больше 10!");
            return false;
        }
        return true;
    }


    // Шаг одного из игроков
    void stepOfPlayer(String line, GameField player) {
        // Выдача игрового поля
        String[][] fieldPlayer = player.getField_player();
        String[] lineSplitSize = line.split(",");
        int y = Integer.parseInt(lineSplitSize[0]);
        int x = Integer.parseInt(lineSplitSize[1]);

        // Проверка что игрок указывал уже эти координаты
        if(fieldPlayer[y][x].equals("\uD83D\uDFE8") || fieldPlayer[y][x].equals("\uD83D\uDFE5")) {
            while ((fieldPlayer[y][x].equals("\uD83D\uDFE8") || (fieldPlayer[y][x].equals("\uD83D\uDFE5")))) {
                System.err.println("Вы уже указывали эти координаты, введите другие");
                line = scanner.nextLine();
                try {
                    String[] lineSplitSize2 = line.split(",");
                    y = Integer.parseInt(lineSplitSize2[0]);
                    x = Integer.parseInt(lineSplitSize2[1]);
                } catch (Exception e) {
                    System.err.println("Введите координаты как указаны в формате!");
                }
            }

        }
        // Проверка попал ли игрок в корабль, цепочкой идет проверка его Ореола, есть ли там еще часть от корабля
        if (fieldPlayer[y][x].equals("🛥️")) {
            if (!Objects.equals(fieldPlayer[y][x + 1], "🛥️")) {
                if (!Objects.equals(fieldPlayer[y][x - 1], "🛥️")) {
                    if (!Objects.equals(fieldPlayer[y + 1][x], "🛥️")) {
                        if (!Objects.equals(fieldPlayer[y - 1][x], "🛥️")) {
                            // Если же вокруг больше нет его частей, то корабль был утоплен
                            System.out.println("Корабль потоплен!");
                            fieldPlayer[y][x] = "\uD83D\uDFE5";
                            player.shipCount += 1;
                            return;
                        }
                    }
                }
            }
            // Если же корабль еще имеет какую либо часть, то это просто попадание
            System.out.println("Попадание!");
            fieldPlayer[y][x] = "\uD83D\uDFE5";
            player.shipCount += 1;
            return;
        }
        // Проверка есть ли еще целые корабли на игровом поле
        if (player.shipCount == 20) {
            System.out.println("Игра закончена, победил игрок " + player.getPlayerName());
            isGame = true;
            return;
        }
        // В противном же случае удар пришелся мимо
            System.out.println("Мимо!");
            fieldPlayer[y][x] = "\uD83D\uDFE8";
    }

    // Вывод поля на консоль
    public void printOfField(int count){
        if(count==0){
            player2.printField();
            for (int i = 0; i < 9; i++) {
                System.out.println();
            }

        } else {
            player1.printField();
            for (int i = 0; i < 9; i++) {
                System.out.println();
            }
        }
    }


    // Вывод координат , которые ранее уже были указаны
    void printOfCoordinates(int count){
        if(count==0){
            for (int i = 0; i < player1.getField_player().length; i++) {
                for (int j = 0; j < player1.getField_player()[0].length; j++) {
                    if(player1.getField_player()[i][j].equals("\uD83D\uDFE8")
                            || player1.getField_player()[i][j].equals("\uD83D\uDFE5")) {
                        System.out.println("y = " + i + "," + "x = " + j);
                    }
                }
            }
        } else {
            for (int i = 0; i < player2.getField_player().length; i++) {
                for (int j = 0; j < player2.getField_player()[0].length; j++) {
                    if(player2.getField_player()[i][j].equals("\uD83D\uDFE8")
                            || player2.getField_player()[i][j].equals("\uD83D\uDFE5")) {
                        System.out.println("y = " + i + "," + "x = " + j);
                    }
                }
            }
        }
    }
}


