package app;

import com.google.gson.JsonObject;
import main.TypeConstants;
import pc.PcServer;

public class Reaction {

    private JsonObject outObject;
    public Reaction(){
        outObject = null;
    }
    public JsonObject reaction(JsonObject object){
        outObject = new JsonObject();
        if(object.get("type").getAsString().equals(TypeConstants.GET)){
            outObject.addProperty(TypeConstants.TYPE, TypeConstants.RES);
            outObject.addProperty(TypeConstants.CLASS, object.get("class").getAsInt());
            outObject.addProperty(TypeConstants.PC, PcServer.getInstance().getState(object.get("class").getAsInt()==202?0:1).toString());
        }else if(object.get("type").getAsString().equals(TypeConstants.CLOSE)){
            PcServer.getInstance().sendMsg(object.get("pc").getAsString());
            outObject.addProperty(TypeConstants.TYPE, TypeConstants.OK);
        }
        else if(object.get("type").getAsString().equals(TypeConstants.ALL)){
            PcServer.getInstance().sendMsgAll();
            outObject.addProperty(TypeConstants.TYPE, TypeConstants.OK);
        }
        return outObject;
    }
}
