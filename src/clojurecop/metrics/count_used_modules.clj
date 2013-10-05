(ns clojurecop.metrics.count-used-modules)


(defn use-statement? [statement]
  (and
    (seq? statement)
     (= ':use (first statement))))

(defn find-use-statement [src-code]
  (first
    (filter use-statement? (first src-code))))



(defn run
  "Count number of modules imported using `:use`"
  [src-code]
  (let [use-statement (find-use-statement src-code)]
    (if (nil? use-statement)
      0
      (count (second use-statement)))))


