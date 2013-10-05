(ns clojurecop.metrics.count-types)


(defn type? [code-unit]
  (try (let [type-def (first (nth code-unit 2))]
        (= 'deftype* type-def))
        (catch Throwable  e false)))



(defn run
  "Count number of types in the src-code unit"
  [src-code]
  (count (filter type? src-code)))
