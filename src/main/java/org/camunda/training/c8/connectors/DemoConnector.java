package org.camunda.training.c8.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;

@Component
@OutboundConnector(
        name = "DemoConnector",
        inputVariables = {"param1", "param2"},
        type = "com.camunda.training.c8.TestConnector:1")
public class DemoConnector implements OutboundConnectorFunction{

    private final Logger LOG = LoggerFactory.getLogger(DemoConnector.class);

    @Override
    public Object execute(OutboundConnectorContext ctx) throws Exception {
        ConnectorRequestData connectorRequestData = ctx.getVariablesAsType(ConnectorRequestData.class);
        ctx.replaceSecrets(connectorRequestData);

        LOG.info(connectorRequestData.toString());

        //execute some business logic, then return the result

        ConnectorResponseData responseData = new ConnectorResponseData();
        responseData.setConnectorResultA("result-A");
        responseData.setConnectorResultB("result-B");
        
        return responseData;
    }
    
}