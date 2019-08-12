package projects;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class GradeCalculator {

	private static DecimalFormat df = new DecimalFormat(".##");
	public double category, percentage;
	public String categoryName;
	public static String letter;
	static ArrayList<GradeCalculator> grades;
	String space = "";

	public GradeCalculator(String categoryName, double category, double percentage) {
		this.categoryName = categoryName;
		this.category = category;
		this.percentage = percentage;
	}

	public double getCategory() {
		return category;
	}

	public double getPercentage() {
		return percentage;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public static ArrayList<GradeCalculator> Grades() {
		grades = new ArrayList<GradeCalculator>();
		Scanner scan = new Scanner(System.in);
		double gradeEarned, gradePercent;
		String courseName;

		while (true) {

			System.out.print("Enter the section type: ");
			courseName = scan.next();
			System.out.print("Enter your percent earned in the category: ");
			gradeEarned = scan.nextDouble();
			System.out.print("Enter % weight: ");
			gradePercent = scan.nextDouble();

			if (courseName.equals("stop") || gradeEarned < 0 || gradePercent < 0) {
				break;

			} else if (gradePercent < 0 && gradeEarned > 0 || gradePercent > 0 && gradeEarned < 0) {
				scan.close();
				throw new IllegalArgumentException("Must input a grade percent >= 0");

			}
			GradeCalculator m = new GradeCalculator(courseName, gradeEarned, gradePercent);
			grades.add(m);
		}

		scan.close();
		return grades;

	}

	public static String compute(ArrayList<GradeCalculator> grades) throws IOException {

		double sum = 0;
		int total = 0;

		System.out.println("");
		System.out.println("Category\t% Earned\t% Weight");
		for (GradeCalculator g : grades) {
			System.out.println(g.toString());
			sum += g.getCategory() * (g.getPercentage() * .01);
			total += g.getPercentage();
		}

		Double num = Double.parseDouble(df.format(sum / total * 100));
		if (num >= 93) {
			letter = " A";
		} else if (num >= 90) {
			letter = " A-";
		} else if (num >= 87) {
			letter = " B+";
		} else if (num >= 83) {
			letter = " B";
		} else if (num >= 80) {
			letter = " B-";
		} else if (num >= 77) {
			letter = " C+";
		} else if (num >= 74) {
			letter = " C";
		} else if (num >= 70) {
			letter = " C-";
		} else if (num >= 67) {
			letter = " D+";
		} else if (num >= 64) {
			letter = " D";
		} else if (num >= 60) {
			letter = " D-";
		} else {
			letter = " F";
		}

		if (total > 100) {
			throw new IOException("Grade percentage exceeds over 100");
		} else if (total != 100 && total < 100) {
			return "\nTotal grade in class: " + num + "," + letter + "\nThere is still " + (100 - total)
					+ "% of your grade that is unknown";
		} else {
			Double finalGrade = Double.parseDouble(df.format(sum / 100 * 100));
			return "\nTotal grade in class: " + finalGrade + "," + letter;

		}

	}

	public String toString() {
		return categoryName + "\t" +  category + "%\t " + percentage + "%";
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(compute(Grades()));

	}

}
