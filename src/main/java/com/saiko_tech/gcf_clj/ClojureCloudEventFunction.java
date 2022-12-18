package com.saiko_tech.gcf_clj;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import com.google.cloud.functions.CloudEventsFunction;
import io.cloudevents.CloudEvent;

public class ClojureCloudEventFunction implements CloudEventsFunction {

    static {
        Thread.currentThread().setContextClassLoader(ClojureCloudEventFunction.class.getClassLoader());
        IFn require = Clojure.var("clojure.core", "require");

        var ns = System.getenv("GCF_CLOJURE_NS");
        require.invoke(Clojure.read(ns));
    }

    @Override
    public void accept(CloudEvent event) throws Exception {
        var ns = System.getenv("GCF_CLOJURE_NS");
        var fn = System.getenv("GCF_CLOJURE_FN");

        Clojure.var(ns, fn).invoke(event);
    }
}
