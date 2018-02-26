package ser210.quinnipiac.edu.piglatinapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by juliannashevchenko on 2/26/18.
 */

public class LatinHandler {

    public LatinHandler() {
    }

    public String getTranslation(String PLJsonStr) throws JSONException {
        JSONObject PLTransJSONObj = new JSONObject(PLJsonStr);
        return PLTransJSONObj.getString("translated");
    }

}
