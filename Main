import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        File theDir = new File("BankAccounts");
        File folder = new File(theDir.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();

        boolean LoggedIn = false;
        boolean NameExists = false;
        boolean LogginCheck = false;
        boolean PasswordFound = false;
        int Check = 1;
        String Username;
        String Password;
        String FileUsername;
        String CheckPassword;
        String txt = ".txt";
        String Line0 =" ";

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

        while (LoggedIn == false) {
            System.out.println();
            System.out.println();
            System.out.println("--- Manhattan Central Bank ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("--> ");
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
                            continue;
                        }
                        reader.close();
                        if(Line0 == Password) {
                            PasswordFound = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (PasswordFound == true) {
                    System.out.println();
                    System.out.println("! Logged In !");
                }
                else{
                    System.out.println();
                    System.out.println("Either your Password or Username was wrong");
                    System.out.println("Password: " + Line0);
                }
            }
            LoggedIn = true;
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
                    System.out.println("This name is not able to be used");
                }
            }

            if (NameExists == false) {
                File myObj = new File(theDir.getAbsolutePath() + File.separator + FileUsername);
                try {
                    myObj.createNewFile();
                    System.out.println();
                    System.out.println("File created: " + myObj.getName() + " In folder " + theDir.getAbsolutePath());
                    System.out.println("Now you can login");
                    System.out.print("Password - ");
                    Password = in.nextLine();
                    System.out.print("Repeat Password - ");
                    CheckPassword = in.nextLine();
                    if (Password.equals(CheckPassword)) {
                        try {
                            FileWriter writer = new FileWriter(theDir.getAbsolutePath() + File.separator + FileUsername, true);
                            writer.write(Password);
                            writer.write("\r\n");   // write new line
                            writer.close();
                            System.out.println("Password Match");
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
    }
}
