(ns clojurecop.core)



(defn- wrap-list [s]
  (str "(" s ")"))



(defn read-clj-file [filepath]
  (read-string (wrap-list (slurp filepath))))



(read-clj-file "src/clojurecop/core.clj")



(defn private-fn? [code-unit]
  (= 'defn- (first code-unit)))

(defn public-fn? [code-unit]
  (= 'defn (first code-unit)))


(defn count-private-fns [src-code]
  (count (filter private-fn? src-code)))


(defn count-public-fns [src-code]
  (count (filter public-fn? src-code)))





(count-private-fns (read-clj-file "src/clojurecop/core.clj"))

(count-public-fns (read-clj-file "src/clojurecop/core.clj"))
