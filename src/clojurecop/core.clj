(ns clojurecop.core
  (:require [riddley.walk :as riddley-walk]
            [clojurecop.metrics.count-private-fns :as count-private-fns]
            [clojurecop.metrics.count-public-fns :as count-public-fns]
            [clojurecop.metrics.count-public-fns-with-doc :as count-public-fns-with-doc]))



(defn- wrap-list [s]
  (str "(" s ")"))


(defn- wrap-list-2 [] "x")

(defn- read-clj-file [filepath]
  (read-string (wrap-list (slurp filepath))))


(defn test-fun-without-doc []
  "xx")

(defn test-fun-with-doc
  "some function with doc"
  []
  "xx")


(def pi 3.14)

(def source-code
  (read-clj-file "src/clojurecop/core.clj"))


(def code-struct
  (riddley-walk/macroexpand-all source-code))




(count-private-fns/run code-struct)

(count-public-fns/run source-code)

(count-public-fns-with-doc/run source-code)



;(public-fn? '(defn x [] 3))
