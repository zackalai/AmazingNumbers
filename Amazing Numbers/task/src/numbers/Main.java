package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

enum NumberProperties {
    EVEN,
    ODD,
    BUZZ,
    DUCK,
    PALINDROMIC,
    GAPFUL,
    SPY,
    SQUARE,
    SUNNY,
    JUMPING,
    HAPPY,
    SAD,
}

public class Main {
    static final int TWO = 2;
    static final int SEVEN = 7;
    static final int TEN = 10;

    public static void main(String[] args) {

        String availProperties = "EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD";

        String welcomeMessage = "Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n";

        System.out.println(welcomeMessage);

        System.out.print("Enter a request: ");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        long number;
        System.out.println();


        while (true) {
//        NumberFormat numfor = NumberFormat.getInstance(new Locale("th", "TH"));
//        System.out.println(numfor.format(number));
            while (input[0] == "") {
                System.out.println(welcomeMessage);

                System.out.print("Enter a request: ");
                input = scanner.nextLine().split(" ");
                System.out.println();

            }
            try {
                number = Long.valueOf(input[0]);

                if (number == 0) {
                    break;
                }
                if (input.length == 1) {
                    StringBuilder numberFormat = new StringBuilder(Long.toString(number));
                    for (int i = numberFormat.length() - 3; i > 0; i -= 3) {
                        numberFormat.insert(i, ',');
                    }
                    if (number > 0) {
                        System.out.println("Properties of " + numberFormat);
                        System.out.println("        buzz: " + isBuzzNumber(number));
                        System.out.println("        duck: " + isDuckNumber(number));
                        System.out.println(" palindromic: " + isPalindrom(number));
                        System.out.println("      gapful: " + isGapful(number));
                        System.out.println("         spy: " + isSpyNum(number));
                        System.out.println("      square: " + isPerfectSquare(number));
                        System.out.println("       sunny: " + isSunnyNumber(number));
                        System.out.println("     jumping: " + isJumpingNumber(number));
                        System.out.println("        even: " + isEven(number));
                        System.out.println("         odd: " + !isEven(number));
                        System.out.println("       happy: " + isHappyNumber(number));
                        System.out.println("         sad: " + !isHappyNumber(number));

                    } else {
                        System.out.println("The first parameter should be a natural number or zero");
                    }

                } else if (input.length == 2) {
                    long firstInput = Long.valueOf(input[0]);
                    long secondInput = Long.valueOf(input[1]);
                    if (firstInput < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                    else if (secondInput <= 0) {
                        System.out.println("The second parameter should be a natural number");

                    } else {
                        for (int i = 0; i < secondInput; i++) {
                            System.out.printf("             %2d is %s\n", firstInput + i, checkProperties(firstInput + i));
                        }
                    }


                } else if (input.length == 0) {
                    System.out.println(welcomeMessage);

                } else if (input.length == 3) {
                    String property = input[2];
                    if (property.charAt(0) == '-') {
                        property = property.substring(1);
                    }
                    if (isPropertiesExist(property)) {
                        long i = Long.valueOf(input[0]);
                        int counter = 0;
                        while (true) {
                            if (input[2].charAt(0) == '-') {
                                if (!isNumberHaveProperty(i, property)) {
                                    StringBuilder numberFormat = new StringBuilder(Long.toString(i));
                                    for (int j = numberFormat.length() - 3; j > 0; j -= 3) {
                                        numberFormat.insert(j, ',');
                                    }
                                    System.out.printf("             %5s is %s\n", numberFormat, checkProperties(i));
                                    counter++;
                                }

                            } else {
                                if (isNumberHaveProperty(i, property)) {
                                    StringBuilder numberFormat = new StringBuilder(Long.toString(i));
                                    for (int j = numberFormat.length() - 3; j > 0; j -= 3) {
                                        numberFormat.insert(j, ',');
                                    }
                                    System.out.printf("             %5s is %s\n", numberFormat, checkProperties(i));
                                    counter++;
                                }

                            }
//                            if (isNumberHaveProperty(i, property)) {
//                                StringBuilder numberFormat = new StringBuilder(Long.toString(i));
//                                for (int j = numberFormat.length() - 3; j > 0; j -= 3) {
//                                    numberFormat.insert(j, ',');
//                                }
//                                System.out.printf("             %5s is %s\n", numberFormat, checkProperties(i));
//                                counter++;
//                            }
                            if (counter == Long.valueOf(input[1])) {
                                break;
                            }
                            i++;
                        }
                    } else {
                        System.out.printf("The property [%s] is wrong.\n", property.toUpperCase());
                        System.out.printf("Available properties: [%s]\n", availProperties);
                    }
//                } else if (input.length == 4) {
//                    if (!isPropertiesExist(input[2]) || !isPropertiesExist(input[3])) {
//                        List<String> nonExistProperties = new ArrayList<>();
//
//                        if (!isPropertiesExist(input[2])) {
//                            nonExistProperties.add(input[2].toUpperCase());
//                        }
//                        if (!isPropertiesExist(input[3])) {
//                            nonExistProperties.add(input[3].toUpperCase());
//                        }
//                        String nonExistPropertiesString = String.join(", ", nonExistProperties);
//                        if (nonExistProperties.size() >= 2) {
//                            System.out.printf("The properties [%s] are wrong.\n", nonExistPropertiesString);
//                        } else {
//                            System.out.printf("The property [%s] is wrong.\n", nonExistPropertiesString);
//                        }
//                        System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]");
//                    } else if (isMutualExclusiveProperties(input[2], input[3])) {
//
//                        System.out.printf("The request contains mutually exclusive properties: [%s]\n", typeOfMutualExclusiveProperties(input[2], input[3]));
//                        System.out.println("There are no numbers with these properties.");
//                    } else {
//                        long i = Long.valueOf(input[0]);
//                        int counter = 0;
//                        while (true) {
//                            if (isNumberHaveProperty(i, input[2]) && isNumberHaveProperty(i, input[3])) {
//                                StringBuilder numberFormat = new StringBuilder(Long.toString(i));
//                                for (int j = numberFormat.length() - 3; j > 0; j -= 3) {
//                                    numberFormat.insert(j, ',');
//                                }
//                                System.out.printf("             %5s is %s\n", numberFormat, checkProperties(i));
//                                counter++;
//                            }
//                            if (counter == Long.valueOf(input[1])) {
//                                break;
//                            }
//                            i++;
//                        }
//
//                    }
                } else if (input.length >= 4) {
                    boolean haveNonExistProperties = false;
                    List<String> nonExistProperties = new ArrayList<>();
                    String property;

                    for (int i = 2; i < input.length; i++) {
                        property = input[i];
                        if (property.charAt(0) == '-') {
                            property = property.substring(1);
                        }

                        if (!isPropertiesExist(property)) {
                            nonExistProperties.add(input[i].toUpperCase());
                            haveNonExistProperties = true;
                        }
                    } // add

                    if (haveNonExistProperties) {
                        String nonExistPropertiesString = String.join(", ", nonExistProperties);
                        if (nonExistProperties.size() >= 2) {
                            System.out.printf("The properties [%s] are wrong.\n", nonExistPropertiesString);
                        } else {
                            System.out.printf("The property [%s] is wrong.\n", nonExistPropertiesString);
                        }
                        System.out.printf("Available properties: [%s]\n", availProperties);

                    } else if (isMutualExclusiveProperties(input)) {
                        System.out.printf("The request contains mutually exclusive properties: %s\n", typeOfMutualExclusiveProperties(input));
                        System.out.println("There are no numbers with these properties.");
                    } else {
                        // Copy Above old code
                        long in = Long.valueOf(input[0]);
                        int counter = 0;
                        boolean haveAllProperties = true;

                        while  (true) {
                            for (int index = 2; index < input.length; index++) {
                                property = input[index];
                                if (property.charAt(0) == '-') {
                                    property = property.substring(1);
                                }
                                if (input[index].charAt(0) == '-') {
                                    if (!isNumberHaveProperty(in, property)) {
                                        haveAllProperties = true;
                                    } else {
                                        haveAllProperties = false;
                                        break;
                                    }

                                } else {
                                    if (isNumberHaveProperty(in, input[index])) {
                                        haveAllProperties = true;
                                    } else {
                                        haveAllProperties = false;
                                        break;
                                    }

                                }
                            }
                            if (haveAllProperties) {
                                StringBuilder numberFormat = new StringBuilder(Long.toString(in));
                                for (int j = numberFormat.length() - 3; j > 0; j -= 3) {
                                    numberFormat.insert(j, ',');
                                }
                                System.out.printf("             %5s is %s\n", numberFormat, checkProperties(in));
                                counter++;

                            }
                            if (counter == Long.valueOf(input[1])) {
                                break;
                            }
                            in++;

                        }
//                            while (true) {
//                                if (isNumberHaveProperty(in, input[2]) && isNumberHaveProperty(in, input[3])) {
//                                    StringBuilder numberFormat = new StringBuilder(Long.toString(in));
//                                    for (int j = numberFormat.length() - 3; j > 0; j -= 3) {
//                                        numberFormat.insert(j, ',');
//                                    }
//                                    System.out.printf("             %5s is %s\n", numberFormat, checkProperties(in));
//                                    counter++;
//                                }
//                                if (counter == Long.valueOf(input[1])) {
//                                    break;
//                                }
//                                in++;
//                            }

                    }
                }
            }
            catch(Exception e) {
                System.out.println("The first parameter should be a natural number or zero.");
            }

            System.out.println();
            System.out.print("Enter a request: ");
            input = scanner.nextLine().split(" ");
            System.out.println();


        }

        System.out.println("Goodbye!");

    }

    public static boolean isMutualExclusiveProperties(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].toUpperCase();
        }
        boolean evenAndOdd = Arrays.stream(input).anyMatch("EVEN"::equals) && Arrays.stream(input).anyMatch("ODD"::equals);
        boolean duckAndSpy = Arrays.stream(input).anyMatch("DUCK"::equals) && Arrays.stream(input).anyMatch("SPY"::equals);
        boolean sunnyAndSquare = Arrays.stream(input).anyMatch("SUNNY"::equals) && Arrays.stream(input).anyMatch("SQUARE"::equals);
        boolean happyAndSad = Arrays.stream(input).anyMatch("HAPPY"::equals) && Arrays.stream(input).anyMatch("SAD"::equals);

        boolean evenAndOddInvert = Arrays.stream(input).anyMatch("-EVEN"::equals) && Arrays.stream(input).anyMatch("-ODD"::equals);
        boolean duckAndSpyInvert = Arrays.stream(input).anyMatch("-DUCK"::equals) && Arrays.stream(input).anyMatch("-SPY"::equals);
//        boolean sunnyAndSquareInvert = Arrays.stream(input).anyMatch("-SUNNY"::equals) && Arrays.stream(input).anyMatch("-SQUARE"::equals);
        boolean happyAndSadInvert = Arrays.stream(input).anyMatch("-HAPPY"::equals) && Arrays.stream(input).anyMatch("-SAD"::equals);

        boolean evenInvert = Arrays.stream(input).anyMatch("EVEN"::equals) && Arrays.stream(input).anyMatch("-EVEN"::equals);
        boolean oddInvert = Arrays.stream(input).anyMatch("ODD"::equals) && Arrays.stream(input).anyMatch("-ODD"::equals);

        boolean buzzInvert = Arrays.stream(input).anyMatch("BUZZ"::equals) && Arrays.stream(input).anyMatch("-BUZZ"::equals);
        boolean palindromicInvert = Arrays.stream(input).anyMatch("PALINDROMIC"::equals) && Arrays.stream(input).anyMatch("-PALINDROMIC"::equals);
        boolean gapfulInvert = Arrays.stream(input).anyMatch("GAPFUL"::equals) && Arrays.stream(input).anyMatch("-GAPFUL"::equals);
        boolean jumpingInvert = Arrays.stream(input).anyMatch("JUMPING"::equals) && Arrays.stream(input).anyMatch("-JUMPING"::equals);

        boolean duckInvert = Arrays.stream(input).anyMatch("DUCK"::equals) && Arrays.stream(input).anyMatch("-DUCK"::equals);
        boolean spyInvert = Arrays.stream(input).anyMatch("SPY"::equals) && Arrays.stream(input).anyMatch("-SPY"::equals);

        boolean sunnyInvert = Arrays.stream(input).anyMatch("SUNNY"::equals) && Arrays.stream(input).anyMatch("-SUNNY"::equals);
        boolean squareInvert = Arrays.stream(input).anyMatch("SQUARE"::equals) && Arrays.stream(input).anyMatch("-SQUARE"::equals);

        boolean happyInvert = Arrays.stream(input).anyMatch("HAPPY"::equals) && Arrays.stream(input).anyMatch("-HAPPY"::equals);
        boolean sadInvert = Arrays.stream(input).anyMatch("SAD"::equals) && Arrays.stream(input).anyMatch("-SAD"::equals);

        return evenAndOdd || duckAndSpy || sunnyAndSquare || happyAndSad || evenInvert || oddInvert || duckInvert || spyInvert
                || sunnyInvert || squareInvert || happyInvert || sadInvert
                || evenAndOddInvert || duckAndSpyInvert || happyAndSadInvert
                    || buzzInvert || palindromicInvert || gapfulInvert || jumpingInvert;

    }

    public static boolean isMutualExclusiveProperties(String firstProperty, String secondProperty) {
        firstProperty = firstProperty.toUpperCase();
        secondProperty = secondProperty.toUpperCase();

        boolean evenAndOdd = "EVEN".equals(firstProperty) && "ODD".equals(secondProperty) || "ODD".equals(firstProperty) && "EVEN".equals(secondProperty);

        // Duck and Spy
        boolean duckAndSpy = "DUCK".equals(firstProperty) && "SPY".equals(secondProperty) || "SPY".equals(firstProperty) && "DUCK".equals(secondProperty);

        // Sunny and Square
        boolean sunnyAndSquare = "SUNNY".equals(firstProperty) && "SQUARE".equals(secondProperty) || "SQUARE".equals(firstProperty) && "SUNNY".equals(secondProperty);

        return evenAndOdd || duckAndSpy || sunnyAndSquare;

    }

    public static String typeOfMutualExclusiveProperties(String[] input) {
        List<String> type = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].toUpperCase();
        }
        boolean evenAndOdd = Arrays.stream(input).anyMatch("EVEN"::equals) && Arrays.stream(input).anyMatch("ODD"::equals);
        boolean duckAndSpy = Arrays.stream(input).anyMatch("DUCK"::equals) && Arrays.stream(input).anyMatch("SPY"::equals);
        boolean sunnyAndSquare = Arrays.stream(input).anyMatch("SUNNY"::equals) && Arrays.stream(input).anyMatch("SQUARE"::equals);
        boolean happyAndSad = Arrays.stream(input).anyMatch("HAPPY"::equals) && Arrays.stream(input).anyMatch("SAD"::equals);


        boolean evenAndOddInvert = Arrays.stream(input).anyMatch("-EVEN"::equals) && Arrays.stream(input).anyMatch("-ODD"::equals);
        boolean duckAndSpyInvert = Arrays.stream(input).anyMatch("-DUCK"::equals) && Arrays.stream(input).anyMatch("-SPY"::equals);
//        boolean sunnyAndSquareInvert = Arrays.stream(input).anyMatch("-SUNNY"::equals) && Arrays.stream(input).anyMatch("-SQUARE"::equals);
        boolean happyAndSadInvert = Arrays.stream(input).anyMatch("-HAPPY"::equals) && Arrays.stream(input).anyMatch("-SAD"::equals);

        boolean evenInvert = Arrays.stream(input).anyMatch("EVEN"::equals) && Arrays.stream(input).anyMatch("-EVEN"::equals);
        boolean oddInvert = Arrays.stream(input).anyMatch("ODD"::equals) && Arrays.stream(input).anyMatch("-ODD"::equals);

        boolean buzzInvert = Arrays.stream(input).anyMatch("BUZZ"::equals) && Arrays.stream(input).anyMatch("-BUZZ"::equals);
        boolean palindromicInvert = Arrays.stream(input).anyMatch("PALINDROMIC"::equals) && Arrays.stream(input).anyMatch("-PALINDROMIC"::equals);
        boolean gapfulInvert = Arrays.stream(input).anyMatch("GAPFUL"::equals) && Arrays.stream(input).anyMatch("-GAPFUL"::equals);
        boolean jumpingInvert = Arrays.stream(input).anyMatch("JUMPING"::equals) && Arrays.stream(input).anyMatch("-JUMPING"::equals);

        boolean duckInvert = Arrays.stream(input).anyMatch("DUCK"::equals) && Arrays.stream(input).anyMatch("-DUCK"::equals);
        boolean spyInvert = Arrays.stream(input).anyMatch("SPY"::equals) && Arrays.stream(input).anyMatch("-SPY"::equals);

        boolean sunnyInvert = Arrays.stream(input).anyMatch("SUNNY"::equals) && Arrays.stream(input).anyMatch("-SUNNY"::equals);
        boolean squareInvert = Arrays.stream(input).anyMatch("SQUARE"::equals) && Arrays.stream(input).anyMatch("-SQUARE"::equals);

        boolean happyInvert = Arrays.stream(input).anyMatch("HAPPY"::equals) && Arrays.stream(input).anyMatch("-HAPPY"::equals);
        boolean sadInvert = Arrays.stream(input).anyMatch("SAD"::equals) && Arrays.stream(input).anyMatch("-SAD"::equals);

        if (evenAndOdd) {
            type.add("EVEN, ODD");
        }
        if (evenAndOddInvert) {
            type.add("-EVEN, -ODD");
        }
        if (evenInvert) {
            type.add("EVEN, -EVEN");
        }
        if (oddInvert) {
            type.add("ODD, -ODD");
        }
        if (buzzInvert) {
            type.add("BUZZ, -BUZZ");
        }
        if (palindromicInvert) {
            type.add("PALINDROMIC, -PALINDROMIC");
        }
        if (gapfulInvert) {
            type.add("GAPFUL, -GAPFUL");
        }
        if (jumpingInvert) {
            type.add("JUMPING, -JUMPING");
        }
//        if (oddInvert) {
//            type.add("ODD, -ODD");
//        }
        if (duckAndSpy) {
            type.add("DUCK, SPY");
        }
        if (duckAndSpyInvert) {
            type.add("-DUCK, -SPY");
        }
        if (duckInvert) {
            type.add("DUCK, -DUCK");
        }
        if (spyInvert) {
            type.add("SPY, -SPY");
        }
        if (sunnyAndSquare) {
            type.add("SUNNY, SQUARE");
        }
//        if (sunnyAndSquareInvert) {
//            type.add("-SUNNY, -SQUARE");
//        }
        if (sunnyInvert) {
            type.add("SUNNY, -SUNNY");
        }
        if (squareInvert) {
            type.add("SQUARE, -SQUARE");
        }
        if (happyAndSad) {
            type.add("HAPPY, SAD");
        }
        if (happyAndSadInvert) {
            type.add("-HAPPY, -SAD");
        }
        if (happyInvert) {
            type.add("HAPPY, -HAPPY");
        }
        if (sadInvert) {
            type.add("SAD, -SAD");
        }
        return type.toString();
    }

    public static String typeOfMutualExclusiveProperties(String firstProperty, String secondProperty) {
        firstProperty = firstProperty.toUpperCase();
        secondProperty = secondProperty.toUpperCase();

        // Even and Odd
        boolean evenAndOdd = "EVEN".equals(firstProperty) && "ODD".equals(secondProperty) || "ODD".equals(firstProperty) && "EVEN".equals(secondProperty);

        // Duck and Spy
        boolean duckAndSpy = "DUCK".equals(firstProperty) && "SPY".equals(secondProperty) || "SPY".equals(firstProperty) && "DUCK".equals(secondProperty);

        // Sunny and Square
        boolean sunnyAndSquare = "SUNNY".equals(firstProperty) && "SQUARE".equals(secondProperty) || "SQUARE".equals(firstProperty) && "SUNNY".equals(secondProperty);

        boolean happyAndSad = "HAPPY".equals(firstProperty) && "SAD".equals(secondProperty) || "SAD".equals(firstProperty) && "HAPPY".equals(secondProperty);

        if (evenAndOdd) {
            return "EVEN, ODD";
        } else if (duckAndSpy) {
            return "DUCK, SPY";
        } else if (sunnyAndSquare) {
            return "SUNNY, SQUARE";
        } else if (happyAndSad) {
            return "HAPPY, SAD";
        }

        return "DON't Have";
    }

    public static boolean isNumberHaveProperty(long num, String property) {
        NumberProperties propertyEnum = NumberProperties.valueOf(property.toUpperCase());
        switch (propertyEnum) {
            case BUZZ:
                if (isBuzzNumber(num)) {
                    return true;
                }
                break;
            case DUCK:
                if (isDuckNumber(num)) {
                    return true;
                }
                break;
            case PALINDROMIC:
                if (isPalindrom(num)) {
                    return true;
                }
                break;
            case GAPFUL:
                if (isGapful(num)) {
                    return true;
                }
                break;
            case SPY:
                if (isSpyNum(num)) {
                    return true;
                }
                break;
            case SQUARE:
                if (isPerfectSquare(num)) {
                    return true;
                }
                break;
            case SUNNY:
                if (isSunnyNumber(num)) {
                    return true;
                }
                break;
            case JUMPING:
                if (isJumpingNumber(num)) {
                    return true;
                }
                break;
            case EVEN:
                if (isEven(num)) {
                    return true;
                }
                break;
            case ODD:
                if (!isEven(num)) {
                    return true;
                }
                break;
            case HAPPY:
                if (isHappyNumber(num)) {
                    return true;
                }
                break;
            case SAD:
                if (!isHappyNumber(num)) {
                    return true;
                }
                break;
        }

        return false;
    }

    public static boolean isPropertiesExist(String property) {
        for (NumberProperties c : NumberProperties.values()) {
            if (c.name().equals(property.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    public static String checkProperties(long number) {
        List<String> allTrueProperties = new ArrayList<>();


        if (isEven(number)) {
            allTrueProperties.add("even");
        }
        if (!isEven(number)) {
            allTrueProperties.add("odd");
        }
        if (isBuzzNumber(number)) {
            allTrueProperties.add("buzz");
        }
        if (isDuckNumber(number)) {
            allTrueProperties.add("duck");
        }
        if (isPalindrom(number)) {
            allTrueProperties.add("palindromic");
        }
        if (isGapful(number)) {
            allTrueProperties.add("gapful");
        }
        if (isSpyNum(number)) {
            allTrueProperties.add("spy");
        }
        if (isPerfectSquare(number)) {
            allTrueProperties.add("square");
        }
        if (isSunnyNumber(number)) {
            allTrueProperties.add("sunny");
        }
        if (isJumpingNumber(number)) {
            allTrueProperties.add("jumping");
        }
        if (isHappyNumber(number)) {
            allTrueProperties.add("happy");
        }
        if (!isHappyNumber(number)) {
            allTrueProperties.add("sad");
        }

        String properties = String.join(", ", allTrueProperties);

        return properties;
    }

    public static boolean isHappyNumber(long num) {
        String numString = String.valueOf(num);
        int length = numString.length();
        long temp = 0;
        long total = 0;
        String[] strTemp;
        List<Long> allTotal = new ArrayList<>();


        if (length == 1) {
            temp = (long) Math.pow(num, 2);
            while (true) {
                strTemp = String.valueOf(temp).split("");

                for (String str : strTemp) {
                    total += Math.pow(Long.valueOf(str), 2);
                }

                if (total == 1) {
                    return true;
                } else if (total == num) {
                    return false;
                } else if (allTotal.contains(total)) {
                    return false;
                }

                allTotal.add(total);

                temp = total;
                total = 0;
            }

        } else {
//            strTemp = String.valueOf(num).split("");
            temp = num;

            while (true) {
                strTemp = String.valueOf(temp).split("");

                for (String str : strTemp) {
                    total += Math.pow(Long.valueOf(str), 2);
                }

                if (total == 1) {
                    return true;
                } else if (total == num) {
                    return false;
                } else if (allTotal.contains(total)) {
                    return false;
                }

                allTotal.add(total);
                temp = total;
                total = 0;
            }

        }
//        temp = (long) Math.pow(num, 2);
//        for (String str : strTemp) {
//            total += Math.pow(Long.valueOf(str), 2);
//        }
//
//        while (true) {
//            strTemp = String.valueOf(temp).split("");
//
//            for (String str : strTemp) {
//                total += Math.pow(Long.valueOf(str), 2);
//            }
//
//            if (total == 1) {
//                return true;
//            } else if (total == num) {
//                return false;
//            }
//            temp = total;
//            total = 0;
//        }

    }

    public static boolean isJumpingNumber(long num) {
        boolean differByOne = true;
        String numString = String.valueOf(num);


        if (numString.length() == 1) {
            return true;
        } else {
            for (int i = 0; i < numString.length() - 1; i++) {
                if (Math.abs(numString.charAt(i) - numString.charAt(i + 1)) == 1) {
                    differByOne &= true;
                } else {
                    return false;
                }
            }
        }
        return differByOne;

    }

    public static boolean isPerfectSquare(long num) {
        double sqrt = Math.sqrt(num);

        return (sqrt - Math.floor(sqrt) == 0);
    }

    public static boolean isSunnyNumber(long num) {
        return isPerfectSquare(num + 1);
    }

    public static boolean isSpyNum(long num) {
        String numString = String.valueOf(num);
        long sum = 0;
        long multiply = 1;

        if (numString.length() == 1) {
            return true;
        } else {
            for (int i = 0; i < numString.length(); i++) {
                sum += Character.getNumericValue(numString.charAt(i));
                multiply *= Character.getNumericValue(numString.charAt(i));
            }

            if (sum == multiply) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isGapful(long num) {
        String numString = String.valueOf(num);
        if (numString.length() < 3) {
            return false;
        } else {
            String firstLast = "" + numString.charAt(0) + numString.charAt(numString.length() - 1);
            long firstLastLong = Long.valueOf(firstLast);

            if (num % firstLastLong == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isPalindrom(long num) {
        String numString = Long.toString(num);
        StringBuilder reversedNum = new StringBuilder(numString);
        if (reversedNum.reverse().toString().equals(numString)) {
            return true;
        }
        return false;
    }

    public static boolean isEven(long num) {
        return (num % TWO == 0) ? true : false;
    }

    public static boolean isBuzzNumber(long num) {
        if (num > 0) {
            boolean isDivisibleBy7 = num % SEVEN == 0;
            boolean isEndsWith7 = num % TEN == SEVEN;

            if (isDivisibleBy7 || isEndsWith7) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("number less than zero");
            return false;
        }
    }

    public static boolean isDuckNumber(long num) {
        while (num > 0) {
            if (num % TEN == 0) {
                return true;
            }
            num = num / TEN;
        }
        return false;
    }

//    public static String explanation(long num) {
//        if (num % TEN == SEVEN && num % SEVEN == 0) {
//            return " is divisible by 7 and ends with 7.";
//        } else if (num % TEN == SEVEN) {
//            return " ends with 7.";
//        } else if (num % SEVEN == 0) {
//            return " is divisible by 7.";
//        } else {
//            return " is neither divisible by 7 nor does it end with 7.";
//        }
//
//    }
}
