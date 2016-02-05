package kent.kentapp;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.net.*;
import org.xml.sax.*;


public class RSSFeed extends Activity {
    //Fields
    String rssResult = "";
    boolean item = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView rss = (TextView)findViewById(R.id.rss);
        try {
            //Initializing...
            URL rssUrl = new URL("https://api.kent.ac.uk/api/v1/news.rss");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            RSSHandler rssHandler = new RSSHandler();
            xmlReader.setContentHandler(rssHandler);
            InputSource inputSource = new InputSource(rssUrl.openStream());
            xmlReader.parse(inputSource);


            //Captured error-types fed to RSS-object.
        } catch (Exception e){}
        rss.setText(rssResult);
    }

    //The  parser.
    private class RSSHandler extends DefaultHandler{

        //Formatting.
        public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
        {
            if (localName.equals("item"))
                item = true;

            if (!localName.equals("item")&&item)
                rssResult = rssResult + localName + ": ";

        }

        public void endElement(String namespaceURI, String localName,
                               String qName) throws SAXException {

        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            String cdata = new String(ch, start, length);
            if (item)
                rssResult = (rssResult +(cdata.trim()).replaceAll("\\s+", " ")+"\t");

        }

    }
}