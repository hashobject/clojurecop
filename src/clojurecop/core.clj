(ns clojurecop.core
  (:require [clojurecop.metrics.count-private-fns :as count-private-fns]
            [clojurecop.metrics.count-public-fns :as count-public-fns]
            [clojurecop.metrics.count-public-fns-with-doc :as count-public-fns-with-doc]))



(defn- wrap-list [s]
  (str "(" s ")"))


(defn- read-clj-file [filepath]
  (read-string (wrap-list (slurp filepath))))


(defn test-fun-without-doc []
  "xx")

(defn test-fun-with-doc
  "some function with doc"
  []
  "xx")


(def source-code (read-clj-file "src/clojurecop/core.clj"))

(count-private-fns/run source-code)

(count-public-fns/run source-code)

(count-public-fns-with-doc/run source-code)



;(public-fn? '(defn x [] 3))
