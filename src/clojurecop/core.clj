(ns clojurecop.core
  (:require [riddley.walk :as riddley-walk]
            [me.raynes.fs :as fs]
            [clojurecop.metrics.count-private-fns :as count-private-fns]
            [clojurecop.metrics.count-public-fns :as count-public-fns]
            [clojurecop.metrics.count-public-fns-with-doc :as count-public-fns-with-doc]))



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


(defn test-fun-without-doc []
  "xx")

(defn test-fun-with-doc
  "some function with doc"
  []
  "xx")


(def pi 3.14)

(def source-code
  (read-clj-file "src/clojurecop/core.clj"))


(def code-struct
  (riddley-walk/macroexpand-all source-code))



(def source-code-files (clj-files "src"))

(.getPath (first source-code-files))

(defn process-file [file]
  (let [path (.getPath file)
        code (read-code-struct path)
        nsname (extract-ns-name code)
        num-private-fns (count-private-fns/run code)
        num-public-fns (count-public-fns/run code)
        num-public-fns-with-doc (count-public-fns-with-doc/run code)
        ]
       {:ns-name nsname
        :num-private-methods num-private-fns
        :num-public-methods num-public-fns
        :num-public-fns-with-doc num-public-fns-with-doc

        }))



(defn make-summary [files-stat]
  (apply merge-with +
         (for [x files-stat]
              (select-keys x [:num-private-methods :num-public-methods :num-public-fns-with-doc]))))

(defn analyze [path]
 (let [files (clj-files path)
       files-stat (doall (map #(process-file %) (clj-files "src")))
       summary (make-summary files-stat)
       summary (assoc summary :num-namespace (count files-stat))
       result {:summary summary
               :entries files-stat}]
     result))

(analyze "src")
