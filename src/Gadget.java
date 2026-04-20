    public class Gadget
{
    private String model;
    private double price;
    private int weight;
    private String size;

    public Gadget(String aModel, double aPrice, int aWeight, String aSize)
    {
        model = aModel;
        price = aPrice;
        weight = aWeight;
        size = aSize;
    }

    public String getModel()
    {
        return model;
    }

    public double getPrice()
    {
        return price;
    }

    public int getWeight()
    {
        return weight;
    }

    public String getSize()
    {
        return size;
    }

    public void display()
    {
        System.out.println("Model: " + model);
        System.out.println("Price: £" + price);
        System.out.println("Weight: " + weight + "g");
        System.out.println("Size: " + size);
    }
}