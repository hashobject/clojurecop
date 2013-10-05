(ns clojurecop.metrics.protocol-doc-rate)


(defn run
  "Percentage of documented procotols"
  [documented total]
  (if (zero? total)
    1
    (/ documented total)))
