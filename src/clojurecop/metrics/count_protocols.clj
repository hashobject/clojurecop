(ns clojurecop.metrics.count-protocols)


(defn protocol? [code-unit]
  (try (let [protocol-def (second (nth (nth code-unit 5) 3))]
         (not (nil? (:on-interface protocol-def))))
        (catch Throwable  e false)))



(defn run
  "Count number of prootocols in the src-code unit"
  [src-code]
  (count (filter protocol? src-code)))
