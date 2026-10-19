//Tumelo Matlakeng
//31863590
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyHangman
{
    public static int MAX_GUESSES = 7;
	public static MyArrayListHM<String> clueses =new MyArrayListHM<String>();
	public static MyArrayListHM<Character> secretClue =new MyArrayListHM<Character>();
   	public static MyArrayListHM<String> words = new MyArrayListHM<String>();
    public static MyArrayListHM<Character> secretWord = new MyArrayListHM<Character>();
    public static MyArrayListHM<Character> guessedWord = new MyArrayListHM<Character>();
    public static int guessesLeft;

    public static void main(String[] args) {
		
		loadWords("words.txt");
		startNewGame();
		System.out.println("Welcome to Hangman!");
		while(guessesLeft>0)
				updateGuessedWord(playGame());
		if(isWordGuessed())
			System.out.println("Correct guess!!\nCongragulations!! You guessed the word "+guessedWord);
		else
			System.out.println("You Lost :(!!\n The secret word was "+secretWord); 
		
    }

    public static void loadWords(String file)
	{
		
        String filePath = file;
        int counter=0;
		int pos=0;
        String[] list =new String[30];
		String [] clues=new String[30];
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
				for (int i=0;i<line.length();i++)
				{
					if(line.charAt(i)=='-')
						pos=i;
						
						
				}
				list[counter]=line.substring(0,pos-1);
				clues[counter]=line.substring(pos+2,line.length());
				//System.out.println(list[counter]+" "+clues[counter]);
				
				++counter;
				
            }
        } catch (IOException e) {
          
            System.out.println(e);
        }
		
		for(int z=0;z<counter;++z)
		{
			words.add(z,list[z]);
			clueses.add(z,clues[z]);
		}
		
		
		//if(words.sortList())
			//System.out.println("Sorted array");
			//System.out.println(words);
			
		
    }
	public static void startNewGame()
	{
		
		Random rand=new Random();
		int pos =rand.nextInt(19);
		String temp=words.get(pos);
		String tempClue=clueses.get(pos);
		temp=temp.toLowerCase();
		for(int i=0;i<temp.length();++i)
			secretWord.add(i,temp.charAt(i));
		for(int i=0;i<tempClue.length();++i)
			secretClue.add(i,tempClue.charAt(i));
		
		
		
		guessesLeft=7;
		
		
	}
	public static char playGame()
	{
		
		char character=' ';
		char underscore='_';
		Scanner input=new Scanner(System.in);
	
		
		if(guessedWord.length()==0)
			for(int i=0;i<secretWord.length();i++)
				guessedWord.add(i,underscore);
		
		System.out.println("Guessed word : "+guessedWord +" clue "+secretClue);		
		//System.out.println();
			
		System.out.println("Guesses left : "+guessesLeft);
		System.out.println("Enter a letter : "); 
		character=(input.next()).charAt(0);
		return character;
		
		
	}
	public static void updateGuessedWord(char guessedLetter)
	{
		char underscore='_';
			
			if(secretWord.contains(guessedLetter))
			{
				for(int x=0;x<secretWord.length();++x)
				{
					if(secretWord.get(x)==guessedLetter)
					{
						guessedWord.remove(x);
						guessedWord.add(x,guessedLetter);
						
						
					}
				}
				if(!guessedWord.contains(underscore))
					guessesLeft=-1;
				
			}
			else
				{
			        
						System.out.println("Incorret guess!");
						--guessesLeft;
				}
			
		
		
	}
	public static boolean isWordGuessed()
	{
		char underscore='_';
		if(!guessedWord.contains(underscore))
			return true;
		return false;
		
	}
		
				
		

}

	

		

    

    

    
	
