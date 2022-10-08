abstract class inter {
    abstract public void viewBalance();
    abstract public void withdrawAmount(double withdrawAmount);
    abstract public void depositAmount(double depositAmount);
}


class atmImplementation extends inter{

    operation o = new operation();

//to get available balance
   public void getBalance(){
        System.out.println("Available balance is"+" "+o.getBalance());
    }

    //to deposit amount
    public void


}
