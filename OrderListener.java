package project370;

import java.awt.List;
import java.awt.event.*;

public class OrderListener implements ItemListener{
   
   List list;
   
   public OrderListener(List l){
       list=l;
   }
   
   @Override
   public void itemStateChanged(ItemEvent e) {
       
       //remove the item on sell list when clicked
       int index = (int) e.getItem();
       GUI.orderList.remove(GUI.orderList.getItem(index));
       
       //set the tax number with updated values
       GUI.taxLabel.setText(TotalCharge.computeTax(GUI.orderList));
       
       //set the total number with updated values
       GUI.totalLabel.setText(TotalCharge.computeTotal(GUI.orderList));

   }


}
