(ns clojurecop.metrics.public-fn-doc-rate)


(defn run
  "Percentage of documented public methods"
  [number-of-documented-fns total-number-of-fns]
  (/ number-of-documented-fns total-number-of-fns))
