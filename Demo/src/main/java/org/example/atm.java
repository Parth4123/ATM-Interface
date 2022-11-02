package org.example;

import javax.security.auth.callback.LanguageCallback;
import java.sql.SQLOutput;
import java.util.Scanner;

public class atm extends atm_DB
{
    private static Scanner obj = new Scanner(System.in);
    public static int USER_ID, LANGUAGE;
    public static void user (){
        System.out.println("1.Existing Customer\n2.New Customer\n3.EXIT");
        System.out.println("Choice:");
        int ch = obj.nextInt();
        if (ch == 1 )//existing user
        {
            // ask for login id and pass.
            System.out.println("Enter your unique id:");
            int  number = obj.nextInt(); // read user id.
            System.out.println("Enter your password: ");
            int pass = obj.nextInt(); // read user password
           existing_user(number,pass);

        }
        else if (ch == 2)//new user
        {
            System.out.println("Enter your name:"); //issue with inserting name
            String name = obj.next();
            System.out.println("Enter your New unique id:");
            int  id = obj.nextInt();
            System.out.println("Enter your New Pass:");
            int pass = obj.nextInt();
            boolean check = new_user(id,pass,name);
            if (check){
               System.out.println("Account created Successfully...");
               lang();
            }
            else {
               System.out.println("OOPS! Something Went Wrong");
               user();
            }
        } else if (ch == 3) {
            System.out.println("**************Thank YOU FOR USING CENTRAL BANK ATM*************");

        } else
        {
            System.out.println("Select  correct choice.");
            user();
        }

    }

    public static void display()
    {
        switch (LANGUAGE)
        {
            case 1:
                System.out.println("1.Pin Change\n2.Deposit Cash\n3.Balance enquiry\n4.Cash Withdrawal\n5.Exit");
                System.out.println("Option:");
                break;
            case 2:
                System.out.println("१.पिन परिवर्तन\n२.मिनी स्टेटमेंट\n३.बैलेंस पूछताछ\n४.नकद निकासी");
                System.out.println("विकल्प:");
                 break;
            case 3:
                System.out.println("१.पिन बदला\n२.मिनी स्टेटमेंट\n३.शिल्लक चौकशी\n४.पैसे काढणे");
                System.out.println("पर्याय:");
                break;
            case 4:
                System.out.println("൧.പിൻ മാറ്റുക\n൨.മിനി പ്രസ്താവന\n൩.ബാലൻസ് അന്വേഷണം\n൪.ക്യാഷ് പിൻവലിക്കൽ");
                System.out.println("ഓപ്ഷൻ:");
                break;
            default:
                System.out.println("Select Correct lang.");

        }
        int op = obj.nextInt(); // selection of operation
        operations(op);// operations to perform


    }

    private static void operations(int ch)
    {
        switch (ch)
        {
            case 1:
                System.out.println("\t\t\t+++++ Pin change section ++++++++");//done with pin change working perfectly
                System.out.println("Enter your Customer ID:");
                int id = obj.nextInt();
                System.out.println("Enter your Previous Pin:");
                int pass = obj.nextInt();
                pass_change(pass,id);
                break;
            case 2:
                System.out.println("+++++++++ Cash Deposit ++++++++++++");
                deposit_cash();
                break;
            case 3:
                System.out.println("+++++ Balance Enquiry+++++");
                balance_enquiry();

                break;
            case 4:
                System.out.println("++++++ Cash Withdrawal +++++");
                cash_withdrawal();
                break;
            case 5:
                System.out.println(" Your been redirect to main menu... ");
                user();
            default:
                System.out.println("select correct option.");
        }
    }


     public static void lang()
     {
         System.out.println("Select Language:\n1.English\n2.Hindi\n3.Marathi\n4.Malayalam");
         int choice = obj.nextInt(); // selection of language
         LANGUAGE = choice;
         display(); // display language.
     }
}
