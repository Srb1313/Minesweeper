import java.util.Scanner;

public class SweeperMain
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		GameManager gameManager=new GameManager();
		gameManager.InitializeGame(scanner);
		String end="n";
		
		do
		{
			gameManager.showCompleteGrid();
			gameManager.playerTurn(scanner);
			System.out.println("Please enter Y to end game");
			end=scanner.next();
		} while (!end.toLowerCase().equals("y"));
		
		scanner.close();		
	}
}
