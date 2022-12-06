package org.camunda.training.c8.connectors;

import io.camunda.connector.api.annotation.Secret;

public class ConnectorRequestData {

    @Secret
    private String someSecretPassword;

    private String param1, param2;

    public String getSomeSecretPassword() {
        return someSecretPassword;
    }

    public void setSomeSecretPassword(String password) {
        this.someSecretPassword = password;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return "ConnectorRequestData [someSecretPassword=" + someSecretPassword + ", param1=" + param1 + ", param2="
                + param2 + "]";
    }
}
