import java.util.regex.*;

public class RegularExp {
    public static void main(String[] args) {
        //5 regular expressions patterns and 2 positive and negative cases
        String[][] patternsAndSubjects = {
                //phone number
                {"^(1-)?(\\(\\d{3}\\)|\\d{3}-)\\d{3}-\\d{4}$", "123-456-7890", "1234567890"},
                //zip code
                {"^\\d{5}(-\\d{4})?$", "95136-1234", "950000"},
                //neu email
                {"^[\\w.-]+@northeastern\\.edu$", "di.n@northeastern.edu", "di.n@neu.com"},
                //SSN
                {"^\\d{3}-\\d{2}-\\d{4}$", "123-45-6789", "123456789"},
                //price in US
                {"^\\$\\d+(\\.\\d{2})?$", "$99.99", "￥9.999"}
        };
        int cnt = 0;
        String[] names = {
                "phone number",
                "zip code",
                "neu email",
                "SSN",
                "price in US"
        };

        for (String[] patternAndSubjects : patternsAndSubjects) {
            Pattern pattern = Pattern.compile(patternAndSubjects[0]);
            System.out.println("Regular expression pattern of " + names[cnt] + ": " +patternAndSubjects[0]);
            cnt += 1;

            for (int i = 1; i <= 2; i++) {
                Matcher matcher = pattern.matcher(patternAndSubjects[i]);
                if (matcher.matches()) {
                    System.out.println(" match: " + patternAndSubjects[i]);
                } else {
                    System.out.println(" not match: " + patternAndSubjects[i]);
                }
            }
            System.out.println();
        }
    }
}






