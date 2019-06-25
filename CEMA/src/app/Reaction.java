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
        if(object.get(TypeConstants.TYPE).getAsString().equals(TypeConstants.GET)){
            res(object);
        }else if(object.get(TypeConstants.TYPE).getAsString().equals(TypeConstants.CLOSE)){
            PcServer.getInstance().sendMsg(object.get(TypeConstants.PC).getAsString(), object.get(TypeConstants.CLASS).getAsString());
            res(object);
            System.out.println(outObject);
        }
        return outObject;
    }

    private void res(JsonObject object){
        outObject.addProperty(TypeConstants.TYPE, TypeConstants.RES);
        outObject.addProperty(TypeConstants.CLASS, object.get(TypeConstants.CLASS).getAsInt());
        outObject.addProperty(TypeConstants.PC, PcServer.getInstance().getState(object.get(TypeConstants.CLASS).getAsInt()==202?0:1).toString());

    }
}

