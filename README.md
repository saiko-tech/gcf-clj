# gcf-clj

Clojure on JVM on Google Cloud Functions.<br>
This project implements a class loader fix to be able to run Clojure on GCP Cloud Functions.

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.markus-wa/gcf-clj.svg)](https://clojars.org/org.clojars.markus-wa/gcf-clj)

## deps.edn

```edn
org.clojars.markus-wa/gcf-clj {:mvn/version "0.0.1"}
```

## Leiningen / Boot

```edn
[org.clojars.markus-wa/gcf-clj "0.0.1"]
```

## Usage

```terminal
$ gcloud functions deploy my-function \
        --gen2 \
        --runtime java17 \
        --entry-point com.saiko_tech.gcf_clj.ClojureCloudFunction \
        --trigger-topic my-topic \
        --region europe-north1 \
        --memory 2Gi \
        --set-env-vars --set-env-vars "GCF_CLOJURE_NS=com.myns" \
        --set-env-vars "GCF_CLOJURE_FN=my-fn" \
        --source dir-containing-uberjar
```

## Acknowledgements

Thanks to https://sparkofreason.github.io/jvm-clojure-google-cloud-function/ for providing the solution approach
