package kent.kentapp;


public class FeedNews extends FeedElement
{
    private String leader;
    private String pubDate;
    private String modDate;

    //String array
    private String tags;

    public FeedNews()
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
            case "Leader":
                leader = value;
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
            case "PublishedDate":
                pubDate = value;
                break;
            case "ModifiedDate":
                modDate = value;
                break;
            case "ImageUrl":
                imgUrl = value;
                break;
            case "Url":
                url = value;
                break;
            case "Tags":
                tags = value;
        }
    }

    //PUT IN ONLY-RELEVANT GETTERS...
    public String getTitle()
    {
        return title;
    }
    public String getLeader()
    {
        return leader;
    }
    public String getImUrl()
    {
        return imgUrl;
    }
    public String getUrl(){return url;}
    //TODO: More?...
}