import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		StudentRecord mainRecord = new StudentRecord();
		Scanner scoreCollector = new Scanner(System.in);
		double score = 0;

		System.out.println("Enter the student's score on the first quiz: ");
		score = scoreCollector.nextDouble();
		mainRecord.setQuiz1(score);
		
		System.out.println("Enter the student's score on the second quiz: ");
		score = scoreCollector.nextDouble();
		mainRecord.setQuiz2(score);

		System.out.println("Enter the student's score on the third quiz: ");
		score = scoreCollector.nextDouble();
		mainRecord.setQuiz3(score);

		System.out.println("Enter the student's score on the midterm: ");
		score = scoreCollector.nextDouble();
		mainRecord.setMidterm(score);

		System.out.println("Enter the student's score on the final: ");
		score = scoreCollector.nextDouble();
		mainRecord.setFinal(score);
		
		mainRecord.calcNumGrade();
		mainRecord.calcLetterGrade();

		mainRecord.printInfo();	
	}


}
class StudentRecord{

	private double quiz1 = -10;
	private double quiz2 = -10;	
	private double quiz3 = -10;
	
	private double midterm = -10;
	
	private double finalexam = -10;

	private double numericGrade = -10;
	private char letterGrade = 'F';
	
	/* ^^^^I created private variables to ensure that the data within the object could only be accessed by its builtin methods.
	 * I started students off with default grades of -10 in all numeric categories so that they can't pass verification until they've been specifically mutated, and a letter grade of 'F' to begin with" 
	 * I chose the char datatype just to save a bit of memory. Every byte counts :)
	 * */	
	
	public void StudentRecord(){
		this.quiz1 = quiz1;
		this.quiz2 = quiz2;
		this.quiz3 = quiz3;

		this.midterm = midterm;

		this.finalexam = finalexam;
	}
	public void StudentRecord(double scoreQuiz1, double scoreQuiz2, double scoreQuiz3, double scoreMidterm, double scoreFinalexam){
		this.quiz1 = quiz1;
                this.quiz2 = quiz2;
                this.quiz3 = quiz3;

                this.midterm = midterm;

                this.finalexam = finalexam;

		// Coding this portion of my class really helped me understand how a versatile object should work, and what its potential responsibilities are.
		// If i feed my constructor nothing in its parameter list, it will set them to the lowest available values. However, if we pass in parameter, we'll have the chance to continue on without UI.
		// It also allows for my verification method to have it's own interaction with the user, but keeps the code from being blocky or poorly segmented.
		// In this version of the constructor, I can exemplify my knowledge of method overloading by making something just a bit more versatile.

		this.setQuiz1(scoreQuiz1);
		this.setQuiz2(scoreQuiz2);
		this.setQuiz3(scoreQuiz3);

		this.setMidterm(scoreMidterm);
		this.setFinal(scoreFinalexam);		
	}

	/*^^^Here I first created a constructor that takes no argument, and just instatiated the object with the lowest possible default values.
	 * I later decided not to allow my main method to be the only way to initialize an object from another class, as it wouldn't make too much sense design-wise.
	 * */

	private double verifyExamScore(double score){

		double min = 0.0;
		double max = 100.0;

		if((score < min) || (score > max)){
			Scanner scoreScanner = new Scanner(System.in);
			while(((score < min) || (score > max))){
				System.out.printf("Please enter a score between %f and %f: ",min, max);
				score = scoreScanner.nextDouble();
			}
			return score;
		}
		else{
			return score;
		}

	}
	
	private double verifyQuizScore(double score){
		double min = 0.0;
                double max = 10.0;

                if((score < min) || (score > max)){
                        Scanner scoreScanner = new Scanner(System.in);
                        while(((score < min) || (score > max))){
                                System.out.printf("Please enter a score between %f and %f: ",min, max);
                                score = scoreScanner.nextDouble();
                        }
                        return score;
                }
                else{
                        return score;
                }

	}
	
	//^^^All of the class's input verification methods.
	//I opted for private methods here because while they may interact with the user, they'll still only be used internally.
	//I also opted to leverage the builtin type verification of the Scanner object's nextDouble method to avoid chunky verification methods.
	
	public void setQuiz1(double score){
		this.quiz1 = verifyQuizScore(score);
	}
	public void setQuiz2(double score){
        	this.quiz2 = verifyQuizScore(score);
	}
	public void setQuiz3(double score){
        	this.quiz3 = verifyQuizScore(score);
	}
	public void setMidterm(double score){
		this.midterm = verifyExamScore(score);
	}
	public void setFinal(double score){
		this.finalexam = verifyExamScore(score);
	}
	//^^^All of the class's mutator methods.
	//^^^I made sure that they all come with builtin input verification to avoid a chunky main method.
	
	public double getQuiz1(){
		return this.quiz1;
	}
	public double getQuiz2(){
                return this.quiz2;
        }
	public double getQuiz3(){
                return this.quiz3;
        }

	public double getMidterm(){
		return this.midterm;
	}
	
	public double getFinal(){
		return this.finalexam;
	}

	///^^^All of the class's accessor methods.
	public String toString(){
		String objString = "\nStudent record:\n" + "Quiz 1: " + Double.toString(this.getQuiz1()) + ", Quiz 2: "+ Double.toString(this.getQuiz2()) +", Quiz 3: " + Double.toString(this.getQuiz3()) + ", Midterm: "+ Double.toString(this.getMidterm())+", Final: "+ Double.toString(this.getFinal()) +"\nOverall Numeric Grade: "+ Double.toString(this.calcNumGrade()) + ", Letter Grade: "+this.calcLetterGrade() + "."; 
		return objString;
	}

	public void printInfo(){
		System.out.println(toString());
	}

	///^^^All of the class's display / formatting / conversion methods
	public double calcNumGrade(){
		numericGrade = ( (.40 * this.getFinal()) + (.35 * this.getMidterm()) + (.25 * (10 * ((this.getQuiz1() + this.getQuiz2() + this.getQuiz3()) / 3.0) )) );
		this.numericGrade = numericGrade;
		return this.numericGrade;
	}
	public char calcLetterGrade(){
		if(this.numericGrade >= 90){
			letterGrade = 'A';
					
		}else if(this.numericGrade >= 80){
			letterGrade = 'B';
			
		}else if(this.numericGrade >= 70){
                        letterGrade = 'C';

                }else if(this.numericGrade >= 60){
                        letterGrade = 'D';

                }else{
			letterGrade = 'F';
		}
		this.letterGrade = letterGrade;
		return this.letterGrade;
	}

	//^^All of the class's grading logic

}
