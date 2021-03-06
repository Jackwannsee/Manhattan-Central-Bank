import javax.print.attribute.standard.NumberUp;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        File theDir = new File("BankAccounts");
        File folder = new File(theDir.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        User[] Users = new User[250];
        Transaction[] Transactions = new Transaction[250];

        User Person;

        boolean LoggedIn = false;
        boolean NameExists = false;
        boolean LoginCheck = false;
        boolean PasswordFound = false;
        int Check = 1;
        String Username;
        String Password;
        String FileUsername = "";
        String CheckPassword;
        String txt = ".txt";
        String Line0 = "";
        String BalanceLines = "";
        String TextWriterUsername = "";

        if (!theDir.exists()) {
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it This is to see if the file is there and to make sure the program doesnt crash
            }
            if (result) {
                System.out.println("DIR created");
            }
        }

        // adding the data to the class
        for (int i = 0; i < listOfFiles.length; i++) {
            //this is to find the password
            try {
                FileReader reader = new FileReader(theDir.getAbsolutePath() + File.separator + (listOfFiles[i].getName()));
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    Line0 = line;
                    continue;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //The one bellow is to get hte username and then turn that into user data
            String Name = listOfFiles[i].getName();
            //not the best but the safest way to remove the txt you can also do "filename = filename.replace(".txt", "");"
            //https://stackoverflow.com/questions/16362639/remove-txt-from-file-name-java       VERY HELPFUL
            if (Name.endsWith(".txt")){
                Name = Name.substring(0, Name.length()-4);
            }

            Users[i] = new User(Name, Line0, 0);
            if (listOfFiles[i].getName().equals(FileUsername)) {
                NameExists = true;
            }

            //this next part is for the money that each account has I will be changing the value with the getters and setters
            try {
                FileReader reader = new FileReader(theDir.getAbsolutePath() + File.separator + (listOfFiles[i].getName()));
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                int Balance = 0;
                int counter = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    if (counter != 0){
                        BalanceLines = line;
//                        Balance = Balance + Integer.parseInt(BalanceLines);
                    }
                    counter++;
                }
                for (int j = 0; j < Users.length; j++) {
                    if (Users[j] != null) {
                        //the problem is here!!!! the name is not corresponding mhm fjjkdhgpd
                        if (Users[j].getUsername().equals(Name)){
                            Users[j].setBalance(Balance);
                        }
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Users[0] = null;

        while (LoggedIn == false) {
            System.out.println();
            System.out.println();
            System.out.println("--- Manhattan Central Bank ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("--> ");
            while (!in.hasNextInt()) {
                System.out.print("-> ");
                in = new Scanner(System.in);
            }
            Check = in.nextInt();
            in.nextLine();

            if (Check == 1) {
                System.out.println();
                System.out.println();
                System.out.println("--- Login ---");

                System.out.print("Username - ");
                Username = in.nextLine();
                System.out.print("Password - ");
                Password = in.nextLine();

                FileUsername = Username + txt;

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().equals(FileUsername)) {
                        NameExists = true;
                    }
                }

                if (NameExists == true) {
                    try {
                        FileReader reader = new FileReader(theDir.getAbsolutePath() + File.separator + FileUsername);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            Line0 = line;
                            break;
                        }

                        if (Line0.equals(Password)) {
                            PasswordFound = true;
                            Person = new User(Username, reader);
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (PasswordFound == true) {
                    System.out.println();
                    System.out.println("! Logged In !");
                    TextWriterUsername = Username;
                    LoggedIn = true;
                    LoginCheck = true;
                } else {
                    System.out.println();
                    System.out.println("Either your Password or Username was wrong");
//                    System.out.println("Password: " + Line0);
                }
            }

            if (Check == 2) {
                System.out.println();
                System.out.println();
                System.out.println("--- Sign Up ---");
                System.out.print("Username - ");
                Username = in.nextLine();

                FileUsername = Username + txt;

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().equals(FileUsername)) {
                        NameExists = true;
                        System.out.println("\nThis name is not able to be used");
                    }
                }

                if (NameExists == false) {
                    File myObj = new File(theDir.getAbsolutePath() + File.separator + FileUsername);
                    try {
                        myObj.createNewFile();
                        System.out.println();
                        System.out.println("Now you can login");
                        System.out.print("Password - ");
                        Password = in.nextLine();
                        System.out.print("Repeat Password - ");
                        CheckPassword = in.nextLine();
                        if (Password.equals(CheckPassword)) {
                            try {
                                //System.out.println("File created: " + myObj.getName() + " In folder " + theDir.getAbsolutePath());
                                FileWriter writer = new FileWriter(theDir.getAbsolutePath() + File.separator + FileUsername, true);
                                writer.write(Password);
                                writer.write("\r\n");   // write new line
                                writer.close();
                                System.out.println("Password Match");
                                LoggedIn = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println();
                            System.out.println("Password did not match");
                        }

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            }
            while(LoginCheck == true) {
                System.out.println();
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Funds");
                System.out.println("3. Forward Funds");
                System.out.print("--> ");
                while (!in.hasNextInt()) {
                    System.out.print("-> ");
                    in = new Scanner(System.in);
                }
                Check = in.nextInt();

                if (Check == 1) {
                    System.out.println();
                    System.out.println("Balance: ");
                }

                if (Check == 2) {
                    System.out.println();
                    System.out.println("How many funds would you like to deposit?");
                    System.out.println("Amount: ");
                }
                if (Check == 3) {
                    System.out.println();
                    System.out.println("To whom would you like to forward funds?");
                    for (int i = 0; i < Users.length; i++) {
                        if(Users[i] != null){
                            System.out.print((i + 1) + ". ");
                            Users[i].informationName();
                            System.out.println();
                        }
                    }
                    System.out.print("# - ");
                    while (!in.hasNextInt()) {
                        System.out.print("> ");
                        in = new Scanner(System.in);
                    }
                    int NumberName = in.nextInt();
                    NumberName = NumberName -1;

                    System.out.print("\nHow much money would you like to send to ");
                    Users[NumberName].informationName();
                    System.out.println("'s account?");
                    System.out.print("Amount: ");
                    while (!in.hasNextInt()) {
                        System.out.print("-> ");
                        in = new Scanner(System.in);
                    }

                    int Amount = in.nextInt();
                    System.out.print("\n" + Amount + " *Jack Bucks* have been added to ");
                    Users[NumberName].informationName();
                    System.out.println("'s account!");
                    String AmountFile = Integer.toString(Amount);

                    // These two try and catch parts make it so that the file writes the name +- and then the amount
                    try {
                        FileUsername = Users[NumberName].informationNameFiles() + txt;
                        FileWriter writer = new FileWriter(theDir.getAbsolutePath() + File.separator + FileUsername, true);
                        writer.write(TextWriterUsername + "+" +AmountFile);
                        writer.write("\r\n");   // write new line
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        FileWriter writer = new FileWriter(theDir.getAbsolutePath() + File.separator + (TextWriterUsername + txt), true);
                        writer.write(Users[NumberName].informationNameFiles() + "-" + AmountFile);
                        writer.write("\r\n");   // write new line
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println();
                    System.out.println("Thank You");
                    LoginCheck = false;
                }
            }
        }
    }
}
