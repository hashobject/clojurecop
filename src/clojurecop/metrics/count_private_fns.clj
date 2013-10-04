(ns clojurecop.metrics.count-private-fns)


(defn private-fn? [code-unit]
  (= 'defn-  (first code-unit)))


(defn run
  "Count number of private fns in the src-code unit"
  [src-code]
  (count (filter private-fn? src-code)))
