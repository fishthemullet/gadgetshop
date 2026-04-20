public class Mobile extends Gadget
{
    private int credit;

    public Mobile(String aModel, double aPrice, int aWeight, String aSize, int aCredit)
    {
        super(aModel, aPrice, aWeight, aSize);
        credit = aCredit;
    }

    public int getCredit()
    {
        return credit;
    }

    public void addCredit(int amount)
    {
        if (amount > 0)
        {
            credit = credit + amount;
        }
        else
        {
            System.out.println("Please enter a positive amount.");
        }
    }

    public void makeCall(String phoneNumber, int duration)
    {
        if (duration <= credit)
        {
            System.out.println("Calling " + phoneNumber + " for " + duration + " minutes.");
            credit = credit - duration;
        }
        else
        {
            System.out.println("Insufficient credit to make this call.");
        }
    }

    @Override
    public void display()
    {
        super.display();
        System.out.println("Calling credit remaining: " + credit + " minutes");
    }
}