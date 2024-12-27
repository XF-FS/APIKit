/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.application.apitypes.graphql;

import burp.application.apitypes.graphql.GraphQLBaseObject;
import burp.application.apitypes.graphql.GraphQLKind;
import burp.application.apitypes.graphql.GraphQLObjectType;
import burp.application.apitypes.graphql.GraphQLParseContext;
import burp.application.apitypes.graphql.GraphQLParseError;
import burp.utils.Constants;
import com.google.gson.JsonObject;

public class GraphQLInputObjectField
        extends GraphQLBaseObject {
    public GraphQLObjectType type;

    public GraphQLInputObjectField(JsonObject inputJson) {
        super(inputJson);
        this.kind = GraphQLKind.INPUT_OBJECT_FIELD;
        this.type = new GraphQLObjectType(inputJson.getAsJsonObject("type"));
    }

    @Override
    public String exportToQuery(GraphQLParseContext context) throws GraphQLParseError {
        String result = this.name + ":" + Constants.GRAPHQL_SPACE;
        if (this.type.isList().booleanValue()) {
            result = result + "[";
        }
        result = result + context.globalObjects.get(this.type.typeName).exportToQuery(context);
        if (this.type.isList().booleanValue()) {
            result = result + "]";
        }
        return result;
    }
}

