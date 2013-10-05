(ns clojurecop.core
  (:require [riddley.walk :as riddley-walk]
            [me.raynes.fs :as fs]
            [clojurecop.metrics.count-private-fns :as count-private-fns]
            [clojurecop.metrics.count-public-fns :as count-public-fns]
            [clojurecop.metrics.count-public-fns-with-doc :as count-public-fns-with-doc]
            [clojurecop.metrics.public-fn-doc-rate :as public-fn-doc-rate]
            [clojurecop.metrics.count-protocols :as count-protocols]
            [clojurecop.metrics.count-protocols-with-doc :as count-protocols-with-doc]
            [clojurecop.metrics.count-types :as count-types]
            ))



(defn- wrap-list [s]
  (str "(" s ")"))

(defn- read-clj-file [filepath]
  (read-string (wrap-list (slurp filepath))))


(defn- read-code-struct [filepath]
  (riddley-walk/macroexpand-all
   (read-clj-file filepath)))


(defn- extract-ns-name [file-code]
  (second (second (second (first file-code)))))


(defn clj-files [path]
  (fs/find-files* path #(re-matches #".*clj" (.getName %))))


(defn process-file [file]
  (let [path (.getPath file)
        code (read-code-struct path)
        nsname (extract-ns-name code)
        num-private-fns (count-private-fns/run code)
        num-public-fns (count-public-fns/run code)
        num-public-fns-with-doc (count-public-fns-with-doc/run code)
        rate-of-documented-fns (public-fn-doc-rate/run num-public-fns-with-doc num-public-fns)
        num-protocols (count-protocols/run code)
        num-protocols-with-doc (count-protocols-with-doc/run code)
        num-types (count-types/run code)
        ]
       {:ns-name nsname
        :num-private-fns num-private-fns
        :num-public-fns num-public-fns
        :num-public-fns-with-doc num-public-fns-with-doc
        :rate-of-documented-fns rate-of-documented-fns
        :num-protocols num-protocols
        :num-protocols-with-doc num-protocols-with-doc
        :num-types num-types
        }))



;(read-code-struct "src/clojurecop/test.clj")

(def x (nth (read-code-struct "src/clojurecop/test.clj") 2))


(not (nil? (:doc (second (nth (nth x 5) 3)))))


(defn make-summary [files-stat]
  (apply merge-with +
         (for [x files-stat]
              (select-keys x [:num-private-fns
                              :num-public-fns
                              :num-public-fns-with-doc
                              :num-protocols
                              :num-protocols-with-doc
                              :num-types]))))

(defn analyze [path]
 (let [files (clj-files path)
       files-stat (doall (map #(process-file %) (clj-files "src")))
       summary (make-summary files-stat)
       summary (assoc summary
                 :num-namespace (count files-stat)
                 :rate-of-documented-fns (public-fn-doc-rate/run
                                           (:num-public-fns-with-doc summary)
                                           (:num-public-fns summary)))
       result {:summary summary
               :entries files-stat}]
     result))

(def result (analyze "src"))


(:summary result)
