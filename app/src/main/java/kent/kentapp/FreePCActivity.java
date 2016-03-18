package kent.kentapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class FreePCActivity extends Activity
{
    private final FeedParser parser = new FeedParser(null);
    private final FreePCs fpc = new FreePCs(this, parser);
    private final TextView textPC = new TextView(this);

    @Override
    public void onCreate(Bundle state)
    {
        super.onCreate(state);

        setContentView(R.layout.free_pcs);
        textPC.setId(R.id.pc);

        main();
    }

    private void main()
    {
        final Thread thread = new Thread(fpc);
        thread.start();
    }

    /**
     * FreePC-thread calls this every 10-seconds
     */
    public void pc(ArrayList<FPCelement> data)
    {
        //Clears previous
        textPC.setText("");

        //View for freePCs'
        for (FPCelement element : data) {
            textPC.append(element.getLocation()+":  "+element.getAmount());
        }
    }

    public void destroy()
    {
        //Destroys activity-instance
        super.onDestroy();
    }
}
