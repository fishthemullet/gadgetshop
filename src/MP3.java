public class MP3 extends Gadget
{
    private int memory;

    public MP3(String aModel, double aPrice, int aWeight, String aSize, int aMemory)
    {
        super(aModel, aPrice, aWeight, aSize);
        memory = aMemory;
    }

    public int getMemory()
    {
        return memory;
    }

    public void downloadMusic(int amount)
    {
        if (amount <= memory)
        {
            memory = memory - amount;
        }
        else
        {
            System.out.println("Insufficient memory available.");
        }
    }

    public void deleteMusic(int amount)
    {
        memory = memory + amount;
    }

    @Override
    public void display()
    {
        super.display();
        System.out.println("Available memory: " + memory);
    }
}