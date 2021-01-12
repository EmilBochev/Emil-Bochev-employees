import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

public class App {
    static ArrayList<Integer> playerPoss=new ArrayList<Integer>();
    static ArrayList<Integer> cpuPoss=new ArrayList<Integer>();
    public static void main(String[] args) {
        char [][] gamePad={{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};

        while(true) {
            showPad(gamePad);

            System.out.println("Enter your placement!");
            Scanner sc = new Scanner(System.in);
            int playerPos = sc.nextInt();
            while (playerPoss.contains(playerPos) || cpuPoss.contains(playerPos))
            {
                System.out.println("The place is taken! Enter other number!");
                playerPos= sc.nextInt();
            }
            placePiece(gamePad, playerPos, "player");
            playerPoss.add(playerPos);
            String result=winner();

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (playerPoss.contains(cpuPos) || cpuPoss.contains(cpuPos))
            {
                cpuPos=random.nextInt(9)+1;
            }
            placePiece(gamePad, cpuPos, "cpu");
            cpuPoss.add(cpuPos);
            result=winner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }
        }

    }

    public static void showPad(char[][] gamepad)
    {
        for (char[] row:gamepad)
        {
            for (char c:row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gamePad,int pos,String user)
    {
        char symbol=' ';
        if(user.equals("player"))
        {
            symbol='X';
            playerPoss.add(pos);
        }
        else {
            symbol = 'O';
            cpuPoss.add(pos);
        }

        switch (pos)
        {
            case 1:gamePad[0][0]=symbol;
                break;
            case 2:gamePad[0][2]=symbol;
                break;
            case 3:gamePad[0][4]=symbol;
                break;
            case 4:gamePad[2][0]=symbol;
                break;
            case 5:gamePad[2][2]=symbol;
                break;
            case 6:gamePad[2][4]=symbol;
                break;
            case 7:gamePad[4][0]=symbol;
                break;
            case 8:gamePad[4][2]=symbol;
                break;
            case 9:gamePad[4][4]=symbol;
                break;
        }
    }

    public static String winner()
    {
        List topRow= Arrays.asList(1,2,3);
        List midRow= Arrays.asList(4,5,6);
        List botRow= Arrays.asList(7,8,9);
        List leftCol= Arrays.asList(1,4,7);
        List midCol= Arrays.asList(2,5,8);
        List rightCol= Arrays.asList(3,6,9);
        List cross1= Arrays.asList(1,5,9);
        List cross2= Arrays.asList(3,5,7);

        List<List> winning=new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l:winning) {
            if(playerPoss.containsAll(l))
            {
                return "Congratulations!!! You Won!!!";
            }
            else if (cpuPoss.containsAll(l))
            {
                return "Sorry! Cpu Wins !";
            }
            else if (playerPoss.size()+cpuPoss.size()==9)
            {
                return "TIЕ!!!";
            }
        }
        return "";
    }
}
