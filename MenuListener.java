package project370;

import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuListener implements ItemListener{

   List list;

   public MenuListener(List l) {
       list = l;
   }
   
   @Override
   public void itemStateChanged(ItemEvent e) {

       int index = (int) e.getItem();
       GUI.orderList.addItem(GUI.menuList.getItem(index));

       // set the tax number with updated values
       GUI.taxLabel.setText(TotalCharge.computeTax(GUI.orderList));

       // set the total number with updated values
       GUI.totalLabel.setText(TotalCharge.computeTotal(GUI.orderList));
   }
}
