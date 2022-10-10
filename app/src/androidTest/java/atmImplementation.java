import android.widget.Toast;

import com.example.atmapp.depositAmount;
import com.example.atmapp.withdrawAmount;




 public class atmImplementation  {

    operation o = new operation();
    depositAmount d= new depositAmount();
    withdrawAmount w =new withdrawAmount();
    atmImplementation am = new atmImplementation();

//to get available balance
   public void viewBalance(){
       System.out.println("Available balance is"+" "+Double.parseDouble(o.getBalance().toString()));
    }
     //withdraw amount
    public void withdrawAmount (double withdrawAmount){
       if( Double.parseDouble(w.withdrawAmount.getText().toString())<=Double.parseDouble(o.getBalance().toString())) {
           System.out.println("amount withdraw successfully" + w.withdrawAmount.getText().toString());
           o.setBalance(Double.parseDouble(o.getBalance().toString()) - Double.parseDouble(w.withdrawAmount.getText().toString()));
           viewBalance();
       }else
       {
           Toast.makeText(d, "insufficient balance", Toast.LENGTH_SHORT).show();
       }
    }

    //to deposit amount
    public void depositAmount(double depositAmount){
        System.out.println("Amount deposited successfully"+d.depositAmount.getText().toString());
        o.setBalance(Double.parseDouble(o.getBalance().toString())+Double.parseDouble(d.depositAmount.getText().toString()));
        viewBalance();
    }


}
