(ns clojurecop.metrics.public-fn-doc-rate)


(defn run
  "Percentage of documented public methods"
  [documented total]
  (if (zero? total)
    1
    (/ documented total)))
