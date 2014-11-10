package imageDownload.controller;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Parse all website and give selected tag
 * JSoup
 * Created by bartlomiejstachura on 08/11/14.
 */
public class WebsiteParse {

    private static String websiteURL; //"http://www.wykop.pl/tag/ladnapani/";
    private String tagHowYoNeedParse = "img[src$=.jpg]";
    private String absUrl = "src";
    private ArrayList<String> imgClearLink;

    /**
     * Parse website and take only needs tag
     * @throws IOException
     */
    public WebsiteParse() throws IOException {
        imgClearLink = new ArrayList<String>();

        Document doc = Jsoup.connect(websiteURL).get();
        Elements imgs = doc.select(tagHowYoNeedParse);
        for (Element img : imgs) {
            imgClearLink.add(img.absUrl(absUrl));
        }
    }

    /**
     * Website URL is necessary to parse site
      * @param websiteURL
     */
    public static void setWebsiteURL(String websiteURL) {
        WebsiteParse.websiteURL = websiteURL;
    }

    /**
     * Parse IMG link and return back clear link
     * @return
     */
    public ArrayList<String> getImgClearLink() {
        return imgClearLink;
    }
}
