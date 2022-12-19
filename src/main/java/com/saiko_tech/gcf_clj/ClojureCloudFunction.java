package com.saiko_tech.gcf_clj;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import com.google.cloud.functions.CloudEventsFunction;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import io.cloudevents.CloudEvent;

public class ClojureCloudFunction implements CloudEventsFunction, HttpFunction {

    private static final IFn service_impl;

    static {
        var thread = Thread.currentThread();
        var oldClassLoader = thread.getContextClassLoader();

        thread.setContextClassLoader(ClojureCloudFunction.class.getClassLoader());

        var require = Clojure.var("clojure.core", "require");
        var ns = System.getenv("GCF_CLOJURE_NS");
        var fn = System.getenv("GCF_CLOJURE_FN");

        require.invoke(Clojure.read(ns));

        service_impl = Clojure.var(ns, fn);

        thread.setContextClassLoader(oldClassLoader);
    }

    @Override
    public void accept(CloudEvent event) throws Exception {
        service_impl.invoke(event);
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        service_impl.invoke(request, response);
    }
}
