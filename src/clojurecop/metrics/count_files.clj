(ns clojurecop.metrics.count-files)


(defn clojure-file-with-ns? [file-unit]
  (= 'clojure.core/in-ns (first (second (first file-unit)))))


(defn run
  "Count number of private fns in the src-code unit"
  [src-code]
  (count (filter private-fn? src-code)))


