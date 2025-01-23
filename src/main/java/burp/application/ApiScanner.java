/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.application;

import burp.BurpExtender;
import burp.IHttpRequestResponse;
import burp.application.apitypes.ApiType;
import burp.application.apitypes.actuator.ApiTypeActuator;
import burp.application.apitypes.graphql.ApiTypeGraphQL;
import burp.application.apitypes.rest.ApiTypeRest;
import burp.application.apitypes.soap.ApiTypeSoap;
import burp.application.apitypes.swagger.ApiTypeSwagger;
import burp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class ApiScanner {
    private final ArrayList<BiFunction<IHttpRequestResponse, Boolean, ApiType>> apiTypeConstructors = new ArrayList();

    public ApiScanner() {
        this.apiTypeConstructors.add(ApiTypeActuator::newInstance);
        this.apiTypeConstructors.add(ApiTypeSwagger::newInstance);
        this.apiTypeConstructors.add(ApiTypeGraphQL::newInstance);
        this.apiTypeConstructors.add(ApiTypeSoap::newInstance);
        this.apiTypeConstructors.add(ApiTypeRest::newInstance);
    }

    public ArrayList<ApiType> detect(final IHttpRequestResponse baseRequestResponse, final boolean isPassive) {
        ExecutorService executor = Executors.newFixedThreadPool(this.apiTypeConstructors.size());
        final ArrayList<ApiType> apiTypes = new ArrayList<ApiType>();
        for (final BiFunction<IHttpRequestResponse, Boolean, ApiType> apiTypeConstructor : this.apiTypeConstructors) {
            executor.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        ApiType apiType = (ApiType) apiTypeConstructor.apply(baseRequestResponse, isPassive);
                        if (apiType.isFingerprintMatch().booleanValue()) {
                            apiTypes.add(apiType);
                        }
                    } catch (Exception e) {
                        BurpExtender.getStderr().println(CommonUtils.exceptionToString(e));
                    }
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5L, TimeUnit.MINUTES);
        } catch (Exception exception) {
            // empty catch block
        }
        return apiTypes;
    }

    public void clearScanState() {
        for (BiFunction<IHttpRequestResponse, Boolean, ApiType> constructor : apiTypeConstructors) {
            try {
                ApiType apiType = constructor.apply(null, true);
                if (apiType instanceof ApiTypeActuator) {
                    ((ApiTypeActuator) apiType).clearScanState();
                } else if (apiType instanceof ApiTypeSwagger) {
                    ((ApiTypeSwagger) apiType).clearScanState();
                }
            } catch (Exception e) {
                BurpExtender.getStderr().println(CommonUtils.exceptionToString(e));
            }
        }
    }
}

