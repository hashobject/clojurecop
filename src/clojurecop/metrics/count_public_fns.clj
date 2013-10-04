(ns clojurecop.metrics.count-public-fns)


(defn public-fn? [code-unit]
  (= "defn" (str (first code-unit))))


(defn run
  "Count number of public fns in the src-code unit"
  [src-code]
  (count (filter public-fn? src-code)))
