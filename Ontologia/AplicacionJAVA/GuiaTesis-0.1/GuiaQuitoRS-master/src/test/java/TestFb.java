import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;

import java.util.List;

/**
 * Created by xavier on 04/03/16.
 */
public class TestFb {
    public static void main(String args[]) {
        FacebookClient facebookClient = new DefaultFacebookClient("CAAVwZCCK912EBAOeQhA6ZAvRu5sQm2BzoZBp3NaFFtwezuNPyUfz64aAIXZCgdTRlR2IiBcs6qwbAZAwGkwxZCcyoVzibUvvBRkiU5QZB48qMh4Lt5isufTuw7mYywkqqlSZCdLJlZAm7o2yGZCr9Rj5bsunw8MTFqmUVL0qCe0mlII2TnngryPcD4yubaB5Hj4RfVASmRWb5K7gZDZD");
//        Connection<Page> pageConnection = facebookClient.executeFqlQuery("SELECT page_id,type, description FROM page WHERE page_id IN (SELECT uid, page_id, type FROM page_fan WHERE uid=me()) AND type='musician/band'");
        Connection<Page> fetchConnection = facebookClient.fetchConnection("me/likes", Page.class, Parameter.with("fields", "id, name, category, last_used_time"));
//        fetchConnection.getTotalCount();
        int i = 0;
        for (List<Page> c : fetchConnection) {
            for (Page page : c) {
                if (page.getCategory().contains("Hotel") ||
                        page.getCategory().contains("Church/Religious Organization") ||
                        page.getCategory().contains("Restaurant/Cafe") ||
                        page.getCategory().contains("Museum/Art Gallery")) {
                    System.out.println("Page\t\t\t|\t\t\tCategory");
                    System.out.println(page.getName() + "\t\t\t|\t\t\t" + page.getCategory());
                    System.out.println(page.getLastUsedTime());
                    System.out.println("---------------------------------------------------------");

                    i++;
                }
            }
        }
        System.out.println(i);
    }
}
