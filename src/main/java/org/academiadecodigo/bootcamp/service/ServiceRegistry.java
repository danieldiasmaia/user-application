package org.academiadecodigo.bootcamp.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmaia on 16-04-2017.
 */
public final  class ServiceRegistry {

    private Service service;
    private static ServiceRegistry instance;
    private Map<String, Service> serviceMap = new HashMap();

    private ServiceRegistry(){

    }

    public static synchronized ServiceRegistry getInstance(){

        if(instance == null){
            instance = new ServiceRegistry();
        }

        return instance;
    }

    public void registerService(String name, Service service){
        serviceMap.put(name,service);
    }



    public Service loadService(String name) {
        return serviceMap.get(name);
    }

    public Map<String, Service> getServiceMap() {
        return serviceMap;
    }

}
