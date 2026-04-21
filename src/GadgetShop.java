import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GadgetShop extends JFrame implements ActionListener
{
    private ArrayList<Gadget> gadgets;

    private JTextField modelField;
    private JTextField priceField;
    private JTextField weightField;
    private JTextField sizeField;
    private JTextField creditField;
    private JTextField memoryField;
    private JTextField phoneNumberField;
    private JTextField durationField;
    private JTextField downloadField;
    private JTextField displayNumberField;

    private JButton addMobileButton;
    private JButton addMP3Button;
    private JButton clearButton;
    private JButton displayAllButton;
    private JButton makeCallButton;
    private JButton downloadMusicButton;

   public GadgetShop()
{
    gadgets = new ArrayList<Gadget>();

    setTitle("Gadget Shop");
    setSize(700, 400);
    setLayout(new GridLayout(7, 4, 5, 5));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    modelField = new JTextField();
    priceField = new JTextField();
    weightField = new JTextField();
    sizeField = new JTextField();
    creditField = new JTextField();
    memoryField = new JTextField();
    phoneNumberField = new JTextField("+");
    durationField = new JTextField();
    downloadField = new JTextField();
    displayNumberField = new JTextField();

    addMobileButton = new JButton("Add Mobile");
    addMP3Button = new JButton("Add MP3");
    clearButton = new JButton("Clear");
    displayAllButton = new JButton("Display All");
    makeCallButton = new JButton("Make A Call");
    downloadMusicButton = new JButton("Download Music");

    add(new JLabel("Model:"));
    add(new JLabel("Price:"));
    add(new JLabel("Weight (g):"));
    add(new JLabel("Size (HxWxD (mm)):"));

    add(modelField);
    add(priceField);
    add(weightField);
    add(sizeField);

    add(new JLabel("Credit:"));
    add(new JLabel("Memory:"));
    add(addMobileButton);
    add(addMP3Button);

    add(creditField);
    add(memoryField);
    add(clearButton);
    add(displayAllButton);

    add(new JLabel("Country Code & Phone No. :"));
    add(new JLabel("Duration:"));
    add(new JLabel("Download:"));
    add(new JLabel("Display Number:"));

    add(phoneNumberField);
    add(durationField);
    add(downloadField);
    add(displayNumberField);

    add(makeCallButton);
    add(downloadMusicButton);
    add(new JLabel(""));
    add(new JLabel(""));

    addMobileButton.addActionListener(this);
    addMP3Button.addActionListener(this);
    clearButton.addActionListener(this);
    displayAllButton.addActionListener(this);
    makeCallButton.addActionListener(this);
    downloadMusicButton.addActionListener(this);
}

    public String getModel()
    {
        return modelField.getText();
    }

    public double getPrice()
    {
        return Double.parseDouble(priceField.getText());
    }

    public int getWeight()
    {
        return Integer.parseInt(weightField.getText());
    }

public String getGadgetSize()
{
    return sizeField.getText();
}

    public int getCredit()
    {
        return Integer.parseInt(creditField.getText());
    }

    public int getMemory()
    {
        return Integer.parseInt(memoryField.getText());
    }

    public String getPhoneNumber()
{
    String phoneNumber = phoneNumberField.getText().trim();

    if (phoneNumber.equals("") || phoneNumber.equals("+"))
    {
        JOptionPane.showMessageDialog(this, "Enter a phone number to make a call.");
        return null;
    }

    if (phoneNumber.charAt(0) == '+')
    {
        for (int i = 1; i < phoneNumber.length(); i++)
        {
            if (!Character.isDigit(phoneNumber.charAt(i)))
            {
                JOptionPane.showMessageDialog(this, "Invalid input. Enter only integers.");
                return null;
            }
        }
    }
    else
    {
        for (int i = 0; i < phoneNumber.length(); i++)
        {
            if (!Character.isDigit(phoneNumber.charAt(i)))
            {
                JOptionPane.showMessageDialog(this, "Invalid input. Enter only integers.");
                return null;
            }
        }
    }

    return phoneNumber;
}

   public int getDuration()
{
    String text = durationField.getText().trim();

    if (text.equals(""))
    {
        JOptionPane.showMessageDialog(this, "Enter phone call duration to make a call");
        return -1;
    }

    try
    {
        int duration = Integer.parseInt(text);

        if (duration < 0)
        {
            JOptionPane.showMessageDialog(this, "Invalid input. Enter 0 or a positive integer.");
            return -1;
        }

        return duration;
    }
    catch (NumberFormatException e)
    {
        JOptionPane.showMessageDialog(this, "Invalid input. Enter only integers.");
        return -1;
    }
}

    public int getDownloadSize()
    {
        return Integer.parseInt(downloadField.getText());
    }

    public int getDisplayNumber()
    {
        int displayNumber = -1;

        try
        {
            displayNumber = Integer.parseInt(displayNumberField.getText());

            if (displayNumber < 0 || displayNumber >= gadgets.size())
            {
                JOptionPane.showMessageDialog(this, "Display number is out of range.");
                displayNumber = -1;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Display number must be an integer.");
        }

        return displayNumber;
    }

    public void clearTextFields()
    {
        modelField.setText("");
        priceField.setText("");
        weightField.setText("");
        sizeField.setText("");
        creditField.setText("");
        memoryField.setText("");
        phoneNumberField.setText("");
        durationField.setText("");
        downloadField.setText("");
        displayNumberField.setText("");
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == addMobileButton)
        {
            Mobile mobile = new Mobile(getModel(), getPrice(), getWeight(), getGadgetSize(), getCredit());
            gadgets.add(mobile);
        }
        else if (e.getSource() == addMP3Button)
        {
            MP3 mp3 = new MP3(getModel(), getPrice(), getWeight(), getGadgetSize(), getMemory());
            gadgets.add(mp3);
        }
        else if (e.getSource() == clearButton)
        {
            clearTextFields();
        }
        else if (e.getSource() == displayAllButton)
        {
            for (int i = 0; i < gadgets.size(); i++)
            {
                System.out.println("Display number: " + i);
                gadgets.get(i).display();
                System.out.println();
            }
        }
      else if (e.getSource() == makeCallButton)
{
    int displayNumber = getDisplayNumber();

    if (displayNumber != -1)
    {
        Gadget selectedGadget = gadgets.get(displayNumber);

        if (selectedGadget instanceof Mobile)
        {
            String phoneNumber = getPhoneNumber();
            int duration = getDuration();

            if (phoneNumber != null && duration != -1)
            {
                Mobile mobile = (Mobile) selectedGadget;
                mobile.makeCall(phoneNumber, duration);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Selected gadget is not a mobile phone.");
        }
    }
}
        else if (e.getSource() == downloadMusicButton)
        {
            int displayNumber = getDisplayNumber();

            if (displayNumber != -1)
            {
                Gadget selectedGadget = gadgets.get(displayNumber);

                if (selectedGadget instanceof MP3)
                {
                    MP3 mp3 = (MP3) selectedGadget;
                    mp3.downloadMusic(getDownloadSize());
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Selected gadget is not an MP3 player.");
                }
            }
        }
    }

    public static void main(String[] args)
    {
        GadgetShop shop = new GadgetShop();
        shop.setVisible(true);
    }
}