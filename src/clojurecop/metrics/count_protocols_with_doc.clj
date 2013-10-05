(ns clojurecop.metrics.count-protocols-with-doc
  (:require [clojurecop.metrics.count-protocols :as count-protocols]))


(defn protocol-with-doc? [code-unit]
  (try (let [protocol-def (second (nth (nth code-unit 5) 3))]
         (and (not (nil? (:doc protocol-def)))
               (count-protocols/protocol? code-unit)))
        (catch Throwable  e false)))



(defn run
  "Count number of protocols in the src-code unit"
  [src-code]
  (count (filter protocol-with-doc? src-code)))
