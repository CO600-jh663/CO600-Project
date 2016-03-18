package kent.kentapp;

public class FeedParseException extends Exception
{
    private String msg;


    public FeedParseException()
    {
        super();
        msg = "The file is of an Unknown Format!";
    }
    public FeedParseException(String message)
    {
        super();
        msg = message;
    }


    @Override
    public String getMessage()
    {
        return msg;
    }
}