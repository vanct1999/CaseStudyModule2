package Method;
import Manage.Services;

import java.util.ArrayList;

public class MethodsServices {
    static ArrayList<Services> services = new ArrayList<>();
    static {
        services.add(new Services(1,"Đồ uống",20000.0));
        services.add(new Services(2,"Đồ ăn ",30000.0));
    }
    public static Services getServiceById(int id){
        Services servicess = null;
        for (Services i : services) {
            if (i.getId() == id){
                servicess = i;
            }
        }
        return servicess;
    }
}
