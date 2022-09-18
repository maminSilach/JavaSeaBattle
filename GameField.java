package JavaSeaBattle;


import java.util.Scanner;

public class GameField {
    private final int FIELD_LENGTH = 12;
    private final int FIELD_WIDTH = 12;

    // Имя игрока
    private final String playerName;

    // Указывается сколько кораблей уничтожено/повреждено, когда счетик доходит до 20 - игра заканчивается
    int shipCount = 0;

    // Наше игровое поле
    private String [][]field_player = new String[FIELD_LENGTH][FIELD_WIDTH];

    private final Scanner scanner = new Scanner(System.in);


    // Конструктор класса
    public GameField(String playerName) {
        this.playerName = playerName;
        fieldOfMas(field_player);
    }



    // Заполнение нашего поля пустым символом, чтобы избежать NullPointerException
    public static void fieldOfMas(String [][] mas) {
        for (int i = 0; i < mas.length; i++) {
            for (int j = 0; j < mas[0].length; j++) {
                mas[i][j] = " ";
            }
        }
    }


// Вывод поля в консоль
    public  void printField(){
        for (int i = 1; i < field_player.length-1; i++) {
            System.out.println();
            System.out.print(i + " ");
            for (int j = 1; j < field_player[0].length-1; j++) {
                System.out.print(field_player[i][j].equals(" ") ? "\uD83D\uDFE6" : field_player[i][j]);
            }
        }
    }


// Запрос координат у пользователя с консоли,
// при неправильном вводе будет действовать бесконечный цикл до тех пор, пока координаты не будут удовлетворять условию
    public void rasstanovka(){
        System.out.println("Начинаем расставлять корабли на поле " + this.getPlayerName());
        System.out.println("Введите коордианты первого однопалубного корабля(формат: y,x)");
        String line = scanner.nextLine().trim();
       while (!placementOfShip(line,1)) {
           System.err.println("Введите координаты как указано в формате");
           line = scanner.nextLine().trim();
       }
        System.out.println("Введите коордианты второго однопалубного корабля(формат: y,x)");
       line = scanner.nextLine().trim();
        while (!placementOfShip(line,1)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты третьего однопалубного корабля(формат: y,x)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,1)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты четвертого однопалубного корабля(формат: y,x)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,1)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты первого двухпалобного корабля(формат: y1,x1;y2,x2)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,2)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты второго двухпалобного корабля(формат: y1,x1;y2,x2)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,2)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты третьего двухпалобного корабля(формат: y1,x1;y2,x2)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,2)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты первого трехпалубного корабля(формат: y1,x1;y2,x2;y3,x3)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,3)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты второго трехпалубного корабля(формат: y1,x1;y2,x2;y3,x3)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,3)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
        System.out.println("Введите коордианты четырехпалубного корабля(формат: y1,x1;y2,x2;y3,x3;y4,x4)");
        line = scanner.nextLine().trim();
        while (!placementOfShip(line,4)) {
            System.err.println("Введите координаты как указано в формате");
            line = scanner.nextLine().trim();
        }
     }


// Проверка координат на верность их заполнения,
// если все было введено верно, будет вызван метод для занесения кораблей на поле
    public boolean placementOfShip(String line,int size) {
    if(line.length() == 0) {
        return false;
    }
         try {
             String [] lineSplitCount = line.split(";");
             switch (size) {
                 case 1:
                     // Для однопалубного корабля заполнение происходит здесь
                     if(Utils.singleShip(line) && Utils.validation(field_player,line,1)) {
                         String[] lineSplitSize = line.split(",");
                         /*
                         Наносится корабль на место указанной координаты и везде на +1 координату поочередно закрашивается
                         поле в зеленный цвет, указывая на его Ореол
                          */
                         field_player[Integer.parseInt(lineSplitSize[0])][Integer.parseInt(lineSplitSize[1])] = "🛥️";
                         field_player[Integer.parseInt(lineSplitSize[0])][Integer.parseInt(lineSplitSize[1]) + 1] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0]) + 1][Integer.parseInt(lineSplitSize[1])] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0])][Integer.parseInt(lineSplitSize[1]) - 1] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0]) - 1][Integer.parseInt(lineSplitSize[1])] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0]) + 1][Integer.parseInt(lineSplitSize[1]) + 1] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0]) + 1][Integer.parseInt(lineSplitSize[1]) - 1] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0]) - 1][Integer.parseInt(lineSplitSize[1]) + 1] = "\uD83D\uDFE9";
                         field_player[Integer.parseInt(lineSplitSize[0]) - 1][Integer.parseInt(lineSplitSize[1]) - 1] = "\uD83D\uDFE9";
                         break;
                     } else {
                         return false;
                     }
                 case 2:
                     // Проверка двухпалобного корабля
                     if(Utils.doubleShip(line) && Utils.validShip(2) && Utils.validation(field_player,line,2)) {
                         // Вызов нанесения двухпалобного корабля на поле
                         Utils.fillOfField(lineSplitCount,field_player);
                         break;
                     }
                     else {
                         return false;
                     }
                 case 3:
                     // Проверка трехпалубного корабля
                     if(Utils.threeShip(line) && Utils.validShip(3) && Utils.validation(field_player,line,3)) {
                         // Вызов нанесения трехпалубного корабля на поле
                         Utils.fillOfField(lineSplitCount,field_player);
                         break;
                     } else {
                         return false;
                     }
                 case 4:
                     // Проверка четырехпалубного корабля
                     if(Utils.fourShip(line) && Utils.validShip(4) && Utils.validation(field_player,line,4)) {
                         // Вызов нанесения четырехпалубного корабля на поле
                         Utils.fillOfField(lineSplitCount,field_player);
                         break;
                     }
                     else {
                         return false;
                     }
                 default: return false;
             }
         } catch (Exception e) {
             /* Если возникает ошибка при split методе, то есть введенные данные не соответствуют формату,
                возвращается false для проверки валидности данных с консоли
             */

             return false;
         }
         return true;
    }

// Геттер имени
    public String getPlayerName() {
        return playerName;
    }


    // Геттер игрового поля
    public String[][] getField_player() {
        return field_player;
    }
}




