package kent.kentapp;

public class FeedEvents extends FeedElement
{
    private String strtDate;
    private String endDate;
    private String location;
    private String description;
    private boolean hasTickets;
    private String brand;

    //String array
    private String types;

    public FeedEvents()
    {
        super();
    }

    /**
     * APPLY addField()
     */
    public void addField(String title, String value)
    {
        switch(title){
            case "Id":
                id = Integer.parseInt(value);
                break;
            case "Title":
                title = value;
                break;
            case "StartDate":
                strtDate = value;
                break;
            case "OrganisationId":
                orgId = Integer.parseInt(value);
                break;
            case "Organisation":
                organisation = value;
                break;
            case "Body":
                body += value;
                break;
            case "EndDate":
                endDate = value;
                break;
            case "Location":
                location = value;
                break;
            case "ImageUrl":
                imgUrl = value;
                break;
            case "Url":
                url = value;
                break;
            case "Types":
                types = value;
                break;
            case "Description":
                description = value;
                break;
            case "Brand":
                brand = value;
                break;
            case "HasTickets":
                if (value.equals("true")){
                    hasTickets = true;
                }
                else{
                    hasTickets = false;
                }
        }
    }

    //PUT IN ONLY-RELEVANT GETTERS...
    public String getDescription()
    {
        return description;
    }

    public boolean hasTickets()
    {
        return hasTickets;
    }

    public String getTitle()
    {
        return title;
    }
}