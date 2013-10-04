(ns clojurecop.metrics.count-public-fns)


(defn public-fn? [code-unit]
  (let [unit-meta (meta (second code-unit))]
    (and
      (not (empty? (:arglists unit-meta)))
      (not (true? (:private unit-meta))))))


(defn run
  "Count number of public fns in the src-code unit"
  [src-code]
  (count (filter public-fn? src-code)))
