package com.saiko_tech.gcf_clj;

import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.cloud.functions.HttpFunction;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class ClojureHttpFunction implements HttpFunction {

    static {
        Thread.currentThread().setContextClassLoader(ClojureHttpFunction.class.getClassLoader());
        IFn require = Clojure.var("clojure.core", "require");

        var ns = System.getenv("GCF_CLOJURE_NS");
        require.invoke(Clojure.read(ns));
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        var ns = System.getenv("GCF_CLOJURE_NS");
        var fn = System.getenv("GCF_CLOJURE_FN");

        Clojure.var(ns, fn).invoke(request, response);
    }
}
