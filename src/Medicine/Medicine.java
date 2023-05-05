package Medicine;

import java.util.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Medicine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList Medicine = loadData();
        boolean MD = true;
        String x;
        String y;

        while (MD) {
            System.out.println("\n----------------------------------------\n \n If you want to Show a Medicine enter:  1 \n If you want to Show All Medicines enter:  2 \n If you want to Buy Medicine enter:  3 \n If you want Update Information enter:  4 \n If you want to Sort Medicines enter:  5 \n If you want to Delete a Medicine enter:  6 \n If You Want To Add Medicine enter: 7 \n If you want exit enter:  Exit  \n----------------------------------------");
            x = sc.nextLine();
            y = x.toUpperCase();
            if (y.equals("1")) {
                System.out.println("Enter Medicine's Name:");
                String Name = sc.nextLine();
                Medicine.PrintMedicineData(Name);
                System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("2")) {
                Medicine.PrintAll();
                 System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("3")) {
                System.out.println("Enter Medicine's Name:");
                String Name = sc.next();
                System.out.println("Enter Medicine's Quantity");
                int Quantity = sc.nextInt();
                Medicine.sellMedicine(Name, Quantity);
                System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("4")) {
                System.out.println("Enter Medicine's Name:");
                String Name = sc.next();
                System.out.println("Enter 1 To Update Name: \nEnter 2 To Update ID: \nEnter 3 To Update Quantity: \nEnter 4 To Update Place: \nEnter 5 To Update Price: \nEnter 6 To Update Manufacture: ");
                int data = sc.nextInt();
                Medicine.update(Name, data);
                System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("5")) {
                System.out.println("If You Want to Sort By Price Enter 1 \n If You Want to Sort By Quantity Enter 2");
                int S = sc.nextInt();
                if (S == 1) {
                    Medicine.SortbyPrice();
                    System.out.println("The List Is Sorted By Price");
                } else if (S == 2) {
                    Medicine.SortbyQuantity();
                    System.out.println("The List Is Sorted By Quantity");
                } else {
                    System.out.println("Error!!");
                }
                 System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("6")) {
                System.out.println("Enter The Name Of Medicine You Want to Delete: ");
                String Name = sc.next();
                Medicine.deleteMedicine(Name);
                System.out.println("The Current Medicine is Deleted");
                  System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("7")) {

                System.out.print("Enter Medicine's Name: ");
                String Name = sc.next();
                System.out.println("Enter Medicine's ID: ");
                int ID = sc.nextInt();
                System.out.print("Enter Medicine's Quantity: ");
                int Quantity = sc.nextInt();
                System.out.print("Enter Medicine's Place: ");
                String Place = sc.next();
                System.out.print("Enter Medicine's Price: ");
                double price = sc.nextDouble();
                System.out.print("Enter Medicine's Manufacture: ");
                String Manufacture = sc.next();
                //System.out.println("Enter Medicine's ExpireDate:");
                // expiredate=sc.nextDate();
                Medicine.AddNewMedicine(ID, Quantity, Name, Place, price, Manufacture);
                System.out.println("\nSuccessfully Added a Medicine.");
                  System.out.println("\nPress Enter to continue");
                sc.nextLine();
            } else if (y.equals("EXIT")) {
                System.out.println("THANK YOU ! BY: \nYamen Helal");
                sc.close();
                MD = false;
            } else {
                System.out.println("Please Enter a Valid Order");
                System.out.println("\nPress Enter to continue");
                sc.nextLine();
            }
        }
    }

    public static LinkedList loadData() {
        JSONParser jsonParser = new JSONParser();
        LinkedList M = new LinkedList();
        try ( FileReader reader = new FileReader("Pharmacy.json")) {

            Object obj = jsonParser.parse(reader);

            JSONArray MedicineList = (JSONArray) obj;

            for (Object o : MedicineList) {
                if (o instanceof JSONObject) {
                    parseMedicineObject((JSONObject) o);
                    String[] med = parseMedicineObject((JSONObject) o);

                    Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(med[6]);

                    M.AddNewMedicine(
                            Integer.parseInt(med[0]),
                            Integer.parseInt(med[1]),
                            med[2],
                            med[3],
                            Double.parseDouble(med[4]),
                            med[5]);

                }
            }

        } catch (FileNotFoundException m) {
            m.printStackTrace();
        } catch (IOException m) {
            m.printStackTrace();
        } catch (ParseException m) {
            m.printStackTrace();
        } catch (java.text.ParseException ex) {
            Logger.getLogger(Medicine.class.getName()).log(Level.SEVERE, null, ex);
        }

        return M;
    }

    private static String[] parseMedicineObject(JSONObject Medicine) {

        JSONObject MedicineObject = (JSONObject) Medicine.get("Medicine");

        String ID = (String) MedicineObject.get("ID");

        String Quantity = (String) MedicineObject.get("Quantity");

        String Name = (String) MedicineObject.get("Name");

        String Place = (String) MedicineObject.get("Place");

        String Price = (String) MedicineObject.get("Price");

        String Manufacture = (String) MedicineObject.get("Manufacture");

        String ExpireDate = (String) MedicineObject.get("ExpireDate");
        String[] med = {ID, Quantity, Name, Place, Price, Manufacture, ExpireDate};

        return med;
    }
}
