package com.example.zingtest.Service;

public class APIService {
    private static String base_url="https://zingmp3project.000webhostapp.com/Service/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
