package com.vishesh.tpc_stud.dashboard.models;


/**
 * Created by vishesh on 25/2/17.
 */
public class NetworkProfile {

    private String url;
    private Network network;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkProfile)) {
            return false;
        }
        NetworkProfile networkProfile = (NetworkProfile) obj;
        return this.getUrl().equals(networkProfile.getUrl()) &&
                this.getNetwork().equals(networkProfile.getNetwork());
    }
}
