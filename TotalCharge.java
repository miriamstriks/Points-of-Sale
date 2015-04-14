package project370;

import java.awt.List;
import java.text.DecimalFormat;

public class TotalCharge {
   
   private static double tax = 0;
   
   //computes total tax owed on sale
   public static String computeTax(List list) {

       double sum = 0;
       String rv = "";
       if (list.getItemCount() != 0) {
           for (int i = 0; i < list.getItemCount(); i++) {
               String temp = list.getItem(i);
               String[] parts = temp.split(" ");
               sum += Double.parseDouble(parts[1]);
           }
       }

       tax = sum * 0.08875;
       DecimalFormat df = new DecimalFormat("0.00");
       rv += df.format(tax);

       return rv;
   } // method computeTax

   //computes total owed for the sale
   public static String computeTotal(List list) {

       double sum = 0;
       String rv = "";
       if (list.getItemCount() != 0) {
           for (int i = 0; i < list.getItemCount(); i++) {
               String temp = list.getItem(i);
               String[] parts = temp.split(" ");
               sum += Double.parseDouble(parts[1]);
           }
       }

       double total = 0;
       total = sum + tax;
       DecimalFormat df = new DecimalFormat("0.00");
       rv += df.format(total);
       return rv;
   } // method computeTotal
   
}//class TotalCharge