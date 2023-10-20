import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Base class: Student
class Student {
    String name;
    List<Float> quizScores = new ArrayList<>(15);
    public Student(String name) {
        this.name = name;
    }
    public void addQuizScore(float score) {
        if(quizScores.size() < 15) {
            quizScores.add(score);
        } else {
            System.out.println("Number of quizzes scores is wrong for this student!");
        }
    }
    public double averageQuizScore() {
        double sum = 0;
        for(float score : quizScores) {
            sum += score;
        }
        return sum / quizScores.size();
    }
}

// Derived class: PartTime
class PartTime extends Student {
    public PartTime(String name) {
        super(name);
    }
}

//Derived class: FullTime
class FullTime extends Student {
    float examScore1;
    float examScore2;
    public FullTime(String name) {

        super(name);
    }
    public void setExamScores(float score1, float score2) {
        examScore1 = score1;
        examScore2 = score2;
    }
}
class Session {
    List<Student> students = new ArrayList<>(20);

    // Add students to the session
    public void addStudent(Student student) {
        if (students.size() < 20) {
            students.add(student);
        } else {
            System.out.println("Number of students is wrong for this session!");
        }
    }

    // Get average scores of each student
    public void printAverageQuizScores() {
        for (Student student : students) {
            System.out.println(student.name + ": " + String.format("%.2f", student.averageQuizScore()));
        }
    }

    // Print the list of quiz scores in ascending order for one session
    public void printSessionQuizScoresInOrder() {
        List<Float> allScores = new ArrayList<>();
        for (Student student : students) {
            allScores.addAll(student.quizScores);
        }
        Collections.sort(allScores);
        System.out.println("Sorted Quiz Scores: " + allScores);
    }

    //Print name of part-time students
    public void printPartTimeStudentNames() {
        for (Student student : students) {
            if (student instanceof PartTime) {
                System.out.println(student.name);
            }
        }
    }

    //Print scores of full-time students
    public void printFullTimeStudentExamScores() {
        for(Student student : students) {
            if(student instanceof FullTime fullTimeStudent) {
                System.out.println(fullTimeStudent.name + ": Exam1: " + String.format("%.2f", fullTimeStudent.examScore1) + ", Exam2: " + String.format("%.2f", fullTimeStudent.examScore2));

            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        //Create an instance of Session
        Session session = new Session();

        //populate it with 20 students and dummy scores
        for(int i = 1; i <= 10; i++) {
         // Let's assume the first 10 students are part-time and rest are full-time
            PartTime ptStudent = new PartTime("PartTimeStudent" + i);
            for(int j = 0; j < 15; j++) {
                ptStudent.addQuizScore((float)(Math.random() * 50 + 50));
            }
            session.addStudent(ptStudent);
        }
        for(int i = 11; i <= 20; i++) {
            FullTime ftStudent = new FullTime("FullTimeStudent" + i);
            for(int j = 0; j < 15; j++) {
                ftStudent.addQuizScore((float)(Math.random() * 40 + 60));
            }
            ftStudent.setExamScores((float)(Math.random() * 40 + 60), (float)(Math.random() * 40 + 60));
            session.addStudent(ftStudent);
        }

        //Call all public methods of Session and capture the output of those methods on console
        System.out.println("Average Quiz Scores:");
        session.printAverageQuizScores();

        System.out.println("\nSession Quiz Scores in Ascending Order:");
        session.printSessionQuizScoresInOrder();

        System.out.println("\nPart-Time Student Names:");
        session.printPartTimeStudentNames();

        System.out.println("\nFull-Time Student Exam Scores:");
        session.printFullTimeStudentExamScores();
    }
}
