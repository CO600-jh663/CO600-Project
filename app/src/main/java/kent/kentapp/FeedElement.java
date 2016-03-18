package kent.kentapp;


public abstract class FeedElement
{
     int id;
     String title;
     int orgId;
     String organisation;

    /**
     * IF MULTIPLE BODY FIELDS...
     * body+=field-val
     */
     String body;

    String imgUrl;
     String url;


    public FeedElement(){body = "";}


    public abstract void addField(String name, String value);
}
