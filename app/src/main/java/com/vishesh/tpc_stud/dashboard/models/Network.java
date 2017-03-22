package com.vishesh.tpc_stud.dashboard.models;

public enum Network {

    GITHUB("Github"), LINKEDIN("LinkedIn"), WEBSITE("Website"), OTHER("Other");

    private final String networkName;

    Network(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkName() {
        return networkName;
    }
}
