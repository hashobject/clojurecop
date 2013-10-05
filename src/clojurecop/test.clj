(ns clojurecop.test)

(defprotocol Fly
  "A simple protocol for flying"
  (fly [this] "Method to fly"))



(defprotocol Test
  "A simple protocol for test"
  (test-me [this] "Method to testing"))


(deftype Person [name]
  Test)


(defn test-fun-without-doc []
  "xx")

(defn test-fun-with-doc
  "some function with doc"
  []
  "xx")


(def pi 3.14)
