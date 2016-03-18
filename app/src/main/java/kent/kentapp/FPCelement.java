package kent.kentapp;

public class FPCelement extends FeedElement
{
    /**
     * Ignores SUPER()-fields
     */
    private String location;
    private int amount;


    public FPCelement()
    {
        //ignores its' fields...
        super();
    }


    //TODO: Keep-in-mind title IS NOT the JSON-title!!
    public void addField(String title, String value)
    {
        switch (title){
            case "name":
                location = value;
                break;
            case "free":
                amount = Integer.parseInt(value);
        }
    }

    //Relevant getters!
    public int getAmount()
    {
        //returns amount
        return amount;
    }

    public String getLocation()
    {
        //returns location...
        return location;
    }
}
