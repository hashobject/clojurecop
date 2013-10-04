(ns clojurecop.metrics.count-public-fns-with-doc
  (:require [clojurecop.metrics.count-public-fns :as count-public-fns]))


(defn fn-has-doc? [code-unit]
   (let [unit-meta (meta (second code-unit))]
     (not (empty? (:doc unit-meta)))))


(defn public-fn-with-doc? [code-unit]
  (and (count-public-fns/public-fn? code-unit)
       (fn-has-doc? code-unit)))

(defn run [src-code]
  (count (filter public-fn-with-doc? src-code)))
