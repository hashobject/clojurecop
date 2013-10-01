(ns clojurecop.core)



(defn- wrap-list [s]
  (str "(" s ")"))



(defn- read-clj-file [filepath]
  (read-string (wrap-list (slurp filepath))))


(defn private-fn? [code-unit]
  (= 'defn- (first code-unit)))

(defn public-fn? [code-unit]
  (= "defn" (str (first code-unit))))

(defn fn-has-doc? [code-unit]
  (string? (nth code-unit 2)))

(defn public-fn-with-doc? [code-unit]
  (and (public-fn? code-unit)
       (fn-has-doc? code-unit)))


(defn count-private-fns
  "Count number of private fns in the src-code unit"
  [src-code]
  (count (filter private-fn? src-code)))


(defn count-public-fns
  "Count number of public fns in the src-code unit"
  [src-code]
  (count (filter public-fn? src-code)))

(defn count-public-fns-with-doc [src-code]
  (count (filter public-fn-with-doc? src-code)))



(count-private-fns (read-clj-file "src/clojurecop/core.clj"))

(count-public-fns (read-clj-file "src/clojurecop/core.clj"))

(count-public-fns-with-doc (read-clj-file "src/clojurecop/core.clj"))



;(public-fn? '(defn x [] 3))
