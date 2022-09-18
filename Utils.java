package JavaSeaBattle;



import java.util.Arrays;
import java.util.Objects;

// Вспомогательный класс с проверочными методами
public class Utils {
    // Максимальное и минимальное значение для ввода координат
    private static final int MIN = 0;
    private static final int MAX = 10;

// Координаты всех кораблей
     private static int x1,y1,x2,y2,x3,y3,x4,y4;

// Метод для проверки условий под однопалубный корабль
public static boolean singleShip(String line) {
    try {
String [] line2 = line.split(",");
y1 = Integer.parseInt(line2[0]);
x1 = Integer.parseInt(line2[1]);

if(line2.length != 2) {
    return false;
}
if(x1 > MAX || x1 < MIN) {
    System.err.println("Первая коордианата должна быть больше нуля и меньше десяти");
    return false;
}
if(y1 > MAX || y1 < MIN) {
    System.err.println("Вторая коордианата должна быть больше нуля и меньше десяти");
    return false;
}


    } catch (NumberFormatException e) {
        return false;
    }
    return true;
}



    // Метод для проверки условий под двухпалубный корабль
    public static boolean doubleShip(String line) {

        String[] lineSplitSize = line.split(";");
       if(lineSplitSize.length != 2) {
           return false;
       }
        for (String s : lineSplitSize) {
            String[] lineSplitSize2 = s.split(",");
            for (String value : lineSplitSize2) {
                if (Integer.parseInt(value) <= MIN || Integer.parseInt(value) > MAX) {
                    System.err.println("Координаты должны быть не меньше нуля и не больше десяти!");
                    return false;
                }
            }
        }
        line = line.replace(";",",");
        String []lineSplitSize3 = line.split(",");
        y1 = Integer.parseInt(lineSplitSize3[0]);x1 = Integer.parseInt(lineSplitSize3[1]);
        y2 = Integer.parseInt(lineSplitSize3[2]);x2 = Integer.parseInt(lineSplitSize3[3]);
        // Координаты нужно вводить от меньшего к большему, для правильности работы некоторых методов
        if(y1 > y2 || x1 > x2) {
            System.err.println("Введите коордианты от меньшего к большему ");
            return false;
        }
        return true;
    }

    // Метод для проверки условий под трехпалубный корабль
    public static boolean threeShip(String line) {
        String[] lineSplitSize = line.split(";");
        if(lineSplitSize.length != 3) {
            return false;
        }
        for (String s : lineSplitSize) {
            String[] lineSplitSize2 = s.split(",");
            for (String value : lineSplitSize2) {
                if (Integer.parseInt(value) <= MIN || Integer.parseInt(value) > MAX) {
                    System.err.println("Координаты должны быть не меньше нуля и не больше десяти!");
                    return false;
                }
            }
        }
        line = line.replace(";",",");
        String []lineSplitSize3 = line.split(",");
        // Каждый раз в наши переменные координаты указывается новое значение
        y1 = Integer.parseInt(lineSplitSize3[0]);x1 = Integer.parseInt(lineSplitSize3[1]);
        y2 = Integer.parseInt(lineSplitSize3[2]);x2 = Integer.parseInt(lineSplitSize3[3]);
        y3 = Integer.parseInt(lineSplitSize3[4]);x3 = Integer.parseInt(lineSplitSize3[5]);
        if(y1 > y2 || x1 > x2 || y1 > y3 || x1 > x3) {
            System.err.println("Введите коордианты от меньшего к большему ");
            return false;
        }
        return true;
    }

    // Метод для проверки условий под четырехпалубный корабль
    public static boolean fourShip(String line) {
        String[] lineSplitSize = line.split(";");
        if(lineSplitSize.length != 4) {
            return false;
        }
        for (String s : lineSplitSize) {
            String[] lineSplitSize2 = s.split(",");
            for (String value : lineSplitSize2) {
                if (Integer.parseInt(value) <= MIN || Integer.parseInt(value) > MAX) {
                    System.err.println("Координаты должны быть не меньше нуля и не больше десяти!");
                    return false;
                }
            }
        }
        line = line.replace(";",",");
        String []lineSplitSize3 = line.split(",");

        // Обновляются координаты
        y1 = Integer.parseInt(lineSplitSize3[0]);x1 = Integer.parseInt(lineSplitSize3[1]);
        y2 = Integer.parseInt(lineSplitSize3[2]);x2 = Integer.parseInt(lineSplitSize3[3]);
        y3 = Integer.parseInt(lineSplitSize3[4]);x3 = Integer.parseInt(lineSplitSize3[5]);
        y4 = Integer.parseInt(lineSplitSize3[6]);x4 = Integer.parseInt(lineSplitSize3[7]);
        if(y1 > y2 || x1 > x2 || y1 > y3 || x1 > x3 || y1 > y4 || x1 > x4  || y2 > y3 || x2 > x3|| y2> y4 || x2>x4) {
            System.err.println("Введите коордианты от меньшего к большему ");
            return false;
        }

        return true;
    }

    public static void fillOfField(String [] lineSplitCount, String [][]field_player){
        String line = Arrays.toString(lineSplitCount);
        int count = 0;

        // Преобразование строки в нужный нам вид для split метода
        line =  line.replace("[","");
        line = line.replace("]","");
        line = line.replace(";",",");
        line = line.replace(" ","");
       String [] lineSplit = line.split(",");
        String[] lineSplitSize2 = new String[]{};
        int x = Integer.parseInt(lineSplit[0]);
        int x2 = Integer.parseInt(lineSplit[2]);

        // Проверяется корабль является вертикальным или же горизонатльным
        if(x - x2 == 1 || x2 - x == 1) {
            for (String s : lineSplitCount) {

                // Нанесение корабля и его Ореола( по бокам)  если он является вертикальным
                lineSplitSize2 = s.split(",");
                field_player[Integer.parseInt(lineSplitSize2[0])][Integer.parseInt(lineSplitSize2[1])] = "🛥️";
                field_player[Integer.parseInt(lineSplitSize2[0])][Integer.parseInt(lineSplitSize2[1]) + 1] = "\uD83D\uDFE9";
                field_player[Integer.parseInt(lineSplitSize2[0])][Integer.parseInt(lineSplitSize2[1]) - 1] = "\uD83D\uDFE9";
                count++;
            }
            // Нанесение ореола на одной клетке в конце
            field_player[Integer.parseInt(lineSplitSize2[0]) + 1 ][Integer.parseInt(lineSplitSize2[1])] = "\uD83D\uDFE9";
            // Нанесение Ореола на двух клетках наискось в конце
            field_player[Integer.parseInt(lineSplitSize2[0]) + 1 ][Integer.parseInt(lineSplitSize2[1]) + 1] = "\uD83D\uDFE9";
            field_player[Integer.parseInt(lineSplitSize2[0]) + 1 ][Integer.parseInt(lineSplitSize2[1]) - 1] = "\uD83D\uDFE9";
            // Нанесение Ореола на одной клетке в начале
            field_player[Integer.parseInt(lineSplitSize2[0]) - count][Integer.parseInt(lineSplitSize2[1])] = "\uD83D\uDFE9";
            // Нанесение Ореола на двух клетках наискось в начале
            field_player[Integer.parseInt(lineSplitSize2[0]) - count][Integer.parseInt(lineSplitSize2[1]) - 1] = "\uD83D\uDFE9";
            field_player[Integer.parseInt(lineSplitSize2[0]) - count][Integer.parseInt(lineSplitSize2[1]) + 1] = "\uD83D\uDFE9";
        } else {
            for (String s : lineSplitCount) {

                // Нанесение корабля и его Ореола(сверху и снизу) если он является горизонтальным
                lineSplitSize2 = s.split(",");
                field_player[Integer.parseInt(lineSplitSize2[0])][Integer.parseInt(lineSplitSize2[1])] = "🛥️";
                field_player[Integer.parseInt(lineSplitSize2[0]) + 1][Integer.parseInt(lineSplitSize2[1])] = "\uD83D\uDFE9";
                field_player[Integer.parseInt(lineSplitSize2[0]) - 1][Integer.parseInt(lineSplitSize2[1])] = "\uD83D\uDFE9";
                count++;
            }
            // Нанесение ореола на одной клетке в конце
            field_player[Integer.parseInt(lineSplitSize2[0])][Integer.parseInt(lineSplitSize2[1]) + 1] = "\uD83D\uDFE9";
            // Нанесение Ореола на двух клетках наискось в конце
            field_player[Integer.parseInt(lineSplitSize2[0])-1][Integer.parseInt(lineSplitSize2[1]) + 1] = "\uD83D\uDFE9";
            field_player[Integer.parseInt(lineSplitSize2[0])+1][Integer.parseInt(lineSplitSize2[1]) + 1] = "\uD83D\uDFE9";
            // Нанесение Ореола на одной клетке в начале
            field_player[Integer.parseInt(lineSplitSize2[0])][Integer.parseInt(lineSplitSize2[1]) - count] = "\uD83D\uDFE9";
            // Нанесение Ореола на двух клетках наискось в начале
            field_player[Integer.parseInt(lineSplitSize2[0]) - 1][Integer.parseInt(lineSplitSize2[1]) - count] = "\uD83D\uDFE9";
            field_player[Integer.parseInt(lineSplitSize2[0]) + 1][Integer.parseInt(lineSplitSize2[1]) - count] = "\uD83D\uDFE9";
        }
    }


    // Одна из валидаций корабля
    public static boolean validShip(int size){

    // Проверка для двухпалобного корабля
    if(size == 2) {

        // Проверка на неразрывность корабля(цифры одной из указанных координат должны быть одинаковыми)
           if((y1 != Utils.y2) && (x1 != x2)) {
               System.err.println("Цифры одной из координат должны совпадать!");
               return false;
           }
           if(y1 - y2 == 1 || y2-y1 == 1) {
             return true;
           }
        if(x1 - x2 == 1 || x2-x1 == 1) {
            return true;
        }

        // Проверка для трехпалубного корабля
    } if(size == 3) {

        /*
        Проверка что координаты указанны верно, корабль стоит последовательно не разрываясь на части
        в противном случае возвращается false с указанной причиной
         */
            if (y1 == y2 && y1 == y3) {
                if((x1 - x2 == 1 || x2 - x1 == 1) && (x1-x3 == 2 || x3-x1 == 2)) {
                    return true;
                }
            }
            if(x1 == x2 && x3 == x1) {
                if((y1 - y2 == 1 || y2 - y1 == 1) && (y1-y3 == 2 || y3-y1 == 2)) {
                    return true;
                }
            }
        }

        // Проверка для трехпалубного корабля
    if(size == 4) {
        if (y1 == y2 && y1 == y3) {
            if((x1 - x2 == 1 || x2 - x1 == 1) && (x1-x3 == 2 || x3-x1 == 2) && (x1-x4==3 || x4-x1 == 3)) {
                return true;
            }
        }
        if(x1 == x2 && x3 == x1) {
            if((y1 - y2 == 1 || y2 - y1 == 1) && (y1-y3 == 2 || y3-y1 == 2) && (y1-y4 == 3 || y4-y1 == 3)) {
                return true;
            }
        }
    }

    // Если одна из валидаций не прошла проверку
        System.err.println("Проверьте правильность координат, корабль должен стоять последовательно, не разрываясь");
        return false;
    }

    // Одна из валидаций корабля
    public static boolean validation(String [][]field_player,String line, int size){
        String Error = "На этом месте уже стоит корабль или же вы задели пространство другого корабля!";
            switch (size) {
                // Проверка для однопалубного корабля
                case 1:
                    if (field_player[y1][x1].equals("🛥️") || field_player[y1][x1].equals("\uD83D\uDFE9")) {
                        System.err.println(Error);return false;}
                    return true;
                case 2:
                    // Проверка цепляет ли новый двухпалубный корабль Ореол чужого корабля
                    if(!Objects.equals(field_player[y1][x1], " ")) {System.err.println(Error);return false;}
                    if(!Objects.equals(field_player[y2][Utils.x2], " ")) {System.err.println(Error);return false;}

                    // Если все хорошо возвращается true
                    return true;

                case 3:
                    // Проверка цепляет ли новый трехпалубный корабль Ореол чужого корабля
                    if(!Objects.equals(field_player[y1][x1], " ")) {System.err.println(Error);return false;}
                    if(!Objects.equals(field_player[y2][Utils.x2], " ")) {System.err.println(Error);return false;}
                    if(!Objects.equals(field_player[y3][x3], " ")) {System.err.println(Error);return false;}
                    return true;

                case 4:
                    // Проверка цепляет ли новый четырехпалубный корабль Ореол чужого корабля
                    if(!Objects.equals(field_player[y1][x1], " ")) {System.err.println(Error);return false;}
                    if(!Objects.equals(field_player[y2][Utils.x2], " ")) {System.err.println(Error);return false;}
                    if(!Objects.equals(field_player[y3][x3], " ")) {System.err.println(Error);return false;}
                    if(!Objects.equals(field_player[y4][x4], " ")) {System.err.println(Error);return false;}
                default: return true;
            }
    }
}





