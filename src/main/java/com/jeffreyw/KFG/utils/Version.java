package com.jeffreyw.KFG.utils;


import java.util.Objects;

public class Version {
    public static boolean checkVersion(String version) {
        Requests req = new Requests();
        final boolean[] go = {false};
        req.sendGetRequest("/version","",
                new Requests.ResponseCallback() {
                    @Override
                    public void onResponse(String resp) {
                        if(Objects.equals(resp, version)){
                            go[0] = true;
                        }
                    }
                    @Override
                    public void onError(Exception e) {

                    }
                });
        return go[0];
    }
}
