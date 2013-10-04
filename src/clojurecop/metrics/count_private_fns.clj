(ns clojurecop.metrics.count-private-fns)


(defn private-fn? [code-unit]
  (let [unit-meta (meta (second code-unit))]
    (and
      (not (empty? (:arglists unit-meta)))
      (true? (:private unit-meta)))))


(defn run
  "Count number of private fns in the src-code unit"
  [src-code]
  (count (filter private-fn? src-code)))
